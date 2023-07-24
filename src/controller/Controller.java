package controller;

import model.ExpressionEvaluator;
import model.Operations;
import viewer.CalculatorGUI;

import java.util.ArrayList;

public class Controller {

    private Operations oper = new Operations();
    private ExpressionEvaluator eval = new ExpressionEvaluator();
    private final double PI = Math.PI;
    private final double EULER = Math.E;
    private ArrayList<Double> operands = new ArrayList();       // Stores all the numbers in an ArrayList in order to have multiple entries to evaluate sequence of expressions
    private double operand;
    private double result;
    private String operator;
    private boolean isOperatorClicked = false;

    public String calculateOperator(String expression) throws ArithmeticException {     // Calculating the arithmetic operations provided by the user in GUI calculator
        operands.clear();
        String[] parts = expression.split("[()*/+-]");
        for (String part : parts) {
            if (!part.trim().isEmpty()) {
                operands.add(Double.parseDouble(part));
            }
        }

        switch (operator) {
            case "+" -> {
                result = 0;
                for (double num : operands) {
                    result += num;
                }
                return String.format("%.8f", result);
            }
            case "-" -> {
                result = operands.get(0);
                for (int i = 1; i < operands.size(); i++) {
                    result -= operands.get(i);
                }
                return String.format("%.8f", result);
            }
            case "*" -> {
                result = 1;
                for (double num : operands) {
                    result *= num;
                }
                return String.format("%.8f", result);
            }
            case "/" -> {
                result = operands.get(0);
                for (int i = 1; i < operands.size(); i++) {
                    if (operands.get(i) != 0) {
                        result /= operands.get(i);
                    } else {
                        CalculatorGUI.setText("DIVISION BY ZERO");
                        throw new ArithmeticException("DIVISION BY ZERO");
                    }
                }
                return String.format("%.8f", result);
            }
        }
        return String.format("%.8f", result);
    }

    /*
    The purpose of performedOperation() is to avoid clearing the text field and operating the numbers
    sequentially by calculating the intermediate result. It only can store two numbers and one operator
    in the text field.
    If isOperatorClicked is false, it means the user is starting a new calculation and method should
    store the numeric value entered by the user and the new operator in operand and operator.
    if isOperatorClicked is true, it means the user has entered a numeric value, clicked an operator,
    clicked another numeric value, and clicked a new operator without pressing the equal button.
    In this case, the method calculates the intermediate result.
     */
    public void performOperation(String newOperator) {
        if (!isOperatorClicked) {
            operand = Double.parseDouble(CalculatorGUI.getText());
            operator = newOperator;
            CalculatorGUI.setText(String.valueOf(operand).concat(operator));
            isOperatorClicked = true;
        } else {
            try {
                String expression = CalculatorGUI.getText();
                String resultText = calculateOperator(expression);
                CalculatorGUI.setText(resultText.concat(newOperator));
                operand = result;
                operator = newOperator;
            } catch (ArithmeticException ex) {
                CalculatorGUI.setText("DIVISION BY ZERO");
            }
        }
    }

    public String getSin(String num) {                      // Calculates the sine of a number and returns it as a String and formats with 8 decimals
        return String.format("%.8f", oper.sin(Double.parseDouble(num)));
    }
    public String getCos(String num) {                      // Calculates the cosine of a number and returns it as a String and formats with 8 decimals
        return String.format("%.8f", oper.cos(Double.parseDouble(num)));
    }
    public String getTan(String num) {                      // Calculates the tangent of a number and returns it as a String and formats with 8 decimals
        return String.format("%.8f", oper.tan(Double.parseDouble(num)));
    }
    public String getSqrt(String num) {                      // Calculates the square root of a number and returns it as a String and formats with 8 decimals
        if (Double.parseDouble(num) < 0) {
            CalculatorGUI.setText("Cannot take negative value for square root");
            throw new IllegalArgumentException("Cannot take negative value for square root");
        } else {
            return String.format("%.8f", oper.sqrt(Double.parseDouble(num)));
        }
    }
    public String getSquare(String num) {                      // Calculates the square of a number and returns it as a String and formats with 8 decimals
        return String.format("%.8f", oper.square(Double.parseDouble(num)));
    }
    public String getFac(String num) {                      // Calculates the factorial of a number and returns it as a String
        if (Integer.parseInt(num) < 0) {
            CalculatorGUI.setText("Cannot take negative value for factorial");
            throw new IllegalArgumentException("Cannot take negative value for factorial");
        } else if (Integer.parseInt(num) == 0) {
            return "1.00000000";
        } else {
            return String.valueOf(oper.factorial(Integer.parseInt(num)));
        }
    }
    public String getLog(String num, String base) {       // Calculates the logarithm of a number with different bases and returns it as a String and formats with 8 decimals
        if (Double.parseDouble(base) <= 0) {
            CalculatorGUI.setText("base of log cannot be zero or negative");
            throw new IllegalArgumentException("base of log cannot be zero or negative");
        } else {
            return String.format("%.8f",oper.log(Double.parseDouble(num),Double.parseDouble(base)));
        }
    }
    public String getLn(String num) {                   // Calculates the natural logarithm of a number and returns it as a String and formats with 8 decimals
        if (Double.parseDouble(num) <= 0) {
            CalculatorGUI.setText("Cannot accept 0 or negative value for ln");
            throw new IllegalArgumentException("Cannot accept 0 or negative value for ln");
        } else {
            return String.format("%.8f", oper.ln(Double.parseDouble(num)));
        }
    }
    public String evaluateExpression(String expression) {       // Evaluates the expression prompted by the user by clicking the parenthesis button
        return String.format("%.8f",eval.evaluateExpression(expression));
    }
    public void delete(String expression) {         // Handles the delete button of the calculator by removing digit by digit of the number
        CalculatorGUI.setText("");
        for (int i = 0; i < expression.length() - 1; i++) {
            CalculatorGUI.setText(CalculatorGUI.getText() + expression.charAt(i));
        }
    }
    public String negate(String expression) {       // Negates the number on the text field of the GUI calculator
        double temp = Double.parseDouble(expression);
        temp *= -1;
        return String.format("%.8f",temp);
    }
    public String getPI() {                        // Provides the Pi number
        return String.format("%.8f", PI);
    }
    public String getE() {                        // Provides the Euler number
        return String.format("%.8f", EULER);
    }
}
