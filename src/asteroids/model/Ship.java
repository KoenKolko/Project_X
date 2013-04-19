package asteroids.model;


import asteroids.Vector;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class for a ship describing its current state.
 * 
 * @author Yannick Horvat (1ste bachelor Informatica) & Koen Jacobs (1ste bachelor Informatica)
 * Repository: https://github.com/KoenKolko/Project_X.git
 * @Version 1.0
 *
 */

public class Ship extends SpaceObject {


	private double angle;									// The angle of the ship (radian)
	private boolean thruster;								// If thruster is on --> True, else --> False
	private final double THRUSTER_FORCE = 1.1E18;			// The force/s of the thruster.
	
	/**
	 * Creates a new ship with the given parameters.
	 * 
	 * @param coordinates	Position of the ship (km,km)
	 * @param velocity		Velocity of the ship (km/s)
	 * @param radius		Radius of the ship (km)
	 * @param angle			The angle of the ship (radian)
	 * @param mass			The mass of the ship (kg)
	 * @post	The coordinates are set to the parameter coordinates.
	 * 			| (new this).getCoordinates() == coordinates
	 * @post 	The velocity of the ship is set to the parameter velocity.
	 * 			| (new this).getXVelocity() == velocity
	 * @post	The radius of the ship is set to the parameter radius.
	 * 			| (new this).getRadius() == radius
	 * @post 	The angle the ship is facing is set to the parameter angle.
	 * 			| (new this).getAngle() == angle	
	 * @post 	The mass of the ship is set to the parameter weight.
	 * 			| (new this).getAngle() == angle
	 * @post 	The thruster of the ship is turned off
	 * 			| (new this).getThruster() == false 
	 */
	public Ship(Vector coordinates, Vector velocity, double radius, double angle, double mass)
	{		
		super(coordinates, velocity, radius, mass);
		this.setAngle(angle);	
		this.setThruster(false);

	}


	/**
	 * 
	 * @return
	 * 		Returns the angle the ship is faced to.
	 */
	@Basic
	public double getAngle() {
		return angle;
	}


	/**
	 * 
	 * @param angle 	The new angle.
	 * 
	 * @pre				The angle has to be valid.
	 * 					| isValidAngle(angle)
	 * 
	 * @post 			The new angle is angle.
	 * 					| if (angle > 2*Math.PI)
	 * 					| 	then (new this).getAngle() == angle%(2*Math.PI)
	 * 					| else if angle < -2*Math.PI
	 * 					| 	then (new this).getAngle() == angle%(-2*Math.PI)
	 */
	// Nominal
	public void setAngle(double angle) {
		assert isValidAngle(angle) : "Wrong angle";
		if (angle > 2*Math.PI)
			angle %= 2*Math.PI;
		else if ( angle < -2*Math.PI)
			angle %= -2*Math.PI;
		this.angle = angle;
	}


	/**
	 * 
	 * @param angle		The angle that has to be checked.
	 * @return
	 * 		Returns if the angle is valid.
	 * 		| !Double.isNaN(angle)
	 */
	public boolean isValidAngle (double angle) {
		if (Double.isNaN(angle) || angle == Double.POSITIVE_INFINITY || angle == Double.NEGATIVE_INFINITY)
			return false;
		return true;
	}


	/**
	 * Turns the ship by a given angle.
	 * 
	 * @param angle		The angle the ship will turns. (radians)
	 * 
	 * @pre		The angle is a number.
	 * 			| isValidAngle(angle)
	 * @post 	The angle is set to the old angle increased by the parameter angle.
	 * 			| (new this).getAngle() == (getAngle() + angle)%(2*Math.PI)
	 */
	// Nominal
	public void turn (double angle) {
		assert (isValidAngle(angle)) : "No valid argument!";
		setAngle(getAngle() + angle);
	}

	// Total
	/**
	 * @effect 
	 * 		move(time)
	 * @effect
	 * 		thrust(time)
	 */
	public void move (double time) {
		super.move(time);
		thrust(time);
	}
	
	/**
	 * 
	 * @param time	The time during which the velocity is increased.
	 * @post 	If the given time is invalid (<= 0 or NaN) or the thruster is off, then the velocity will remain unchanged.
	 * 			Else the x- and y-velocities are calculated and adjusted.
	 * 			| if (Double.isNaN(amount) || amount <= 0 || !getThruster)
	 * 			|	then return;
	 * 			| else
	 * 			|	double acceleration = ( (THRUSTER_FORCE*time)  /  getMass() )
	 *			|	double vXNew = getVelocity().getX() + acceleration * Math.cos(getAngle())
	 *			|	double vYNew = getVelocity().getY() + acceleration * Math.sin(getAngle())
	 *			|	Vector newVelocity = new Vector(vXNew, vYNew);
	 *@effect
	 *		setVelocity(newVelocity) 	
	 * 			 		
	 */
	// Total 
	public void thrust (double time) {
		if (!getThruster() || !isValidThrustTime(time))
			return;
		double acceleration = ( (THRUSTER_FORCE*time)  /  getMass() );
		double vXNew = getVelocity().getX() + acceleration * Math.cos(getAngle());		// the new x-velocity
		double vYNew = getVelocity().getY() + acceleration * Math.sin(getAngle());		// the new y-velocity
		Vector newVelocity = new Vector(vXNew, vYNew);
		setVelocity(newVelocity);
		
	}
	
	public boolean isValidThrustTime (double time) {
		return super.isValidMoveTime(time);
	}
	
	/**
	 * 
	 * @return whether the thruster is on
	 * 		| thruster
	 */
	public boolean getThruster () {
		return thruster;
	}

	/**
	 * 
	 * @param thruster 		The new state of the thruster.
	 * 
	 * @post	The thruster is set to its new state.
	 * 		|	this.thruster = thruster;
	 * 			
	 */
	public void setThruster (boolean thruster) {
		this.thruster = thruster;
	}
	/**
	 * A bullet is created from this ship and fired.
	 * Bullet bullet = new Bullet(this);
	 * @post The bullet is fired
	 * 		|	if ( bullet.fitsInWorld(getWorld()) )
			|	getWorld().addObject(bullet);
	 */
	public void fireBullet(){
		Bullet bullet = new Bullet(this);
		if ( bullet.fitsInWorld(getWorld()) )
			getWorld().addObject(bullet);
	}
}

