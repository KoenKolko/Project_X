package asteroids.model.programs.expression.binaryExpression;

import asteroids.model.programs.expression.BinaryExpression;
import asteroids.model.programs.expression.Expression;

public class LessThanOrEqualTo extends BinaryExpression {

	public LessThanOrEqualTo(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);
	}

}
