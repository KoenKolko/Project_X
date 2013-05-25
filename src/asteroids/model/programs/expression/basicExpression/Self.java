package asteroids.model.programs.expression.basicExpression;

import asteroids.model.SpaceObject;
import asteroids.model.programs.Program;

public class Self extends EntityLiteral{

	

	public Self(int line, int column) {
		super(line, column, null);
	}

	@Override
	public String toString() {
		return "Self";
	}
	
	public SpaceObject getValue() {
		return Program.ship;
	}
	
	
	
	

}
