package com.qualityunit;

import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DataAnalyserTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Test
    public void testDataAnalyserFromExistingFile() {
        DataAnalyser.fromResourceFolder("test_example.txt");
    }

    @Test
    public void testDataAnalyserExample() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        DataAnalyser.fromResourceFolder("test_example.txt");
        assertEquals("83" + "\r\n" + "100" + "\r\n" + "-" + "\r\n", outContent.toString());
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testDataAnalyserFromNonExistingFile() {
        try {
            DataAnalyser.fromResourceFolder("blahblah.txt");
            fail("Exception was't thrown");
        } catch (Exception e) {
            assertEquals(DataAnalyserException.class, e.getClass());
            assertEquals("Wrong filename", e.getMessage());
        }
    }

    @Test
    public void testDataAnalyserInvalidLineType() {
        try {
            DataAnalyser.fromResourceFolder("test_invalid_line_type.txt");
            fail("Exception was't thrown");
        } catch (Exception e) {
            assertEquals(DataAnalyserException.class, e.getClass());
            assertEquals(String.format("Line [%s] doesn't start with C or D", "B 1.1 8 P 01.01.2012-01.12.2012"), e.getMessage());
        }
    }

    @Test
    public void testDataAnalyserInvalidCountOfLines() {
        try {
            DataAnalyser.fromResourceFolder("test_invalid_count_of_lines.txt");
            fail("Exception was't thrown");
        } catch (Exception e) {
            assertEquals(DataAnalyserException.class, e.getClass());
            assertEquals("Invalid count of lines", e.getMessage());
        }
    }

    @Test
    public void testDataAnalyserInvalidServiceValue() {
        try {
            DataAnalyser.fromResourceFolder("test_invalid_service_value.txt");
            fail("Exception was't thrown");
        } catch (Exception e) {
            assertEquals(DataAnalyserException.class, e.getClass());
            assertEquals(String.format("Invalid service value %s", "1.1.1"), e.getMessage());
        }
    }

    @Test
    public void testDataAnalyserInvalidQuestionValue() {
        try {
            DataAnalyser.fromResourceFolder("test_invalid_question_value.txt");
            fail("Exception was't thrown");
        } catch (Exception e) {
            assertEquals(DataAnalyserException.class, e.getClass());
            assertEquals(String.format("Invalid question value %s", "5.5.1.3"), e.getMessage());
        }
    }

    @Test
    public void testDataAnalyserInvalidResponsePeriod() {
        try {
            DataAnalyser.fromResourceFolder("test_response_period.txt");
            fail("Exception was't thrown");
        } catch (Exception e) {
            assertEquals(DataAnalyserException.class, e.getClass());
            assertEquals(String.format("Invalid response period %s", "8.10.2012-20.11.2012-08.09.2013"), e.getMessage());
        }
    }

    @Test
    public void testDataAnalyserNumberOfLinesLagerThanStated() {
        try {
            DataAnalyser.fromResourceFolder("test_number_of_lines_larger_than_stated.txt");
            fail("Exception was't thrown");
        } catch (Exception e) {
            assertEquals(DataAnalyserException.class, e.getClass());
            assertEquals("Number of lines is lager than stated", e.getMessage());
        }
    }

    @Test
    public void testDataAnalyserNumberOfLinesSmallerThanStated() {
        try {
            DataAnalyser.fromResourceFolder("test_number_of_lines_smaller_than_stated.txt");
            fail("Exception was't thrown");
        } catch (Exception e) {
            assertEquals(DataAnalyserException.class, e.getClass());
            assertEquals("Number of lines is smaller than stated", e.getMessage());
        }
    }

    @Test
    public void testDataAnalyserInvalidServiceId() {
        try {
            DataAnalyser.fromResourceFolder("test_invalid_service_id.txt");
            fail("Exception was't thrown");
        } catch (Exception e) {
            assertEquals(DataAnalyserException.class, e.getClass());
            assertEquals(String.format("Invalid service_id %s", "11"), e.getMessage());
        }
    }

    @Test
    public void testDataAnalyserInvalidServiceVariationId() {
        try {
            DataAnalyser.fromResourceFolder("test_invalid_service_variation_id.txt");
            fail("Exception was't thrown");
        } catch (Exception e) {
            assertEquals(DataAnalyserException.class, e.getClass());
            assertEquals(String.format("Invalid service_variation_id %s", "4"), e.getMessage());
        }
    }

    @Test
    public void testDataAnalyserInvalidQuestionTypeId() {
        try {
            DataAnalyser.fromResourceFolder("test_invalid_question_type_id.txt");
            fail("Exception was't thrown");
        } catch (Exception e) {
            assertEquals(DataAnalyserException.class, e.getClass());
            assertEquals(String.format("Invalid question_type_id %s", "-3"), e.getMessage());
        }
    }

    @Test
    public void testDataAnalyserInvalidQuestionCategoryId() {
        try {
            DataAnalyser.fromResourceFolder("test_invalid_question_category_id.txt");
            fail("Exception was't thrown");
        } catch (Exception e) {
            assertEquals(DataAnalyserException.class, e.getClass());
            assertEquals(String.format("Invalid question_category_id %s", "21"), e.getMessage());
        }
    }

    @Test
    public void testDataAnalyserInvalidQuestionSubCategoryId() {
        try {
            DataAnalyser.fromResourceFolder("test_invalid_question_subcategory_id.txt");
            fail("Exception was't thrown");
        } catch (Exception e) {
            assertEquals(DataAnalyserException.class, e.getClass());
            assertEquals(String.format("Invalid question_sub-category_id %s", "6"), e.getMessage());
        }
    }

    @Test
    public void testDataAnalyserInvalidResponseType() {
        try {
            DataAnalyser.fromResourceFolder("test_invalid_response_type.txt");
            fail("Exception was't thrown");
        } catch (Exception e) {
            assertEquals(DataAnalyserException.class, e.getClass());
            assertEquals(String.format("Invalid response type %s", "C"), e.getMessage());
        }
    }

    @Ignore()
    @Test
    public void testDataAnalyserFromMaxLinesFile() {
        DataAnalyser.fromResourceFolder("test_example_max_lines.txt");
    }
}
