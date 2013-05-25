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
	private static Map<String, Type> globals;
	public static Ship ship;
	private Sequence sequence	 			= null;
	public static Boolean paused 			= false;
	public static Boolean typeCheckMode		= false;

	public Program (Map<String, Type> globals, Statement statement) {
		if (!(statement instanceof Sequence))
			throw new IllegalArgumentException();
		setSequence((Sequence)statement);
		setGlobals(globals);
		Program.setPaused(false);
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

	public static Map<String, Type> getGlobals() {
		return globals;
	}

	public static void setGlobals(Map<String, Type> globals) {
		Program.globals = globals;
	}

	public static Boolean getTypeCheckMode() {
		return typeCheckMode;
	}

	public static void setTypeCheckMode(Boolean typeCheckMode) {
		Program.typeCheckMode = typeCheckMode;
	}

	
}
