package com.dozken.service.impl;

import com.dozken.model.AthleteResult;
import com.dozken.model.AthleteScore;
import com.dozken.model.EventResult;
import com.dozken.model.EventScore;
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

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AthleteScoreServiceImplTest {

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
    void tearDown() throws Exception {
        Path pathToBeDeleted = Paths.get("src/test/resources/out");
        if (Files.exists(pathToBeDeleted)) {
            Files.walk(pathToBeDeleted)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .filter(File::exists)
                    .forEach(x -> assertTrue(x.delete()));

            assertFalse(Files.exists(pathToBeDeleted));
        }
    }

    @Test
    void getScoreOfOneAthlete() {
        //GIVEN
        Path inputFilePath = Paths.get("src/test/resources/clark_kent_results.csv");
        List<AthleteResult> results = resultService.getResults(inputFilePath);

        //WHEN
        List<AthleteScore> scores = scoreService.getScores(results);

        //THEN
        Optional<AthleteScore> optionalAthleteScore = scores.stream().filter(x -> x.getFullName().equals("Clark Kent")).findFirst();
        assertTrue(optionalAthleteScore.isPresent());
        AthleteScore actual = optionalAthleteScore.get();

        assertNotNull(actual.getEventScores());
        assertEquals(10, actual.getEventScores().size());
        List<EventScore> eventScores = actual.getEventScores();
        eventScores.forEach(score -> assertEquals(1000, score.getScore()));

        assertEquals(1000 * 10, actual.getScore());

        assertEquals("1", actual.getPlace());
    }

    @Test
    void getScoresOfTwoAthletes() {
        //GIVEN
        Path inputFilePath = Paths.get("src/test/resources/clark_kent2_results.csv");
        List<AthleteResult> results = resultService.getResults(inputFilePath);

        //WHEN
        List<AthleteScore> scores = scoreService.getScores(results);

        //THEN
        Optional<AthleteScore> optionalAthleteScore = scores.stream().filter(x -> x.getFullName().equals("Clark Kent")).findFirst();
        assertTrue(optionalAthleteScore.isPresent());
        AthleteScore actual = optionalAthleteScore.get();

        assertNotNull(actual.getEventScores());
        assertEquals(10, actual.getEventScores().size());
        List<EventScore> eventScores = actual.getEventScores();
        eventScores.forEach(score -> assertEquals(1000, score.getScore()));

        assertEquals(1000 * 10, actual.getScore());

        assertEquals("1-2", actual.getPlace());
    }

    @Test
    void exportScores() {
        //GIVEN
        Path inputFilePath = Paths.get("src/test/resources/clark_kent2_results.csv");
        List<AthleteResult> results = resultService.getResults(inputFilePath);
        //WHEN
        List<AthleteScore> scores = scoreService.getScores(results);
        //THEN

        assertTrue(new File("src/test/resources/out").mkdirs());
        scoreService.export(scores, Paths.get("src/test/resources/out/output.xml"));
        assertTrue(new File("src/test/resources/out/output.xml").exists());
    }

    @Test
    void givenEmptyAthleteScoreWhenExportThenException() {
        Assertions.assertThrows(RuntimeException.class, () -> scoreService.export(null, Paths.get("some path")));
    }

    @Test
    void givenEmptyPathWhenExportThenException() {
        Assertions.assertThrows(RuntimeException.class, () -> scoreService.export(Collections.singletonList(new AthleteScore()), null));
    }


}