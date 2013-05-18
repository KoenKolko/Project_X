package asteroids.model.programs;

import asteroids.model.Ship;

public abstract class Program {
	
	int line, column;
	Ship ship = null;
	
	public Program(int line, int column) {
		setLine(line);
		setColumn(column);
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

}
