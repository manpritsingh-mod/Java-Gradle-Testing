package com.example.calculator;

/**
 * A simple calculator class with basic arithmetic operations.
 * This class intentionally contains some checkstyle violations for testing.
 */
public class Calculator {
    
    // Checkstyle violation: missing javadoc for field
    private static final double PI_VALUE = 3.14159;
    
    /**
     * Adds two numbers.
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    public double add(double a, double b) {
        return a + b;
    }
    
    /**
     * Subtracts second number from first.
     * @param a first number
     * @param b second number
     * @return difference of a and b
     */
    public double subtract(double a, double b) {
        return a - b;
    }
    
    /**
     * Multiplies two numbers.
     * @param a first number
     * @param b second number
     * @return product of a and b
     */
    public double multiply(double a, double b) {
        return a * b;
    }
    
    /**
     * Divides first number by second.
     * @param a dividend
     * @param b divisor
     * @return quotient of a divided by b
     * @throws IllegalArgumentException if divisor is zero
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        return a / b;
    }
    
    // Checkstyle violation: line too long (over 120 characters)
    public double calculateCircleArea(double radius) { return PI_VALUE * radius * radius; /* This line is intentionally too long to trigger checkstyle violation */ }
    
    // Checkstyle violation: missing javadoc
    public boolean isEven(int number) {
        return number % 2 == 0;
    }
    
    /**
     * Calculates power of a number.
     * @param base base number
     * @param exponent exponent
     * @return base raised to the power of exponent
     */
    public double power(double base, int exponent) {
        double result = 1;
        for (int i = 0; i < Math.abs(exponent); i++) {
            result *= base;
        }
        return exponent < 0 ? 1 / result : result;
    }
}