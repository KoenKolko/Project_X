package asteroids.model.programs.expression.doubleExpression;

import asteroids.model.SpaceObject;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.DoubleExpression;
import asteroids.model.programs.expression.basicExpression.DoubleLiteral;

public class GetX extends DoubleExpression {

	public GetX(int line, int column, Expression e) {
		super(line, column, e);
	}
	
	public double getValue() {
		SpaceObject object = checkEntity(super.getE1());
		return object.getLocation().getX();
	}
	
	public Expression getE1() {
		SpaceObject object = checkEntity(super.getE1());
		if (object == null)
			return new DoubleLiteral(getLine(), getColumn(), -1);
		return new DoubleLiteral(getLine(), getColumn(), object.getLocation().getX());
	}

}
