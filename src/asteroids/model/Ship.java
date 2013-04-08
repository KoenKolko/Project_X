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
	private double thrusterForce = 1.1 * Math.pow(10,18);	// The force/s of the thruster.
	
	/**
	 * Creates a new ship with the given parameters.
	 * 
	 * @param x				x-position of the ship (km)
	 * @param y				y-position of the ship (km)
	 * @param xVelocity		Velocity in x-direction (km/s)
	 * @param yVelocity		Velocity in y-direction (km/s)
	 * @param radius		Radius of the ship (km)
	 * @param angle			The angle of the ship (radian)
	 * 
	 * @post	The x-coordinate is set to the parameter x.
	 * 			| (new this).getX() == x
	 * @post 	The y-coordinate is set to the parameter y.
	 * 			| (new this).getY() == y
	 * @post 	The velocity on the x-axis is set to the parameter xVelocity.
	 * 			| (new this).getXVelocity() == xVelocity
	 * @post	The velocity on the y-axis is set to the parameter yVelocity.
	 * 			| (new this).getYVelocity() == yVelocity
	 * @post	The radius of the ship is set to the parameter radius.
	 * 			| (new this).getRadius() == radius
	 * @post 	The angle the ship is facing is set to the parameter angle.
	 * 			| (new this).getAngle() == angle	
	 * @post	The radius will be larger then 10
	 * 			| (new this).getRadius > 10
	 * 
	 */
	public Ship(Vector coordinates, Vector velocity, double radius, double angle, double mass)
	{		
		super(coordinates, velocity, radius);
		this.setAngle(angle);	
		this.setMass(mass);
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
	public void setAngle(double angle) {
		assert isValidAngle(angle) : "Wrong angle";
		if (angle > 2*Math.PI)
			angle %= 2*Math.PI;
		else if ( angle < -2*Math.PI)
			angle %= -2*Math.PI;
		this.angle = angle;
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
	public void turn (double angle) {
		assert (isValidAngle(angle)) : "No valid argument!";
		setAngle(getAngle() + angle);
	}

	/**
	 * 
	 * @param angle		The angle that has to be checked.
	 * @return
	 * 		Returns if the angle is valid.
	 * 		| !Double.isNaN(angle)
	 */
	public boolean isValidAngle (double angle) {
		return !Double.isNaN(angle);
	}


	/**
	 * 
	 * @param amount	The amount by which the velocity is increased.
	 * @post 	If the given amount is invalid (< 0 or NaN) or the amount is zero, then the velocity will remain unchanged.
	 * 			Else if the new velocity is greater than the speed of light (C), then the velocity in the x and y-axis will be adjusted
	 * 			so that the velocity equals the speed of light (C).
	 * 			Else, the new velocity is smaller or equal to speed of light (C), then the new x and y velocities are calculated 
	 * 			and adjusted.
	 * 			| if (Double.isNaN(amount) || amount <= 0)
	 * 			|	then return;
	 * 			| if (calcVelocity(vXNew, vYNew) > C)
	 * 			|	then (new this).calcVelocity(getXVelocity(), getYVelocity()) == C
	 *			| else 
	 *			| 	then (new this).getXVelocity() = vXNew && (new this).getYVelocity() = vYNew 
	 *			|	(new this).calcVelocity(getXVelocity(), getYVelocity()) <= C
	 * 			 		
	 */
	public void thrust (double amount) {
		if(!isValidThrust(amount))
			return;
		double vXNew = getVelocity().getX() + amount * Math.cos(getAngle());		// the new x-velocity
		double vYNew = getVelocity().getY() + amount * Math.sin(getAngle());		// the new y-velocity
		Vector newVelocity = new Vector(vXNew, vYNew);
		setVelocity(newVelocity);
		
	}

	private boolean isValidThrust(double amount) {
		if (Double.isNaN(amount) || amount <= 0)
			return false;
		return true;
	}
	
	
	
	public void setThruster (boolean thruster) {
		this.thruster = thruster;
	}
	
	public boolean getThruster () {
		return thruster;
	}
	
	public void setThrusterForce (double thrusterForce) {
		if(!isValidThrusterForce(thrusterForce)) 
			throw new IllegalArgumentException();
		this.thrusterForce = thrusterForce;
	}
	
	public double getThrusterForce () {
		return this.thrusterForce;
	}
	
	private boolean isValidThrusterForce (double thrusterForce) {
		if (Double.isNaN(thrusterForce))
			return false;
		return true;
	}
	
	public void fireBullet(){
		
	}
	

}

