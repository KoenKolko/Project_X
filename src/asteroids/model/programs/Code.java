package asteroids.model.programs;

import asteroids.model.SpaceObject;
import asteroids.model.programs.expression.BooleanExpression;
import asteroids.model.programs.expression.DoubleExpression;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.basicExpression.BooleanLiteral;
import asteroids.model.programs.expression.basicExpression.DoubleLiteral;
import asteroids.model.programs.expression.basicExpression.EntityLiteral;
import asteroids.model.programs.expression.basicExpression.Null;
import asteroids.model.programs.expression.basicExpression.Self;
import asteroids.model.programs.expression.basicExpression.Variable;

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
	
	public double checkDouble(Expression e) {
		double value = 0;
		Boolean found = false;
		while (!found){
			found = true;
			if (e instanceof DoubleLiteral)
				value = ((DoubleLiteral)e).getValue();
			else if (e instanceof DoubleExpression)
				value = ((DoubleExpression)e).getValue();
			else if (e instanceof Variable)
			{
				e = (((Variable)e).getValue());
				found = false;
			}
			else throw new RuntimeException();

		}
		return value;
	}
	
	public Boolean checkBoolean(Expression e) {
		Boolean value = false;
		Boolean found = false;
		while (!found){
			found = true;
			if (e instanceof BooleanLiteral)
				value = ((BooleanLiteral)e).getValue();
			else if (e instanceof BooleanExpression)
				value = ((BooleanExpression)e).getValue();
			else if (e instanceof Variable)
			{
				e = (((Variable)e).getValue());
				found = false;
			}
			else throw new RuntimeException();

		}
		return value;
	}
	
	public SpaceObject checkEntity(Expression e) {
		SpaceObject value = null;
		Boolean found = false;
		while (!found){
			found = true;
			if (e instanceof EntityLiteral || e instanceof Null || e instanceof Self)
				value = ((EntityLiteral)e).getValue();
			else if (e instanceof Variable)
			{
				if (Program.getTypeCheckMode())
					value = null;
				else 
				{
				e = (((Variable)e).getValue());
				found = false;
				}
			}
			else throw new RuntimeException();

		}
		return value;
	}

}
