package asteroids.model.programs.statement.actionStatement;

import asteroids.model.programs.Program;
import asteroids.model.programs.statement.ActionStatement;

public class Fire extends ActionStatement {

	public Fire(int line, int column) {
		super(line, column);
	}

	@Override
	public void execute() {
		if (Program.ship == null)
			throw new RuntimeException();
		Program.ship.fireBullet();
		
	}

}
