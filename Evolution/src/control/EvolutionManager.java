package control;

import java.util.ArrayList;
import java.util.List;

import creature.Meat;
import creature.Organism;
import creature.Plant;

public class EvolutionManager {

	private List<Plant> plants;
	
	private List<Meat> meats;
	
	private long round;
	
	private List<Organism> organisms;
	
	private List<Organism> herbivores;
	
	private List<Organism> carnivores;
	
	private static EvolutionManager manager;
	
	public static EvolutionManager getInstance() {
		if (manager == null) {
			//TODO fix invariability
			manager = new EvolutionManager(5);
		}
		return manager;
	}
	
	private EvolutionManager(int numOrganisms) {
		if (numOrganisms <= 0) {
			throw new IllegalArgumentException();
		}
		plants = new ArrayList<>();
		meats = new ArrayList<>();
		organisms = new ArrayList<>();
		for (int q = 0; q < numOrganisms; q++) {
			organisms.add(new Organism());
		}
		//We don't need to initialize herbivores and carnivores, because they will be
		//recreated every time we sort the organisms
	}
	
	public void generatePlants(int num) {
		if (num < 0) {
			throw new IllegalArgumentException();
		}
		plants = new ArrayList<>();
		for (int q = 0; q < num; q++) {
			plants.add(new Plant());
		}
	}
	
	public void payHalfCost() {
		for (int q = 0; q < organisms.size(); q++) {
			Organism o = organisms.get(q);
			if (!o.payHalfCost()) {
				meats.add(new Meat(o));
				organisms.remove(q);
				q--;
			}
		}
	}
	
	public void prepayAndSortVores() {
		payHalfCost();
		
		herbivores = new ArrayList<>();
		carnivores = new ArrayList<>();
		for (int q = 0; q < organisms.size(); q++) {
			if (Organism.rng.nextInt(100) <= organisms.get(q).getVore()) {
				herbivores.add(organisms.get(q));
			} else {
				carnivores.add(organisms.get(q));
			}
		}
	}
	
	public void herbivorePhase() {
		HerbivoreThread[] threads = new HerbivoreThread[herbivores.size()];
		for (int q = 0; q < herbivores.size(); q++) {
			threads[q] = new HerbivoreThread(herbivores.get(q));
		}
		for (int q = 0; q < threads.length; q++) {
			threads[q].start();
		}
		try {
			for (int q = 0; q < threads.length; q++) {
				threads[q].join();
			}
		} catch(InterruptedException e) {
		}
	}
	
	private class HerbivoreThread extends Thread {
		private Organism org;
		
		public HerbivoreThread(Organism org) {
			this.org = org;
		}
		
		public void run() {
			int trials = org.getGreed();
			do {
				try {
					Thread.sleep(100 - org.getSpeed());
				} catch (InterruptedException e) {
				}
			
				int chance = 0;
				synchronized(Organism.rng) {
					chance = Organism.rng.nextInt(Math.max(1, 100 - org.getSense()));
				}
				synchronized(plants) {
					if (!plants.isEmpty() && chance <= plants.size()) {
						int idx = Math.max(0, chance - 1);
						org.receiveEnergy(plants.remove(idx).getEnergy());
					}
				}
				trials--;
			} while (trials > 0);
		}
	}

	public void violentPhase() {
		for (int q = 0; q < organisms.size(); q++) {
			Organism atk = organisms.get(q);
			if (Organism.rng.nextInt(100) <= Math.max(0, organisms.get(q).getViolentMagnitude())) {
				int range = organisms.get(q).getViolentRange();
				for (int w = 0; w < organisms.size() && range > 0; w++) {
					Organism def = organisms.get(w);
					if (atk != def && atk.testAttackability(def)
							&& Organism.rng.nextInt(Math.max(1, def.getSense())) == 0) {
						int power = Math.max(0, atk.getStrength());
						def.decreaseEnergy(power * 3);
						atk.decreaseEnergy(power);
						if (def.getEnergy() <= 0) {
							meats.add(new Meat(organisms.remove(w)));
							if (w < q) {
								q--;
							}
							w--;
						}
						range--;
					}
				}
			}
		}
	}
	
