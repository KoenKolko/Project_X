package asteroids;

public class Asteroid extends SpaceObject {

	private final double density = 2.65 * Math.pow(10, 12);		// The mass density of the asteroid. (kg/km^3)

	public Asteroid (Vector coordinates, Vector velocity, double radius) {
		super (coordinates, velocity, radius);
		setMassWithDensity(getDensity());
	}

	public double getDensity() {
		return density;
	}

}
