package asteroids.model.programs;

public abstract class Code {
	
	int line, column;
	
	public Code(int line, int column) {
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

}
