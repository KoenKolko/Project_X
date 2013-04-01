package asteroids;

import be.kuleuven.cs.som.annotate.*;

public abstract class SpaceObject {
	
	private double radius;
	private Vector location, velocity;
	protected static final double C = 300000;						// Speed of light (km/s)
	
	protected SpaceObject(Vector coordinates, Vector velocity, double radius)
	{
		setLocation(coordinates);
		setVelocity(velocity);
		setRadius(radius);
	}
	
	/**
	 * 
	 * @return
	 * 		Returns the radius of the ship.
	 */
	@Basic
	public double getRadius() {
		return radius;
	}

	/**
	 * 
	 * @param radius	The new radius.
	 * @post 			The new radius is equal to radius.
	 * 					|(new this).getRadius() == radius
	 * @throws IllegalArgumentException
	 * 					The parameter radius is invalid.
	 * 					| !isValidRadius(radius)
	 */
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
	private boolean isValidRadius (double radius)
	{
		return (!Double.isNaN(radius) && radius >= 10);
	}

	public void setLocation(Vector newVector)
	{
		if (!newVector.isValidVector())
			throw new IllegalArgumentException();
		this.location = newVector;
	}

	public Vector getLocation()
	{
		return this.location;
	}

	public void setVelocity(Vector newVelocity)
	{
		if (!newVelocity.isValidVector())
			this.velocity = new Vector(0,0);
		else if (newVelocity.getNorm() > C) {
			double constant = Math.sqrt( Math.pow(C, 2) / Math.pow(newVelocity.getNorm(), 2) );
			this.velocity = new Vector(velocity.getX()*constant, velocity.getY()*constant);
		}
		else this.velocity = newVelocity;
	}
	
	public Vector getVelocity () 
	{
		return this.velocity;
	}
	
	public double getDistanceBetween (SpaceObject other) {
		return getLocation().getDistanceBetween(other.getLocation());
	}
	
	public boolean overlap(SpaceObject other){
		if (other == null) 																	// The other object doesn't exist.
			throw new IllegalArgumentException("Invalid ship!");
		return( (getRadius() + other.getRadius()) > this.getDistanceBetween(other)); 		// If the distance is smaller than the sum of the radii, the objects overlap.
	}	

	public void move (double time) {
		if (!isValidTime(time))
			throw new IllegalArgumentException();
		setLocation(getLocation().add(getVelocity().multiply(time)));
	}
	
	public boolean isValidTime (double time) { 
		return !(Double.isNaN(time) || time < 0);
	}
	
	public double getTimeToCollision(SpaceObject other){	
		if (other == null) 																// The other object doesn't exist.
			throw new IllegalArgumentException("Invalid ship!");
		
		Vector deltaR = other.getLocation().substract(getLocation());
		Vector deltaV = other.getVelocity().substract(getVelocity());
		double sigma = getRadius() + other.getRadius();
		double VR = deltaV.multiply(deltaR);
		double RR = deltaR.multiply(deltaR);
		double VV = deltaV.multiply(deltaV);
		double d = Math.pow(VR, 2) - VV*(RR - Math.pow(sigma, 2));
		
		if(this.overlap(other))
			return Double.POSITIVE_INFINITY; 					// The object overlap.
		else if(Double.compare(d,0) <= 0)
			return Double.POSITIVE_INFINITY; 					// The object will not collide.
		else if(Double.compare(VR,0) >=0)
			return Double.POSITIVE_INFINITY;		
		else
			return -((VR + Math.sqrt(d)) / VV); 				// Calculate the time to collision.
	}
	
	public double[] getCollisionPosition(SpaceObject other)
	{
		if (other == null) 									// The other object does not exist.
			throw new IllegalArgumentException("Invalid ship!");
		
		double timeToCollision = this.getTimeToCollision(other);
		
		if(timeToCollision != Double.POSITIVE_INFINITY){			
			Vector locationThis = getLocation().add(getVelocity().multiply(timeToCollision));
			Vector locationOther = other.getLocation().add(other.getVelocity().multiply(timeToCollision));
			double sumRadii = this.getRadius() + other.getRadius();
			locationThis.multiply(other.getRadius());
			locationOther.multiply(this.getRadius());
			locationThis.add(locationOther);
			locationThis.multiply(1/sumRadii);
			return new double[] {locationThis.getX(), locationThis.getY() };
		}
		else return null;
	}
	

}
