package com.qualityunit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class DataAnalyserTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testCreateDataAnalyserFromExistingFile() {
        DataAnalyser dataAnalizator = DataAnalyser.from("example.txt");
    }

    @Test(expected = DataAnalyserException.class)
    public void testCreateDataAnalyserFromNonExistingFile() {
        DataAnalyser dataAnalizator = DataAnalyser.from("blahblah.txt");
    }

    @Test
    public void testDataAnalyserExample() {
        DataAnalyser dataAnalizator = DataAnalyser.from("example.txt");
        assertEquals("83" + "\r\n" + "100" + "\r\n" + "-" + "\r\n", outContent.toString());
    }

    //плохая не числовая первая строка
    //отрицательная первая строка
    //строка больще 100 000
    //строк больще чем count of numbers
    // строк меньше чем count of numbers
    // count of numbers =0
    //строка не начинается с С или В
    //среднее время, проверить округление
    //елли в квешен айди больще параметров через точку
    //неправильный формат даты
    //три даты в периоде
    //периоды включения
}
