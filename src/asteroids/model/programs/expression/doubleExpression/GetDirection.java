package asteroids.model.programs.expression.doubleExpression;

import asteroids.model.programs.Program;
import asteroids.model.programs.expression.DoubleExpression;
public class GetDirection extends DoubleExpression {

	public GetDirection(int line, int column) {
		super(line, column);
	}

	@Override
	public double getValue() {
		return Program.ship.getAngle();
	}

}
