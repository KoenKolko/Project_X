package asteroids.model.programs;

import asteroids.model.programs.expression.DoubleExpression;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.basicExpression.DoubleLiteral;
import asteroids.model.programs.expression.basicExpression.Variable;

public class DoubleType extends Type {

	public static boolean checkTypeStatic(Expression e) {
		if (e == null)
			return true;
		if (e instanceof Variable)
		{
			if (!Program.getGlobals().containsKey(((Variable)e).getString()))
				return false;
			return Program.getGlobals().get(((Variable)e).getString()) instanceof DoubleType;
		}
		else if (e instanceof DoubleExpression)
			return checkTypeStatic( ((DoubleExpression)e).getE1() ) && checkTypeStatic( ((DoubleExpression)e).getE2() );
		else if (e instanceof DoubleLiteral)
			return true;
		return false;
	}

	public boolean checkType(Expression e) {
		return DoubleType.checkTypeStatic(e);
	}

	
}
