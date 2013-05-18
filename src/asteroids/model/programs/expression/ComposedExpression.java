package asteroids.model.programs.expression;

public abstract class ComposedExpression extends Expression {
	
	protected Expression e1, e2;
	
	public ComposedExpression(int line, int column, Expression e1, Expression e2) {
		super(line, column);
		setE1(e1);
		setE2(e2);
		
	}
	
	public ComposedExpression(int line, int column, Expression e1) {
		this(line, column, e1, null);
	}

	public Expression getE1() {
		return e1;
	}

	public void setE1(Expression e1) {
		this.e1 = e1;
	}

	public Expression getE2() {
		return e2;
	}

	public void setE2(Expression e2) {
		this.e2 = e2;
	}

}
