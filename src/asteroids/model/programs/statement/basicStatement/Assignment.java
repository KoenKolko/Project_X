package asteroids.model.programs.statement.basicStatement;

import asteroids.model.Ship;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.statement.BasicStatement;

public class Assignment extends BasicStatement {
	
	private String string;
	private Expression expression;

	public Assignment(int line, int column, String string, Expression e) {
		super(line, column);
		setString(string);
		setExpression(e);
	}

	@Override
	public void execute(Ship ship) {}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

}
