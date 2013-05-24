package asteroids.model.programs.expression.doubleExpression;

import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.DoubleExpression;

public class Cos extends DoubleExpression {

	public Cos(int line, int column, Expression e) {
		super(line, column, e);
	}
	
	public double getValue() {
		return Math.cos(checkDouble(getE1()));
	}
	
	

}
