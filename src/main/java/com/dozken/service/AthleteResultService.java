package com.dozken.service;

import com.dozken.model.AthleteResult;
import com.dozken.model.AthleteScore;

import java.nio.file.Path;
import java.util.List;

public interface AthleteResultService {

    List<AthleteResult> getResults(Path inputFilePath);

    List<AthleteScore> getScores(List<AthleteResult> athleteResults);

    void exportScores(List<AthleteScore> athleteScores, Path outputFile);

}
