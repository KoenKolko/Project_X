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
		this.setRadius(radius);
		this.setAngle(angle);		
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
	 * Calculates the velocity of the ship.
	 * 
	 * @Pre		velocity == getVelocity
	 * @Post	(new this).getVelocity == Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2))
	 */
	public void calcVelocity() {
		velocity = Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
	}
	
	public double getVelocity() {
		return velocity;
	}
}
 
