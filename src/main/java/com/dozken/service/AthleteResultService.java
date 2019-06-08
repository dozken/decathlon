package com.dozken.service;

import com.dozken.model.AthleteResult;

import java.nio.file.Path;
import java.util.List;

public interface AthleteResultService {
    List<AthleteResult> getResults(Path inputFilePath);
}
