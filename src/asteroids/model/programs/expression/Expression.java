package asteroids.model.programs.expression;

import asteroids.model.programs.Code;

public abstract class Expression extends Code {

	
	public Expression (int line, int column)
	{
		super(line, column);
	}
	
	public abstract String toString();
	
	

}
