package asteroids.model.programs.statement.basicStatement;


import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.SpaceObject;
import asteroids.model.World;
import asteroids.model.programs.Program;
import asteroids.model.programs.expression.basicExpression.EntityLiteral;
import asteroids.model.programs.parsing.ProgramFactory.ForeachType;
import asteroids.model.programs.statement.BasicStatement;
import asteroids.model.programs.statement.Statement;

public class Foreach extends BasicStatement {

	private String variableName;
	private Statement body;
	private ForeachType type;

	public Foreach(int line, int column, ForeachType type, String variableName, Statement body) {
		super(line, column);
		setVariableName(variableName);
		setBody(body);
		setType(type);
	}

	@Override
	public void execute() {
		World world = Program.ship.getWorld();
		Program program = Program.ship.getProgram();
		switch (getType())
		{
		case SHIP:
			for (Ship s : world.getShips())
			{
				program.getValues().put(getVariableName(), new EntityLiteral(getLine(), getColumn(), s));
				getBody().execute();
			}
				
		case ASTEROID:
			for (Asteroid a : world.getAsteroids())
			{
				program.getValues().put(getVariableName(), new EntityLiteral(getLine(), getColumn(), a));
				getBody().execute();
			}
		case BULLET:
			for (Bullet b : world.getBullets())
			{
				program.getValues().put(getVariableName(), new EntityLiteral(getLine(), getColumn(), b));
				getBody().execute();
			}
		case ANY:
			for (SpaceObject s : world.getSpaceObjects())
			{
				program.getValues().put(getVariableName(), new EntityLiteral(getLine(), getColumn(), s));
				getBody().execute();
			}
		}

	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		this.body = body;
	}

	public ForeachType getType() {
		return type;
	}

	public void setType(ForeachType type) {
		this.type = type;
	}

}
