package asteroids.model;

import asteroids.Util;
import asteroids.Vector;
import be.kuleuven.cs.som.annotate.*;

public abstract class SpaceObject {

	protected double radius, mass; 									// The mass of the ship (kg)
	protected Vector location, velocity;
	protected World world;
	protected static double C = 300000;						// Speed of light (km/s)

	protected SpaceObject(Vector coordinates, Vector velocity, double radius, double mass)
	{
		setLocation(coordinates);
		setVelocity(velocity);
		setRadius(radius);
		setMass(mass);
	}
	
	protected SpaceObject(Vector coordinates, Vector velocity, double radius)
	{
		this(coordinates, velocity, radius, 1.0);
	}
	
	protected SpaceObject() {}

	/**
	 * 
	 * @return
	 * 		Returns the radius of the SpaceObject.
	 */
	@Basic
	public double getRadius() {
		return radius;
	}

	/**
	 * 
	 * @param radius	The new radius.+
	 * @post 			The new radius is equal to radius.
	 * 					|(new this).getRadius() == radius
	 * @throws IllegalArgumentException
	 * 					The parameter radius is invalid.
	 * 					| !isValidRadius(radius)
	 */
	// Defensively
	public void setRadius(double radius) throws IllegalArgumentException {
		if (!isValidRadius(radius))
			throw new IllegalArgumentException("Invalid radius!");
		this.radius = radius;
	}

	/**
	 * Checks the radius of the ship.
	 * 
	 * @param radius	The new radius of this ship.	
	 * @return	
	 * 		Returns if the radius is valid.
	 * 		| !Double.isNaN(radius) || radius >= 10
	 */
	public boolean isValidRadius (double radius)
	{
		if (Double.isNaN(radius) || radius <= 0)
			return false;
		return true;
	}

	public Vector getLocation()
	{
		return this.location;
	}

	//Defensively
	public void setLocation(Vector newVector)
	{
		if (!isValidLocation(newVector))
			throw new IllegalArgumentException();
		this.location = newVector;
	}

	private boolean isValidLocation(Vector newLocation) {
		if (!newLocation.isValidVector())
			return false;
		return true;
	}

	public Vector getVelocity () 
	{
		return this.velocity;
	}
	
	//Total
	public void setVelocity(Vector newVelocity)
	{
		if (!isValidVelocity(newVelocity))
			this.velocity = new Vector(0,0);
		
		else if (newVelocity.getNorm() > C) 
		{
			double constant = C / newVelocity.getNorm();
			this.velocity = newVelocity.multiply(constant);
		}
		
		else this.velocity = newVelocity;
	}
	
	private boolean isValidVelocity (Vector newVelocity) {
		if (!newVelocity.isValidVector())
			return false;
		return true;
	}

	// Defensively
	public double getDistanceBetween (SpaceObject other) {
		if (other == null) 																				// The other object doesn't exist.
			throw new IllegalArgumentException();
		return getLocation().getDistanceBetween(other.getLocation());
	}

	// Defensively
	public boolean overlap(SpaceObject other){
		if (other == null) 																				// The other object doesn't exist.
			throw new IllegalArgumentException("Invalid ship!");
		return Double.compare(getRadius() + other.getRadius(), this.getDistanceBetween(other)) > 0;		// If the distance is smaller than the sum of the radii, the objects overlap.
	}	

	// Total
	public void move (double time) {
		if (!isValidMoveTime(time))
			time = 0.0;
		setLocation(getLocation().add(getVelocity().multiply(time)));
	}

	public boolean isValidMoveTime (double time) {
		if (Double.isNaN(time) || Double.compare(time, 0) < 0 || time == Double.POSITIVE_INFINITY || time == Double.NEGATIVE_INFINITY)
			return false;
		return true;
	}

