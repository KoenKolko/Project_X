package asteroids.model;

import java.util.Set;
import asteroids.Vector;
import asteroids.CollisionListener;

public class World {
	private static final double minSize = 0;
	private static final double maxSize = Double.MAX_VALUE;
	private Vector dimensions;
	private Set<Ship> allShips;
	private Set<Asteroid> allAsteroids;
	private Set<Bullet> allBullets;
	
	public World(Vector dimensions){
		setDimensions(dimensions);
	}
	
	private boolean isValidDimensions(Vector dimensions){
		return(dimensions.getX() >= minSize && dimensions.getX() <= maxSize && dimensions.getY() >= minSize && dimensions.getY() <= maxSize);
	}
	
	public void setDimensions(Vector dimensions){
		if (!isValidDimensions(dimensions))
			throw new IllegalArgumentException("Invalid dimension for world!");
		this.dimensions = dimensions;
	}
	
	public double getWidth(){
		return dimensions.getX();
	}
	
	public double getHeigth(){
		return dimensions.getY();
	}
	
	public Set<Ship> getShips(){
		return allShips;
	}
	
	public Set<Asteroid> getAsteroids(){
		return allAsteroids;
	}
	
	public Set<Bullet> getBullets(){
		return allBullets;
	}
	
	public void addShip(Ship ship){
		ship.setWorld(this);
		allShips.add(ship);		
	}
	
	public void addAsteroid(Asteroid asteroid){
		asteroid.setWorld(this);
		allAsteroids.add(asteroid);
	}
	
	public void removeShip(Ship ship){
		ship.setWorld(null);
		allShips.remove(ship);	
	}
	
	public void removeAsteroid(Asteroid asteroid){
		asteroid.setWorld(null);
		allAsteroids.remove(asteroid);
	}
	
	public void evolve(double dt, CollisionListener collisionListener){
		
	}
}
