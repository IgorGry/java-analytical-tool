package com.qualityunit;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataAnalyser {

    private static final int MAX_NUMBER_OF_LINES = 100_000;
    private int countOfLines;
    private List<LineC> lines = new ArrayList<>();


    public static DataAnalyser fromResourceFolder(String fileName) {
        return new DataAnalyser(fileName);
    }

    private DataAnalyser(String fileName) {
        processFile(fileName);
    }

    private void processFile(String fileName) {
        String currentLine;
        int actualCountOfLine = 0;
        Objects.requireNonNull(fileName);

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/" + fileName)))) {
            currentLine = bufferedReader.readLine();
            int parsedCountOfLines = parseCountOfLines(currentLine);
            setCountOfLines(parsedCountOfLines);
            while (actualCountOfLine < countOfLines && (currentLine = bufferedReader.readLine()) != null) {
                String splitLine[] = currentLine.split(" ");
                if (splitLine[0].equals("C")) {
                    LineC lineC = parseLineC(splitLine);
                    lines.add(lineC);
                } else if (splitLine[0].equals("D")) {
                    LineD lineD = parseLineD(splitLine);
                    printAverageWaitingTime(lineD);
                } else {
                    throw new DataAnalyserException(String.format("Line [%s] doesn't start with C or D", currentLine));
                }
                actualCountOfLine++;
            }
            validateActualCountOfLines(actualCountOfLine, bufferedReader.readLine() != null);
        } catch (IOException e) {
            throw new DataAnalyserException("Wrong filename", e);
        }
    }

    private int parseCountOfLines(String firstLine) {
        return Integer.parseInt(firstLine);
    }

    private void setCountOfLines(int countOfLines) {
        validateCountOfLines(countOfLines);
        this.countOfLines = countOfLines;
    }

    private void validateCountOfLines(int countOfLines) {
        if (countOfLines < 0 || countOfLines > MAX_NUMBER_OF_LINES) {
            throw new DataAnalyserException("Invalid count of lines");
        }
    }

    private LineC parseLineC(String[] splitLine) {
        LineC lineC = new LineC();
        parseService(splitLine[1], lineC);
        parseQuestion(splitLine[2], lineC);
        parseResponseType(splitLine[3], lineC);
        parseResponseDate(splitLine[4], lineC);
        parseWaitingTimeInMinutes(splitLine[5], lineC);
        return lineC;
    }

    private LineD parseLineD(String[] splitLine) {
        LineD lineD = new LineD();
        parseService(splitLine[1], lineD);
        parseQuestion(splitLine[2], lineD);
        parseResponseType(splitLine[3], lineD);
        parseResponsePeriod(splitLine[4], lineD);
        return lineD;
    }


    private void parseService(String s, Line line) {
        if (!s.equals("*")) {
            String service[] = s.split("\\.");
            line.setServiceId(Byte.parseByte(service[0]));
            if (service.length == 2) {
                line.setServiceVariationId(Byte.parseByte(service[1]));
            } else if (service.length > 2) {
                throw new DataAnalyserException("Invalid service value " + s);
            }
        }
    }

    private void parseQuestion(String s, Line line) {
        if (!s.equals("*")) {
            String question[] = s.split("\\.");
            line.setQuestionTypeId(Byte.parseByte(question[0]));
            if (question.length >= 2) {
                line.setQuestionCategoryId(Byte.parseByte(question[1]));
                if (question.length == 3) {
                    line.setQuestionSubCategoryId(Byte.parseByte(question[2]));
                } else if (question.length > 3) {
                    throw new DataAnalyserException("Invalid question value " + s);
                }
            }
        }
    }

    private void parseResponseType(String s, Line line) {
        line.setResponseType(s.charAt(0));
    }

    private void parseResponseDate(String date, LineC lineC) {
        LocalDate parsedResponseDate = parseDate(date);
        lineC.setResponseDate(parsedResponseDate);
    }

    private LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }

    private void parseWaitingTimeInMinutes(String s, LineC lineC) {
        lineC.setWaitingTimeInMinutes(Integer.parseInt(s));
    }

    private void parseResponsePeriod(String s, LineD lineD) {
        String responsePeriod[] = s.split("-");
        LocalDate parsedDateFrom = parseDate(responsePeriod[0]);
        LocalDate parsedDateTo = null;
        if (responsePeriod.length == 2) {
            parsedDateTo = parseDate(responsePeriod[1]);
        } else if (responsePeriod.length > 2) {
            throw new DataAnalyserException("Invalid response period " + s);
        }
        lineD.setDateFrom(parsedDateFrom);
        lineD.setDateTo(parsedDateTo == null ? parsedDateFrom : parsedDateTo);

    }

    private void printAverageWaitingTime(LineD lineD) {

        Double averageWaitingTime = lines.stream()
                .parallel()
                .filter(lineC -> lineD.getServiceId() == 0 || lineD.getServiceId() == lineC.getServiceId())
                .filter(lineC -> lineD.getServiceVariationId() == 0 || lineD.getServiceVariationId() == lineC.getServiceVariationId())
                .filter(lineC -> lineD.getQuestionTypeId() == 0 || lineD.getQuestionTypeId() == lineC.getQuestionTypeId())
                .filter(lineC -> lineD.getQuestionCategoryId() == 0 || lineD.getQuestionCategoryId() == lineC.getQuestionCategoryId())
                .filter(lineC -> lineD.getQuestionSubCategoryId() == 0 || lineD.getQuestionSubCategoryId() == lineC.getQuestionSubCategoryId())
                .filter(lineC -> lineD.getResponseType() == lineC.getResponseType())
                .filter(lineC -> (!(lineC.getResponseDate().isBefore(lineD.getDateFrom()) || lineC.getResponseDate().isAfter(lineD.getDateTo()))))
                .mapToInt(LineC::getWaitingTimeInMinutes)
                .average()
                .orElse(-1);
        int result = averageWaitingTime.intValue();
        System.out.println(result == -1 ? "-" : result);

    }

    private void validateActualCountOfLines(int actualCountOfLine, boolean isLineLeft) {
        if (isLineLeft) {
            throw new DataAnalyserException("Number of lines is lager than stated");
        }
        if (actualCountOfLine < countOfLines) {
            throw new DataAnalyserException("Number of lines is smaller than stated");
        }
    }

}