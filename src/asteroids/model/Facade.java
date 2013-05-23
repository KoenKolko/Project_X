package asteroids.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.antlr.v4.runtime.RecognitionException;

import asteroids.CollisionListener;
import asteroids.IFacade;
import asteroids.ModelException;
import asteroids.Vector;
import asteroids.model.programs.Program;
import asteroids.model.programs.Type;
import asteroids.model.programs.expression.Expression;
import asteroids.model.programs.parsing.ProgramFactoryImpl;
import asteroids.model.programs.parsing.ProgramParser;
import asteroids.model.programs.statement.Statement;

@SuppressWarnings("rawtypes")
public class Facade implements IFacade {

	@Override
	public Object createWorld(double width, double height) {
		Vector dimensions = new Vector(width, height);
		return new World(dimensions);
	}

	@Override
	public double getWorldWidth(Object world) {
		return ((World) world).getWidth();
	}

	@Override
	public double getWorldHeight(Object world) {
		return ((World) world).getHeigth();
	}

	@Override
	public Set getShips(Object world) {
		return ((World) world).getShips();
	}

	@Override
	public Set getAsteroids(Object world) {
		return ((World) world).getAsteroids();
	}
	
	@Override
	public Set getBullets(Object world) {
		return ((World) world).getBullets();
	}

	@Override
	public void addShip(Object world, Object ship) {
		((World) world).addObject((Ship) ship);
	}

	@Override
	public void addAsteroid(Object world, Object asteroid) {
		((World) world).addObject((Asteroid) asteroid);
	}

	@Override
	public void removeShip(Object world, Object ship) {
		((World) world).removeObject((Ship) ship);
	}

	@Override
	public void removeAsteroid(Object world, Object asteroid) {
		((World) world).removeObject((Asteroid) asteroid);
	}

	@Override
	public void evolve(Object world, double dt,
			CollisionListener collisionListener) {
		((World) world).evolve(dt, collisionListener);
		
	}

	@Override
	public Object createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double direction, double mass) {
		try { 
			Vector location = new Vector (x,y);
			Vector velocity = new Vector (xVelocity, yVelocity);
			return new Ship(location, velocity, radius, direction, mass); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occured."); }
	}

	@Override
	public boolean isShip(Object o) {
		if (o instanceof Ship)
			return true;
		return false;
	}

	@Override
	public double getShipX(Object ship) {
		try { return ((Ship) ship).getLocation().getX(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getShipY(Object ship) {
		try {return ((Ship) ship).getLocation().getY();}
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getShipXVelocity(Object ship) {
		try {return ((Ship) ship).getVelocity().getX(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }

	}

	@Override
	public double getShipYVelocity(Object ship) {
		try {return ((Ship) ship).getVelocity().getY(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }

	}

	@Override
	public double getShipRadius(Object ship) {
		try { return ((Ship) ship).getRadius(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getShipDirection(Object ship) {
		try {return ((Ship) ship).getAngle(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }

	}

	@Override
	public double getShipMass(Object ship) {
		try {return ((Ship) ship).getMass(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public Object getShipWorld(Object ship) {
		try {return ((Ship) ship).getWorld(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public boolean isShipThrusterActive(Object ship) {
		try {return ((Ship) ship).getThruster(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public void setThrusterActive(Object ship, boolean active) {
		try {((Ship) ship).setThruster(active); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public void turn(Object ship, double angle) {
		try {((Ship) ship).turn(angle);}
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }

	}

	@Override
	public void fireBullet(Object ship) {
		try {((Ship) ship).fireBullet();}
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }

	}

	@Override
	public Object createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius) {
		try { 
			Vector location = new Vector (x,y);
			Vector velocity = new Vector (xVelocity, yVelocity);
			return new Asteroid(location, velocity, radius); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occured."); }
	}

	@Override
	public Object createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius, Random random) {
		try {
			Vector position = new Vector(x,y);
			Vector velocity = new Vector(xVelocity,yVelocity);
			return new Asteroid(position, velocity, radius, random);
		}
		catch (RuntimeException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public boolean isAsteroid(Object o) {
		if (o instanceof Asteroid)
			return true;
		return false;
	}

	@Override
	public double getAsteroidX(Object asteroid) {
		try { return ((Asteroid) asteroid).getLocation().getX(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getAsteroidY(Object asteroid) {
		try { return ((Asteroid) asteroid).getLocation().getY(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getAsteroidXVelocity(Object asteroid) {
		try {return ((Asteroid) asteroid).getVelocity().getX(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getAsteroidYVelocity(Object asteroid) {
		try {return ((Asteroid) asteroid).getVelocity().getY(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }

	}

	@Override
	public double getAsteroidRadius(Object asteroid) {
		try {return ((Asteroid) asteroid).getRadius(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getAsteroidMass(Object asteroid) {
		try {return ((Asteroid) asteroid).getMass(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public Object getAsteroidWorld(Object asteroid) {
		try {return ((Asteroid) asteroid).getWorld(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public boolean isBullets(Object o) {
		if (o instanceof Bullet)
			return true;
		return false;
	}

	@Override
	public double getBulletX(Object bullet) {
		try { return ((Bullet) bullet).getLocation().getX(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getBulletY(Object bullet) {
		try { return ((Bullet) bullet).getLocation().getY(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getBulletXVelocity(Object bullet) {
		try {return ((Bullet) bullet).getVelocity().getX(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getBulletYVelocity(Object bullet) {
		try {return ((Bullet) bullet).getVelocity().getX(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getBulletRadius(Object bullet) {
		try {return ((Bullet) bullet).getRadius(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getBulletMass(Object bullet) {
		try {return ((Bullet) bullet).getMass(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public Object getBulletWorld(Object bullet) {
		try {return ((Bullet) bullet).getWorld(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public Object getBulletSource(Object bullet) {
		try {return ((Bullet) bullet).getSource(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public ParseOutcome parseProgram(String text) {
		ProgramFactoryImpl factory = new ProgramFactoryImpl();
	    ProgramParser<Expression, Statement, Type> parser = new ProgramParser<>(factory);
	    try {
	        parser.parse(text);
	        List<String> errors = parser.getErrors();
	        if(! errors.isEmpty()) {
	          return ParseOutcome.failure(errors.get(0));
	        } else {
	          return ParseOutcome.success(new Program(parser.getGlobals(), parser.getStatement())); 
	        }
	    } catch(RecognitionException e) {
	      return ParseOutcome.failure(e.getMessage());
	    }
		
	}

	@Override
	public ParseOutcome loadProgramFromStream(InputStream stream)
			throws IOException {
		String string = "";
		int charNumber = 0;
		while (charNumber != -1)
		{
			charNumber = stream.read();
			string += Character.toString((char) charNumber);
		}
		stream.close();
		System.out.println(string);
		return parseProgram(string);
	
	}

	@Override
	public ParseOutcome loadProgramFromUrl(URL url) throws IOException {
		InputStream input = url.openStream();
		return loadProgramFromStream(input);
	}

	@Override
	public boolean isTypeCheckingSupported() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TypeCheckOutcome typeCheckProgram(Object program) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShipProgram(Object ship, Object program) {
		if (!(ship instanceof Ship) || !(program instanceof Program))
			throw new IllegalArgumentException();
		((Program)program).setShip((Ship)ship);
		((Ship)ship).setProgram((Program)program);
		
	}	
	

}
