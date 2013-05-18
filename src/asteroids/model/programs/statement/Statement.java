package asteroids.model.programs.statement;

import asteroids.model.programs.Program;

public abstract class Statement extends Program {

	public Statement(int line, int column) {
		super(line, column);
	}
	
	public abstract void execute();

}
