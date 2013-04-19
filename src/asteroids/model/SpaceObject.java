package asteroids.model;

import asteroids.Util;
import asteroids.Vector;
import be.kuleuven.cs.som.annotate.*;

public abstract class SpaceObject {

	protected double radius, mass; 									// The mass of the ship (kg)
	protected Vector location, velocity;
	protected World world;
	protected static double C = 300000;						// Speed of light (km/s)

	/**
	 * 
	 * @param coordinates
	 * @param velocity
	 * @param radius
	 * @param mass
	 * 
	 * @post
	 * 		|location in subclasses.
	 * 		|velocity in subclasses.
	 * 		|radius in subclasses.
	 * 		|mass in subclasses.	 * 
	 */
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
	 * Checks the radius of the spaceObject.
	 * 
	 * @param radius	The new radius of this spaceObject.	
	 * @return	
	 * 		Returns if the radius is valid.
	 * 		| !Double.isNaN(radius) || radius >= 0
	 */
	public boolean isValidRadius (double radius)
	{
		if (Double.isNaN(radius) || radius <= 0)
			return false;
		return true;
	}
	/**
	 * 
	 * @return location
	 */
	public Vector getLocation()
	{
		return this.location;
	}
	/**
	 * 
	 * @param newVector
	 * 
	 * @post 
	 * 		|(new this).getLocation = newVector
	 * @throws IllegalArgumentException
	 * 		if !isValidLocation(newVector)
	 * 		
	 */
	public void setLocation(Vector newVector)
	{
		if (!isValidLocation(newVector))
			throw new IllegalArgumentException();
		this.location = newVector;
	}

	private boolean isValidLocation(Vector newLocation) {
		if (newLocation == null)
			return false;
		return true;
	}

	/**
	 * 
	 * @return velocity
	 */
	public Vector getVelocity () 
	{
		return this.velocity;
	}

	//Total
	/**
	 * 
	 * @param newVelocity The new velocity
	 * 
	 * @post The new velocity will not be higher than C
	 * 
	 * @effect
	 * 		|(new this).getVelocity == newVelocity
	 */
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
		if (newVelocity == null)
			return false;
		return true;
	}

	// Defensively
	/**
	 * 
	 * @param other The spaceObject to get the distance to.
	 * @return getLocation().getDistanceBetween(other.getLocation()
	 * @throws IllegalArgumentException
	 * 		|if(other == null)
	 */
	public double getDistanceBetween (SpaceObject other) {
		if (other == null) 																				// The other object doesn't exist.
			throw new IllegalArgumentException();
		return getLocation().getDistanceBetween(other.getLocation());
	}

	// Defensively
	/**
	 * 
	 * @param other The spaceObject to check overlap with
	 * @return Double.compare(getRadius() + other.getRadius(), this.getDistanceBetween(other)) > 0
	 * @throws IllegalArgumentException
	 * 		|if(other == null)
	 */
	public boolean overlap(SpaceObject other){
		if (other == null) 																				// The other object doesn't exist.
			throw new IllegalArgumentException("Invalid ship!");
		return Double.compare(getRadius() + other.getRadius(), this.getDistanceBetween(other)) > 0;		// If the distance is smaller than the sum of the radii, the objects overlap.
	}	

	// Total
	/**
	 * 
	 * @param time	The time to move this object
	 * 
	 * @effect 
	 * 		|setLocation(getLocation().add(getVelocity().multiply(time)))
	 */
	public void move (double time) {
		if (!isValidMoveTime(time))
			time = 0.0;
		setLocation(getLocation().add(getVelocity().multiply(time)));
	}
	/**
	 * 
	 * @param time The time to check
	 * @return 	(Double.isNaN(time) || Double.compare(time, 0) < 0 || time == Double.POSITIVE_INFINITY || time == Double.NEGATIVE_INFINITY)
	 */
	public boolean isValidMoveTime (double time) {
		if (Double.isNaN(time) || Double.compare(time, 0) < 0 || time == Double.POSITIVE_INFINITY || time == Double.NEGATIVE_INFINITY)
			return false;
		return true;
	}

	/**
	 *
	 * @param other The other spaceObject to check.
	 * @throws IllegalArgumentException
	 * The other spaceObject doesn't exist.
	 * | other == null
	 * @return
	 * Returns the time before the spaceObjects collide.
	 * | if(this.overlap(other))
	 * | then return Double.POSITIVE_INFINITY
	 * | else if(Double.compare(d,0) <= 0)
	 * | then return Double.POSITIVE_INFINITY
	 * | else if(Double.compare(VR,0) >=0)
	 * | then return Double.POSITIVE_INFINITY
	 * | else
	 * | then return -( (VR+Math.sqrt(d)) / VV)
	 */
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

	/**
	 *
	 * @param other The spaceObject that could collide with the ship.
	 * @throws IllegalArgumentException
	 * The other spaceObject doesn't exist.
	 * | other == null
	 * @return
	 * Returns the position where the 2 spaceObjects will collide. It returns null if they won't.
	 * | if(timeToCollision != Double.POSITIVE_INFINITY)
	 * | double[] positions = new double[2]
	 * | positions[0] = (thisX * other.getRadius() + otherX * getRadius())/sumRadii
	 * | positions[1] = (thisY * other.getRadius() + otherY * getRadius())/sumRadii
	 * | return positions
	 * | else return null
	 */
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
	/**
	 * 
	 * @return mass
	 */
	public double getMass () {
		return this.mass;
	}

	/**
	 * 
	 * @param mass 		The mass to set.
	 * @post
	 * 			(new this).mass = mass
	 * @throws IllegalArgumentException
	 * 		|if(!isValidMass()
	 */
	public void setMass (double mass) {
		if (!isValidMass(mass))
			throw new IllegalArgumentException();
		this.mass = mass;
	}
	/**
	 * 
	 * @param density The new density
	 * @post
	 * 		|(new this.getMass() == (4/3) * Math.PI * Math.pow(getRadius(), 3) * density
	 */
	public void setMassWithDensity (double density) {
		double mass = (4/3) * Math.PI * Math.pow(getRadius(), 3) * density;
		setMass(mass);
	}
	
	private boolean isValidMass (double mass) {
		if (Double.isNaN(mass) || mass <= 0 || mass == Double.POSITIVE_INFINITY)
			return false;
		return true;
	}

	/**
	 * 
	 * @return world
	 */
	public World getWorld(){
		return this.world;
	}

	/**
	 * 
	 * @param world 	The world this object will be placed in.
	 * @post
	 * 		|(new this).getWorld = world
	 */
	public void setWorld(World world) {
		this.world = world;
	}
	/**
	 * @post
	 * 		|this.getWorld() == null
	 * 
	 */
	public void removeWorld() {
		setWorld(null);
	}

	/**
	 * 
	 * @param world		The world to check.
	 * @return !(getLocation().getX() - getRadius() < 0 || getLocation().getY() - getRadius() < 0 || getLocation().getX() + getRadius() > world.getWidth() || getLocation().getY() + getRadius() > world.getHeigth() 
	 */
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
	/**
	 * @effect
	 * 		|getWorld.removeObject(this)
	 */
	public void die () {
		if (getWorld() == null)
			return;
		getWorld().removeObject(this);
	}

}
