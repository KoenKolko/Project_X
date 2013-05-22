package asteroids.model.programs.expression;

public abstract class BasicExpression extends Expression {

	
	public BasicExpression(int line, int column) {
		super(line, column);
		
	}
	
	public abstract String toString();

}
