package asteroids.model;

import java.util.Random;

import asteroids.Vector;

public class Asteroid extends SpaceObject {

	private final double density = 2.65 * Math.pow(10, 12);		// The mass density of the asteroid. (kg/km^3)
	private Random random;


	public Asteroid (Vector coordinates, Vector velocity, double radius, Random random) {
		super (coordinates, velocity, radius);
		setMassWithDensity(getDensity());
		setRandom(random);
	}

	public Asteroid (Vector coordinates, Vector velocity, double radius) {
		this (coordinates, velocity, radius, new Random());
	}

	public double getDensity() {
		return density;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public void die () {
		if (getWorld() == null || getRadius() < 30)
		{
			super.die();
			return;
		}

		double radius = getRadius()/2;

		// Create baby 1
		double angle = random.nextDouble();
		Vector velocity1 = new Vector(1.5*getVelocity().getNorm() * Math.cos(angle) , 1.5*getVelocity().getNorm() * Math.sin(angle));
		Vector position = new Vector( getLocation().getX()+radius, getLocation().getY());
		Asteroid baby1 = new Asteroid(position, velocity1, radius);

		// Create baby2
		Vector velocity2 = velocity1.multiply(-1);
		position = new Vector( getLocation().getX()-radius, getLocation().getY());
		Asteroid baby2 = new Asteroid(position, velocity2, radius);

		getWorld().addObject(baby1);
		getWorld().addObject(baby2);
		getWorld().removeObject(this);
	}
}