	public void carnivorePhase() {
		CarnivoreThread[] threads = new CarnivoreThread[carnivores.size()];
		for (int q = 0; q < carnivores.size(); q++) {
			threads[q] = new CarnivoreThread(carnivores.get(q));
		}
		for (int q = 0; q < threads.length; q++) {
			threads[q].start();
		}
		try {
			for (int q = 0; q < threads.length; q++) {
				threads[q].join();
			}
		} catch(InterruptedException e) {
		}
	}
	
	private class CarnivoreThread extends Thread {
		private Organism org;
		
		public CarnivoreThread(Organism org) {
			this.org = org;
		}
		
		public void run() {
			int trials = org.getGreed();
			do {
				try {
					Thread.sleep(100 - org.getSpeed());
				} catch (InterruptedException e) {
				}
			
				int chance = 0;
				synchronized(Organism.rng) {
					chance = Organism.rng.nextInt(Math.max(1, 100 - org.getSense()));
				}
				synchronized(meats) {
					if (chance <= meats.size()) {
						int idx = Math.min(meats.size() - 1, Math.max(0, chance - 1));
						if (!meats.isEmpty() && org.getSize() >= meats.get(idx).getSize() * 1.2) {
							org.receiveEnergy(meats.remove(idx).getEnergy());
						}
					}
				}
				trials--;
			} while (trials > 0);
		}
	}

	public void friendlyPhase() {
		for (int q = 0; q < organisms.size(); q++) {
			Organism giv = organisms.get(q);
			int toGive = giv.getaAltruisticMagnitude();
			int givenTo = 0;
			for (int w = 0; w < organisms.size() && toGive > 0 && givenTo < giv.getaAltruisticRange(); w++) {
				Organism rec = organisms.get(w);
				if (q != w && rec.getEnergy() < rec.getEnergyCap() / 4 && giv.testFriendliness(rec)) {
					givenTo--;
					while (rec.getEnergy() < rec.getEnergyCap() / 4 && toGive > 0) {
						giv.decreaseEnergy(1);
						rec.receiveEnergy(1);
						toGive--;
					}
				}
			}
		}
	}
	
	public void killDead() {
		for (int q = 0; q < organisms.size(); q++) {
			if (organisms.get(q).getEnergy() <= 0) {
				meats.add(new Meat(organisms.remove(q)));
				q--;
			}
		}
	}
	
	public void reproductionPhase() {
		List<Organism> kids = new ArrayList<>();
		List<Organism> sParents = new ArrayList<>();
		for (int q = 0; q < organisms.size(); q++) {
			Organism p1 = organisms.get(q);
			if (p1.isPregnant()) {
				Organism k = p1.reproduceAsexually();
				if (k != null) {
					kids.add(k);
				}
			} else if (p1.canReproduce()) {
				if (Organism.rng.nextInt(100) > p1.getSexuality()) {
					Organism k = p1.reproduceAsexually();
					if (k != null) {
						kids.add(k);
					}
				} else {
					sParents.add(p1);
				}
			}
		}
		for (int q = 0; q < sParents.size(); q++) {
			Organism p1 = sParents.get(q);
			boolean go = true;
			for (int w = 0; w < sParents.size() && go; w++) {
				Organism p2 = sParents.get(w);
				if (p1 != p2 && p1.testCompatibility(p2) && p2.testCompatibility(p1) ) {
					Organism k = p1.mate(p2);
					if (k != null) {
						kids.add(k);
					}
					sParents.remove(q);
					q--;
					go = false;
				}
			}
		}
		organisms.addAll(kids);
	}
	
	public void endRound() {
		//Turn meat to plants
//		for (int q = 0; q < meats.size(); q++) {
//			plants.add(new Plant(meats.get(q).getEnergy()));
//		}
		meats = new ArrayList<>();
		
		//Increment Round
		round++;
	}
	
	public long getRound() {
		return round;
	}
	
	public List<Organism> getOrganisms() {
		return organisms;
	}
	
}
