package asteroids.model.programs.expression.unaryExpression;

import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.UnaryExpression;
import asteroids.model.programs.expression.basicExpression.DoubleLiteral;

public class Sqrt extends UnaryExpression {

	public Sqrt(int line, int column, Expression e) {
		super(line, column, e);
	}
	
	public double getValue() {
		if (!(getE() instanceof DoubleLiteral))
			throw new IllegalArgumentException();
		DoubleLiteral e = (DoubleLiteral)getE();
		return Math.sqrt(e.getValue());
	}

}
