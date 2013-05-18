package asteroids.model.programs.expression;

public abstract class BooleanExpression extends ComposedExpression{

	
	public BooleanExpression(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);
	}
	
	public BooleanExpression(int line, int column, Expression e1) {
		super(line, column, e1);
	}
	
	public abstract Boolean getValue();

}
