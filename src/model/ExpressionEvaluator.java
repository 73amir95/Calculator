package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ExpressionEvaluator {

    public ExpressionEvaluator() {

    }

    // implementing "Shunting Yard Algorithm" for evaluating the expression with parentheses, and precedence
    public double evaluateExpression(String expression) {
        Queue<String> outputQueue = new LinkedList<>();
        Stack<String> operatorStack = new Stack<>();

        // Removing whitespace and converting the expression into tokens
        String[] tokens = expression.replaceAll("\\s+", "").split("(?<=[-+*/()^])|(?=[-+*/()^])");

        for (String token : tokens) {
            if (isNumeric(token)) {
                outputQueue.add(token);  // Adding numbers directly to the output queue
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() && isOperator(operatorStack.peek()) &&
                        getPrecedence(token) <= getPrecedence(operatorStack.peek())) {
                    outputQueue.add(operatorStack.pop());  // Pop operators from stack to output queue
                }
                operatorStack.push(token);  // Push the current operator to the stack
            } else if (token.equals("(")) {
                operatorStack.push(token);  // Push opening parentheses to the stack
            } else if (token.equals(")")) {
                // Pop operators from stack to output queue until matching opening parenthesis is found
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    outputQueue.add(operatorStack.pop());
                }
                if (!operatorStack.isEmpty() && operatorStack.peek().equals("(")) {
                    operatorStack.pop();  // Discard the opening parenthesis
                } else {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
            } else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
        }

        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek().equals("(")) {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            outputQueue.add(operatorStack.pop());
        }

        return evaluatePostfixExpression(outputQueue);
    }

    private double evaluatePostfixExpression(Queue<String> postfixExpression) {
        Stack<Double> operandStack = new Stack<>();

        while (!postfixExpression.isEmpty()) {
            String token = postfixExpression.remove();
            if (isNumeric(token)) {
                operandStack.push(Double.parseDouble(token));  // Push numbers to the operand stack
            } else if (isOperator(token)) {
                if (operandStack.size() < 2) {
                    throw new IllegalArgumentException("Invalid expression");
                }
                double operand2 = operandStack.pop();
                double operand1 = operandStack.pop();
                double result = applyOperator(token, operand1, operand2);
                operandStack.push(result);  // Push the result back to the stack
            } else {
                throw new IllegalArgumentException("Invalid token in postfix expression: " + token);
            }
        }

        if (operandStack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return operandStack.pop();
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String str) {

        return str.matches("[-+*/^]");
    }

    private int getPrecedence(String operator) {        // Evaluate the precedence of operators
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private double applyOperator(String operator, double operand1, double operand2) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    throw new ArithmeticException("DIVISION BY ZERO");
                }
            case "^":
                return Math.pow(operand1, operand2);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
