package asteroids.model.programs.expression;

public class Double extends BasicExpression {
	
	private double value;

	public Double(int line, int column, double d) {
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
