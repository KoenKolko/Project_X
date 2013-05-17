package asteroids.model.programs.expression.binaryExpression;

import asteroids.model.programs.expression.BinaryExpression;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.basicExpression.DoubleLiteral;

public class Mul extends BinaryExpression {

	public Mul(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);	
	}
	
	public double getValue() {
		if (!(getE1() instanceof DoubleLiteral) || !(getE2() instanceof DoubleLiteral))
			throw new IllegalArgumentException();
		DoubleLiteral e1 = (DoubleLiteral)getE1();
		DoubleLiteral e2 = (DoubleLiteral)getE2();
		return e1.getValue() * e2.getValue();
	}

}