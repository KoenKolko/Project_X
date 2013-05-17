package asteroids.model.programs.expression.unaryExpression;

import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.UnaryExpression;
import asteroids.model.programs.expression.basicExpression.BooleanLiteral;

public class Not extends UnaryExpression {

	public Not(int line, int column, Expression e) {
		super(line, column, e);
	}
	
	public Boolean getValue() {
		if (!(getE() instanceof BooleanLiteral))
			throw new IllegalArgumentException();
		BooleanLiteral e = (BooleanLiteral)getE();
		return !e.getValue();
	}

}
