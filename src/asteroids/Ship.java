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
		return calcVelocity(this.getXVelocity(), this.getYVelocity());
	}
	
	
	public double getDistanceBetween(Ship otherShip) throws IllegalArgumentException {
		if (otherShip == null) // The other ship doesn't exist.
			throw new IllegalArgumentException("Invalid ship!");
		return Math.sqrt(Math.pow(this.getXDistanceBetween(otherShip), 2) - Math.pow(this.getYDistanceBetween(otherShip), 2)); // The distance between the two centers.
	}
	
	private double getXDistanceBetween(Ship otherShip){
		return this.getX() - otherShip.getX(); // The x-distance between the two ships.
	}
	
	private double getYDistanceBetween(Ship otherShip){
		return this.getY() - otherShip.getY(); // The y-distance between the two ships.
	}
	
	private double calcDeltaRSquared(Ship otherShip){
		return Math.pow((this.getX() - otherShip.getX()),2) + Math.pow((this.getY() - otherShip.getY()),2);
	}
	
	private double calcDeltaVSquared(Ship otherShip){
		return Math.pow((this.getXVelocity() - otherShip.getXVelocity()),2) + Math.pow((this.getYVelocity() - otherShip.getYVelocity()),2);
	}
	
	private double calcDeltaVDeltaR(Ship otherShip){
		return (this.getX() * (this.getXVelocity()) - (otherShip.getX() * otherShip.getXVelocity()) + this.getY() * (this.getYVelocity()) - (otherShip.getY() * otherShip.getYVelocity()));
	}
	
	public boolean overlap(Ship otherShip){
		return(this.getRadius() + otherShip.getRadius() > this.getDistanceBetween(otherShip)); // If the distance is smaller than the sum of the radii, the ships overlap.
	}	
	public double getTimeToCollision(Ship otherShip){		
		double sigma = this.getRadius() + otherShip.getRadius();
		double VR = calcDeltaVDeltaR(otherShip);
		double RR = calcDeltaRSquared(otherShip);
		double VV = calcDeltaVSquared(otherShip);
		double d = Math.pow(VR, 2) - ((VV) * (RR - Math.pow(sigma,2)));
		if(this.overlap(otherShip)){
			return Double.POSITIVE_INFINITY; // The ships overlap.
		}
		else if(Double.compare(d,0) <= 0) {
			return Double.POSITIVE_INFINITY; // The ships will not collide.
		}
		else{
			return ((VR + Math.sqrt(d))/RR); // Calculate the time to collision.
		}
	}
	
	public double[] getCollisionPosition(Ship otherShip)
	{
		double timeToCollision = this.getTimeToCollision(otherShip);
		if(timeToCollision != Double.POSITIVE_INFINITY){
			double[] positions = new double[2];
			positions[0] = this.getX() + timeToCollision * this.getXVelocity();
			positions[1] = this.getY() + timeToCollision * this.getYVelocity();
			return positions;
		}
		else{
			return null;
		}
	}
	
}

