package com.dozken.constants;

import com.dozken.model.enums.Event;
import com.dozken.model.enums.Parameter;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final Map<Event, Parameter> POINTS_SYSTEM = new HashMap<>();

    static {
        POINTS_SYSTEM.put(Event.SPRINT_100M, new Parameter(25.4347d, 18d, 1.81d));
        POINTS_SYSTEM.put(Event.LONG_JUMP, new Parameter(0.14354d, 220d, 1.4d));
        POINTS_SYSTEM.put(Event.SHORT_PUT, new Parameter(51.39d, 1.5d, 1.05d));
        POINTS_SYSTEM.put(Event.HIGH_JUMP, new Parameter(0.8465d, 75d, 1.42d));
        POINTS_SYSTEM.put(Event.SPRINT_400M, new Parameter(1.53775d, 82d, 1.81d));
        POINTS_SYSTEM.put(Event.HURDLES_110M, new Parameter(5.74352d, 28.5d, 1.92d));
        POINTS_SYSTEM.put(Event.DISCUS_THROW, new Parameter(12.91d, 4d, 1.1d));
        POINTS_SYSTEM.put(Event.POLE_VAULT, new Parameter(0.2797d, 100d, 1.35d));
        POINTS_SYSTEM.put(Event.JAVELIN_THROW, new Parameter(10.14d, 7d, 1.08d));
        POINTS_SYSTEM.put(Event.RUN_1500M, new Parameter(0.03768d, 480d, 1.85d));
    }

    public static final String CSV_SEPERATOR = ";";

    public static final int DECATHLON_EVENTS_SIZE = 10;

}
