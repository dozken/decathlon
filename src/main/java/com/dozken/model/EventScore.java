package com.dozken.model;

import com.dozken.model.base.AbstractModel;
import com.dozken.model.enums.Event;

public class EventScore extends AbstractModel {
    private Event event;
    private Double result;
    private Integer score;

    public EventScore(Event event, Double result, int score) {
        this.event = event;
        this.result = result;
        this.score = score;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
