package com.dozken;

import com.dozken.repository.AthleteResultRepository;
import com.dozken.repository.AthleteScoreRepository;
import com.dozken.repository.impl.AthleteResultRepositoryImpl;
import com.dozken.repository.impl.AthleteScoreRepositoryImpl;
import com.dozken.service.AthleteResultService;
import com.dozken.service.AthleteScoreService;
import com.dozken.service.DecathlonService;
import com.dozken.service.impl.AthleteResultServiceImpl;
import com.dozken.service.impl.AthleteScoreServiceImpl;
import com.dozken.service.impl.DecathlonServiceImpl;

public class Application {

    private final AthleteResultRepository resultRepository;
    private final AthleteResultService resultService;
    private final DecathlonService decathlonService;
    private final AthleteScoreService scoreService;
    private final AthleteScoreRepository scoreRepository;

    private Application() {
        scoreRepository = new AthleteScoreRepositoryImpl();
        resultRepository = new AthleteResultRepositoryImpl();
        resultService = new AthleteResultServiceImpl(resultRepository);
        scoreService = new AthleteScoreServiceImpl(scoreRepository);
        decathlonService = new DecathlonServiceImpl(resultService, scoreService);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java com.company.Decathlon <input_file> <output_file>");
            System.out.println("Example: java com.company.Decathlon csv/Decathlon_input.txt output/Decathlon_output.xml");
        } else {
            String inputFilePath = args[0];
            String outputFilePath = args[1];

            new Application().decathlonService.processAthleteResults(inputFilePath, outputFilePath);
            System.out.println("Successfully created file: " + outputFilePath);
        }
    }

}
