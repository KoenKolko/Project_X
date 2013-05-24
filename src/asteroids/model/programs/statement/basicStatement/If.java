package asteroids.model.programs.statement.basicStatement;

import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.statement.BasicStatement;
import asteroids.model.programs.statement.Statement;

public class If extends BasicStatement {
	
	private Expression condition;
	private Statement then, otherwise;

	public If(int line, int column, Expression condition, Statement then, Statement otherwise) {
		super(line, column);
		setCondition(condition);
		setThen(then);
		setOtherwise(otherwise);
	}
	
	public If(int line, int column, Expression condition, Statement then) {
		this(line, column, condition, then, null);
	}

	@Override
	public void execute() {
		if ( checkBoolean(getCondition()) )
			getThen().execute();
		else if (getOtherwise() != null)
			getOtherwise().execute();
	}
	

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public Statement getThen() {
		return then;
	}

	public void setThen(Statement then) {
		if (then == null)
			throw new IllegalArgumentException();
		this.then = then;
	}

	public Statement getOtherwise() {
		return otherwise;
	}

	public void setOtherwise(Statement otherwise) {
		this.otherwise = otherwise;
	}


}
