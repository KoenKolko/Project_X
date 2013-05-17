package asteroids.model.programs.expression.binaryExpression;

import asteroids.model.programs.expression.BinaryExpression;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.basicExpression.BooleanLiteral;

public class Or extends BinaryExpression {

	public Or(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);		
	}
	
	public Boolean getValue() {
		if (!(getE1() instanceof BooleanLiteral) || !(getE2() instanceof BooleanLiteral))
			throw new IllegalArgumentException();
		BooleanLiteral e1 = (BooleanLiteral)getE1();
		BooleanLiteral e2 = (BooleanLiteral)getE2();
		return e1.getValue() || e2.getValue();
	}

}
