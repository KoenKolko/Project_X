package asteroids.model.programs.expression;

import asteroids.model.programs.expression.basicExpression.BooleanLiteral;

public abstract class BooleanExpression extends ComposedExpression{

	
	public BooleanExpression(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);
	}
	
	public BooleanExpression(int line, int column, Expression e1) {
		super(line, column, e1);
		
		
	}
	
	public String toString() {
		return Boolean.toString(getValue());
	}
	
	public Expression getLiteral() {
		return new BooleanLiteral(getLine(), getColumn(), getValue());
	}
	
	public abstract Boolean getValue();
	
}
