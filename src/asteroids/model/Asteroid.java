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

	public void removeWorld(){
		getWorld().removeAsteroid(this);
	}
	
	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}
	
	public void die () {
		if (getWorld() == null || getRadius() < 30)
			super.die();
		
		double radius = getRadius()/2;
		
		// Create baby 1
		double angle = random.nextDouble();
		Vector velocity = new Vector(1.5*getVelocity().getNorm() * Math.cos(angle) , 1.5*getVelocity().getNorm() * Math.sin(angle));
		// Hier kan een fout zitten als de baby spawnt buiten de boundaries.
		Vector position = new Vector( getLocation().getX()+radius, getLocation().getY());
		Asteroid baby1 = new Asteroid(position, velocity, radius);
		
		// Create baby2
		velocity = getVelocity().multiply(-1.5);
		position = new Vector( getLocation().getX()-radius, getLocation().getY());
		Asteroid baby2 = new Asteroid(position, velocity, radius);
		
		
		getWorld().addAsteroid(baby1);
		getWorld().addAsteroid(baby2);
		getWorld().removeAsteroid(this);
	}
}
