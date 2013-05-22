package asteroids.model.programs.expression.basicExpression;

import asteroids.model.programs.expression.BasicExpression;

public class Variable extends BasicExpression{

	private String value;
	public Variable(int line, int column, String string) {
		super(line, column);
		setValue(string);
	}
	
	public String toString() {
		return getValue();
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
