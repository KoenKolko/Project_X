package asteroids.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import asteroids.Collision;
import asteroids.Util;
import asteroids.Vector;
import asteroids.CollisionListener;

public class World {
	private static final double minSize = 0;
	private static final double maxSize = Double.MAX_VALUE;
	private Vector dimensions;
	private Set<Ship> allShips = new HashSet<Ship>();
	private Set<Asteroid> allAsteroids = new HashSet<Asteroid>();
	private Set<Bullet> allBullets = new HashSet<Bullet>();

	public World(Vector dimensions){
		setDimensions(dimensions);
	}

	private boolean isValidDimensions(Vector dimensions){
		return(	dimensions.getX() >= minSize && 
				dimensions.getX() <= maxSize && 
				dimensions.getY() >= minSize && 
				dimensions.getY() <= maxSize);
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

	public ArrayList<SpaceObject> getSpaceObjects() {
		ArrayList<SpaceObject> spaceobjects = new ArrayList<SpaceObject>();
		spaceobjects.addAll(allShips);
		spaceobjects.addAll(allAsteroids);
		spaceobjects.addAll(allBullets);
		return spaceobjects;
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


	public void evolve(double dt, CollisionListener collisionListener) {
		
		Comparator<Collision> comparator = new CollisionComparitor();
		PriorityQueue<Collision> pq = new PriorityQueue<Collision>(2, comparator);
		for(SpaceObject p : getSpaceObjects())
		{
			pq.add(new Collision(p, p.collisionTimeWithBoundaries()));
			for(SpaceObject q : getSpaceObjects())
			{
				Collision collision = new Collision(p,q, p.getTimeToCollision(q));
				pq.add(collision);
			}
		}
		Collision nearestCollision = pq.poll();
		double firstCollisionTime = nearestCollision.getTime();
		while(firstCollisionTime <= dt)
		{
			//System.out.println(firstCollisionTime);
			for(SpaceObject object : getSpaceObjects()) object.move(firstCollisionTime);
			resolve(nearestCollision);
			dt -= firstCollisionTime;
			Comparator<Collision> comparatorLocal = new CollisionComparitor();
			PriorityQueue<Collision> pqLocal = new PriorityQueue<Collision>(2, comparatorLocal);
			for(SpaceObject p : getSpaceObjects())
			{
				pqLocal.add(new Collision(p, p.collisionTimeWithBoundaries()));
				for(SpaceObject q : getSpaceObjects())
				{
					Collision collision = new Collision(p,q, p.getTimeToCollision(q));
					pqLocal.add(collision);
				}
			}
			nearestCollision = pqLocal.poll();
			firstCollisionTime = nearestCollision.getTime();
		}
		for(SpaceObject object : getSpaceObjects()) object.move(dt);
	}
	private void resolve (Collision collision) {
	
		SpaceObject entity1 = collision.getEntity1();
		SpaceObject entity2 = collision.getEntity2();
	
		// Option 1: Entity1 collides with boundaries.
		if (entity2 == null)
		{
			if (Util.fuzzyEquals(entity1.getLocation().getX()+entity1.getRadius(), getWidth())	||	Util.fuzzyEquals(entity1.getLocation().getX()-entity1.getRadius(), 0.0) )
			{
				Vector temp = entity1.getVelocity();
				entity1.setVelocity(new Vector(-temp.getX(), temp.getY()));
			}
	
			else 
			{
				Vector temp = entity1.getVelocity();
				entity1.setVelocity(new Vector(temp.getX(), -temp.getY()));
			}
	
		}
		// Option 2: Entity1 collides with Entity2.
		else 
		{
			double sigma = Math.sqrt( 	Math.pow(entity1.getRadius()*entity1.getVelocity().getX() - entity2.getRadius()*entity2.getVelocity().getX(), 2)   +
										Math.pow(entity1.getRadius()*entity1.getVelocity().getY() - entity2.getRadius()*entity2.getVelocity().getY(), 2)
									);
			double J = 	(2*entity1.getMass()*entity2.getMass()  * ( (entity2.getVelocity().getNorm()-entity1.getVelocity().getNorm()) * (entity2.getRadius()-entity1.getRadius()) ) )  / 
						( sigma*(entity1.getMass()+entity2.getMass()) );
			double JX = (J*(entity2.getLocation().getX()-entity1.getLocation().getX()))		/		sigma;
			double JY = (J*(entity2.getLocation().getY()-entity1.getLocation().getY()))		/		sigma;
	
			Vector vel1 = new Vector (entity1.getVelocity().getX() + JX/entity1.getMass(), entity1.getVelocity().getY() + JY/entity1.getMass());
			entity1.setVelocity(vel1);
	
			Vector vel2 = new Vector (entity2.getVelocity().getX() - JX/entity2.getMass(), entity2.getVelocity().getY() - JY/entity2.getMass());
			entity2.setVelocity(vel2);
		}
	}

	public void evolveProto(double dt, CollisionListener collisionListener) {

		ArrayList<ArrayList<Collision>> 	collisions 	= createCollisionArray();								// Create a 2D ArrayList of Collisions.
		double timePassed = 0;

		while (collisions.get(0).get(0).getTime() <= dt)
		{
			Collision current = collisions.get(0).get(0);														// Current collision that will happen.
			double time = current.getTime()-timePassed;
			//System.out.println(time);
			for (SpaceObject p 	: getSpaceObjects()		)
			{
				p.move(time);
				if (p instanceof Ship && ((Ship) p).getThruster())
				{
					ArrayList<Collision> temp = createCollisionListForEntity(current.getEntity2(), current.getTime());
					int pos = positionEntity(current.getEntity1(), collisions);
					collisions.set(pos, temp);
					double timeEntity = collisions.get(pos).get(0).getTime();
					while (pos+1 < collisions.size() && timeEntity > collisions.get(pos+1).get(0).getTime())
						Collections.swap(collisions, pos+1, pos++);
					while (pos-1 >= 0 && timeEntity < collisions.get(pos-1).get(0).getTime())
						Collections.swap(collisions, pos-1, pos--);
				}
			}
			resolveCollision(current, timePassed);																// Execute the current collision.
			timePassed =  current.getTime();

			if (current.getEntity2() != null)																	// Recalculate collisions with Entity2 because of adjusted velocity.
			{
				ArrayList<Collision> temp = createCollisionListForEntity(current.getEntity2(), timePassed);
				int pos = positionEntity(current.getEntity1(), collisions);
				collisions.set(pos, temp);
				double timeEntity = collisions.get(pos).get(0).getTime();
				while (pos+1 < collisions.size() && timeEntity > collisions.get(pos+1).get(0).getTime())
					Collections.swap(collisions, pos+1, pos++);
				while (pos-1 >= 0 && timeEntity < collisions.get(pos-1).get(0).getTime())
					Collections.swap(collisions, pos-1, pos--);
			}

			if (current.getEntity1() != null)																	// Recalculate collision with Entity1 because of adjusted velocity.
			{
				ArrayList<Collision> temp = createCollisionListForEntity(current.getEntity1(), timePassed);
				int pos = 0;
				collisions.set(pos, temp);
				double timeEntity = collisions.get(pos).get(0).getTime();
				while (timeEntity > collisions.get(pos+1).get(0).getTime())
					Collections.swap(collisions, pos++, pos+1);

			}

		}

		double time = dt-timePassed;
		
		for (SpaceObject p 	: getSpaceObjects()		) 		p.move(time);

	}

	private ArrayList<ArrayList<Collision>> createCollisionArray() {
	
		ArrayList<ArrayList<Collision>> 	collisions 		= 	new ArrayList<ArrayList<Collision>>();	// 2D ArrayList with all the collisions.
		ArrayList<SpaceObject> 				objects 		= 	getSpaceObjects();						// List of all the SpaceObjects in this World.
	
		int minTimePos1 = -1;																			// Position of Lowest time to collision in first dimension of collisions.
		double minTime1 = Double.POSITIVE_INFINITY;														// The time of minTimePos1
	
		for (int i = 0; i < objects.size(); i++)
		{
			ArrayList<Collision> temp = new ArrayList<Collision>();
			int minTimePos2 = 0;																		// Position of Lowest time to collision in second dimension of collisions.
			double minTime2 = objects.get(i).collisionTimeWithBoundaries();								// The time of minTimePos2
			temp.add(new Collision(objects.get(i), minTime2));											// Add collision of Object with the boundaries to 'collisions'.
	
			for (SpaceObject q : objects)																// Add collision with each other Object to 'collisions'.
				if (objects.get(i) != q)
				{
					double time = objects.get(i).getTimeToCollision(q);
					if ((time == Double.POSITIVE_INFINITY && temp.size() == 0) || time != Double.POSITIVE_INFINITY) 
						temp.add(new Collision(objects.get(i), q, time));
					if (time < minTime2)
					{
						minTime2 = time;
						minTimePos2 = temp.size() - 1;
					}
				}
			Collections.swap(temp, 0, minTimePos2);														// Put lowest time in front of ArrayList.
			collisions.add(temp);
			if (minTime2 < minTime1)
			{
				minTime1 = minTime2;
				minTimePos1 = collisions.size() - 1;
			}
		}
		Collections.swap(collisions, 0, minTimePos1);													// Put lowest time in front of ArrayList in first dimension.
		return collisions;
	
	}

	private void resolveCollision (Collision collision, double timePassed) {

		SpaceObject entity1 = collision.getEntity1();
		SpaceObject entity2 = collision.getEntity2();

		// Option 1: Entity1 collides with boundaries.
		if (entity2 == null)
		{
			Vector newLocation = entity1.getLocation().add(entity1.getVelocity().multiply( (collision.getTime()-timePassed)));
			if (Util.fuzzyEquals(newLocation.getX()+entity1.getRadius(), getWidth())	||	Util.fuzzyEquals(newLocation.getX()-entity1.getRadius(), 0.0) )
			{
				Vector temp = entity1.getVelocity();
				entity1.setVelocity(new Vector(-temp.getX(), temp.getY()));
			}

			else 
			{
				Vector temp = entity1.getVelocity();
				entity1.setVelocity(new Vector(temp.getX(), -temp.getY()));
			}

		}
		// Option 2: Entity1 collides with Entity2.
		else 
		{
			double sigma = Math.sqrt( 	Math.pow(entity1.getRadius()*entity1.getVelocity().getX() - entity2.getRadius()*entity2.getVelocity().getX(), 2)   +
										Math.pow(entity1.getRadius()*entity1.getVelocity().getY() - entity2.getRadius()*entity2.getVelocity().getY(), 2)
									);
			double J = 	(2*entity1.getMass()*entity2.getMass()  * ( (entity2.getVelocity().getNorm()-entity1.getVelocity().getNorm()) * (entity2.getRadius()-entity1.getRadius()) ) )  / 
						( sigma*(entity1.getMass()+entity2.getMass()) );
			double JX = (J*(entity2.getLocation().getX()-entity1.getLocation().getX()))		/		sigma;
			double JY = (J*(entity2.getLocation().getY()-entity1.getLocation().getY()))		/		sigma;

			Vector vel1 = new Vector (entity1.getVelocity().getX() + JX/entity1.getMass(), entity1.getVelocity().getY() + JY/entity1.getMass());
			entity1.setVelocity(vel1);

			Vector vel2 = new Vector (entity2.getVelocity().getX() - JX/entity2.getMass(), entity2.getVelocity().getY() - JY/entity2.getMass());
			entity2.setVelocity(vel2);
		}
	}
	
	private ArrayList<Collision> createCollisionListForEntity(SpaceObject object, double timePassed) {
		ArrayList<Collision> temp = new ArrayList<Collision>();
	
		int minTimePos = 0;
		double minTime = object.collisionTimeWithBoundaries();
		temp.add(new Collision(object, minTime+timePassed));
	
		for (SpaceObject q : getSpaceObjects())
			if (object != q)
			{
				double time = object.getTimeToCollision(q);
				if ((time == Double.POSITIVE_INFINITY && temp.size() == 0) || time != Double.POSITIVE_INFINITY) 
					temp.add(new Collision(object, q, time+timePassed));
				if (time < minTime)
				{
					minTime = time;
					minTimePos = temp.size() - 1;
				}
			}
		Collections.swap(temp, 0, minTimePos);
	
	
		return temp;
	}

	private int positionEntity (SpaceObject object, ArrayList<ArrayList<Collision>> array) {
		int currentPos = 0;
		boolean found = false;
		while (!found)
		{
			if (array.get(currentPos).get(0).getEntity1() == object)
				found = true;
			else currentPos++;
		}

		return currentPos;
	}



}
