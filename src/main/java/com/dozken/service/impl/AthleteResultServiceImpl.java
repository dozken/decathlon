package com.dozken.service.impl;

import com.dozken.constants.Constants;
import com.dozken.model.AthleteResult;
import com.dozken.model.AthleteScore;
import com.dozken.model.EventResult;
import com.dozken.model.enums.Event;
import com.dozken.model.enums.EventType;
import com.dozken.model.enums.Parameter;
import com.dozken.repository.AthleteResultRepository;
import com.dozken.service.AthleteResultService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AthleteResultServiceImpl implements AthleteResultService {

    private AthleteResultRepository repository;

    public AthleteResultServiceImpl(AthleteResultRepository repository) {
        this.repository = repository;
    }

    /**
     * Points = INT(A(B — P)C) for track events (faster time produces a higher score)
     * Points = INT(A(P — B)C) for field events (greater distance or height produces a higher score)
     */
    private int calculateEventPoint(EventResult x) {
        Event event = x.getEvent();
        Parameter param = Constants.POINTS_SYSTEM.get(event);
        int point = 0;
        if (event.getType() == EventType.TRACK) {
            point = (int) (param.getA() * Math.pow(param.getB() - x.getResult(), param.getC()));
        } else if (event.getType() == EventType.FIELD) {
            Double result = x.getResult();
            if (Arrays.asList(Event.LONG_JUMP, Event.HIGH_JUMP, Event.POLE_VAULT).contains(event))
                result *= 100;
            point = (int) (param.getA() * Math.pow(result - param.getB(), param.getC()));
        }
        x.setPoint(point);
        return point;
    }


    private AthleteScore calculateScore(AthleteResult athleteResult) {
        List<EventResult> eventResults = athleteResult.getEventResults();

        Integer score = eventResults.stream().mapToInt(this::calculateEventPoint).sum();

        return new AthleteScore(score, athleteResult);
    }

    @Override
    public List<AthleteResult> getResults(Path inputFilePath) {
        if (Files.notExists(inputFilePath)) {
            throw new RuntimeException("File not found: " + inputFilePath.getFileName().toString());
        }

        return repository.getAll(inputFilePath);
    }

    @Override
    public List<AthleteScore> getScores(List<AthleteResult> athleteResults) {
        Comparator<AthleteScore> scoreComparator = (x, y) -> Integer.compare(y.getScore(), x.getScore());

        List<AthleteScore> athleteScores = athleteResults.stream().map(this::calculateScore)
                .sorted(scoreComparator)
                .collect(toList());


        for (int i = 0; i < athleteScores.size(); i++) {

            int sharedPlace = i;
            while (sharedPlace < athleteScores.size() && athleteScores.get(i).getScore() == athleteScores.get(sharedPlace).getScore()) {
                sharedPlace++;
            }

            if (sharedPlace != i+1) {
                for (int j = i; j < sharedPlace; j++) {
                    athleteScores.get(j).setPlace((i + 1) + "-" + (sharedPlace));
                }
            } else {
                athleteScores.get(i).setPlace(String.valueOf(i + 1));
            }
        }

        return athleteScores;
    }


    @Override
    public void exportScores(List<AthleteScore> athleteScores, Path outputFile) {
        //TODO
        // - export to xls or other
    }

}
