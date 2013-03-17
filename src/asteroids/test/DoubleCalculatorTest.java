package asteroids.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.DoubleCalculator;
import asteroids.Util;

public class DoubleCalculatorTest {
	DoubleCalculator calc;
	@Before
	public void setUp() throws Exception {
		calc = new DoubleCalculator();
	}

	@Test 
	public void testAdd() {
		assertEquals(8.0, calc.addDoubles(-6, 14), Util.EPSILON);
	}
	
	@Test (expected=ArithmeticException.class)
	public void testAddFail() {
		calc.addDoubles(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
	}
	
	@Test 
	public void testMultiply() {
		assertEquals(-12.0, calc.multiplyDoubles(-6, 2), Util.EPSILON);
	}
	
	@Test (expected=ArithmeticException.class)
	public void testMultiplyFail() {
		calc.multiplyDoubles(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
	}

}