package asteroids.model;

import asteroids.Vector;

public class Bullet extends SpaceObject {
	
	private final double density = 7.8 * Math.pow(10, 12);		// The mass density of the asteroid. (kg/km^3)
	private Ship source;										// The ship that fired the bullet.
	

	public Bullet (Vector coordinates, Vector velocity, double radius, Ship source) {
		super (coordinates, velocity, radius);
		setMassWithDensity(getDensity());
		setSource(source);
	}
	
	public Bullet (Vector coordinates, Ship source) {
		super();
		double x = source.getRadius() * Math.cos(source.getAngle());
		double y = source.getRadius() * Math.sin(source.getAngle());
		setLocation(coordinates.add(new Vector(x,y)));
		setVelocity(calcVelocity());
		setRadius(3.0);
		setMassWithDensity(getDensity());
		setSource(source);
	}
	
	private Vector calcVelocity() {
		double speed = 250.0;
		double x = speed * Math.cos(getSource().getAngle());
		double y = speed * Math.sin(getSource().getAngle());
		return new Vector(x,y);
	}
	
	public double getDensity() {
		return density;
	}
	
	public void setSource(Ship source) {
		if (source == null)
			throw new IllegalArgumentException();
		this.source = source;
	}
	
	public Ship getSource () {
		return source;
	}

}