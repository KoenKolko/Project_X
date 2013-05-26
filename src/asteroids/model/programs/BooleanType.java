package asteroids.model.programs;

import asteroids.model.programs.expression.BooleanExpression;
import asteroids.model.programs.expression.DoubleExpression;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.expression.basicExpression.BooleanLiteral;
import asteroids.model.programs.expression.basicExpression.DoubleLiteral;
import asteroids.model.programs.expression.basicExpression.EntityLiteral;
import asteroids.model.programs.expression.basicExpression.Variable;

public class BooleanType extends Type {

	public static boolean checkTypeStatic(Expression e) {
		if (e == null)
			return true;
		if (e instanceof Variable)
		{
			if (!Program.getGlobals().containsKey(((Variable)e).getString()))
				return false;
			return Program.getGlobals().get(((Variable)e).getString()) instanceof BooleanType;
		}

		else if (e instanceof BooleanExpression)
		{
			Expression e1 = ((BooleanExpression)e).getE1();
			Expression e2 = ((BooleanExpression)e).getE2();
			if (e1 instanceof Variable && e2 instanceof Variable)
			{
				try{

					if (Program.getGlobals().get(((Variable)e1).getString()) instanceof DoubleType) 
						return DoubleType.checkTypeStatic(e1) && DoubleType.checkTypeStatic(e2);
					else if (Program.getGlobals().get(((Variable)e1).getString()) instanceof EntityType)
						return EntityType.checkTypeStatic(e1) && EntityType.checkTypeStatic(e2);
					else return checkTypeStatic( e1 ) && checkTypeStatic( e2 );
				}
				catch (NullPointerException exception) {return false;}
			}
			else if ( (DoubleExpression.class.isAssignableFrom(e1.getClass()) || DoubleLiteral.class.isAssignableFrom(e1.getClass())) 
					|| (DoubleExpression.class.isAssignableFrom(e2.getClass()) || DoubleLiteral.class.isAssignableFrom(e2.getClass())))
				return DoubleType.checkTypeStatic(e1) && DoubleType.checkTypeStatic(e2);
			else if ( EntityLiteral.class.isAssignableFrom(e1.getClass()) 
					|| EntityLiteral.class.isAssignableFrom(e2.getClass()))
				return EntityType.checkTypeStatic(e1) && EntityType.checkTypeStatic(e2);
			else return checkTypeStatic( e1 ) && checkTypeStatic( e2 );
		}
		else if (e instanceof BooleanLiteral)
			return true;
		return false;
	}

	public boolean checkType(Expression e) {
		return BooleanType.checkTypeStatic(e);
	}


}
