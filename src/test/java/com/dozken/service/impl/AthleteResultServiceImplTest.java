package com.dozken.service.impl;

import com.dozken.model.AthleteResult;
import com.dozken.model.AthleteScore;
import com.dozken.model.EventResult;
import com.dozken.model.enums.Event;
import com.dozken.model.enums.EventType;
import com.dozken.repository.AthleteResultRepository;
import com.dozken.repository.impl.AthleteResultRepositoryImpl;
import com.dozken.service.AthleteResultService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AthleteResultServiceImplTest {

    private AthleteResultService service;
    private AthleteResult expected;
    
    @BeforeEach
    void setUp() {
        AthleteResultRepository repo = new AthleteResultRepositoryImpl();
        service = new AthleteResultServiceImpl(repo);

        expected = new AthleteResult();
        expected.setFullname("John Smith");
        expected.setEventResults(Arrays.asList(
                EventResult.valueOf(Event.SPRINT_100M,  12.61),
                EventResult.valueOf(Event.LONG_JUMP,5.00),
                EventResult.valueOf(Event.SHORT_PUT, 9.22),
                EventResult.valueOf(Event.HIGH_JUMP, 1.50),
                EventResult.valueOf(Event.SPRINT_400M, 60.39),
                EventResult.valueOf(Event.HURDLES_110M, 16.43),
                EventResult.valueOf(Event.DISCUS_THROW, 21.60),
                EventResult.valueOf(Event.POLE_VAULT, 2.60),
                EventResult.valueOf(Event.JAVELIN_THROW, 35.81),
                EventResult.valueOf(Event.RUN_1500M,  toSeconds("5.25.72"))));
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
        List<AthleteResult> results = service.getResults(inputFilePath);

        //WHEN
        Optional<AthleteResult> optionalAthleteResult = results.stream().filter(x -> x.getFullname().equals(expected.getFullname())).findFirst();

        //THEN
        assertTrue(optionalAthleteResult.isPresent());
        AthleteResult actual = optionalAthleteResult.get();
        assertEquals(actual.getEventResults().get(0).getResult(), expected.getEventResults().get(0).getResult());
    }

    @Test
    void getScores() {
        //GIVEN
        Path inputFilePath = Paths.get("src/test/resources/clark_kent_results.csv");
        List<AthleteResult> results = service.getResults(inputFilePath);

        //WHEN
        List<AthleteScore> scores = service.getScores(results);

        //THEN
        Optional<AthleteScore> optionalAthleteScore = scores.stream().filter(x -> x.getAthleteResult().getFullname().equals("Clark Kent")).findFirst();
        assertTrue(optionalAthleteScore.isPresent());
        AthleteScore actual = optionalAthleteScore.get();

        assertNotNull(actual.getAthleteResult());
        assertTrue(actual.getAthleteResult().getEventResults().size() == 10);
        List<EventResult> eventResults = actual.getAthleteResult().getEventResults();
        eventResults.forEach(eventResult -> {
            System.out.println(eventResult.getEvent()+ " " + eventResult.getPoint());
            assertTrue(eventResult.getPoint() > 990);
        });

        assertTrue(actual.getScore() >= 9990);

        assertEquals("1" , actual.getPlace());



    }

    @Test
    void exportScores() {
    }

    private double toSeconds(String run1500mSeconds) {
        String[] data = run1500mSeconds.split("\\.");
        if (data.length == 2) {
            return Double.valueOf(run1500mSeconds);
        } else if (data.length == 3) {
            Integer minutes = Integer.parseInt(data[0]);
            return minutes * 60 + Double.valueOf(data[1] + "." + data[2]);
        } else {
            throw new RuntimeException("Cannot parse string time to seconds.");
        }
    }


}