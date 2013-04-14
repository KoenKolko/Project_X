package asteroids;

import asteroids.model.SpaceObject;

public class Collision {

	private double time;
	private SpaceObject entity1;
	private SpaceObject entity2;
	
	// Constructor for collision with 2 objects
	public Collision (SpaceObject entity1, SpaceObject entity2, double timeToCollision) {
		this.time = timeToCollision;
		this.entity1 = entity1;
		this.entity2 = entity2;
	}

	
	// Constructor for collision with boundaries of a world.
	public Collision (SpaceObject entity1, double timeToCollisionWithBoundaries) {
		this.time = timeToCollisionWithBoundaries;
		this.entity1 = entity1;
		this.entity2 = null;
	}


	public double getTime() {
		return time;
	}


	public void setTime(double time) {
		this.time = time;
	}


	public SpaceObject getEntity1() {
		return entity1;
	}


	public void setEntity1(SpaceObject entity1) {
		this.entity1 = entity1;
	}


	public SpaceObject getEntity2() {
		return entity2;
	}


	public void setEntity2(SpaceObject entity2) {
		this.entity2 = entity2;
	}
	
	
}
