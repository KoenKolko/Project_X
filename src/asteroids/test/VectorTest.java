package asteroids.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.Util;
import asteroids.Vector;

public class VectorTest {

	Vector vector1, vector2, result;
	double x, y;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstructor() {
		x = 5; 
		y = 6;
		vector1 = new Vector(x,y);
		assertEquals(5, vector1.getX(), Util.EPSILON);
		assertEquals(6, vector1.getY(), Util.EPSILON);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorInfinite() {
		x = Double.POSITIVE_INFINITY; 
		y = Double.NEGATIVE_INFINITY;
		vector1 = new Vector(x,y);
	}
	
	@Test
	public void testAdd() {
		x = 5; 
		y = 2;
		vector1 = new Vector(x,y);
		vector2 = new Vector(5,2);
		result  = vector1.add(vector2);
		assertEquals(10, result.getX(), Util.EPSILON);
		assertEquals(4, result.getY(), Util.EPSILON);
	}
	
	@Test
	public void testAddNull() {
		x = 5; 
		y = 2;
		vector1 = new Vector(x,y);
		result  = vector1.add(null);
		assertEquals(5, result.getX(), Util.EPSILON);
		assertEquals(2, result.getY(), Util.EPSILON);
	}
	
	@Test
	public void testSubtract() {
		x = 5; 
		y = 2;
		vector1 = new Vector(x,y);
		vector2 = new Vector(5,2);
		result  = vector1.subtract(vector2);
		assertEquals(0, result.getX(), Util.EPSILON);
		assertEquals(0, result.getY(), Util.EPSILON);
	}
	
	@Test
	public void testSubtractNull() {
		x = 5; 
		y = 2;
		vector1 = new Vector(x,y);
		result  = vector1.subtract(null);
		assertEquals(5, result.getX(), Util.EPSILON);
		assertEquals(2, result.getY(), Util.EPSILON);
	}
	
	@Test
	public void testMultiply() {
		x = 5; 
		y = 2;
		vector1 = new Vector(x,y);
		vector2 = new Vector(5,2);
		double result  = vector1.multiply(vector2);
		assertEquals(29, result, Util.EPSILON);
		assertEquals(29, result, Util.EPSILON);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testMultiplyNull() {
		x = 5; 
		y = 2;
		vector1 = new Vector(x,y);
		vector1.multiply(null);
	}

	@Test
	public void testMultiplyConstant() {
		x = 5; 
		y = 2;
		vector1 = new Vector(x,y);
		double constant = 5;
		result  = vector1.multiply(constant);
		assertEquals(25, result.getX(), Util.EPSILON);
		assertEquals(10, result.getY(), Util.EPSILON);
	}
	
	@Test
	public void testMultiplyInfinite() {
		x = 5; 
		y = 2;
		vector1 = new Vector(x,y);
		double constant = Double.POSITIVE_INFINITY;
		result  = vector1.multiply(constant);
		assertEquals(5, result.getX(), Util.EPSILON);
		assertEquals(2, result.getY(), Util.EPSILON);
	}
	
	@Test
	public void testGetNorm() {
		x = 5; 
		y = 2;
		vector1 = new Vector(x,y);
		double norm = vector1.getNorm();
		assertEquals(Math.sqrt(29), norm, Util.EPSILON);
	}
	
	@Test
	public void testEquals() {
		x = 5; 
		y = 2;
		vector1 = new Vector(x,y);
		vector2 = new Vector(x,y);
		assertEquals(true, vector1.equals(vector2));
	}
	
	@Test
	public void testGetDistanceBetween() {
		x = 5; 
		y = 2;
		vector1 = new Vector(x,y);
		vector2 = new Vector(10,4);
		assertEquals(Math.sqrt(29), vector1.getDistanceBetween(vector2), Util.EPSILON);
	}
}
