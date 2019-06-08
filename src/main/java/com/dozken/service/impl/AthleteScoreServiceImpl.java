package com.dozken.service.impl;

import com.dozken.constants.Constants;
import com.dozken.constants.Parameter;
import com.dozken.model.AthleteResult;
import com.dozken.model.AthleteScore;
import com.dozken.model.EventResult;
import com.dozken.model.EventScore;
import com.dozken.model.enums.Event;
import com.dozken.model.enums.EventType;
import com.dozken.repository.AthleteScoreRepository;
import com.dozken.service.AthleteScoreService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AthleteScoreServiceImpl implements AthleteScoreService {

    private AthleteScoreRepository repository;

    public AthleteScoreServiceImpl(AthleteScoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AthleteScore> getScores(List<AthleteResult> athleteResults) {
        Comparator<AthleteScore> scoreComparator = (x, y) -> Integer.compare(y.getScore(), x.getScore());

        List<AthleteScore> athleteScores = athleteResults.stream().map(this::mapToAthleteScore)
                .sorted(scoreComparator)
                .collect(toList());

        return calculateRanking(athleteScores);
    }

    @Override
    public void export(List<AthleteScore> athleteScores, Path outputFile) {
        if (athleteScores == null || athleteScores.isEmpty()) {
            throw new RuntimeException("To export athlete scores, 'athleteScores' must not be empty");
        }
        if (outputFile == null || outputFile.getParent() == null || Files.notExists(outputFile.getParent())) {
            throw new RuntimeException("To export athlete scores, 'output path' must exist");
        }

        repository.export(athleteScores, outputFile);
    }


    private AthleteScore mapToAthleteScore(AthleteResult athleteResult) {
        List<EventResult> eventResults = athleteResult.getEventResults();
        List<EventScore> eventScores = eventResults.stream().map(this::calculateEventScore).collect(toList());
        Integer score = eventScores.stream().mapToInt(EventScore::getScore).sum();

        return new AthleteScore(athleteResult.getFullName(), eventScores, score);
    }

    /*
     * Points = INT(A(B — P)C) for track events (faster time produces a higher score)
     * Points = INT(A(P — B)C) for field events (greater distance or height produces a higher score)
     */
    private EventScore calculateEventScore(EventResult x) {
        Event event = x.getEvent();
        Parameter param = Constants.POINTS_SYSTEM.get(event);
        int score = 0;
        if (event.getType() == EventType.TRACK) {
            score = (int) (param.getA() * Math.pow(param.getB() - x.getResult(), param.getC()));
        } else if (event.getType() == EventType.FIELD) {
            Double result = x.getResult();
            if (Arrays.asList(Event.LONG_JUMP, Event.HIGH_JUMP, Event.POLE_VAULT).contains(event))
                result *= 100;
            score = (int) (param.getA() * Math.pow(result - param.getB(), param.getC()));
        }

        return new EventScore(event, x.getResult(), score);
    }

    private List<AthleteScore> calculateRanking(List<AthleteScore> athleteScores) {
        for (int i = 0; i < athleteScores.size(); i++) {
            int sharedPlace = i + 1;
            while (sharedPlace < athleteScores.size() && athleteScores.get(i).getScore().equals(athleteScores.get(sharedPlace).getScore()))
                sharedPlace++;

            if (sharedPlace != i + 1) {
                for (int j = i; j < sharedPlace; j++)
                    athleteScores.get(j).setPlace((i + 1) + "-" + (sharedPlace));
                i = sharedPlace;
            } else
                athleteScores.get(i).setPlace(String.valueOf(i + 1));
        }
        return athleteScores;
    }


}
