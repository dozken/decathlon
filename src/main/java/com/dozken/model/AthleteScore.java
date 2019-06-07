package com.dozken.model;

public class AthleteScore {

    private String place;
    private Integer score;
    private AthleteResult athleteResult;

    public AthleteScore() {
    }

    public AthleteScore(Integer score, AthleteResult athleteResult) {
        this.score = score;
        this.athleteResult = athleteResult;
    }

    public AthleteScore(String place, Integer score, AthleteResult athleteResult) {
        this(score, athleteResult);
        this.place = place;
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

    public void setScore(Integer score) {
        this.score = score;
    }

    public AthleteResult getAthleteResult() {
        return athleteResult;
    }

    public void setAthleteResult(AthleteResult athleteResult) {
        this.athleteResult = athleteResult;
    }
}
