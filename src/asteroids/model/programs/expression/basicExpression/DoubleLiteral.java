package asteroids.model.programs.expression.basicExpression;

import asteroids.model.programs.expression.BasicExpression;

public class DoubleLiteral extends BasicExpression {
	
	private double value;

	public DoubleLiteral(int line, int column, double d) {
		super(line, column);
		setValue(d);
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
