package asteroids.model.programs.expression.doubleExpression;

import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.DoubleExpression;

public class Sin extends DoubleExpression {

	public Sin(int line, int column, Expression e) {
		super(line, column, e);
	}
	
	public double getValue() {
		return Math.sin(checkDouble(getE1()));
	}
}
