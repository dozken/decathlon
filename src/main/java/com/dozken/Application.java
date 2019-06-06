package com.dozken;

import com.dozken.service.impl.DecathlonServiceImpl;

public class Application {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java com.company.Decathlon <input_file> <output_file>");
            System.out.println("Example: java com.company.Decathlon csv/Decathlon_input.txt output/Decathlon_output.xml");
        } else {
            DecathlonServiceImpl decathlonService = new DecathlonServiceImpl();

            String inputFilePath = args[0];
            String outputFilePath = args[1];
            decathlonService.processAthleteResults(inputFilePath, outputFilePath);
            System.out.println("Successfully created file: " + outputFilePath);
        }
    }

}
