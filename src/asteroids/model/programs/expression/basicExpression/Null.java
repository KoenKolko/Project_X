package asteroids.model.programs.expression.basicExpression;

import asteroids.model.SpaceObject;

public class Null extends EntityLiteral{

	

	public Null(int line, int column) {
		super(line, column, null);
	}

	@Override
	public String toString() {
		return "null";
	}
	
	public SpaceObject getValue() {
		return null;
	}
}
