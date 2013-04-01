package asteroids;

public class Vector {
	
	private final double X;
	private final double Y;
	
	public Vector(double x, double y) 
	{
		this.X = x;
		this.Y = y;
	}
	
	public double getX() {
		return this.X;
	}
	
	public double getY() {
		return this.Y;
	}
	
	public Vector add (Vector otherVector)
	{
		double x1, x2, y1, y2;
		x1 = this.getX();
		x2 = otherVector.getX();
		y1 = this.getY();
		y2 = otherVector.getY();
		return new Vector(x1+x2, y1+y2);
	}
	
	public Vector substract (Vector otherVector)
	{
		return this.add(  new Vector(  -otherVector.getX(), -otherVector.getY()  )  );
	}
	
	public double multiply (Vector otherVector) 
	{
		double x1, x2, y1, y2;
		x1 = this.getX();
		x2 = otherVector.getX();
		y1 = this.getY();
		y2 = otherVector.getY();
		
		return x1*x2 + y1*y2;
	}
	
	public Vector multiply (double constant)
	{
		return new Vector( getX()*constant, getY()*constant);
	}
	
	public double getNorm ()
	{
		return Math.sqrt( Math.pow(getX(), 2) + Math.pow(getY(), 2) );
	}
	
	public boolean isValidVector() 
	{
		if (Double.isNaN(getX()) || Double.isNaN(getY())) 
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
		return substract(otherVector).getNorm();
	}
}
