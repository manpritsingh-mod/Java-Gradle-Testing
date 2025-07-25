package com.example.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Calculator functionality.
 * Contains comprehensive tests for all calculator operations.
 */
@DisplayName("Calculator Tests")
class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    @DisplayName("Addition should work correctly")
    void testAddition() {
        assertThat(calculator.add(2.0, 3.0)).isEqualTo(5.0);
        assertThat(calculator.add(-1.0, 1.0)).isEqualTo(0.0);
        assertThat(calculator.add(0.0, 0.0)).isEqualTo(0.0);
    }
    
    @Test
    @DisplayName("Subtraction should work correctly")
    void testSubtraction() {
        assertThat(calculator.subtract(5.0, 3.0)).isEqualTo(2.0);
        assertThat(calculator.subtract(1.0, 1.0)).isEqualTo(0.0);
        assertThat(calculator.subtract(-1.0, -1.0)).isEqualTo(0.0);
    }
    
    @Test
    @DisplayName("Multiplication should work correctly")
    void testMultiplication() {
        assertThat(calculator.multiply(2.0, 3.0)).isEqualTo(6.0);
        assertThat(calculator.multiply(-2.0, 3.0)).isEqualTo(-6.0);
        assertThat(calculator.multiply(0.0, 5.0)).isEqualTo(0.0);
    }
    
    @Test
    @DisplayName("Division should work correctly")
    void testDivision() {
        assertThat(calculator.divide(6.0, 2.0)).isEqualTo(3.0);
        assertThat(calculator.divide(5.0, 2.0)).isEqualTo(2.5);
        assertThat(calculator.divide(-6.0, 2.0)).isEqualTo(-3.0);
    }
    
    @Test
    @DisplayName("Division by zero should throw exception")
    void testDivisionByZero() {
        assertThatThrownBy(() -> calculator.divide(5.0, 0.0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Division by zero is not allowed");
    }
    
    @ParameterizedTest
    @DisplayName("Even number check should work correctly")
    @ValueSource(ints = {2, 4, 6, 8, 10, 0, -2, -4})
    void testEvenNumbers(int number) {
        assertThat(calculator.isEven(number)).isTrue();
    }
    
    @ParameterizedTest
    @DisplayName("Odd number check should work correctly")
    @ValueSource(ints = {1, 3, 5, 7, 9, -1, -3, -5})
    void testOddNumbers(int number) {
        assertThat(calculator.isEven(number)).isFalse();
    }
    
    @ParameterizedTest
    @DisplayName("Power calculation should work correctly")
    @CsvSource({
        "2.0, 3, 8.0",
        "3.0, 2, 9.0",
        "5.0, 0, 1.0",
        "2.0, -2, 0.25",
        "10.0, 1, 10.0"
    })
    void testPower(double base, int exponent, double expected) {
        assertThat(calculator.power(base, exponent)).isEqualTo(expected);
    }
    
    @Test
    @DisplayName("Circle area calculation should work correctly")
    void testCircleArea() {
        double radius = 5.0;
        double expectedArea = 3.14159 * radius * radius;
        assertThat(calculator.calculateCircleArea(radius)).isEqualTo(expectedArea);
    }
    
    @Test
    @DisplayName("Circle area with zero radius")
    void testCircleAreaZeroRadius() {
        assertThat(calculator.calculateCircleArea(0.0)).isEqualTo(0.0);
    }
}