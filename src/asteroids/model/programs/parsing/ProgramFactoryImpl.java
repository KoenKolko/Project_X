package asteroids.model.programs.parsing;

import java.util.List;

import asteroids.model.programs.expression.*;
import asteroids.model.programs.expression.basicExpression.*;
import asteroids.model.programs.expression.booleanExpression.*;
import asteroids.model.programs.expression.doubleExpression.*;

public class ProgramFactoryImpl implements ProgramFactory {

	@Override
	public Object createDoubleLiteral(int line, int column, double d) {
		return new DoubleLiteral(line, column, d);
	}

	@Override
	public Object createBooleanLiteral(int line, int column, boolean b) {
		return new BooleanLiteral(line, column, b);
	}

	@Override
	public Object createAnd(int line, int column, Object e1, Object e2) {
		if (!(e1 instanceof Expression) || !(e2 instanceof Expression))
			throw new IllegalArgumentException();
		return new And(line, column, ((Expression)e1), ((Expression)e2));
	}

	@Override
	public Object createOr(int line, int column, Object e1, Object e2) {
		if (!(e1 instanceof Expression) || !(e2 instanceof Expression))
			throw new IllegalArgumentException();
		return new Or(line, column, ((Expression)e1), ((Expression)e2));
	}

	@Override
	public Object createNot(int line, int column, Object e) {
		if (!(e instanceof Expression))
			throw new IllegalArgumentException();
		return new Not(line, column, ((Expression)e));
	}

	@Override
	public Object createNull(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createSelf(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createGetX(int line, int column, Object e) {
		if (!(e instanceof Expression))
			throw new IllegalArgumentException();
		return new GetX(line, column, ((Expression)e));
	}

	@Override
	public Object createGetY(int line, int column, Object e) {
		if (!(e instanceof Expression))
			throw new IllegalArgumentException();
		return new GetY(line, column, ((Expression)e));
	}

	@Override
	public Object createGetVX(int line, int column, Object e) {
		if (!(e instanceof Expression))
			throw new IllegalArgumentException();
		return new GetVX(line, column, ((Expression)e));
	}

	@Override
	public Object createGetVY(int line, int column, Object e) {
		if (!(e instanceof Expression))
			throw new IllegalArgumentException();
		return new GetVY(line, column, ((Expression)e));
	}

	@Override
	public Object createGetRadius(int line, int column, Object e) {
		if (!(e instanceof Expression))
			throw new IllegalArgumentException();
		return new GetRadius(line, column, ((Expression)e));
	}

	@Override
	public Object createVariable(int line, int column, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createLessThan(int line, int column, Object e1, Object e2) {
		if (!(e1 instanceof Expression) || !(e2 instanceof Expression))
			throw new IllegalArgumentException();
		return new LessThan(line, column, ((Expression)e1), ((Expression)e2));
	}

	@Override
	public Object createGreaterThan(int line, int column, Object e1, Object e2) {
		if (!(e1 instanceof Expression) || !(e2 instanceof Expression))
			throw new IllegalArgumentException();
		return new GreaterThan(line, column, ((Expression)e1), ((Expression)e2));
	}

	@Override
	public Object createLessThanOrEqualTo(int line, int column, Object e1, Object e2) {
		if (!(e1 instanceof Expression) || !(e2 instanceof Expression))
			throw new IllegalArgumentException();
		return new LessThanOrEqualTo(line, column, ((Expression)e1), ((Expression)e2));
	}

	@Override
	public Object createGreaterThanOrEqualTo(int line, int column, Object e1, Object e2) {
		if (!(e1 instanceof Expression) || !(e2 instanceof Expression))
			throw new IllegalArgumentException();
		return new GreaterThanOrEqualTo(line, column, ((Expression)e1), ((Expression)e2));
	}

	@Override
	public Object createEquality(int line, int column, Object e1, Object e2) {
		if (!(e1 instanceof Expression) || !(e2 instanceof Expression))
			throw new IllegalArgumentException();
		return new Equality(line, column, ((Expression)e1), ((Expression)e2));
	}

	@Override
	public Object createInequality(int line, int column, Object e1, Object e2) {
		if (!(e1 instanceof Expression) || !(e2 instanceof Expression))
			throw new IllegalArgumentException();
		return new Inequality(line, column, ((Expression)e1), ((Expression)e2));
	}

	@Override
	public Object createAdd(int line, int column, Object e1, Object e2) {
		if (!(e1 instanceof Expression) || !(e2 instanceof Expression))
			throw new IllegalArgumentException();
		return new Add(line, column, ((Expression)e1), ((Expression)e2));
	}

	@Override
	public Object createSubtraction(int line, int column, Object e1, Object e2) {
		if (!(e1 instanceof Expression) || !(e2 instanceof Expression))
			throw new IllegalArgumentException();
		return new Subtraction(line, column, ((Expression)e1), ((Expression)e2));
	}

	@Override
	public Object createMul(int line, int column, Object e1, Object e2) {
		if (!(e1 instanceof Expression) || !(e2 instanceof Expression))
			throw new IllegalArgumentException();
		return new Mul(line, column, ((Expression)e1), ((Expression)e2));
	}

	@Override
	public Object createDivision(int line, int column, Object e1, Object e2) {
		if (!(e1 instanceof Expression) || !(e2 instanceof Expression))
			throw new IllegalArgumentException();
		return new Division(line, column, ((Expression)e1), ((Expression)e2));
	}

	@Override
	public Object createSqrt(int line, int column, Object e) {
		if (!(e instanceof Expression))
			throw new IllegalArgumentException();
		return new Sqrt(line, column, ((Expression)e));
	}

	@Override
	public Object createGetDirection(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createSin(int line, int column, Object e) {
		if (!(e instanceof Expression))
			throw new IllegalArgumentException();
		return new Sin(line, column, ((Expression)e));
	}

	@Override
	public Object createCos(int line, int column, Object e) {
		if (!(e instanceof Expression))
			throw new IllegalArgumentException();
		return new Cos(line, column, ((Expression)e));
	}

	@Override
	public Object createEnableThruster(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createDisableThruster(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createFire(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createTurn(int line, int column, Object angle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createAssignment(int line, int column, String variable,
			Object rhs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createIf(int line, int column, Object condition, Object then,
			Object otherwise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createWhile(int line, int column, Object condition,
			Object body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createForeach(int line, int column, ForeachType type,
			String variableName, Object body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createSkip(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createSequence(int line, int column, List statements) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createPrint(int line, int column, Object e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createDoubleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createBooleanType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createEntityType() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
