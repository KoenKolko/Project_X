package asteroids.model.programs.expression.basicExpression;

import asteroids.model.programs.expression.BasicExpression;

public class BooleanLiteral extends BasicExpression {

	private Boolean value;
	
	public BooleanLiteral(int line, int column, Boolean b) {
		super(line, column);
		setValue(b);
	}

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

}
