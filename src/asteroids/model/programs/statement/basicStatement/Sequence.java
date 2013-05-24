package asteroids.model.programs.statement.basicStatement;

import java.util.List;

import asteroids.model.programs.statement.BasicStatement;
import asteroids.model.programs.statement.Statement;

public class Sequence extends BasicStatement {

	List<Statement> statements;
	
	public Sequence(int line, int column, List<Statement> statements) {
		super(line, column);
		setStatements(statements);
		
	}

	@Override
	public void execute() {
		for (Statement s : getStatements())
			s.execute();
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

}
