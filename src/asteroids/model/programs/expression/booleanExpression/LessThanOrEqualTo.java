package asteroids.model.programs.expression.booleanExpression;

import asteroids.model.programs.expression.BooleanExpression;
import asteroids.model.programs.expression.Expression;

public class LessThanOrEqualTo extends BooleanExpression {

	public LessThanOrEqualTo(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);
	}
	
	public Boolean getValue() {
		return checkDouble(getE1()) <= checkDouble(getE2());
	}

}
