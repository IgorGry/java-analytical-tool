package com.qualityunit;


public class DataAnalyserException extends RuntimeException {
    public DataAnalyserException(String message) {
        super(message);
    }

    public DataAnalyserException(String message, Throwable cause) {
        super(message, cause);
    }

}
