package com.dozken.service.impl;

import com.dozken.repository.AthleteResultRepository;
import com.dozken.repository.AthleteScoreRepository;
import com.dozken.repository.impl.AthleteResultRepositoryImpl;
import com.dozken.repository.impl.AthleteScoreRepositoryImpl;
import com.dozken.service.AthleteResultService;
import com.dozken.service.AthleteScoreService;
import com.dozken.service.DecathlonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DecathlonServiceImplTest {

    private AthleteResultService resultService;
    private AthleteScoreService scoreService;
    private DecathlonService service;

    @BeforeEach
    void setUp() {
        AthleteResultRepository resultRepository = new AthleteResultRepositoryImpl();
        AthleteScoreRepository scoreRepository = new AthleteScoreRepositoryImpl();

        resultService = new AthleteResultServiceImpl(resultRepository);
        scoreService = new AthleteScoreServiceImpl(scoreRepository);
        service = new DecathlonServiceImpl(resultService, scoreService);
    }

    @AfterEach
    void tearDown() throws Exception {
        Path pathToBeDeleted = Paths.get("src/test/resources/out");
        if (Files.exists(pathToBeDeleted)) {
            Files.walk(pathToBeDeleted)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(x -> assertTrue(x.delete()));

            assertFalse(Files.exists(pathToBeDeleted));
        }
    }

    @Test
    void processAthleteResults() {
        String inputPath = "src/test/resources/clark_kent_results.csv";
        String outputPath = "src/test/resources/out/output.xml";
        assertTrue(new File("src/test/resources/out").mkdirs());

        Path outputFilePath = Paths.get(outputPath);
        service.processAthleteResults(inputPath, outputPath);
        assertTrue(Files.exists(outputFilePath));

    }

    @Test
    void givenEmptyInputThenExpectException() {
        //GIVEN
        String inputPath = "";
        String outputPath = "some output";

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.processAthleteResults(inputPath, outputPath));
    }

    @Test
    void givenEmptyOutputThenExpectException() {
        //GIVEN
        String inputPath = "some input";
        String outputPath = "";

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.processAthleteResults(inputPath, outputPath));
    }
}