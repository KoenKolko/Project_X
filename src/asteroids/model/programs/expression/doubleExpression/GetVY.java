package asteroids.model.programs.expression.doubleExpression;

import asteroids.model.Ship;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.DoubleExpression;
import asteroids.model.programs.expression.basicExpression.EntityLiteral;

public class GetVY extends DoubleExpression {

	public GetVY(int line, int column, Expression e) {
		super(line, column, e);
	}
	
	public double getValue() {
		if (!(getE1() instanceof EntityLiteral))
			throw new IllegalArgumentException();
		EntityLiteral entity = (EntityLiteral)getE1();
		if (!(entity.getValue() instanceof Ship))
			throw new IllegalArgumentException();
		Ship ship = (Ship)entity.getValue();
		return ship.getVelocity().getY();
	}

}
