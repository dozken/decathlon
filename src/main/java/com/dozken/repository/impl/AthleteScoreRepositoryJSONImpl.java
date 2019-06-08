package com.dozken.repository.impl;

import com.dozken.model.AthleteScore;
import com.dozken.repository.AthleteScoreRepository;

import java.nio.file.Path;
import java.util.List;

public class AthleteScoreRepositoryJSONImpl implements AthleteScoreRepository {

    @Override
    public void export(List<AthleteScore> athleteScores, Path outputFile) {
        //TODO output json implementation goes here
        throw new UnsupportedOperationException("Implement json export");
    }
}
