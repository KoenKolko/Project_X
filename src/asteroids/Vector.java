package asteroids;

import be.kuleuven.cs.som.annotate.Basic;

public class Vector {

	private double x;										// x-position of the ship (km)
	private double y;										// y-position of the ship (km)
	private double xVelocity;								// Velocity in x-direction (km/s)
	private double yVelocity;								// Velocity in y-direction (km/s)
	public static DoubleCalculator calc = new DoubleCalculator(); // A calculator to calc with Doubles.

	
	
	public Vector(double x, double y, double xVelocity, double yVelocity) {
		this.x = x;
		this.y = y;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}
	
	public Vector(double x, double y) {
		this(x, y, 0.0, 0.0);
	}
	
	public Vector() {
		this(0.0, 0.0, 0.0, 0.0);
	}
	
	public void movement (double time) {
		if (!isValidTime(time))
			throw new IllegalArgumentException();
		setX(calc.addDoubles(getX(), calc.multiplyDoubles(getXVelocity(), time)));					// x = x + velocity*time
		setY(calc.addDoubles(getY(), calc.multiplyDoubles(getYVelocity(), time)));					// y = y + velocity*time
	}
	
	public double getDistanceBetween(Vector otherVector) throws IllegalArgumentException {
		if (otherVector == null) 				// The other vector doesn't exist.
			throw new IllegalArgumentException("Invalid vector!");
		return Math.sqrt(calc.addDoubles(calc.multiplyDoubles(deltaX(otherVector), deltaX(otherVector)),
				calc.multiplyDoubles(deltaY(otherVector), deltaY(otherVector)))); 
	}
	
	/**
	 * 
	 * @param time	The time that has to be checked.
	 * @return	
	 * 		Returns if the time is valid.
	 * 		| !(Double.isNaN(time) || time < 0)  
	 */
	public boolean isValidTime (double time) { 
		return !(Double.isNaN(time) || time < 0);
	}
	
	public double deltaX(Vector otherVector){
		return calc.addDoubles(getX(), -otherVector.getX()); 					// The x-distance between with another vector.
	}
	
	public double deltaY(Vector otherVector){
		return calc.addDoubles(getY(), -otherVector.getY()); 					// The y-distance between with another vector.
	}
	
	/**
	 * 
	 * @return
	 * 		Returns the x-coordinate of the ship.
	 * 
	 */
	@Basic
	public double getX() {
		return x;
	}

	/**
	 * 
	 * @param x		The new x-coordinate
	 * @post 		X-position has been set to x.
	 * 				| (new this).getX() == x
	 * @throws IllegalArgumentException
	 * 		The entered x-parameter is invalid.
	 * 		| Double.isNaN(x)
	 */
	public void setX(double x) throws IllegalArgumentException {
		if (Double.isNaN(x))
			throw new IllegalArgumentException("Invalid x-coordinate");
		this.x = x;
	}

	/**
	 * 
	 * @return
	 * 		Returns the y-coordinate of the ship.
	 */
	@Basic
	public double getY() {
		return y;
	}

	/**
	 * 
	 * @param y		The new y-coordinate
	 * @post 		Y-position has been set to y.
	 * 				| (new this).getY() == y
	 * @throws IllegalArgumentException
	 * 				The entered y-parameter is invalid.
	 * 				| Double.isNaN(y)
	 */
	public void setY(double y) throws IllegalArgumentException {
		if (Double.isNaN(y))
			throw new IllegalArgumentException("Invalid y-coordinate");
		this.y = y;
	}

	/**
	 * 
	 * @return
	 * 		Returns the velocity of the ship in the x-axis.
	 */
	@Basic
	public double getXVelocity() {
		return xVelocity;
	}

	/**
	 * 
	 * @param xVelocity		The new x-velocity of the ship.
	 * @post 	If xVelocity is not a number, the velocity is set to zero.
	 * 			Else, the x-velocity is set to xVelocity.
	 * 			| if (Double.isNaN(xVelocity))
	 * 			| 	then (new this).getXVelocity == 0
	 * 			| else (new this).getXVelocity == xVelocity
	 */
	public void setXVelocity(double xVelocity) {
		if (Double.isNaN(xVelocity))
			this.xVelocity = 0.0;
		else this.xVelocity = xVelocity;
	}

	/**
	 * 
	 * @return
	 * 		Returns the velocity of the ship in the y-axis.
	 */
	@Basic
	public double getYVelocity() {
		return yVelocity;
	}

	/**
	 * 
	 * @param yVelocity		The new y-velocity of the ship.
	 * @post 	If yVelocity is not a number, the velocity is set to zero.
	 * 			Else, the y-velocity is set to yVelocity.
	 * 			| if (Double.isNaN(yVelocity))
	 * 			| 	then (new this).getYVelocity == 0
	 * 			| else (new this).getYVelocity == yVelocity
	 */
	public void setYVelocity(double yVelocity) {
		if (Double.isNaN(yVelocity))
			this.yVelocity = 0.0;
		else this.yVelocity = yVelocity;
	}
	
	/**
	 * Calculates the velocity of a given x and y velocity.
	 * 
	 * @param x		The x-velocity.
	 * @param y		The y-velocity.
	 * @return		If one of the parameters is not a number, return 0.0. 
	 * 				Else, return the velocity.
	 *				| if ( Double.isNaN(x) || Double.isNaN(y) )
	 *				| 	then return 0.0
	 *				| else return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))
	 */
	public static double calcVelocity(double x, double y) {
		if (Double.isNaN(x) || Double.isNaN(y))
			return 0.0;
		return Math.sqrt(calc.multiplyDoubles(x, x)+ calc.multiplyDoubles(y, y));
	}
	
	public double getVelocity() {
		return calcVelocity(xVelocity, yVelocity);
	}
	
}
