package asteroids.model.programs.statement.actionStatement;

import asteroids.model.Ship;
import asteroids.model.programs.statement.ActionStatement;

public class Fire extends ActionStatement {

	public Fire(int line, int column) {
		super(line, column);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Ship ship) {
		if (ship == null)
			throw new RuntimeException();
		ship.fireBullet();
		
	}

}
