package asteroids.model.programs.expression.basicExpression;

import asteroids.model.SpaceObject;
import asteroids.model.programs.expression.BasicExpression;

public class EntityLiteral extends BasicExpression {

	private SpaceObject value;
	
	public EntityLiteral(int line, int column, SpaceObject spaceObject) {
		super(line, column);
		setValue(spaceObject);
	}

	public SpaceObject getValue() {
		return value;
	}

	public void setValue(SpaceObject value) {
		this.value = value;
	}

}
