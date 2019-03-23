package com.qualityunit;

import org.junit.Test;

public class DataAnalyserTest {
    @Test
    public void testCreateDataAnalyserFromExistingFile(){
        DataAnalyser dataAnalizator = DataAnalyser.from("example.txt");
    }

    @Test(expected = DataAnalyserException.class)
    public void testCreateDataAnalyserFromNonExistingFile() {
        DataAnalyser dataAnalizator = DataAnalyser.from("blahblah.txt");
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
}
