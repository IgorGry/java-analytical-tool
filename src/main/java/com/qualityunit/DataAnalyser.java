package com.qualityunit;


import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataAnalyser {

    private static final int MAX_NUMBER_OF_LINES = 100_000;
    private int countOfLines;
    private List<LineC> lines = new ArrayList<>();


    public static DataAnalyser from(String fileName) {
        return new DataAnalyser(fileName);
    }

    private DataAnalyser(String fileName) {
        File file = getFile(fileName);
        processFile(file);
    }

    private File getFile(String fileName) {
        Objects.requireNonNull(fileName);
        URL fileUrl = getFileUrl(fileName);
        return new File(fileUrl.getFile());
    }

    private URL getFileUrl(String fileName) {
        URL fileUrl = getClass().getClassLoader().getResource(fileName);
        if (fileUrl == null) {
            throw new DataAnalyserException("Wrong file path");
        }
        return fileUrl;
    }

    private void processFile(File file) {
        String currentLine;
        int actualCountOfLine = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            currentLine = bufferedReader.readLine();
            int parsedCountOfLines = parseCountOfLines(currentLine);
            setCountOfLines(parsedCountOfLines);
            while ((currentLine = bufferedReader.readLine()) != null && actualCountOfLine <= countOfLines) {
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
            validateActualCountOfLines(actualCountOfLine);//todo if try one more readLine in case if actualCout>Count and actualCunt<Count
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new DataAnalyserException("Wrong file path");
        }
    }

    private int parseCountOfLines(String firstLine) {
        int countOfLines = Integer.parseInt(firstLine);
        return countOfLines;
    }

    private void setCountOfLines(int countOfLines) {
        validateCountOfLines(countOfLines);
        this.countOfLines = countOfLines;
    }

    private void validateCountOfLines(int countOfLines) {
        if (countOfLines < 0 || countOfLines > 100_000) {
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
        return null;//todo
    }


    private void parseService(String s, Line line) {
        String service[] = s.split(".");
        line.setServiceId(Byte.parseByte(service[0]));
        if (service.length == 2) {
            line.setServiceVariationId(Byte.parseByte(service[1]));
        } else if (service.length > 2) {
            throw new DataAnalyserException("Invalid service value " + s);
        }
    }

    private void parseQuestion(String s, Line line) {
        String question[] = s.split(".");
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

    private void parseResponseType(String s, Line line) {
        line.setResponseType(s.charAt(0));
    }

    private void parseResponseDate(String date, LineC lineC) {
        LocalDate parsedResponseDate = parseDate(date);
        lineC.setResponseDate(parsedResponseDate);
    }

    private LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        return parsedDate;
    }

    private void parseWaitingTimeInMinutes(String s, LineC lineC) {
        lineC.setWaitingTimeInMinutes(Integer.parseInt(s));
    }

    private void printAverageWaitingTime(LineD lineD) {
        //todo
    }

    private void validateActualCountOfLines(int actualCountOfLine) {
        //todo
        System.out.println(lines);
    }

}