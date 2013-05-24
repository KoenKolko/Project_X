package asteroids.model.programs;

import asteroids.model.programs.expression.Expression;

public class DoubleType extends Type {

	@Override
	public boolean checkType(Expression e) {
		return false;
	}

}
