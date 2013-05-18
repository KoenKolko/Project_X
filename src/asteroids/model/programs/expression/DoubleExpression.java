package asteroids.model.programs.expression;

public abstract class DoubleExpression extends ComposedExpression {
	
	public DoubleExpression(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);
	}

	public DoubleExpression(int line, int column, Expression e1) {
		super(line, column, e1);
	}
	
	public abstract double getValue();

}
