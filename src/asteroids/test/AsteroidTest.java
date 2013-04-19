package asteroids.test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.Util;
import asteroids.Vector;
import asteroids.model.Asteroid;
import asteroids.model.World;

public class AsteroidTest {
	Asteroid asteroid;
	Asteroid otherAsteroid;
	@Before
	public void setUp() throws Exception {
		asteroid 		=  	new Asteroid(new Vector(100,200), new Vector(10,-10), 20);
		otherAsteroid 	= 	new Asteroid(new Vector(100,100), new Vector(30,-15), 20);
	}

	@SuppressWarnings("unused")
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorInvalidRadiusZero() {
		Asteroid asteroid = new Asteroid(new Vector(100,200), new Vector(10,-10), 0);
	}
	
	@SuppressWarnings("unused")
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorInvalidRadiusNegative() {
		Asteroid asteroid = new Asteroid(new Vector(100,200), new Vector(10,-10), -10);
	}

	@Test
	public void testDeath(){
		Vector worldSize = new Vector(100,100);
		World world = new World(worldSize);
		Asteroid asteroid = new Asteroid(new Vector(40,40), new Vector(10,-10), 35);
		world.addObject(asteroid);
		assertEquals(world.getSpaceObjects().size(),1,Util.EPSILON);
		asteroid.die();
		assertEquals(world.getSpaceObjects().size(),2,Util.EPSILON);
	}
}
