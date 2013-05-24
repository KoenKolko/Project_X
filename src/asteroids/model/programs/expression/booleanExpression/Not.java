package asteroids.model.programs.expression.booleanExpression;

import asteroids.model.programs.expression.BooleanExpression;
import asteroids.model.programs.expression.Expression;

public class Not extends BooleanExpression {

	public Not(int line, int column, Expression e) {
		super(line, column, e);
	}
	
	public Boolean getValue() {
		return !checkBoolean(getE1());
	}

}
