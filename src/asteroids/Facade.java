package asteroids;

public class Facade implements IFacade {

	@Override
	public IShip createShip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IShip createShip(double x, double y, double xVelocity,double yVelocity, double radius, double angle) throws ModelException {		
		try { return new Ship(x, y, xVelocity, yVelocity, radius, angle); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occured."); }
	}

	@Override
	public double getX(IShip ship) throws ModelException {
		try { return ((Ship) ship).getX(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getY(IShip ship) throws ModelException {
		try {return ((Ship) ship).getY();}
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getXVelocity(IShip ship) throws ModelException {
		try {return ((Ship) ship).getXVelocity(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getYVelocity(IShip ship) throws ModelException {
		try {return ((Ship) ship).getYVelocity(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getRadius(IShip ship) throws ModelException {
		try { return ((Ship) ship).getRadius(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getDirection(IShip ship) throws ModelException {
		try {return ((Ship) ship).getAngle(); }
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public void move(IShip ship, double dt) throws ModelException{
		try {((Ship) ship).move(dt);}
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
		
	}

	@Override
	public void thrust(IShip ship, double amount) throws ModelException{
		try {((Ship) ship).thrust(amount);}		
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public void turn(IShip ship, double angle) throws ModelException{
		try {((Ship) ship).turn(angle);}
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getDistanceBetween(IShip ship1, IShip ship2) throws ModelException{
		try {return ((Ship) ship1).getDistanceBetween((Ship) ship2);}
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public boolean overlap(IShip ship1, IShip ship2) throws ModelException{
		try {return ((Ship) ship1).overlap((Ship) ship2);}
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double getTimeToCollision(IShip ship1, IShip ship2) throws ModelException{
		try {return ((Ship) ship1).getTimeToCollision((Ship) ship2);}
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

	@Override
	public double[] getCollisionPosition(IShip ship1, IShip ship2) throws ModelException{
		try {return ((Ship) ship1).getCollisionPosition((Ship) ship2);}
		catch (IllegalArgumentException e) { throw new ModelException("An error has occurred."); }
	}

}
