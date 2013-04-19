package asteroids.test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.Util;
import asteroids.Vector;
import asteroids.model.Ship;

public class ShipTest {
	Ship ship;
	Ship otherShip;
	@Before
	public void setUp() throws Exception {
		ship 		=  	new Ship(new Vector(100,200), new Vector(10,-10), 20, -Math.PI, 4E15);
		otherShip 	= 	new Ship(new Vector(100,100), new Vector(30,-15), 20, 0, 4E15);
	}

	
	@SuppressWarnings("unused")
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorInvalidRadiusZero() {
		Ship testship = new Ship(new Vector(100,200), new Vector(10,-10), 0, -Math.PI, 4E15);
	}

	@SuppressWarnings("unused")
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorInvalidRadiusNegative() {
		Ship testship = new Ship(new Vector(100,200), new Vector(10,-10), -5, -Math.PI, 4E15);
	}

	@Test
	public void moveTest() {
		Double time = 1.0;
		ship.move(time);
		assertEquals(110.0, ship.getLocation().getX(), Util.EPSILON);
		assertEquals(190.0, ship.getLocation().getY(), Util.EPSILON);
	}
	@Test
	public void moveTestNaN(){
		Double time = Double.NaN;
		double shipSpeedOrig = ship.getVelocity().getNorm();
		ship.move(time);
		assertEquals(shipSpeedOrig,ship.getVelocity().getNorm(), Util.EPSILON);
	}
	@Test
	public void moveTestNegativeTime() {
		Double negTime = -1.0;
		double shipSpeedOrig = ship.getVelocity().getNorm();
		ship.move(negTime);
		assertEquals(shipSpeedOrig,ship.getVelocity().getNorm(), Util.EPSILON);
	}
	
	@Test
	public void moveTestZeroTime() {
		Double zeroTime = 0.0;
		ship.move(zeroTime);
	}
	
	@Test
	public void isValidTimeTest() {
		double time = 3.0;
		assertEquals(true, ship.isValidMoveTime(time));
	}
	
	@Test()
	public void isValidTimeNaN(){
		Double time = Double.NaN;
		assertEquals(false, ship.isValidMoveTime(time));
	}
	
	@Test
	public void isValidTimeBoundaryValues() {
		double time = -5.0;
		assertEquals(false, ship.isValidMoveTime(time));
		time = 0.0;
		assertEquals(true, ship.isValidMoveTime(time));
	}
	
	@Test
	public void turnTest(){
		double angle = Math.PI;
		ship.turn(angle);
		assertEquals(0.0,ship.getAngle(),Util.EPSILON);
		angle = 2*Math.PI;
		ship.turn(angle);
		assertEquals(2*Math.PI, ship.getAngle(), Util.EPSILON);
	}
				
	@Test
	public void thrustTestNormal(){
		double time = 1;
		ship.thrust(time);
		assertEquals(10,ship.getVelocity().getX(),Util.EPSILON);
		assertEquals(-10,ship.getVelocity().getY(),Util.EPSILON);
		
	}
	
	@Test
	public void thrustTestSpeedOfLight() {
		double time = 5E100;
		ship.setThruster(true);
		ship.thrust(time);
		assertEquals(300000.0, ship.getVelocity().getNorm(), Util.EPSILON);
	}
	
	@Test
	public void thrustTestNegativeTime() {
		double time = -500;
		ship.thrust(time);
		assertEquals(10.0, ship.getVelocity().getX(), Util.EPSILON);
		assertEquals(-10.0, ship.getVelocity().getY(), Util.EPSILON);
	}
	@Test
	public void thrustTestZero() {
		double amount = 0.0;
		ship.thrust(amount);
		assertEquals(10.0, ship.getVelocity().getX(), Util.EPSILON);
		assertEquals(-10.0, ship.getVelocity().getY(), Util.EPSILON);
	}
	@Test()
	public void thrustTestNaN(){
		double amount = Double.NaN;
		ship.thrust(amount);
		assertEquals(10.0, ship.getVelocity().getX(), Util.EPSILON);
		assertEquals(-10.0, ship.getVelocity().getY(), Util.EPSILON);
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
	
	@Test (expected=IllegalArgumentException.class) 
	public void testGetDistanceBetweenNullPointer() {
		otherShip = null;
		ship.getDistanceBetween(otherShip);
	}
	
	@Test 
	public void testGetDistanceBetweenNegativeX() {
		ship =  new Ship(new Vector(-100,200), new Vector(10,-10), 20, -Math.PI, 4E15);
		otherShip = new Ship(new Vector(-100,100), new Vector(30,-15), 20, 0, 4E15);
		assertEquals(100.0,ship.getDistanceBetween(otherShip),Util.EPSILON);
	}
	
	@Test 
	public void testGetDistanceBetweenNegativeY() {
		ship =  new Ship(new Vector(100,-200), new Vector(10,-10), 20, -Math.PI, 4E15);
		otherShip = new Ship(new Vector(100,-100), new Vector(30,-15), 20, 0, 4E15);
		assertEquals(100.0,ship.getDistanceBetween(otherShip),Util.EPSILON);
	}
	
	@Test 
	public void testGetDistanceBetweenNegativeXY() {
		ship =  new Ship(new Vector(-100,-200), new Vector(10,-10), 20, -Math.PI, 4E15);
		otherShip = new Ship(new Vector(-100,-100), new Vector(30,-15), 20, 0, 4E15);
		assertEquals(100.0,ship.getDistanceBetween(otherShip),Util.EPSILON);
	}
	
	@Test
	public void testOverlapFalse(){
		assertEquals(false,ship.overlap(otherShip));
	}
	
	@Test
	public void testOverlapTrue(){
		Ship overlappingShip = new Ship(new Vector(105,190), new Vector(10,-10), 20, -Math.PI, 4E15);
		assertEquals(true,ship.overlap(overlappingShip));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testOverlapNullPointer() {
		otherShip = null;
		ship.overlap(otherShip);
	}
	
	@Test
	public void testSelfOverlap(){
		assertEquals(true,ship.overlap(ship));
	}
	
	@Test
	public void testTimeToCollisionFalse(){
		assertEquals(Double.POSITIVE_INFINITY , ship.getTimeToCollision(otherShip), Util.EPSILON);
		
	}
	
	@Test
	public void testTimeToCollision(){
		ship =  new Ship(new Vector(100,200), new Vector(-10,0), 20, -Math.PI, 4E15);
		Ship shipToCollide =  new Ship(new Vector(0,200), new Vector(10,0), 20, -Math.PI, 4E15);
		assertEquals(3.0, ship.getTimeToCollision(shipToCollide),Util.EPSILON);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testTimeToCollisionNullPointer() {
		otherShip = null;
		ship.getTimeToCollision(otherShip);
	}
	
	@Test
	public void testGetCollisionPosition() {
		ship =  new Ship(new Vector(100,200), new Vector(-10,0), 20, -Math.PI, 4E15);
		Ship shipToCollide =  new Ship(new Vector(0,200), new Vector(10,0), 20, -Math.PI, 4E15);
		assertEquals(50.0, ship.getCollisionPosition(shipToCollide)[0],Util.EPSILON);
		assertEquals(200, ship.getCollisionPosition(shipToCollide)[1],Util.EPSILON);
	}
	
	@Test
	public void testGetCollisionPositionNoCollision() {
		assertEquals(null, ship.getCollisionPosition(otherShip));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testGetCollisionPositionNullPointer() {
		otherShip = null;
		ship.getCollisionPosition(otherShip);
	}
	
}