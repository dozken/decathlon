package com.dozken.service.impl;

import com.dozken.model.AthleteResult;
import com.dozken.model.AthleteScore;
import com.dozken.service.AthleteResultService;
import com.dozken.service.AthleteScoreService;
import com.dozken.service.DecathlonService;
import com.dozken.utils.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DecathlonServiceImpl implements DecathlonService {

    private final AthleteResultService resultService;
    private final AthleteScoreService scoreService;

    public DecathlonServiceImpl(AthleteResultService resultService, AthleteScoreService scoreService) {
        this.resultService = resultService;
        this.scoreService = scoreService;
    }

    @Override
    public void processAthleteResults(String inputFilePath, String outputFilePath) {
        if (StringUtils.isEmpty(inputFilePath)) {
            throw new IllegalArgumentException("Input file path cannot be empty");
        }
        if (StringUtils.isEmpty(outputFilePath)) {
            throw new IllegalArgumentException("Output file path cannot be empty");
        }

        Path inputFile = Paths.get(inputFilePath);
        Path outputFile = Paths.get(outputFilePath);

        List<AthleteResult> athleteResults = resultService.getResults(inputFile);
        List<AthleteScore> athleteScores = scoreService.getScores(athleteResults);
        scoreService.export(athleteScores, outputFile);
    }

}
