package asteroids.model.programs.statement;

public abstract class ActionStatement extends Statement {

	public ActionStatement(int line, int column) {
		super(line, column);
	}
	
	public abstract void execute();

}
