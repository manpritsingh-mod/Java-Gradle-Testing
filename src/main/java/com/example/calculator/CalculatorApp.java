package com.example.calculator;

import java.util.Scanner;

/**
 * Main application class for the calculator.
 * This class contains the main method and user interface.
 */
public class CalculatorApp {
    
    private static final Calculator calculator = new Calculator();
    
    /**
     * Main method to run the calculator application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Simple Calculator");
        System.out.println("Available operations: +, -, *, /, ^, area, even");
        System.out.println("Type 'quit' to exit");
        
        while (true) {
            System.out.print("Enter operation: ");
            String operation = scanner.nextLine().trim();
            
            if ("quit".equalsIgnoreCase(operation)) {
                break;
            }
            
            try {
                processOperation(operation, scanner);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        scanner.close();
        System.out.println("Calculator closed.");
    }
    
    private static void processOperation(String operation, Scanner scanner) {
        switch (operation) {
            case "+":
                performBinaryOperation("addition", scanner, calculator::add);
                break;
            case "-":
                performBinaryOperation("subtraction", scanner, calculator::subtract);
                break;
            case "*":
                performBinaryOperation("multiplication", scanner, calculator::multiply);
                break;
            case "/":
                performBinaryOperation("division", scanner, calculator::divide);
                break;
            case "^":
                performPowerOperation(scanner);
                break;
            case "area":
                performAreaOperation(scanner);
                break;
            case "even":
                performEvenCheck(scanner);
                break;
            default:
                System.out.println("Unknown operation: " + operation);
        }
    }
    
    private static void performBinaryOperation(String operationName, Scanner scanner, BinaryOperation op) {
        System.out.print("Enter first number: ");
        double a = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter second number: ");
        double b = Double.parseDouble(scanner.nextLine());
        
        double result = op.apply(a, b);
        System.out.println("Result of " + operationName + ": " + result);
    }
    
    private static void performPowerOperation(Scanner scanner) {
        System.out.print("Enter base: ");
        double base = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter exponent: ");
        int exponent = Integer.parseInt(scanner.nextLine());
        
        double result = calculator.power(base, exponent);
        System.out.println("Result: " + result);
    }
    
    private static void performAreaOperation(Scanner scanner) {
        System.out.print("Enter radius: ");
        double radius = Double.parseDouble(scanner.nextLine());
        
        double area = calculator.calculateCircleArea(radius);
        System.out.println("Circle area: " + area);
    }
    
    private static void performEvenCheck(Scanner scanner) {
        System.out.print("Enter integer: ");
        int number = Integer.parseInt(scanner.nextLine());
        
        boolean isEven = calculator.isEven(number);
        System.out.println(number + " is " + (isEven ? "even" : "odd"));
    }
    
    @FunctionalInterface
    private interface BinaryOperation {
        double apply(double a, double b);
    }
}