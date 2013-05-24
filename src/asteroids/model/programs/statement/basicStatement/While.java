package asteroids.model.programs.statement.basicStatement;

import asteroids.model.programs.Program;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.statement.BasicStatement;
import asteroids.model.programs.statement.Statement;

public class While extends BasicStatement {

	private Expression condition;
	private Statement body;
	private Boolean conditionValue;

	public While(int line, int column, Expression condition, Statement body) {
		super(line, column);
		setCondition(condition);
		setBody(body);
	}

	@Override
	public void execute() {
		if (!Program.getPaused())
			setConditionValue(checkBoolean(getCondition()));

		while (getConditionValue())

		{
			getBody().execute();
			if (Program.getPaused())
				break;
		}


	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
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

	public Boolean getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(Boolean conditionValue) {
		this.conditionValue = conditionValue;
	}

}
