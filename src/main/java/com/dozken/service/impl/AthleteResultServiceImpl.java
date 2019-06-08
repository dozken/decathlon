package com.dozken.service.impl;

import com.dozken.model.AthleteResult;
import com.dozken.repository.AthleteResultRepository;
import com.dozken.service.AthleteResultService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class AthleteResultServiceImpl implements AthleteResultService {

    private AthleteResultRepository repository;

    public AthleteResultServiceImpl(AthleteResultRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<AthleteResult> getResults(Path inputFilePath) {
        if (inputFilePath == null || Files.notExists(inputFilePath)) {
            throw new RuntimeException("To get athlete results, 'input path' must exist");
        }

        return repository.getAll(inputFilePath);
    }


}
