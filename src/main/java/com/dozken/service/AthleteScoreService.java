package com.dozken.service;

import com.dozken.model.AthleteResult;
import com.dozken.model.AthleteScore;

import java.nio.file.Path;
import java.util.List;

public interface AthleteScoreService {
    void export(List<AthleteScore> athleteScores, Path outputFile);

    List<AthleteScore> getScores(List<AthleteResult> athleteResults);
}
