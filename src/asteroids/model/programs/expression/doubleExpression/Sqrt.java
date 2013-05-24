package asteroids.model.programs.expression.doubleExpression;

import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.DoubleExpression;

public class Sqrt extends DoubleExpression {

	public Sqrt(int line, int column, Expression e) {
		super(line, column, e);
	}
	
	public double getValue() {
		return Math.sqrt(checkDouble(getE1()));
	}

}
