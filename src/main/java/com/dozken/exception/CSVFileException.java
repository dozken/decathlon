package com.dozken.exception;

import java.io.IOException;

public class CSVFileException extends RuntimeException {
    public CSVFileException(String message, IOException ex) {
        super(message, ex);
    }
}
