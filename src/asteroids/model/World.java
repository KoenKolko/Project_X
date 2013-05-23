package asteroids.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import asteroids.Collision;
import asteroids.Util;
import asteroids.Vector;
import asteroids.CollisionListener;

public class World {
	private static double MIN_SIZE = 0;
	private static double MAX_SIZE = Double.MAX_VALUE;
	private static double TIME_FOR_COMMANDS = 0.2;
	private double programTime = 0;
	private Vector dimensions;
	private ArrayList<SpaceObject> allObjects = new ArrayList<SpaceObject>();

	public World(Vector dimensions){
		setDimensions(dimensions);
	}

	private boolean isValidDimensions(Vector dimensions){
		return(	dimensions.getX() >= MIN_SIZE && 
				dimensions.getX() <= MAX_SIZE && 
				dimensions.getY() >= MIN_SIZE && 
				dimensions.getY() <= MAX_SIZE);
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

	public Set<SpaceObject> getObjects() {
		Set<SpaceObject> set = new HashSet<SpaceObject>(getSpaceObjects());
		return set;
	}

	public ArrayList<SpaceObject> getSpaceObjects() {
		return allObjects;
	}

	public Set<Ship> getShips(){
		Set<Ship> allShips = new HashSet<Ship>();
		for (SpaceObject p : getObjects())
			if (p instanceof Ship)
				allShips.add((Ship)p);
		return allShips;
	}

	public Set<Asteroid> getAsteroids(){
		Set<Asteroid> allAsteroids = new HashSet<Asteroid>();
		for (SpaceObject p : getObjects())
			if (p instanceof Asteroid)
				allAsteroids.add((Asteroid)p);
		return allAsteroids;
	}

	public Set<Bullet> getBullets(){
		Set<Bullet> allBullets = new HashSet<Bullet>();
		for (SpaceObject p : getObjects())
			if (p instanceof Bullet)
				allBullets.add((Bullet)p);
		return allBullets;
	}

	// Defensively
	public void addObject (SpaceObject object) {
		if (object == null || !object.fitsInWorld(this))
			throw new IllegalArgumentException();
		boolean valid = true;
		object.setWorld(this); 

		for (SpaceObject p : getObjects())
		{
			if (object instanceof Bullet) 
			{
				if (object.overlap(p) && ((Bullet) object).getSource() != p)
				{
					p.die();
					object.die();
					valid = false;
				}
			}

			else
			{
				if (object.overlap(p))
					valid = false;
			}
		}

		if (valid)
			allObjects.add(object);

	}


	// Defensively
	public void removeObject (SpaceObject object) {
		if (object == null)
			throw new IllegalArgumentException();
		object.removeWorld();
		allObjects.remove(object);
	}

	public void moveAllObjects (double time) {
		for(SpaceObject object : getSpaceObjects()) object.move(time);
	}

	public void executeAllPrograms () {
		for(Ship ship : getShips()) 
			if (ship.getProgram() != null)
				ship.getProgram().executeNextCommand();
	}


	// Defensively
	public void evolve(double dt, CollisionListener collisionListener) {


		if (!isValidEvolveTime(dt))
			throw new IllegalArgumentException();

		Comparator<Collision> comparator = new CollisionComparitor();
		PriorityQueue<Collision> pq = new PriorityQueue<Collision>(2, comparator);
		for(SpaceObject p : getSpaceObjects())
		{
			pq.add(new Collision(p, p.collisionTimeWithBoundaries()));
			for(SpaceObject q : getSpaceObjects())
				if (! ( ( p instanceof Ship && q instanceof Bullet && ((Bullet) q).getSource() == p ) || ( q instanceof Ship && p instanceof Bullet && ((Bullet) p).getSource() == q ) ) )
				{
					Collision collision = new Collision(p,q, p.getTimeToCollision(q));
					pq.add(collision);
				}

		}
		Collision nextCollision = pq.poll();
		double nextCollisionTime = nextCollision.getTime();
		while(nextCollisionTime <= dt)
		{


			moveAllObjects(nextCollisionTime);

			nextCollision.resolve();
			//collisionListener.objectCollision(entity1, entity2, x, y);


			dt -= nextCollisionTime;

			Comparator<Collision> comparatorLocal = new CollisionComparitor();
			PriorityQueue<Collision> pqLocal = new PriorityQueue<Collision>(2, comparatorLocal);
			for(SpaceObject p : getSpaceObjects())
			{
				pqLocal.add(new Collision(p, p.collisionTimeWithBoundaries()));
				for(SpaceObject q : getSpaceObjects())
					if (! ( ( p instanceof Ship && q instanceof Bullet && ((Bullet) q).getSource() == p ) || ( q instanceof Ship && p instanceof Bullet && ((Bullet) p).getSource() == q ) ) )
					{
						Collision collision = new Collision(p,q, p.getTimeToCollision(q));
						pqLocal.add(collision);
					}
			}
			nextCollision = pqLocal.poll();
			nextCollisionTime = nextCollision.getTime();

			checkProgramTime(nextCollisionTime);

		}


		moveAllObjects(dt);
		checkProgramTime(dt);
	}


	// Optimized version of evolve: alpha
	public void evolve2(double dt, CollisionListener collisionListener) {

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


			System.out.println("Die 1");
			entity1.die();
			System.out.println("Die 2");
			entity2.die();
		}
	}

	private ArrayList<Collision> createCollisionListForEntity(SpaceObject object, double timePassed) {
		// zit een fout :)
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

	public boolean isValidEvolveTime (double time) {
		if (Double.isNaN(time) || time == Double.POSITIVE_INFINITY || Double.compare(time, 0) < 0)
			return false;
		return true;
	}

	private void checkProgramTime(double time) {
		double newTime = getProgramTime() + time;
		int xTimeExecute = (int) (newTime / TIME_FOR_COMMANDS);
		for (int i = 0; i < xTimeExecute; i++)
			executeAllPrograms();
		setProgramTime(newTime % TIME_FOR_COMMANDS);
	}

	public double getProgramTime() {
		return programTime;
	}

	public void setProgramTime(double programTime) {
		this.programTime = programTime;
	}



}
