package asteroids.model.programs.expression.booleanExpression;

import asteroids.model.programs.expression.BooleanExpression;
import asteroids.model.programs.expression.Expression;

public class Or extends BooleanExpression {

	public Or(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);		
	}
	
	public Boolean getValue() {
		return checkBoolean(getE1()) || checkBoolean(getE2());
	}

}
