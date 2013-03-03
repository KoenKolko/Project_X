package asteroids;

/**
 * A class of ships describing the current state.
 * 
 * @author Yannick & Koen
 * @Version 1.0
 *
 */

public class Ship implements IShip {
	
	private double x;
	private double y;
	private double xVelocity;
	private double yVelocity;
	private double radius;
	private double angle;
	
	
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double angle)
	{		
		this.setX(x);
		this.setY(y);
		this.setXVelocity(xVelocity);
		this.setYVelocity(yVelocity);
		this.setRadius(radius);
		this.setAngle(angle);		
	}
	
	private boolean isValidRadius(double radius)
	{
		return (radius >= 10);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getXVelocity() {
		return xVelocity;
	}

	public void setXVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}

	public double getYVelocity() {
		return yVelocity;
	}

	public void setYVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
}
 
