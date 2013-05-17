package asteroids.model.programs.expression;

public abstract class UnaryExpression extends ComposedExpression {

	private Expression e;
	
	public UnaryExpression(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);
		setE(e);
	}

	public Expression getE() {
		return e;
	}

	public void setE(Expression e) {
		this.e = e;
	}

}
