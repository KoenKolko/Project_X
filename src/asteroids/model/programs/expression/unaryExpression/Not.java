package asteroids.model.programs.expression.unaryExpression;

import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.UnaryExpression;

public class Not extends UnaryExpression {

	public Not(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);
	}

}
