package asteroids.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.CollisionListener;
import asteroids.Vector;
import asteroids.model.Ship;
import asteroids.model.World;

public class WorldTest {

	World world;
	Vector dimensions;
	
	@Before
	public void setUp() throws Exception {
		dimensions 	= 	new Vector	(1024, 786);
		world 		= 	new World	(dimensions);
	}

	//
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorInvalidDimensionsInfinite() {
		dimensions = new Vector(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
		world = new World (dimensions);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorInvalidDimensionsNegative() {
		dimensions = new Vector(-1, -1);
		world = new World (dimensions);
	}
	
	@Test
	public void testAddObject() {
		Ship ship = new Ship ( new Vector (900, 780), new Vector (0,0), 1, 1, 1);
		world.addObject(ship);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testAddObjectNull() {
		Ship ship = null;
		world.addObject(ship);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testAddObjectDoesNotFitInWorld() {
		Ship ship = new Ship ( new Vector (1024, 786), new Vector (0,0), 1, 1, 1);
		world.addObject(ship);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testAddObjectDoesNotFitInWorldNegative() {
		Ship ship = new Ship ( new Vector (-1024, -786), new Vector (0,0), 1, 1, 1);
		world.addObject(ship);
	}
	
	@Test
	public void testRemoveObject() {
		Ship ship = new Ship ( new Vector (900, 780), new Vector (0,0), 1, 1, 1);
		world.addObject(ship);
		world.removeObject(ship);
		assertEquals(0, world.getObjects().size());
	}

	@Test (expected=IllegalArgumentException.class)
	public void testRemoveObjectNull() {
		world.removeObject(null);
	}
	
	@Test
	public void testIsValidEvolveTime() {
		boolean state = world.isValidEvolveTime(5);
		assertEquals(true, state);
	}
	
	@Test
	public void testIsValidEvolveTimeNegative() {
		boolean state = world.isValidEvolveTime(-1);
		assertEquals(false, state);
	}
	
}
