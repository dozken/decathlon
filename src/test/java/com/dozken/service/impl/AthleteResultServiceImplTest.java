package com.dozken.service.impl;

import com.dozken.model.AthleteResult;
import com.dozken.model.EventResult;
import com.dozken.model.enums.Event;
import com.dozken.repository.AthleteResultRepository;
import com.dozken.repository.AthleteScoreRepository;
import com.dozken.repository.impl.AthleteResultRepositoryImpl;
import com.dozken.repository.impl.AthleteScoreRepositoryImpl;
import com.dozken.service.AthleteResultService;
import com.dozken.service.AthleteScoreService;
import com.dozken.utils.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AthleteResultServiceImplTest {

    private AthleteResultService resultService;
    private AthleteScoreService scoreService;
    private AthleteResult expected;

    @BeforeEach
    void setUp() {
        AthleteResultRepository resultRepository = new AthleteResultRepositoryImpl();
        AthleteScoreRepository scoreRepository = new AthleteScoreRepositoryImpl();

        resultService = new AthleteResultServiceImpl(resultRepository);
        scoreService = new AthleteScoreServiceImpl(scoreRepository);

        expected = new AthleteResult();
        expected.setFullName("John Smith");
        expected.setEventResults(Arrays.asList(
                EventResult.valueOf(Event.SPRINT_100M, 12.61),
                EventResult.valueOf(Event.LONG_JUMP, 5.00),
                EventResult.valueOf(Event.SHORT_PUT, 9.22),
                EventResult.valueOf(Event.HIGH_JUMP, 1.50),
                EventResult.valueOf(Event.SPRINT_400M, 60.39),
                EventResult.valueOf(Event.HURDLES_110M, 16.43),
                EventResult.valueOf(Event.DISCUS_THROW, 21.60),
                EventResult.valueOf(Event.POLE_VAULT, 2.60),
                EventResult.valueOf(Event.JAVELIN_THROW, 35.81),
                EventResult.valueOf(Event.RUN_1500M, StringUtils.toSeconds("5.25.72"))));
    }

    @AfterEach
    void tearDown() {
        //TODO remove xml
    }

    /**
     * Points = INT(A(B — P)C) for track events (faster time produces a higher score)
     * Points = INT(A(P — B)C) for field events (greater distance or height produces a higher score)
     */
    @Test
    void getResults() {
        //GIVEN
        Path inputFilePath = Paths.get("src/test/resources/results.csv");
        List<AthleteResult> results = resultService.getResults(inputFilePath);

        //WHEN
        Optional<AthleteResult> optionalAthleteResult = results.stream().filter(x -> x.getFullName().equals(expected.getFullName())).findFirst();

        //THEN
        assertTrue(optionalAthleteResult.isPresent());
        AthleteResult actual = optionalAthleteResult.get();
        assertEquals(actual.getEventResults().get(0).getResult(), expected.getEventResults().get(0).getResult());
    }

    @Test
    void givenEmptyInputPathWhenGetResultsThenException() {
        Assertions.assertThrows(RuntimeException.class, () -> resultService.getResults(null));

    }

}