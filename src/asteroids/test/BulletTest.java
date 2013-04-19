package asteroids.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import asteroids.Vector;
import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.Util;

public class BulletTest {

	Bullet bullet;
	Ship ship;
	
	@Before
	public void setUp() throws Exception {
		ship = new Ship(new Vector(1,1), new Vector(1,1), 1,1,1);
	}
	
	@Test
	public void testConstructor1() {
		bullet = new Bullet( new Vector(1,1), new Vector(1, 1), 1, ship);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorNull1() {
		ship = null;
		bullet = new Bullet( new Vector(1,1), new Vector(1, 1), 1, ship);
	}
	
	@Test
	public void testConstructor2() {
		bullet = new Bullet(ship);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorNull2() {
		ship = null;
		bullet = new Bullet(ship);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetSourceNull() {
		bullet = new Bullet(ship);
		bullet.setSource(null);
	}
	
	@Test
	public void testIncreaseCollisionCounter() {
		
		bullet = new Bullet(ship);
		bullet.increaseCollisionCounter();
		assertEquals(1, bullet.getCollisionCounter(),Util.EPSILON);
		
	}
	

}