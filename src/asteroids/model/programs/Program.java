package asteroids.model.programs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import asteroids.model.Ship;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.statement.Statement;
import asteroids.model.programs.statement.basicStatement.Sequence;

public class Program {

	private Map<String, Expression> values 	= new HashMap<String, Expression>();
	public static Ship ship;
	private Sequence sequence	 			= null;
	public static Boolean paused 			= false;

	public Program (Map<String, Type> globals, Statement statement) {
		if (!(statement instanceof Sequence))
			throw new IllegalArgumentException();
		setSequence((Sequence)statement);
	}

	public void executeNextCommand() {
		Boolean done = false;
		while(!done) {
			if (getSequence().getStatements().size() == 0)
				Program.ship.setProgram(null);
			else if (getSequence().getStatements().size() == 1)
				throw new RuntimeException();
			else {
				List<Statement> statements = getSequence().getStatements();
				statements.get(0).execute();
				//				if (statements.get(0) instanceof DisableThruster)
				//					done = true;
				//				if (statements.get(0) instanceof EnableThruster)
				//					done = true;
				//				if (statements.get(0) instanceof Fire)
				//					done = true;
				//				if (statements.get(0) instanceof Turn)
				//					done = true;
				//				if (statements.get(0) instanceof Skip)
				//					done = true;
				
				if (getPaused())
					break;
				if (!(statements.get(1) instanceof Sequence))
					throw new IllegalArgumentException();
				setSequence((Sequence)statements.get(1));

			}
		}
	}

	public Map<String, Expression> getValues() {
		return values;
	}

	public void setValues(Map<String, Expression> values) {
		this.values = values;
	}


	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	public static Ship getShip() {
		return ship;
	}

	public static void setShip(Ship ship) {
		Program.ship = ship;
	}

	public static Boolean getPaused() {
		return paused;
	}

	public static void setPaused(Boolean paused) {
		Program.paused = paused;
	}

}
