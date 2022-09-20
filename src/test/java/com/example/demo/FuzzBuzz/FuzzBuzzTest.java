package com.example.demo.FuzzBuzz;


import com.example.demo.configuration.FizzBuzz;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FuzzBuzzTest {
    @DisplayName("Test Small CSV")
    @ParameterizedTest(name ="value={0}, expected= {1}")
    @CsvFileSource(resources = "/small-test-data.csv")
    @Test
    @Order(3)
    void testSmallDataFile(int value,String expected){
        assertEquals(FizzBuzz.compute(value),expected);
    }
    @Order(2)
    @DisplayName("Testing Medium CSV")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources="/medium-test-data.csv")
    void testMediumDataFile(int value, String expected) {
        assertEquals(expected, FizzBuzz.compute(value));
    }
    @Order(1)
    @DisplayName("Testing Large CSV")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources="/large-test-data.csv")
    void testLargeDataFile(int value, String expected) {
        assertEquals(expected, FizzBuzz.compute(value));
    }
}
