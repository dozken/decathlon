package com.dozken.model;

import java.util.List;

public class AthleteResult {

    private String fullname;
    private List<EventResult> eventResults;


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public List<EventResult> getEventResults() {
        return eventResults;
    }

    public void setEventResults(List<EventResult> eventResults) {
        this.eventResults = eventResults;
    }
}
