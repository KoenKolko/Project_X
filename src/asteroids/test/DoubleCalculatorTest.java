package asteroids.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.DoubleCalculator;

public class DoubleCalculatorTest {
	DoubleCalculator calc;
@Before
public void setUp() throws Exception {
	calc = new DoubleCalculator();
}

@Test
public void testAddFail() {
	double maxDouble = Double.MAX_VALUE;	
	calc.addDoubles(maxDouble,5);
	}

}