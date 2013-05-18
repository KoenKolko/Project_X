package asteroids.model.programs.expression.doubleExpression;

import asteroids.model.programs.expression.DoubleExpression;
import asteroids.model.programs.expression.basicExpression.*;
import asteroids.model.programs.expression.Expression;

public class Subtraction extends DoubleExpression {

	public Subtraction(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);		
	}
	
	public double getValue() {
		if (!(getE1() instanceof DoubleLiteral) || !(getE2() instanceof DoubleLiteral))
			throw new IllegalArgumentException();
		DoubleLiteral e1 = (DoubleLiteral)getE1();
		DoubleLiteral e2 = (DoubleLiteral)getE2();
		return e1.getValue() - e2.getValue();
	}

}
