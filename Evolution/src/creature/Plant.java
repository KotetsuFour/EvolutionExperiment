package creature;

public class Plant {

	private int energy;
	
	public Plant() {
		energy = Organism.rng.nextInt(50);
	}
	
	public Plant(int e) {
		energy = e;
	}
	
	public int getEnergy() {
		return energy;
	}
}
