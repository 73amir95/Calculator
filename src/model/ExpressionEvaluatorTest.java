package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ExpressionEvaluatorTest {

    private ExpressionEvaluator evaluator;

    @Before
    public void setUp() {
        evaluator = new ExpressionEvaluator();
    }

    @Test
    public void testBasicArithmetic() {
        assertEquals(10.0, evaluator.evaluateExpression("5+5"), 0.000001);
        assertEquals(5.0, evaluator.evaluateExpression("10-5"), 0.000001);
        assertEquals(25.0, evaluator.evaluateExpression("5*5"), 0.000001);
        assertEquals(2.0, evaluator.evaluateExpression("10/5"), 0.000001);
    }

    @Test
    public void testOperatorPrecedence() {
        assertEquals(15.0, evaluator.evaluateExpression("5+5*2"), 0.000001);
        assertEquals(0.0, evaluator.evaluateExpression("10-20/2"), 0.000001);
        assertEquals(20.0, evaluator.evaluateExpression("(5+5)*2"), 0.000001);
    }

    @Test
    public void testPrecedenceWithNestedParentheses() {
        assertEquals(10.0, evaluator.evaluateExpression("(2 + 3) * (4 / 2)"), 0.000001);
        assertEquals(4.0, evaluator.evaluateExpression("(2 * 3) - (4 / 2)"), 0.000001);
        assertEquals(625.0, evaluator.evaluateExpression("(2 + 3) ^ (2 * 2)"), 0.000001);
        assertEquals(10.0, evaluator.evaluateExpression("(((2 + 3) * 4) / 2)"), 0.000001);
        assertEquals(-1.0, evaluator.evaluateExpression("(1 + (2 * (3 + (4 * (5 - 6)))))"), 0.000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMismatchedParentheses() {
        evaluator.evaluateExpression("(5+5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidExpression() {
        evaluator.evaluateExpression("5+&");
    }
}
