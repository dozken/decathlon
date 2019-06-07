package com.dozken.constants;

import com.dozken.model.enums.Event;
import com.dozken.model.enums.Parameter;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final Map<Event, Parameter> POINTS_SYSTEM = new HashMap<>();

    static {
        POINTS_SYSTEM.put(Event.SPRINT_100M, new Parameter(25.4347, 18, 1.81));
        POINTS_SYSTEM.put(Event.LONG_JUMP, new Parameter(0.14354, 220, 1.4));
        POINTS_SYSTEM.put(Event.SHORT_PUT, new Parameter(51.39, 1.5, 1.05));
        POINTS_SYSTEM.put(Event.HIGH_JUMP, new Parameter(0.8465, 75, 1.42));
        POINTS_SYSTEM.put(Event.SPRINT_400M, new Parameter(1.53775, 82, 1.81));
        POINTS_SYSTEM.put(Event.HURDLES_110M, new Parameter(5.74352, 28.5, 1.92));
        POINTS_SYSTEM.put(Event.DISCUS_THROW, new Parameter(12.91, 4, 1.1));
        POINTS_SYSTEM.put(Event.POLE_VAULT, new Parameter(0.2797, 100, 1.35));
        POINTS_SYSTEM.put(Event.JAVELIN_THROW, new Parameter(10.14, 7, 1.08));
        POINTS_SYSTEM.put(Event.RUN_1500M, new Parameter(0.03768, 480, 1.85));
    }

    public static final String CSV_SEPERATOR = ";";

    public static final int DECATHLON_EVENTS_SIZE = 10;

}
