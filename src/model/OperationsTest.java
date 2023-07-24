package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class OperationsTest {

    private Operations operations;

    @Before
    public void setUp() {
        operations = new Operations();
    }

    @Test
    public void testSin() {
        assertEquals(0.0, operations.sin(0.0), 0.000001);
        assertEquals(1.0, operations.sin(Math.PI / 2), 0.000001);
        assertEquals(0.70710678, operations.sin(Math.PI / 4), 0.000001);
    }

    @Test
    public void testCos() {
        assertEquals(1.0, operations.cos(0.0), 0.000001);
        assertEquals(0.0, operations.cos(Math.PI / 2), 0.000001);
        assertEquals(0.70710678, operations.cos(Math.PI / 4), 0.000001);
    }

    @Test
    public void testTan() {
        assertEquals(0.0, operations.tan(0.0), 0.000001);
        assertEquals(1.0, operations.tan(Math.PI / 4), 0.000001);
        assertEquals(1.633123935319537E16, operations.tan(Math.PI / 2), 0.000001);
    }

    @Test
    public void testSquare() {
        assertEquals(25.0, operations.square(5.0), 0.000001);
        assertEquals(144.0, operations.square(12.0), 0.000001);
    }

    @Test
    public void testSqrt() {
        assertEquals(5.0, operations.sqrt(25.0), 0.000001);
        assertEquals(4.0, operations.sqrt(16.0), 0.000001);
    }

    @Test
    public void testFactorial() {
        assertEquals(1, operations.factorial(0));
        assertEquals(1, operations.factorial(1));
        assertEquals(120, operations.factorial(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSqrtWithNegativeValue() {
        operations.sqrt(-5.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialWithNegativeValue() {
        operations.factorial(-5);
    }

    @Test
    public void testLn() {
        assertEquals(0.0, operations.ln(1.0), 0.000001);
        assertEquals(1.60943791, operations.ln(5.0), 0.000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLnWithNonPositiveValue() {
        operations.ln(-5.0);
    }

    @Test
    public void testLog() {
        assertEquals(0.0, operations.log(1.0, 10.0), 0.000001);
        assertEquals(2.0, operations.log(100.0, 10.0), 0.000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLogWithNonPositiveBase() {
        operations.log(5.0, -2.0);
    }
}

