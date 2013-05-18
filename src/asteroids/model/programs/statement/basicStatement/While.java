package asteroids.model.programs.statement.basicStatement;

import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.basicExpression.BooleanLiteral;
import asteroids.model.programs.statement.BasicStatement;
import asteroids.model.programs.statement.Statement;

public class While extends BasicStatement {

	private Expression condition;
	private Statement body;
	
	public While(int line, int column, Expression condition, Statement body) {
		super(line, column);
		setCondition(condition);
		setBody(body);
	}

	@Override
	public void execute() {
		while (( (BooleanLiteral) getCondition()).getValue())
			getBody().execute();
		
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		if (condition == null || !(condition instanceof BooleanLiteral))
			throw new IllegalArgumentException();
		this.condition = condition;
	}

	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		if (body == null)
			throw new IllegalArgumentException();
		this.body = body;
	}

}
