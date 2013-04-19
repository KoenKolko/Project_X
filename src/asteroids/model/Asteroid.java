package asteroids.model;

import java.util.Random;

import asteroids.Vector;

public class Asteroid extends SpaceObject {

	private final double density = 2.65 * Math.pow(10, 12);		// The mass density of the asteroid. (kg/km^3)
	private Random random;

	/**
	 * Creates a new ship with the given parameters.
	 * 
	 * @param coordinates		Position of the asteroid (km,km)
	 * @param velocity			Velocity of the asteroid (km/s)
	 * @param radius			Radius of the asteroid (km)
	 * @param random			A random generator. 
	 * @post	The coordinates of the asteroid are set to the parameter coordinates.
	 * 			| (new this).getCoordinates() == coordinates
	 * @post 	The velocity of the asteroid is set to the parameter velocity.
	 * 			| (new this).getVelocity() == velocity
	 * @post	The radius of the asteroid is set to the parameter radius.
	 * 			| (new this).getRadius() == radius
	 * @post	The mass of the asteroid is calculated and set
	 * 			| setMassWithDensity(getDensity())
	 */
	public Asteroid (Vector coordinates, Vector velocity, double radius, Random random) {
		super (coordinates, velocity, radius);
		setMassWithDensity(getDensity());
		setRandom(random);
	}
	/**
	 * @above
	 */
	public Asteroid (Vector coordinates, Vector velocity, double radius) {
		this (coordinates, velocity, radius, new Random());
	}

	/**
	 * @return density
	 */	
	public double getDensity() {
		return density;
	}
	/**
	 * @return random
	 */
	public Random getRandom() {
		return random;
	}
	/**
	 * 
	 * @param random 		The Random object used in later calculations.
	 * 
	 * @post
	 * 			|(new this).random = random
	 */
	public void setRandom(Random random) {
		this.random = random;
	}
	
	/**
	 * Handles the death of an asteroid.
	 * 
	 * @post
	 * 		| if ((getWorld() == null || getRadius < 30))
	 * 		|	then 	super.die
	 * 		|			return
	 * 		| else
	 * 		|	then	double radius = getRadius()/2;
	 * 		|			double angle = random.nextDouble()
	 * 		|			Vector velocity1 = new Vector(1.5*getVelocity().getNorm() * Math.cos(angle) , 1.5*getVelocity().getNorm() * Math.sin(angle))
	 *		|			Vector position1 = new Vector( getLocation().getX()+radius, getLocation().getY())
	 * 		|			Vector velocity2 = velocity1.multiply(-1)
	 *		|			Vector position2 = new Vector( getLocation().getX()-radius, getLocation().getY())
	 * 		|			world.removeObject(this)
	 * 		|			world.addObject(new Asteroid(position, velocity1, radius))
	 * 		|			world.addObject(new Asteroid(position, velocity2, radius))
	 */
	public void die () {
		World world = getWorld();
		
		if (world == null || getRadius() < 30)
		{
			super.die();
			return;
		}

		double radius = getRadius()/2;

		// Create baby 1
		double angle = random.nextDouble();
		Vector velocity1 = new Vector(1.5*getVelocity().getNorm() * Math.cos(angle) , 1.5*getVelocity().getNorm() * Math.sin(angle));
		Vector position1 = new Vector( getLocation().getX()+radius, getLocation().getY());
		Asteroid baby1 = new Asteroid(position1, velocity1, radius);

		// Create baby2
		Vector velocity2 = velocity1.multiply(-1);
		Vector position2 = new Vector( getLocation().getX()-radius, getLocation().getY());
		Asteroid baby2 = new Asteroid(position2, velocity2, radius);
		
		world.removeObject(this);
		world.addObject(baby1);
		world.addObject(baby2);
	}
}
