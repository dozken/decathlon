package com.dozken.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "athleteScores")
public class AthleteScoreDocument implements Serializable {
    private List<AthleteScore> athleteScores;

    @XmlElement(name = "athleteScore")
    public void setAthleteScores(List<AthleteScore> athleteScores) {
        this.athleteScores = athleteScores;
    }

    public List<AthleteScore> getAthleteScores() {
        return athleteScores;
    }
}
