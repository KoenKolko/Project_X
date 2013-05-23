package asteroids.model.programs;

import java.util.HashMap;
import java.util.Map;

import asteroids.model.Ship;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.statement.Statement;
import asteroids.model.programs.statement.basicStatement.Sequence;

public class Program {

	private Map<String, Expression> values 	= new HashMap<String, Expression>();
	private Ship ship;
	private Sequence initialSequence 		= null;
	private Sequence sequence	 			= null;

	public Program (Map<String, Type> globals, Statement statement) {
		if (!(statement instanceof Sequence))
			throw new IllegalArgumentException();
		System.out.println(((Sequence)statement).getStatements().size());
		setSequence((Sequence)statement);
		setInitialSequence(getSequence());
	}

	public void executeNextCommand() {
		if (getSequence().getStatements().size() == 0)
			setSequence(getInitialSequence());
		getSequence().getStatements().get(0).execute(getShip());
		if (!(getSequence().getStatements().get(1) instanceof Sequence))
			throw new IllegalArgumentException();
		setSequence((Sequence)getSequence().getStatements().get(1));
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

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	public Sequence getInitialSequence() {
		return initialSequence;
	}

	public void setInitialSequence(Sequence initialSequence) {
		this.initialSequence = initialSequence;
	}

}
