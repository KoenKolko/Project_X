package asteroids.model.programs.statement.basicStatement;

import asteroids.model.programs.Program;
import asteroids.model.programs.expression.BooleanExpression;
import asteroids.model.programs.expression.DoubleExpression;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.basicExpression.BooleanLiteral;
import asteroids.model.programs.expression.basicExpression.DoubleLiteral;
import asteroids.model.programs.expression.basicExpression.Variable;
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
	public void execute() {
		Expression e = getExpression();
		Boolean found = false;
		while (!found){
			if (e instanceof DoubleExpression)
				e = new DoubleLiteral(e.getLine(), e.getColumn(), ((DoubleExpression) e).getValue());
			else if (e instanceof Variable && (((Variable)e).getValue() instanceof DoubleExpression || ((Variable)e).getValue() instanceof DoubleLiteral))
				e = ((Variable)e).getValue();
			else found = true;
		}
		found = false;
		while (!found){
			if (e instanceof BooleanExpression)
				e = new BooleanLiteral(e.getLine(), e.getColumn(), ((BooleanExpression) e).getValue());
			else if (e instanceof Variable && (((Variable)e).getValue() instanceof BooleanExpression || ((Variable)e).getValue() instanceof BooleanLiteral))
				e = ((Variable)e).getValue();
			else found = true;
		}

		Program.ship.getProgram().getValues().put(getString(), e);
	}

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
