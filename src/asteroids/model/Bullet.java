package asteroids.model;

import asteroids.Vector;

public class Bullet extends SpaceObject {
	
	private static double 	DENSITY = 7.8E12;						// The mass density of the asteroid. (kg/km^3)
	private static int 		MAX_COLLISIONS_WITH_BOUNDARIES 	= 2;
	private static double 	SPEED = 250.0;
	private Ship source;											// The ship that fired the bullet.
	private int collisionCounter = 0;
	
	public Bullet (Vector coordinates, Vector velocity, double radius, Ship source) {
		super (coordinates, velocity, radius);
		setMassWithDensity(DENSITY);
		setSource(source);
	}
	
	// Special constructor for a fired bullet.
	/**
	 * 
	 * @param source		The source ship of this bullet.
	 * @post	The coordinates are set in accordance with the ship this bullet was fired from
	 * 			|	double x = (source.getRadius() + getRadius()) * Math.cos(source.getAngle())
	 * 			|	double y = (source.getRadius() + getRadius()) * Math.sin(source.getAngle())
	 * 			| 	(new this).getCoordinates() == ship.getCoordinates().add(new Vector(x,y)
	 *@post		The velocity of the bullet is set in accordance with the ship this bullet was fired from and the predetermined speed.
	 *			|	(new this).getVelocity() == calcVelocity()
	 *@post		The radius of the bullet is set to the predetermined radius.
	 *			|	(new this).getRadius() == 3.0
	 *@effect	The mass of the bullet is calculated and set.
	 *			|	setMassWithDensity	(DENSITY);
	 */
	public Bullet (Ship source) {
		
		setSource			(source);
		
		Vector coordinates = source.getLocation();
		
		double x = (source.getRadius() + getRadius()) * Math.cos(source.getAngle());
		double y = (source.getRadius() + getRadius()) * Math.sin(source.getAngle());
		
		
		setLocation			(coordinates.add(new Vector(x,y)));
		setVelocity			(calcVelocity());
		setRadius			(3.0);
		setMassWithDensity	(getDensity());
	}
	
	private Vector calcVelocity() {
		double x = SPEED * Math.cos(getSource().getAngle());
		double y = SPEED * Math.sin(getSource().getAngle());
		return new Vector(x,y);
	}
	/**
	 * 
	 * @return DENSITY
	 */
	public double getDensity() {
		return DENSITY;
	}
	/**
	 * 
	 * @return source
	 */
	public Ship getSource () {
		return source;
	}

	// Defensively
	/**
	 * 
	 * @param source		The source ship.
	 * @post
	 * 		|(new this).getSource = source
	 * @throws IllegalArgumentException
	 * 		| source == null
	 */
	public void setSource(Ship source) {
		if (source == null)
			throw new IllegalArgumentException();
		this.source = source;
	}
	/**
	 * 
	 * @return collisionCounter
	 */
	public int getCollisionCounter() {
		return collisionCounter;
	}
	/**
	 * @post
	 * 		|(new this).getCollisionCounter == getCollisionCounter + 1
	 * @post
	 * 		|if(getCollisionCounter() == MAX_COLLISIONS_WITH_BOUNDARIES)
	 * 		|then super.die
	 */
	public void increaseCollisionCounter() {
		collisionCounter++;
		if (getCollisionCounter() == MAX_COLLISIONS_WITH_BOUNDARIES)
			die();
	}
	
	public void die() {
		getSource().decreaseBulletsInWorldCounter();
		super.die();
	}
	
}
