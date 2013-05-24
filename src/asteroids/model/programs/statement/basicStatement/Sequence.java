package asteroids.model.programs.statement.basicStatement;

import java.util.List;

import asteroids.model.programs.Program;
import asteroids.model.programs.statement.actionStatement.*;
import asteroids.model.programs.statement.BasicStatement;
import asteroids.model.programs.statement.Statement;

public class Sequence extends BasicStatement {

	List<Statement> statements;
	private int currentPos = 0;
	private Boolean paused = false;

	public Sequence(int line, int column, List<Statement> statements) {
		super(line, column);
		setStatements(statements);

	}

	@Override
	public void execute() {
		if (!getPaused())
			Program.setPaused(false);
		for (int i = currentPos; i < getStatements().size(); i++ )
		{
			try {
			getStatements().get(i).execute();
			if (getStatements().get(i) instanceof Sequence && ((Sequence)getStatements().get(i)).getPaused())
			{
				setCurrentPos(i);
				setPaused(true);
				Program.setPaused(true);
				break;

			}
			else if (i == getStatements().size()-1)
			{
				setCurrentPos(0);
				setPaused(false);
				Program.setPaused(false);
			}

			else if (getStatements().get(i) instanceof Fire || getStatements().get(i) instanceof Turn || getStatements().get(i) instanceof DisableThruster || getStatements().get(i) instanceof EnableThruster || getStatements().get(i) instanceof Skip)
			{
				setCurrentPos(i+1);
				setPaused(true);
				Program.setPaused(true);
				break;
			}
			}
			
			catch (RuntimeException e) {
				Program.ship.setProgram(null);
				break;
			}
		}

	}

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

	public int getCurrentPos() {
		return currentPos;
	}

	public void setCurrentPos(int currentPos) {
		this.currentPos = currentPos;
	}

	public Boolean getPaused() {
		return paused;
	}

	public void setPaused(Boolean paused) {
		this.paused = paused;
	}

}
