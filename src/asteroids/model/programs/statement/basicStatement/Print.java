package asteroids.model.programs.statement.basicStatement;

import asteroids.model.Ship;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.statement.BasicStatement;

public class Print extends BasicStatement {

	private Expression expression;
	
	public Print(int line, int column, Expression e) {
		super(line, column);
		setExpression(e);
	}

	@Override
	public void execute(Ship ship) {
		System.out.println(getExpression().toString());
	}
	
	public void execute() { this.execute(null); }

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

}
