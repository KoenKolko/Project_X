package asteroids.model.programs.expression.basicExpression;

import asteroids.model.programs.expression.BasicExpression;
import asteroids.model.programs.expression.Expression;

public class Variable extends BasicExpression{

	private String string;
	private Expression value = null;
	
	public Variable(int line, int column, String string) {
		super(line, column);
		setString(string);
	}
	
	public String toString() {
		return getString();
	}
	
	public Expression getValue() {
		return this.value;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public void setValue(Expression value) {
		this.value = value;
	}
	

}
