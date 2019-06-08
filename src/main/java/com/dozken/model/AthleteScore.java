package com.dozken.model;

import com.dozken.model.base.AbstractModel;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class AthleteScore extends AbstractModel {
    private String place;
    private Integer score;
    private String fullName;
    private List<EventScore> eventScores;

    public AthleteScore() {
    }

    public AthleteScore(String fullName, List<EventScore> eventScores, Integer score) {
        this.fullName = fullName;
        this.eventScores = eventScores;
        this.score = score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getScore() {
        return score;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<EventScore> getEventScores() {
        return eventScores;
    }

    @XmlElement(name = "eventScore")
    public void setEventScores(List<EventScore> eventScores) {
        this.eventScores = eventScores;
    }

}
