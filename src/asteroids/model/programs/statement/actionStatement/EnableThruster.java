package asteroids.model.programs.statement.actionStatement;

import asteroids.model.Ship;
import asteroids.model.programs.statement.ActionStatement;

public class EnableThruster extends ActionStatement{

	public EnableThruster(int line, int column) {
		super(line, column);
		
	}
	
	@Override
	public void execute() {
		Ship ship = getShip();
		if (ship == null)
			throw new RuntimeException();
		ship.setThruster(true);
		
	}

}
