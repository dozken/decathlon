package com.dozken.model.enums;

public enum Event {

    SPRINT_100M(EventType.TRACK),
    LONG_JUMP(EventType.TRACK),
    SHORT_PUT(EventType.FIELD),
    HIGH_JUMP(EventType.TRACK),
    SPRINT_400M(EventType.TRACK),
    HURDLES_110M(EventType.TRACK),
    DISCUS_THROW(EventType.FIELD),
    POLE_VAULT(EventType.TRACK),
    JAVELIN_THROW(EventType.FIELD),
    RUN_1500M(EventType.TRACK);

    private EventType type;

    Event(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }
}
