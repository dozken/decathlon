package com.dozken.model.enums;

public enum Event {
    SPRINT_100M(EventType.TRACK),
    LONG_JUMP(EventType.FIELD),
    SHORT_PUT(EventType.FIELD),
    HIGH_JUMP(EventType.FIELD),
    SPRINT_400M(EventType.TRACK),
    HURDLES_110M(EventType.TRACK),
    DISCUS_THROW(EventType.FIELD),
    POLE_VAULT(EventType.FIELD),
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
