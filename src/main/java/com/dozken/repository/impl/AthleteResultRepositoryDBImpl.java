package com.dozken.repository.impl;

import com.dozken.model.AthleteResult;
import com.dozken.repository.AthleteResultRepository;

import java.nio.file.Path;
import java.util.List;

public class AthleteResultRepositoryDBImpl implements AthleteResultRepository {

    @Override
    public List<AthleteResult> getAll(Path path) {
        //TODO implement data retrieving from DB
        throw new UnsupportedOperationException("implement retrieving data from DB");
    }


}
