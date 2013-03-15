package asteroids.test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.IShip;
import asteroids.Ship;
import asteroids.Util;

public class ShipTest {
	Ship ship;
	Ship otherShip;
	@Before
	public void setUp() throws Exception {
		ship =  new Ship(100, 200, 10, -10, 20, -Math.PI);
		otherShip = new Ship(100, 100, 30, -15, 20, 0);
	}


	@Test
	public void moveTestNormal() {
		Double time = 1.0;
		ship.move(time);
		assertEquals(110.0, ship.getX(), Util.EPSILON);
		assertEquals(190.0, ship.getY(), Util.EPSILON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void moveTestNegativeTime() {
		Double negTime = -1.0;
		ship.move(negTime);
	}
	
	@Test
	public void turnTestNormal(){
		double angle = Math.PI;
		ship.turn(angle);
		assertEquals(0.0,ship.getAngle(),Util.EPSILON);
	}
	
	@Test
	public void thrustTestNormal(){
		double amount = 1;
		ship.thrust(amount);
		assertEquals(9,ship.getXVelocity(),Util.EPSILON);
		assertEquals(-10,ship.getYVelocity(),Util.EPSILON);
		
	}
	@Test
	public void testGetXSetX(){
		ship.setX(5.0);
		assertEquals(5.0,ship.getX(),Util.EPSILON);
	}
	@Test
	public void testGetYSetY(){
		ship.setY(2.0);
		assertEquals(2.0,ship.getY(),Util.EPSILON);
	}
	
	@Test
	public void testGetXVelocitySetXVelocity(){
		ship.setXVelocity(5.0);
		assertEquals(5.0,ship.getXVelocity(),Util.EPSILON);
	}
	
	@Test
	public void testGetYVelocitySetYVelocity(){
		ship.setYVelocity(2.0);
		assertEquals(2.0,ship.getYVelocity(),Util.EPSILON);
	}
	
	@Test
	public void testGetAngleSetAngle(){
		ship.setAngle(Math.PI);
		assertEquals(Math.PI,ship.getAngle(),Util.EPSILON);
	}
	
	@Test
	public void testGetRadiusSetRadius(){
		ship.setRadius(20.0);
		assertEquals(20.0,ship.getRadius(),Util.EPSILON);
	}
	
	@Test
	public void testGetDistanceBetween(){
		assertEquals(100.0,ship.getDistanceBetween(otherShip),Util.EPSILON);
	}
	
	@Test
	public void testNonOverlap(){
		assertEquals(false,ship.overlap(otherShip));
	}
	
	@Test
	public void testOverlap(){
		Ship overlappingShip = new Ship(105, 190, 10, -10, 20, -Math.PI);
		assertEquals(true,ship.overlap(overlappingShip));
	}
	
	@Test
	public void testSelfOverlap(){
		assertEquals(true,ship.overlap(ship));
	}
	
	@Test
	public void testTimeToNoCollision(){
		assertEquals(Double.POSITIVE_INFINITY , ship.getTimeToCollision(otherShip), Util.EPSILON);
		
	}
	
	@Test
	public void testTimeToCollision(){
		ship =  new Ship(100, 200, -10, 0, 20, -Math.PI);
		Ship shipToCollide =  new Ship(0, 200, 10, 0, 20, -Math.PI);
		assertEquals(3.0, ship.getTimeToCollision(shipToCollide),Util.EPSILON);
	}
	
	@Test
	public void testGetCollisionPosition() {
		ship =  new Ship(100, 200, -10, 0, 20, -Math.PI);
		Ship shipToCollide =  new Ship(0, 200, 10, 0, 20, -Math.PI);
		assertEquals(70.0, ship.getCollisionPosition(shipToCollide)[0],Util.EPSILON);
		assertEquals(200, ship.getCollisionPosition(shipToCollide)[1],Util.EPSILON);
	}
	
	
}