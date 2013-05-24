package asteroids.model.programs.expression.basicExpression;

import asteroids.model.programs.Program;
import asteroids.model.programs.expression.BasicExpression;
import asteroids.model.programs.expression.Expression;

public class Variable extends BasicExpression{

	private String string;
	
	public Variable(int line, int column, String string) {
		super(line, column);
		setString(string);
	}
	
	public String toString() {
		return getValue().toString();
	}
	
	public Expression getValue() {
		Program program = Program.ship.getProgram();
		return program.getValues().get(getString());
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
	

}
