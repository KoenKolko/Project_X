package asteroids;

/**
 * A class of ships describing the current state.
 * 
 * @author Yannick & Koen
 * @Version 1.0
 *
 */

public class Ship implements IShip {
	
	private double x;					// x-position of the ship (km)
	private double y;					// y-position of the ship (km)
	private double xVelocity;			// Velocity in x-direction (km/s)
	private double yVelocity;			// Velocity in y-direction (km/s)
	private double velocity;			// Total velocity (km/s)
	private double radius;				// Radius of the ship (km)
	private double angle;				// The angle of the ship (radian)
	private final double C = 300000;	// Speed of light (km/s)
	
	
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double angle)
	{		
		this.setX(x);
		this.setY(y);
		this.setXVelocity(xVelocity);
		this.setYVelocity(yVelocity);
		this.setVelocity(  calcVelocity(  getXVelocity(), getYVelocity()  )  );
		this.setRadius(radius);
		this.setAngle(angle);	
		
	}
	
	public void move (double time) throws IllegalArgumentException {
		if (time <= 0)
			throw new IllegalArgumentException("Your time is less then zero!");
		setX(getX() + (getXVelocity()*time));				// x = x + velocity*time
		setY(getY() + (getYVelocity()*time));				// y = y + velocity*time
		
	}
	
	public void turn (double angle) {
		assert (!Double.isNaN(angle)) : "No valid argument!";
		setAngle(getAngle() + angle);
	}
	
	public void thrust (double amount) {
		if(Double.isNaN(amount))
			return;
		double vXNew = getXVelocity() + amount*Math.cos(getAngle());		// the new x-velocity
		double vYNew = getYVelocity() + amount*Math.sin(getAngle());		// the new y-velocity
		if (calcVelocity(vXNew, vYNew) > C) {								// if (speed > 300 000km/s)
			double constant = Math.sqrt(  Math.pow(C,2)    /   (  Math.pow(getXVelocity(), 2) + Math.pow(getYVelocity(), 2)  )  );		// constant multiple, so the new speed will be C.
			vXNew *= constant;
			vYNew *= constant;	
		}
		setXVelocity(vXNew); 
		setYVelocity(vYNew);
		setVelocity(  calcVelocity(  getXVelocity(), getYVelocity()  )  );
	}
	
	private boolean isValidRadius (double radius)
	{
		return (radius >= 10);
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) throws IllegalArgumentException {
		if (Double.isNaN(x))
			throw new IllegalArgumentException("Invalid x-coordinate");
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) throws IllegalArgumentException {
		if (Double.isNaN(x))
			throw new IllegalArgumentException("Invalid y-coordinate");
		this.y = y;
	}

	public double getXVelocity() {
		return xVelocity;
	}

	public void setXVelocity(double xVelocity) {
		if (Double.isNaN(xVelocity))
			this.xVelocity = 0;
		else this.xVelocity = xVelocity;
	}

	public double getYVelocity() {
		return yVelocity;
	}

	public void setYVelocity(double yVelocity) {
		if (Double.isNaN(yVelocity))
			this.yVelocity = 0;
		else this.yVelocity = yVelocity;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		assert (Double.isNaN(angle));
		this.angle = angle;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) throws IllegalArgumentException {
		if (!isValidRadius(radius))
			throw new IllegalArgumentException("Invalid radius!");
		this.radius = radius;
	}
	
	/**
	 * Calculates the velocity of a given x and y velocity.
	 * 
	 */
	public double calcVelocity(double x, double y) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public double getVelocity() {
		return this.velocity;
	}
	
	public void setVelocity(double velocity) {
		if (Double.isNaN(velocity))
			this.velocity = 0;
		else this.velocity = velocity;
	}
}
 
