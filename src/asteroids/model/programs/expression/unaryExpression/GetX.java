package asteroids.model.programs.expression.unaryExpression;

import asteroids.model.Ship;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.UnaryExpression;
import asteroids.model.programs.expression.basicExpression.EntityLiteral;

public class GetX extends UnaryExpression {

	public GetX(int line, int column, Expression e) {
		super(line, column, e);
	}
	
	public double getValue() {
		if (!(getE() instanceof EntityLiteral))
			throw new IllegalArgumentException();
		EntityLiteral entity = (EntityLiteral)getE();
		if (!(entity.getValue() instanceof Ship))
			throw new IllegalArgumentException();
		Ship ship = (Ship)entity.getValue();
		return ship.getLocation().getX();
	}

}
