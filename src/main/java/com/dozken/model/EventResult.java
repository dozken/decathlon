package com.dozken.model;

import com.dozken.model.enums.Event;

import java.math.BigDecimal;

public class EventResult {

    private Event event;
    private Double result;
    private Integer point;


    public EventResult(Event event, Double result) {
        this.event = event;
        this.result = result;
    }


    public Event getEvent() {
        return event;
    }

    public Double getResult() {
        return result;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public static EventResult valueOf(Event event, String result) {
        return new EventResult(event, Double.valueOf(result));
    }

    public static EventResult valueOf(Event event, Double result) {
        return new EventResult(event, result);
    }
}
