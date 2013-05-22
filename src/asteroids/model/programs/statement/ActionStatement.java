package asteroids.model.programs.statement;

import asteroids.model.Ship;

public abstract class ActionStatement extends Statement {

	public ActionStatement(int line, int column) {
		super(line, column);
	}
	
	public abstract void execute(Ship ship);

}
