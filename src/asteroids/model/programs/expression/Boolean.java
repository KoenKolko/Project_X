package asteroids.model.programs.expression;

public class Boolean extends BasicExpression {

	private Boolean value;
	
	public Boolean(int line, int column, Boolean b) {
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
