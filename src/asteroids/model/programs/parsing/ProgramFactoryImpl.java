package asteroids.model.programs.parsing;

import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Variable;

import asteroids.model.programs.Type;
import asteroids.model.programs.expression.*;
import asteroids.model.programs.expression.basicExpression.*;
import asteroids.model.programs.expression.booleanExpression.*;
import asteroids.model.programs.expression.doubleExpression.*;
import asteroids.model.programs.statement.Statement;
import asteroids.model.programs.statement.actionStatement.*;
import asteroids.model.programs.statement.basicStatement.*;

public class ProgramFactoryImpl implements ProgramFactory<Expression, Statement, Type> {

	@Override
	public Expression createDoubleLiteral(int line, int column, double d) {
		return new DoubleLiteral(line, column, d);
	}

	@Override
	public Expression createBooleanLiteral(int line, int column, boolean b) {
		return new BooleanLiteral(line, column, b);
	}

	@Override
	public Expression createAnd(int line, int column, Expression e1, Expression e2) {
		return new And(line, column, e1, e2);
	}

	@Override
	public Expression createOr(int line, int column, Expression e1, Expression e2) {
		return new Or(line, column, e1, e2);
	}

	@Override
	public Expression createNot(int line, int column, Expression e) {
		return new Not(line, column, e);
	}

	@Override
	public Expression createNull(int line, int column) {
		return new EntityLiteral(line, column, null);
	}

	@Override
	public Expression createSelf(int line, int column) {
		return new EntityLiteral(line, column, null);
	}

	@Override
	public Expression createGetX(int line, int column, Expression e) {
		return new GetX(line, column, e);
	}

	@Override
	public Expression createGetY(int line, int column, Expression e) {
		return new GetY(line, column, e);
	}

	@Override
	public Expression createGetVX(int line, int column, Expression e) {
		return new GetVX(line, column, e);
	}

	@Override
	public Expression createGetVY(int line, int column, Expression e) {
		return new GetVY(line, column, e);
	}

	@Override
	public Expression createGetRadius(int line, int column, Expression e) {
		return new GetRadius(line, column, e);
	}

	@Override
	public Expression createVariable(int line, int column, String name) {
		return new asteroids.model.programs.expression.basicExpression.Variable(line, column, name);
	}

	@Override
	public Expression createLessThan(int line, int column, Expression e1, Expression e2) {
		return new LessThan(line, column, e1, e2);
	}

	@Override
	public Expression createGreaterThan(int line, int column, Expression e1, Expression e2) {
		return new GreaterThan(line, column, e1, e2);
	}

	@Override
	public Expression createLessThanOrEqualTo(int line, int column, Expression e1, Expression e2) {
		return new LessThanOrEqualTo(line, column, e1, e2);
	}

	@Override
	public Expression createGreaterThanOrEqualTo(int line, int column, Expression e1, Expression e2) {
		return new GreaterThanOrEqualTo(line, column, e1, e2);
	}

	@Override
	public Expression createEquality(int line, int column, Expression e1, Expression e2) {
		return new Equality(line, column, e1, e2);
	}

	@Override
	public Expression createInequality(int line, int column, Expression e1, Expression e2) {
		return new Inequality(line, column, e1, e2);
	}

	@Override
	public Expression createAdd(int line, int column, Expression e1, Expression e2) {
		return new Add(line, column, e1, e2);
	}

	@Override
	public Expression createSubtraction(int line, int column, Expression e1, Expression e2) {
		return new Subtraction(line, column, e1, e2);
	}

	@Override
	public Expression createMul(int line, int column, Expression e1, Expression e2) {
		return new Mul(line, column, e1, e2);
	}

	@Override
	public Expression createDivision(int line, int column, Expression e1, Expression e2) {
		if (!(e1 instanceof Expression) || !(e2 instanceof Expression))
			throw new IllegalArgumentException();
		return new Division(line, column, ((Expression)e1), ((Expression)e2));
	}

	@Override
	public Expression createSqrt(int line, int column, Expression e) {
		if (!(e instanceof Expression))
			throw new IllegalArgumentException();
		return new Sqrt(line, column, ((Expression)e));
	}

	@Override
	public Expression createGetDirection(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSin(int line, int column, Expression e) {
		if (!(e instanceof Expression))
			throw new IllegalArgumentException();
		return new Sin(line, column, ((Expression)e));
	}

	@Override
	public Expression createCos(int line, int column, Expression e) {
		if (!(e instanceof Expression))
			throw new IllegalArgumentException();
		return new Cos(line, column, ((Expression)e));
	}

	@Override
	public Statement createEnableThruster(int line, int column) {
		return new EnableThruster(line, column);
	}

	@Override
	public Statement createDisableThruster(int line, int column) {
		return new DisableThruster(line, column);
	}

	@Override
	public Statement createFire(int line, int column) {
		return new Fire(line, column);
	}

	@Override
	public Statement createTurn(int line, int column, Expression angle) {
		return new Turn(line, column, angle);
	}

	@Override
	public Statement createAssignment(int line, int column, String variable, Expression rhs) {
		return new Assignment(line, column, variable, rhs);
	}

	@Override
	public Statement createIf(int line, int column, Expression condition, Statement then, Statement otherwise) {
		return new If(line, column, condition, then, otherwise);
	}

	@Override
	public Statement createWhile(int line, int column, Expression condition, Statement body) {
		return new While(line, column, condition, body);
	}

	@Override
	public Statement createForeach(int line, int column, ForeachType type, String variableName, Statement body) {
		System.out.println("foreach");
		return new Foreach(line, column, type, variableName, body);
	}

	@Override
	public Statement createSkip(int line, int column) {
		return new Skip(line, column);
	}

	@Override
	public Statement createSequence(int line, int column, List<Statement> statements) {
		return new Sequence(line, column, statements);
	}

	@Override
	public Statement createPrint(int line, int column, Expression e) {
		return new Print(line, column, e);
	}

	@Override
	public Type createDoubleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type createBooleanType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type createEntityType() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
