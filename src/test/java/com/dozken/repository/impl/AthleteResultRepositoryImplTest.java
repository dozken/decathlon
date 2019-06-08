package com.dozken.repository.impl;

import com.dozken.exception.CSVFileException;
import com.dozken.model.AthleteResult;
import com.dozken.repository.AthleteResultRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AthleteResultRepositoryImplTest {

    private AthleteResultRepository repository;

    @BeforeEach
    void setUp() {
        repository = new AthleteResultRepositoryImpl();
    }

    @Test
    void givenIllegalPathExpectCSVFileException() {
        Path inputFilePath = Paths.get("wrong path");

        Assertions.assertThrows(CSVFileException.class, () -> repository.getAll(inputFilePath));
    }

    @Test
    void givenCSVFileExpectSuccess() {
        Path inputFilePath = Paths.get("src/test/resources/clark_kent_results.csv");

        List<AthleteResult> all = repository.getAll(inputFilePath);

        assertNotNull(all);
        assertEquals(all.size(), 1);
        assertEquals(all.get(0).getFullName(), "Clark Kent");

    }


}