package com.dozken.repository.impl;

import com.dozken.model.AthleteScore;
import com.dozken.model.AthleteScoreDocument;
import com.dozken.repository.AthleteScoreRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.nio.file.Path;
import java.util.List;

public class AthleteScoreRepositoryImpl implements AthleteScoreRepository {

    @Override
    public void export(List<AthleteScore> athleteScores, Path outputFile) {
        AthleteScoreDocument xmlDocument = new AthleteScoreDocument();
        xmlDocument.setAthleteScores(athleteScores);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AthleteScoreDocument.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(xmlDocument, outputFile.toFile());
        } catch (JAXBException ex) {
            throw new RuntimeException("Could not product xml file", ex);
        }
    }
}
