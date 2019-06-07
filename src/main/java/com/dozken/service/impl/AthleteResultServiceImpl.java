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

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
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
     *
     * */
    private AthleteScore toAthleteScore(AthleteResult athleteResult) {
        List<EventResult> eventResults = athleteResult.getEventResults();

        BigDecimal score = eventResults.stream().map(x -> {
            Event event = x.getEvent();
            if(event.getType() == EventType.TRACK){
                Parameter param = Constants.POINTS_SYSTEM.get(event);
                double scoreD = param.getA() * Math.pow(param.getB() - x.getResult(), param.getC());
            }
            Constants.POINTS_SYSTEM.get(event);
            return BigDecimal.ZERO;
        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        return new AthleteScore();
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
        //TODO
        // - toAthleteScore scores

        return athleteResults.stream().map(this::toAthleteScore).collect(toList());
    }

    @Override
    public void exportScores(List<AthleteScore> athleteScores, Path outputFile) {
        //TODO
        // - export to xls or other
    }

}
