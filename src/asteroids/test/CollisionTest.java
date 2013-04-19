package asteroids.test;

import static org.junit.Assert.*;
import org.junit.Test;
import asteroids.Collision;
import asteroids.Util;
import asteroids.Vector;
import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;

public class CollisionTest {

	Ship ship;
	Asteroid asteroid;
	Collision collision;

	@Test
	public void testConstructor() {
		double time	= 5.0;
		ship 		= new Ship(new Vector(1,1), new Vector(1,1), 1,1,1);
		asteroid 	= new Asteroid(new Vector(1,1), new Vector(1,1), 1);
		collision 	= new Collision (ship, asteroid, time);
	}
	
	@Test
	public void testConstructorNull() {
		double time	= 5.0;
		collision 	= new Collision (null, null, time);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorNegativeTime() {
		double time	= -5.0;
		ship 		= new Ship(new Vector(1,1), new Vector(1,1), 1,1,1);
		asteroid 	= new Asteroid(new Vector(1,1), new Vector(1,1), 1);
		collision 	= new Collision (ship, asteroid, time);
	}
	
	@Test
	public void testResolveCollisionBetweenTwoEntities() {
					ship 		= new Ship		(new Vector(1,1), new Vector(1,1), 1,1,1);
		World 		world		= new World		(new Vector(100,100));
		Bullet 		bullet		= new Bullet 	(new Vector(20,50), new Vector(5,0), 1, ship);
		Asteroid 	asteroid	= new Asteroid 	(new Vector(80,50), new Vector(-5,0), 	1);
		
		world.addObject(bullet);
		world.addObject(asteroid);
		
		collision 		= new Collision (bullet, asteroid, 0.0);
		collision.resolve();
		
		assertEquals(true, world.getObjects().size()==0);
	}
	
	@Test
	public void testResolveCollisionWithBoundary() {
					ship 		= new Ship		(new Vector(1,1), new Vector(1,1), 1,1,1);
		World 		world		= new World		(new Vector(100,100));
		Bullet 		bullet		= new Bullet 	(new Vector(99,50), new Vector(5,0), 1, ship);
		
		world.addObject(bullet);
			
		collision 		= new Collision (bullet, null, 0.0);
		collision.resolve();
		
		assertEquals(-5, bullet.getVelocity().getX(), Util.EPSILON);
	}

}
