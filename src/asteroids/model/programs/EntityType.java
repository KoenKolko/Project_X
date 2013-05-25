package asteroids.model.programs;

import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.basicExpression.EntityLiteral;
import asteroids.model.programs.expression.basicExpression.Null;
import asteroids.model.programs.expression.basicExpression.Self;
import asteroids.model.programs.expression.basicExpression.Variable;

public class EntityType extends Type {

	public static boolean checkTypeStatic(Expression e) {
		if (e instanceof Variable)
			return Program.getGlobals().get(((Variable)e).getString()) instanceof EntityType;
		else if (EntityLiteral.class.isAssignableFrom(e.getClass()))
			return true;
		return false;
	}

	public boolean checkType(Expression e) {
		return EntityType.checkTypeStatic(e);
	}

}
