package com.dozken.model;

import java.util.List;

public class AthleteResult {
    private String fullName;
    private List<EventResult> eventResults;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<EventResult> getEventResults() {
        return eventResults;
    }

    public void setEventResults(List<EventResult> eventResults) {
        this.eventResults = eventResults;
    }
}
