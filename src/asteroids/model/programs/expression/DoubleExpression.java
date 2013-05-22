package asteroids.model.programs.expression;

import asteroids.model.programs.expression.basicExpression.DoubleLiteral;

public abstract class DoubleExpression extends ComposedExpression {
	
	public DoubleExpression(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);
	}

	public DoubleExpression(int line, int column, Expression e1) {
		super(line, column, e1);
	}
	
	public Expression getLiteral() {
		return new DoubleLiteral(getLine(), getColumn(), getValue());
	}
	
	public String toString() {
		return Double.toString(getValue());
	}
	
	public abstract double getValue();

}
