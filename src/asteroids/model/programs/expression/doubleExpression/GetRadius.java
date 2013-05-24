package asteroids.model.programs.expression.doubleExpression;

import asteroids.model.SpaceObject;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.DoubleExpression;

public class GetRadius extends DoubleExpression {

	public GetRadius(int line, int column, Expression e) {
		super(line, column, e);
	}
	
	public double getValue() {
//		if (!(getE1() instanceof EntityLiteral))
//			throw new IllegalArgumentException();
		SpaceObject object = checkEntity(getE1());
		return object.getRadius();
	}

}
