package asteroids.model.programs.expression.doubleExpression;

import asteroids.model.programs.expression.DoubleExpression;
import asteroids.model.programs.expression.Expression;

public class Mul extends DoubleExpression {

	public Mul(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);	
	}
	
	public double getValue() {
		return checkDouble(getE1()) * checkDouble(getE2());
	}

}
