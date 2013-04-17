package asteroids.model;

import asteroids.Vector;

public class Bullet extends SpaceObject {
	
	private final double density = 7.8 * Math.pow(10, 12);		// The mass density of the asteroid. (kg/km^3)
	private Ship source;										// The ship that fired the bullet.
	private int collisionCounter = 0;		
	public static int MAX_COLLISIONS_WITH_BOUNDARIES = 2;
	public static double SPEED = 250.0;
	

	
	public Bullet (Vector coordinates, Vector velocity, double radius, Ship source) {
		super (coordinates, velocity, radius);
		setMassWithDensity(getDensity());
		setSource(source);
	}
	
	public Bullet (Ship source) {
		
		if (source == null)
			return;
		setSource(source);
		
		Vector coordinates = source.getLocation();
		
		double x = (source.getRadius() + getRadius()) * Math.cos(source.getAngle());
		double y = (source.getRadius() + getRadius()) * Math.sin(source.getAngle());
		
		setLocation(coordinates.add(new Vector(x,y)));
		setVelocity(calcVelocity());
		setRadius(3.0);
		setMassWithDensity(getDensity());
	}
	
	
	public boolean isValidRadius (double radius)
	{
		return (!Double.isNaN(radius));
	}
	
	private Vector calcVelocity() {
		double x = SPEED * Math.cos(getSource().getAngle());
		double y = SPEED * Math.sin(getSource().getAngle());
		return new Vector(x,y);
	}
	
	public double getDensity() {
		return density;
	}
	
	public Ship getSource () {
		return source;
	}

	public void setSource(Ship source) {
		if (source == null)
			throw new IllegalArgumentException();
		this.source = source;
	}
	
	public int getCollisionCounter() {
		return collisionCounter;
	}

	public void increaseCollisionCounter() {
		collisionCounter++;
		if (getCollisionCounter() == MAX_COLLISIONS_WITH_BOUNDARIES)
			super.die();
	}
	
}
