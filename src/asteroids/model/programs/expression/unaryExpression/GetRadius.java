package asteroids.model.programs.expression.unaryExpression;

import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.UnaryExpression;

public class GetRadius extends UnaryExpression {

	public GetRadius(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);
	}

}
