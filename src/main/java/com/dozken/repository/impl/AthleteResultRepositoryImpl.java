package com.dozken.repository.impl;

import com.dozken.exception.CSVFileException;
import com.dozken.model.AthleteResult;
import com.dozken.model.EventResult;
import com.dozken.model.enums.Event;
import com.dozken.repository.AthleteResultRepository;
import com.dozken.utils.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.dozken.constants.Constants.CSV_SEPARATOR;
import static com.dozken.constants.Constants.DECATHLON_EVENTS_SIZE;
import static java.util.stream.Collectors.toList;

public class AthleteResultRepositoryImpl implements AthleteResultRepository {

    @Override
    public List<AthleteResult> getAll(Path path) {
        List<AthleteResult> list;

        try (Stream<String> stream = Files.lines(path)) {
            list = stream
                    .filter(x -> !x.trim().isEmpty())
                    .map(this::mapToAthleteResult).collect(toList());
        } catch (IOException ex) {
            throw new CSVFileException("Exception while reading file.", ex);
        }

        return list;
    }

    private AthleteResult mapToAthleteResult(String line) {
        String[] data = line.trim().split(CSV_SEPARATOR);
        // +1 for name column
        if (data.length != DECATHLON_EVENTS_SIZE + 1) {
            throw new RuntimeException("Athlete`s: " + data[0] + ", sport events size is not: " + DECATHLON_EVENTS_SIZE);
        }
        if (StringUtils.isEmpty(data[0])) {
            throw new IllegalArgumentException("Athlete`s name was not specified");
        }
        for (int i = 1; i <= 9; i++) {
            if (StringUtils.isNotNumeric(data[i])) {
                throw new IllegalArgumentException("Athlete`s result data is not correct");
            }
        }
        if (StringUtils.isNotTime(data[10])) {
            throw new IllegalArgumentException("Athlete`s result data is not correct");
        }

        double run1500mSeconds;
        try {
            run1500mSeconds = StringUtils.toSeconds(data[10]);
        } catch (DateTimeParseException ex) {
            throw new RuntimeException("Could not parse: ", ex);
        }

        AthleteResult athleteResult = new AthleteResult();
        athleteResult.setFullName(data[0]);
        athleteResult.setEventResults(Arrays.asList(
                EventResult.valueOf(Event.SPRINT_100M, data[1]),
                EventResult.valueOf(Event.LONG_JUMP, data[2]),
                EventResult.valueOf(Event.SHORT_PUT, data[3]),
                EventResult.valueOf(Event.HIGH_JUMP, data[4]),
                EventResult.valueOf(Event.SPRINT_400M, data[5]),
                EventResult.valueOf(Event.HURDLES_110M, data[6]),
                EventResult.valueOf(Event.DISCUS_THROW, data[7]),
                EventResult.valueOf(Event.POLE_VAULT, data[8]),
                EventResult.valueOf(Event.JAVELIN_THROW, data[9]),
                EventResult.valueOf(Event.RUN_1500M, run1500mSeconds))
        );

        return athleteResult;
    }

}
