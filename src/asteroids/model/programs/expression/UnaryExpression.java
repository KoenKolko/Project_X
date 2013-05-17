package asteroids.model.programs.expression;

public abstract class UnaryExpression extends ComposedExpression {

	private Expression e;
	
	public UnaryExpression(int line, int column, Expression e) {
		super(line, column);
		setE(e);
	}

	public Expression getE() {
		return e;
	}

	public void setE(Expression e) {
		this.e = e;
	}

}
