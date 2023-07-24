# Calculator

## Overview

This project implements a feature-rich calculator with a graphical user interface (GUI). The calculator allows users to perform basic arithmetic operations, supports complex expressions using parentheses, and provides scientific functions such as sin, cos, tan, factorial, power, square root, logarithm (with various bases), and natural logarithm (ln).

## Classes

The project comprises the following packages:

1. **model**: consists of the following classes:
   - `Operations`: The logic for performing calculations, including basic arithmetic operations and scientific functions.
   - `ExpressionEvaluator`: This class implements the Shunting Yard algorithm for evaluating expressions, considering operator precedence and parentheses. It is prompted by the user by clicking on the parenthesis button.
   - `OperationsTest`: A JUnit test class to evaluate the performance of the functions in Operations.
   - `ExpressionEvaluatorTest`: A JUnit test class to evaluate the performance of the `evaluateExpression()` function, especially on extreme nested parentheses.

2. **controller**: consists of the following class:
   - `Controller`: The Controller class is the backbone of the calculator application, responsible for handling user interactions, performing calculations, and coordinating between the GUI (CalculatorGUI), mathematical operations (Operations), and expression evaluation (ExpressionEvaluator) components.

3. **viewer**: consists of the following class:
   - `CalculatorGUI`: This class is responsible for creating the GUI and handling the design and functionality of the buttons.

4. **calculator**: consists of the following class:
   - `DemoCalculator`: The executable class to run the calculator.

## Features

- Basic arithmetic operations: Addition, Subtraction, Multiplication, and Division.
- Parentheses support: Complex expressions can be entered using parentheses.
- Operator precedence: Multiplication and Division have higher precedence over Addition and Subtraction.
- Rational number precision: Results are displayed with a minimum of 8 decimal places.
- Constants support: Pi (π) and Euler's number (e) are available for use in calculations.
- Scientific functions: Sin, Cos, Tan, Factorial, Power, Square Root, Logarithm (with various bases), and Natural Logarithm (ln).

## Usage

The GUI calculator provides an intuitive interface for performing various calculations. Here are some examples of how to use it:

- Basic arithmetic: Simply click the numeric buttons and operator buttons to build the expression and click the equal button to get the result.
- Parentheses support: Click the "()" button to insert parentheses, and then enter the desired expression within the parentheses.
- Scientific functions: Click the respective buttons (sin, cos, tan, etc.) to apply the corresponding function to the entered value.
- Negative numbers: To input a negative number, click the numeric button for the number and then click the "(-)" button to make it negative.

## Limitations

- The calculator is not able to calculate Trigonometry and scientific functions of the expressions provided in "()".
- The factorial feature is not compatible to calculate over integer 25.
