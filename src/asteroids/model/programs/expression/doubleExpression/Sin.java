package asteroids.model.programs.expression.doubleExpression;

import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.DoubleExpression;
import asteroids.model.programs.expression.basicExpression.DoubleLiteral;

public class Sin extends DoubleExpression {

	public Sin(int line, int column, Expression e) {
		super(line, column, e);
	}
	
	public double getValue() {
		if (!(getE1() instanceof DoubleLiteral))
			throw new IllegalArgumentException();
		DoubleLiteral e = (DoubleLiteral)getE1();
		return Math.sin(e.getValue());
	}
}
