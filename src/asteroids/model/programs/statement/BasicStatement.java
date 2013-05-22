package asteroids.model.programs.statement;

import asteroids.model.Ship;

public abstract class BasicStatement extends Statement {

	public BasicStatement(int line, int column) {
		super(line, column);
	}
	
	public abstract void execute(Ship ship);

}
