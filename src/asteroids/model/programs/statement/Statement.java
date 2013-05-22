package asteroids.model.programs.statement;

import asteroids.model.Ship;
import asteroids.model.programs.Code;

public abstract class Statement extends Code {

	public Statement(int line, int column) {
		super(line, column);
	}
	
	public abstract void execute(Ship ship);

}
