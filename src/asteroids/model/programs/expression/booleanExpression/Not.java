package asteroids.model.programs.expression.booleanExpression;

import asteroids.model.programs.expression.BooleanExpression;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.basicExpression.BooleanLiteral;

public class Not extends BooleanExpression {

	public Not(int line, int column, Expression e) {
		super(line, column, e);
	}
	
	public Boolean getValue() {
		if (!(getE1() instanceof BooleanLiteral))
			throw new IllegalArgumentException();
		BooleanLiteral e = (BooleanLiteral)getE1();
		return !e.getValue();
	}

}
