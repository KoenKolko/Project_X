package asteroids.model.programs.expression;

public abstract class ComposedExpression extends Expression {
	
	public ComposedExpression(int line, int column, Expression e1, Expression e2) {
		super(line, column);
	}

}
