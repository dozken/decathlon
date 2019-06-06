package com.dozken.repository;

import com.dozken.model.AthleteResult;

import java.nio.file.Path;
import java.util.List;

public interface AthleteResultRepository {

    List<AthleteResult> getAll(Path path);
}
