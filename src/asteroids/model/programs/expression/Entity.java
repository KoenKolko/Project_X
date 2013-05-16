package asteroids.model.programs.expression;

import asteroids.model.SpaceObject;

public class Entity extends BasicExpression {

	private SpaceObject value;
	
	public Entity(int line, int column, SpaceObject spaceObject) {
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
