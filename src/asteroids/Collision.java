package asteroids;

import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Ship;
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
	
	public void resolve () {
	
	// Option 1: Entity1 collides with boundaries.
			if (entity2 == null)
			{
				if (Util.fuzzyEquals(entity1.getLocation().getX()+entity1.getRadius(), entity1.getWorld().getWidth())	||	Util.fuzzyEquals(entity1.getLocation().getX()-entity1.getRadius(), 0.0) )
				{
					Vector temp = entity1.getVelocity();
					entity1.setVelocity(new Vector(-temp.getX(), temp.getY()));
				}

				else 
				{
					Vector temp = entity1.getVelocity();
					entity1.setVelocity(new Vector(temp.getX(), -temp.getY()));
				}

				if (entity1 instanceof Bullet)
					((Bullet) entity1).increaseCollisionCounter();

			}
			// Option 2: Entity1 collides with Entity2.
			else 
			{
				if (entity1 instanceof Bullet || entity2 instanceof Bullet)
				{
					entity1.die();
					entity2.die();
				}

				if ( (entity1 instanceof Ship && entity2 instanceof Ship) || (entity1 instanceof Asteroid && entity2 instanceof Asteroid))
				{
					setBounceVelocity();
					return;
				}

				if (entity1 instanceof Asteroid && entity2 instanceof Ship)
					entity2.die();
				if (entity2 instanceof Asteroid && entity1 instanceof Ship)
					entity1.die();


			}
		}

		private void setBounceVelocity () {
			double sigma = entity1.getRadius() + entity2.getRadius();
            double m1 = entity1.getMass();
            double m2 = entity2.getMass();
            Vector deltaR = entity1.getLocation().subtract(entity2.getLocation());
            Vector deltaV = entity1.getVelocity().subtract(entity2.getVelocity());
            double deltaVR = deltaV.multiply(deltaV);
            double J =      (2 * m1 * m2 * deltaVR )  / ( sigma * (m1 + m2) );
            double Jx = (J * deltaR.getX()) / sigma;
            double Jy = (J * deltaR.getY()) / sigma;
           

            Vector vel1 = new Vector(entity1.getVelocity().getX() + Jx/m1, entity1.getVelocity().getY() + Jy/m1);
            Vector vel2 = new Vector(entity2.getVelocity().getX() - Jx/m2, entity2.getVelocity().getY() - Jy/m2);
            entity1.setVelocity(vel1);
            entity2.setVelocity(vel2);

		}
	
	
}
