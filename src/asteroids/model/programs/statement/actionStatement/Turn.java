package asteroids.model.programs.statement.actionStatement;

import asteroids.model.programs.Program;
import asteroids.model.programs.expression.DoubleExpression;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.basicExpression.DoubleLiteral;
import asteroids.model.programs.expression.basicExpression.Variable;
import asteroids.model.programs.statement.ActionStatement;

public class Turn extends ActionStatement {

	private Expression e;

	public Turn(int line, int column, Expression e) {
		super(line, column);
		System.out.println(e.getClass());
		setE(e);
	}

	@Override
	public void execute() {
		if (Program.ship == null)
			throw new RuntimeException();
		double angle;
		if (e instanceof DoubleLiteral)
			angle = ((DoubleLiteral) e).getValue();
		else if (e instanceof Variable)
		{
			Expression expression = ((Variable) e).getValue();
			if (expression instanceof DoubleLiteral)
				angle = ((DoubleLiteral)expression).getValue();
			else if (expression instanceof DoubleExpression)
				angle = ((DoubleExpression)expression).getValue();
			else throw new RuntimeException();
		}
		else throw new IllegalArgumentException();
		Program.ship.turn(angle);

	}

	public Expression getE() {
		return e;
	}

	public void setE(Expression e) {
		this.e = e;
	}

}
