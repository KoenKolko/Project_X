package asteroids;

public class Vector {
	
	private final double X;
	private final double Y;
	
	public Vector(double x, double y) 
	{		
		this.X = x;
		this.Y = y;
		if (!isValidVector())
			throw new IllegalArgumentException();
	}
	
	public double getX() {
		return this.X;
	}
	
	public double getY() {
		return this.Y;
	}
	
	public Vector add (Vector otherVector)
	{
		if (otherVector == null)
			return this;
		
		double x1, x2, y1, y2;
		x1 = this.getX();
		x2 = otherVector.getX();
		y1 = this.getY();
		y2 = otherVector.getY();
		return new Vector(x1+x2, y1+y2);
	}
	
	public Vector subtract (Vector otherVector)
	{
		if (otherVector == null)
			return this;
		return this.add(  new Vector(  -otherVector.getX(), -otherVector.getY()  )  );
	}
	
	public double multiply (Vector otherVector) 
	{
		if (otherVector == null)
			throw new IllegalArgumentException();
		
		double x1, x2, y1, y2;
		x1 = this.getX();
		x2 = otherVector.getX();
		y1 = this.getY();
		y2 = otherVector.getY();
		return x1*x2 + y1*y2;
	}
	
	public Vector multiply (double constant)
	{
		if (!isValidConstant(constant))
			return this;
		return new Vector( getX()*constant, getY()*constant);
	}
	
	public double getNorm ()
	{
		return Math.sqrt( Math.pow(getX(), 2) + Math.pow(getY(), 2) );
	}
	
	private boolean isValidVector() 
	{
		if (Double.isNaN(getX()) || Double.isNaN(getY())) 
			return false;
		if (Double.isInfinite(getX()) || Double.isInfinite(getY()))
			return false;
		return true;
	}
	
	public boolean isValidConstant (double constant) {
		if (Double.isNaN(constant) || Double.isInfinite(constant))
			return false;
		return true;
	}
	
	public boolean equals (Object other)
	{
		if (other == null || !(other instanceof Vector) )
			return false;
		return Util.fuzzyEquals(getX(), ( (Vector) other).getX() ) && Util.fuzzyEquals(getY(), ( (Vector) other).getY() );
	}
	
	public double getDistanceBetween (Vector otherVector)
	{
		return subtract(otherVector).getNorm();
	}
	
}
