package asteroids.model.programs.expression.booleanExpression;

import asteroids.model.programs.expression.BooleanExpression;
import asteroids.model.programs.expression.Expression;

public class Inequality extends BooleanExpression {

	public Inequality(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);
	}
	
	public Boolean getValue() {
		try {
			return checkBoolean(getE1()) != checkBoolean(getE2());
		}
		catch (RuntimeException e) {
			try {
				return checkDouble(getE1()) != checkDouble(getE2());
				}
				catch (RuntimeException exc2) {
					return checkEntity(getE1()) != checkEntity(getE2());
				}
		}
	}

}
