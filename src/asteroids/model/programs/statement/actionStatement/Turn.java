package asteroids.model.programs.statement.actionStatement;

import asteroids.model.Ship;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.basicExpression.DoubleLiteral;
import asteroids.model.programs.statement.ActionStatement;

public class Turn extends ActionStatement {
	
	private Expression e;
	private double angle;

	public Turn(int line, int column, Expression e) {
		super(line, column);
		if (!(e instanceof DoubleLiteral))
			throw new IllegalArgumentException();
		setAngle(((DoubleLiteral) e).getValue());
		setE(e);
	}

	@Override
	public void execute() {
		Ship ship = getShip();
		if (ship == null)
			throw new RuntimeException();
		ship.turn(getAngle());
		
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public Expression getE() {
		return e;
	}

	public void setE(Expression e) {
		this.e = e;
	}

}
