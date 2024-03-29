package com.dozken.model;

import com.dozken.model.base.AbstractModel;
import com.dozken.model.enums.Event;

public class EventResult extends AbstractModel {
    private Event event;
    private Double result;

    private EventResult(Event event, Double result) {
        this.event = event;
        this.result = result;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Event getEvent() {
        return event;
    }

    public Double getResult() {
        return result;
    }

    public static EventResult valueOf(Event event, String result) {
        return new EventResult(event, Double.valueOf(result));
    }

    public static EventResult valueOf(Event event, Double result) {
        return new EventResult(event, result);
    }
}