	// Defensively
	public double getTimeToCollision(SpaceObject other){	
		if (other == null) 																// The other object doesn't exist.
			throw new IllegalArgumentException("Invalid ship!");

		Vector deltaR = other.getLocation().subtract(getLocation());
		Vector deltaV = other.getVelocity().subtract(getVelocity());
		double sigma = getRadius() + other.getRadius();
		double VR = deltaV.multiply(deltaR);
		double RR = deltaR.multiply(deltaR);
		double VV = deltaV.multiply(deltaV);
		double d = Math.pow(VR, 2) - VV*(RR - Math.pow(sigma, 2));
		
		
		if(this.overlap(other))
			return Double.POSITIVE_INFINITY; 							// The object overlap.
		else if(Double.compare(d,0) <= 0)
			return Double.POSITIVE_INFINITY; 							// The object will not collide.
		else if(Double.compare(VR,0) >= 0)
			return Double.POSITIVE_INFINITY;		
		else
			return -((VR + Math.sqrt(d)) / VV) ; 						// Calculate the time to collision.
		
	}

	// Defensively
	public double[] getCollisionPosition(SpaceObject other)
	{
		if (other == null) 												// The other object does not exist.
			throw new IllegalArgumentException("Invalid ship!");

		double timeToCollision = this.getTimeToCollision(other);

		if(timeToCollision != Double.POSITIVE_INFINITY)
		{			
			double sumRadii 		= 	this.getRadius() + other.getRadius();
			Vector locationThis 	= 	getLocation().add(getVelocity().multiply(timeToCollision));
			Vector locationOther 	= 	other.getLocation().add(other.getVelocity().multiply(timeToCollision));
			locationOther 			= 	locationOther.multiply(this.getRadius());
			locationThis 			= 	locationThis.multiply(other.getRadius()).add(locationOther).multiply(1/sumRadii);
			return new double[] {locationThis.getX(), locationThis.getY() };
		}
		
		else return null;
	}


	public double collisionTimeWithBoundaries() {
		
		if (this.world == null)
			return Double.POSITIVE_INFINITY;
		if ( Util.fuzzyEquals(getVelocity().getNorm(), 0.0) )
			return Double.POSITIVE_INFINITY;
		
		double timeToY = collisionWithAxis(getLocation().getY(), getVelocity().getY(), getWorld().getHeigth());
		double timeToX = collisionWithAxis(getLocation().getX(), getVelocity().getX(), getWorld().getWidth());
		
		if (Double.compare(timeToY,timeToX) < 0)
			return timeToY;
		else return timeToX;
		
	}

	
	private double collisionWithAxis(double coord, double velocity, double axis) {
		
		if (Util.fuzzyEquals(velocity, 0))
			return Double.POSITIVE_INFINITY;
		if (velocity > 0)
			return (axis - coord - this.getRadius()) / velocity;
		else
			return (0 - coord + this.getRadius()) / velocity;
		
		
	}

	public double getMass () {
		return this.mass;
	}

	// Defensively
	public void setMass (double mass) {
		if (!isValidMass(mass))
			throw new IllegalArgumentException();
		this.mass = mass;
	}
	
	public void setMassWithDensity (double density) {
		double mass = (4/3) * Math.PI * Math.pow(getRadius(), 3) * density;
		setMass(mass);
	}

	private boolean isValidMass (double mass) {
		if (Double.isNaN(mass) || mass <= 0 || mass == Double.POSITIVE_INFINITY)
			return false;
		return true;
	}


	public World getWorld(){
		return this.world;
	}

	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public void removeWorld() {
		setWorld(null);
	}
	
	
	public boolean fitsInWorld(World world) {
		
		if (world == null)
			return false;
		
		double x 	= 	getLocation().getX();
		double y 	= 	getLocation().getY();
		double rad 	= 	getRadius();
		
		if (x-rad < 0 || y-rad < 0 || x+rad > world.getWidth() || y+rad > world.getHeigth() )
			return false;
		else return true;
		
	}

	// Total
	public void die () {
		if (getWorld() == null)
			return;
		getWorld().removeObject(this);
	}

}
