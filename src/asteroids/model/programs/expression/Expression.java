package asteroids.model.programs.expression;

public abstract class Expression {
	
	int line, column;
	
	public Expression (int line, int column)
	{
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
