package asteroids.model.programs.statement.actionStatement;

import asteroids.model.Ship;
import asteroids.model.programs.statement.ActionStatement;

public class Skip extends ActionStatement{

	public Skip(int line, int column) {
		super(line, column);
	}

	@Override
	public void execute(Ship ship) {}
	public void execute() {this.execute(null);}

}
