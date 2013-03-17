package asteroids;

public class DoubleCalculator {

	public Double addDoubles(double x, double y) throws ArithmeticException{
		if ( (x == Double.POSITIVE_INFINITY && y == Double.NEGATIVE_INFINITY) || (y == Double.POSITIVE_INFINITY && x == Double.NEGATIVE_INFINITY))
			throw new ArithmeticException();
		return x + y;

	}

	public Double multiplyDoubles(double x, double y) throws ArithmeticException{
		if ( (x == Double.POSITIVE_INFINITY && y == Double.NEGATIVE_INFINITY) || (y == Double.POSITIVE_INFINITY && x == Double.NEGATIVE_INFINITY))
			throw new ArithmeticException();
		return x * y;
	}
}
