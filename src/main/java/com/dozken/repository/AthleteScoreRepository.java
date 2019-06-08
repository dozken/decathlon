package com.dozken.repository;

import com.dozken.model.AthleteScore;

import java.nio.file.Path;
import java.util.List;

public interface AthleteScoreRepository {
    void export(List<AthleteScore> athleteScores, Path outputFile);
}
