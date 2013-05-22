package asteroids.model.programs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import asteroids.model.Ship;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.statement.Statement;
import asteroids.model.programs.statement.basicStatement.Assignment;
import asteroids.model.programs.statement.basicStatement.Sequence;

public class Program {
	
	private Map<String, Expression> values 	= new HashMap<String, Expression>();
	private Ship ship;
	ArrayList<Statement> statements 		= new ArrayList<Statement>();
	private int statementIndex 				= 0;
	
	public Program (Map<String, Type> globals, Statement statement) {
		if (!(statement instanceof Sequence))
			throw new IllegalArgumentException();
		for (Statement s : ((Sequence)statement).getStatements())
			getStatements().add(s);
		
	}
	
	public void executeNextCommand() {
		Statement newStatement = getStatements().get(getStatementIndex());
		if (newStatement instanceof Assignment)
			getValues().put( ((Assignment)newStatement).getString(), ((Assignment)newStatement).getExpression());
		
		else newStatement.execute(getShip());
		incrStatementIndex();
	}

	public Map<String, Expression> getValues() {
		return values;
	}

	public void setValues(Map<String, Expression> values) {
		this.values = values;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public ArrayList<Statement> getStatements() {
		return statements;
	}

	public void setStatements(ArrayList<Statement> statements) {
		this.statements = statements;
	}

	public int getStatementIndex() {
		return statementIndex;
	}

	public void setStatementIndex(int statementIndex) {
		this.statementIndex = statementIndex;
	}
	
	public void incrStatementIndex() {
		this.statementIndex++;
	}

}
