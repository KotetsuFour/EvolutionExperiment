package creature;

public class Meat {

	private int energy;
	
	private int size;
	
	public Meat(Organism victim) {
		energy = victim.getMeatEnergy();
		size = victim.getSize();
		
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public int getSize() {
		return size;
	}
}
