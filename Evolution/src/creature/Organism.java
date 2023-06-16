package creature;

import java.util.Random;

public class Organism {

	public static final Random rng = new Random();
	
	//Stats
	private int speed;
	private int sense;
	private int size;
	private int altruisticMagnitude;
	private int altruisticRange;
	private int violentMagnitude;
	private int violentRange;
	private int strength;
	private int gestationPeriod;
	private int mutationLikelihood;
	private int mutationMagnitude;
	private int redColor;
	private int greenColor;
	private int blueColor;
	private int greed;
	private int vore;
	private int sexuality;
	
	//Altruistic Considerations
	private int aSpeed;
	private int aSense;
	private int aSize;
	private int aAltruisticMagnitude;
	private int aAltruisticRange;
	private int aViolentMagnitude;
	private int aViolentRange;
	private int aStrength;
	private int aGestationPeriod;
	private int aMutationLikelihood;
	private int aMutationMagnitude;
	private int aRedColor;
	private int aGreenColor;
	private int aBlueColor;
	private int aGreed;
	private int aVore;
	private int aSexuality;
	
	//Violent Considerations
	private int vSpeed;
	private int vSense;
	private int vSize;
	private int vAltruisticMagnitude;
	private int vAltruisticRange;
	private int vViolentMagnitude;
	private int vViolentRange;
	private int vStrength;
	private int vGestationPeriod;
	private int vMutationLikelihood;
	private int vMutationMagnitude;
	private int vRedColor;
	private int vGreenColor;
	private int vBlueColor;
	private int vGreed;
	private int vVore;
	private int vSexuality;
	
	//Sexual Considerations
	private int sSpeed;
	private int sSense;
	private int sSize;
	private int sAltruisticMagnitude;
	private int sAltruisticRange;
	private int sViolentMagnitude;
	private int sViolentRange;
	private int sStrength;
	private int sGestationPeriod;
	private int sMutationLikelihood;
	private int sMutationMagnitude;
	private int sRedColor;
	private int sGreenColor;
	private int sBlueColor;
	private int sGreed;
	private int sVore;
	private int sSexuality;
	
	private int energy;
	
	private int energyCap;
	
	private int energyCost;
	
	private Organism child;
	
	private int timeTillBirth;
	
	public Organism() {
		this.speed = 0;
		this.sense = 0;
		this.size = 10;
		this.altruisticMagnitude = 0;
		this.altruisticRange = 0;
		this.violentMagnitude = 50;
		this.violentRange = 0;
		this.strength = 0;
		this.gestationPeriod = 0;
		this.mutationLikelihood = 50; //Percent
		this.mutationMagnitude = 3;
		this.redColor =30;
		this.greenColor = 140;
		this.blueColor = 130;
		this.greed = 0;
		this.vore = 50; //Percent
		this.sexuality = 0; //Percent
		//Difference must be less than or equal to
		this.aSpeed = 10;
		this.aSense = 10;
		this.aSize = 10;
		this.aAltruisticMagnitude = 10;
		this.aAltruisticRange = 10;
		this.aViolentMagnitude = 10;
		this.aViolentRange = 10;
		this.aStrength = 10;
		this.aGestationPeriod = 10;
		this.aMutationLikelihood = 10;
		this.aMutationMagnitude = 10;
		this.aRedColor = 10;
		this.aGreenColor = 10;
		this.aBlueColor = 10;
		this.aGreed = 10;
		this.aVore = 10;
		this.aSexuality = 10;
		//Difference must be greater than or equal to
		this.vSpeed = 10;
		this.vSense = 10;
		this.vSize = 10;
		this.vAltruisticMagnitude = 10;
		this.vAltruisticRange = 10;
		this.vViolentMagnitude = 10;
		this.vViolentRange = 10;
		this.vStrength = 10;
		this.vGestationPeriod = 10;
		this.vMutationLikelihood = 10;
		this.vMutationMagnitude = 10;
		this.vRedColor = 10;
		this.vGreenColor = 10;
		this.vBlueColor = 10;
		this.vGreed = 10;
		this.vVore = 10;
		this.vSexuality = 10;
		//Difference must be less than or equal to
		this.sSpeed = 10;
		this.sSense = 10;
		this.sSize = 10;
		this.sAltruisticMagnitude = 10;
		this.sAltruisticRange = 10;
		this.sViolentMagnitude = 10;
		this.sViolentRange = 10;
		this.sStrength = 10;
		this.sGestationPeriod = 10;
		this.sMutationLikelihood = 10;
		this.sMutationMagnitude = 10;
		this.sRedColor = 10;
		this.sGreenColor = 10;
		this.sBlueColor = 10;
		this.sGreed = 10;
		this.sVore = 10;
		this.sSexuality = 10;
		this.energy = 30;
		this.energyCap = 90;
		calculateEnergyCost();
		this.child = null;
		this.timeTillBirth = 0;
	}

	private Organism(Organism p1, Organism p2) {
		this.mutationLikelihood = (p1.mutationLikelihood + p2.mutationLikelihood) / 2;
		this.mutationMagnitude = (p1.mutationMagnitude + p2.mutationMagnitude) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				mutationMagnitude = Math.min(Integer.MAX_VALUE, mutationMagnitude + 1);
			} else {
				mutationMagnitude = Math.max(1, mutationMagnitude - 1);
			}
		}
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				mutationLikelihood = Math.min(Integer.MAX_VALUE, mutationLikelihood + mutationMagnitude);
			} else {
				mutationLikelihood = Math.max(1, mutationLikelihood - mutationMagnitude);
			}
		}
		this.speed = (p1.speed + p2.speed) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				speed += mutationMagnitude;
			} else {
				speed = Math.max(0, speed - mutationMagnitude);
			}
		}
		this.sense = (p1.sense + p2.sense) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sense += mutationMagnitude;
			} else {
				sense = Math.max(0, sense - mutationMagnitude);
			}
		}
		this.size = (p1.size + p2.size) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				size += mutationMagnitude;
			} else {
				size = Math.max(0, size - mutationMagnitude);
			}
		}
		this.altruisticMagnitude = (p1.altruisticMagnitude + p2.altruisticMagnitude) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				altruisticMagnitude += mutationMagnitude;
			} else {
				altruisticMagnitude = Math.max(0, altruisticMagnitude - mutationMagnitude);
			}
		}
		this.altruisticRange = (p1.altruisticRange + p2.altruisticRange) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				altruisticRange += mutationMagnitude;
			} else {
				altruisticRange = Math.max(0, altruisticRange - mutationMagnitude);
			}
		}
		this.violentMagnitude = (p1.violentMagnitude + p2.violentMagnitude) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				violentMagnitude = Math.min(100, Math.max(0, violentMagnitude + mutationMagnitude));
			} else {
				violentMagnitude = Math.min(100, Math.max(0, violentMagnitude - mutationMagnitude));
			}
		}
		this.violentRange = (p1.violentRange + p2.violentRange) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				violentRange += mutationMagnitude;
			} else {
				violentRange = Math.max(0, violentRange - mutationMagnitude);
			}
		}
		this.strength = (p1.strength + p2.strength) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				strength += mutationMagnitude;
			} else {
				strength = Math.max(0, strength - mutationMagnitude);
			}
		}
		this.gestationPeriod = (p1.gestationPeriod + p2.gestationPeriod) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				gestationPeriod += mutationMagnitude;
			} else {
				gestationPeriod = Math.max(0, gestationPeriod - mutationMagnitude);
			}
		}
		this.redColor = (p1.redColor + p2.redColor) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				redColor += mutationMagnitude;
			} else {
				redColor -= mutationMagnitude;
			}
		}
		this.greenColor = (p1.greenColor + p2.greenColor) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				greenColor += mutationMagnitude;
			} else {
				greenColor -= mutationMagnitude;
			}
		}
		this.blueColor = (p1.blueColor + p2.blueColor) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				blueColor += mutationMagnitude;
			} else {
				blueColor -= mutationMagnitude;
			}
		}
		this.greed = (p1.greed + p2.greed) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				greed += mutationMagnitude;
			} else {
				greed = Math.max(0, greed - mutationMagnitude);
			}
		}
		this.vore = (p1.vore + p2.vore) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vore = Math.min(100, Math.max(0, vore + mutationMagnitude));
			} else {
				vore = Math.min(100, Math.max(0, vore - mutationMagnitude));
			}
		}
		this.sexuality = (p1.sexuality + p2.sexuality) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sexuality = Math.min(100, Math.max(0, sexuality + mutationMagnitude));
			} else {
				sexuality = Math.min(100, Math.max(0, sexuality - mutationMagnitude));
			}
		}
		this.aSpeed = (p1.aSpeed + p2.aSpeed) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aSpeed += mutationMagnitude;
			} else {
				aSpeed -= mutationMagnitude;
			}
		}
		this.aSense = (p1.aSense + p2.aSense) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aSense += mutationMagnitude;
			} else {
				aSense -= mutationMagnitude;
			}
		}
		this.aSize = (p1.aSize + p2.aSize) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aSize += mutationMagnitude;
			} else {
				aSize -= mutationMagnitude;
			}
		}
		this.aAltruisticMagnitude = (p1.aAltruisticMagnitude + p2.aAltruisticMagnitude) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aAltruisticMagnitude += mutationMagnitude;
			} else {
				aAltruisticMagnitude -= mutationMagnitude;
			}
		}
		this.aAltruisticRange = (p1.aAltruisticRange + p2.aAltruisticRange) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aAltruisticRange += mutationMagnitude;
			} else {
				aAltruisticRange -= mutationMagnitude;
			}
		}
		this.aViolentMagnitude = (p1.aViolentMagnitude + p2.aViolentMagnitude) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aViolentMagnitude += mutationMagnitude;
			} else {
				aViolentMagnitude -= mutationMagnitude;
			}
		}
		this.aViolentRange = (p1.aViolentRange + p2.aViolentRange) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aViolentRange += mutationMagnitude;
			} else {
				aViolentRange -= mutationMagnitude;
			}
		}
		this.aStrength = (p1.aStrength + p2.aStrength) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aStrength += mutationMagnitude;
			} else {
				aStrength -= mutationMagnitude;
			}
		}
		this.aGestationPeriod = (p1.aGestationPeriod + p2.aGestationPeriod) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aGestationPeriod += mutationMagnitude;
			} else {
				aGestationPeriod -= mutationMagnitude;
			}
		}
		this.aMutationLikelihood = (p1.aMutationLikelihood + p2.aMutationLikelihood) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aMutationLikelihood += mutationMagnitude;
			} else {
				aMutationLikelihood -= mutationMagnitude;
			}
		}
		this.aMutationMagnitude = (p1.aMutationMagnitude + p2.aMutationMagnitude) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aMutationMagnitude += mutationMagnitude;
			} else {
				aMutationMagnitude -= mutationMagnitude;
			}
		}
		this.aRedColor = (p1.aRedColor + p2.aRedColor) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aRedColor += mutationMagnitude;
			} else {
				aRedColor -= mutationMagnitude;
			}
		}
		this.aGreenColor = (p1.aGreenColor + p2.aGreenColor) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aGreenColor += mutationMagnitude;
			} else {
				aGreenColor -= mutationMagnitude;
			}
		}
		this.aBlueColor = (p1.aBlueColor + p2.aBlueColor) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aBlueColor += mutationMagnitude;
			} else {
				aBlueColor -= mutationMagnitude;
			}
		}
		this.aGreed = (p1.aGreed + p2.aGreed) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aGreed += mutationMagnitude;
			} else {
				aGreed -= mutationMagnitude;
			}
		}
		this.aVore = (p1.aVore + p2.aVore) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aVore += mutationMagnitude;
			} else {
				aVore -= mutationMagnitude;
			}
		}
		this.aSexuality = (p1.aSexuality + p2.aSexuality) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aSexuality += mutationMagnitude;
			} else {
				aSexuality -= mutationMagnitude;
			}
		}
		this.vSpeed = (p1.vSpeed + p2.vSpeed) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vSpeed += mutationMagnitude;
			} else {
				vSpeed -= mutationMagnitude;
			}
		}
		this.vSense = (p1.vSense + p2.vSense) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vSense += mutationMagnitude;
			} else {
				vSense -= mutationMagnitude;
			}
		}
		this.vSize = (p1.vSize + p2.vSize) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vSize += mutationMagnitude;
			} else {
				vSize -= mutationMagnitude;
			}
		}
		this.vAltruisticMagnitude = (p1.vAltruisticMagnitude + p2.vAltruisticMagnitude) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vAltruisticMagnitude += mutationMagnitude;
			} else {
				vAltruisticMagnitude -= mutationMagnitude;
			}
		}
		this.vAltruisticRange = (p1.vAltruisticRange + p2.vAltruisticRange) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vAltruisticRange += mutationMagnitude;
			} else {
				vAltruisticRange -= mutationMagnitude;
			}
		}
		this.vViolentMagnitude = (p1.vViolentMagnitude + p2.vViolentMagnitude) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vViolentMagnitude += mutationMagnitude;
			} else {
				vViolentMagnitude -= mutationMagnitude;
			}
		}
		this.vViolentRange = (p1.vViolentRange + p2.vViolentRange) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vViolentRange += mutationMagnitude;
			} else {
				vViolentRange -= mutationMagnitude;
			}
		}
		this.vStrength = (p1.vStrength + p2.vStrength) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vStrength += mutationMagnitude;
			} else {
				vStrength -= mutationMagnitude;
			}
		}
		this.vGestationPeriod = (p1.vGestationPeriod + p2.vGestationPeriod) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vGestationPeriod += mutationMagnitude;
			} else {
				vGestationPeriod -= mutationMagnitude;
			}
		}
		this.vMutationLikelihood = (p1.vMutationLikelihood + p2.vMutationLikelihood) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vMutationLikelihood += mutationMagnitude;
			} else {
				vMutationLikelihood -= mutationMagnitude;
			}
		}
		this.vMutationMagnitude = (p1.vMutationMagnitude + p2.vMutationMagnitude) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vMutationMagnitude += mutationMagnitude;
			} else {
				vMutationMagnitude -= mutationMagnitude;
			}
		}
		this.vRedColor = (p1.vRedColor + p2.vRedColor) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vRedColor += mutationMagnitude;
			} else {
				vRedColor -= mutationMagnitude;
			}
		}
		this.vGreenColor = (p1.vGreenColor + p2.vGreenColor) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vGreenColor += mutationMagnitude;
			} else {
				vGreenColor -= mutationMagnitude;
			}
		}
		this.vBlueColor = (p1.vBlueColor + p2.vBlueColor) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vBlueColor += mutationMagnitude;
			} else {
				vBlueColor -= mutationMagnitude;
			}
		}
		this.vGreed = (p1.vGreed + p2.vGreed) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vGreed += mutationMagnitude;
			} else {
				vGreed -= mutationMagnitude;
			}
		}
		this.vVore = (p1.vVore + p2.vVore) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vVore += mutationMagnitude;
			} else {
				vVore -= mutationMagnitude;
			}
		}
		this.vSexuality = (p1.vSexuality + p2.vSexuality) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vSexuality += mutationMagnitude;
			} else {
				vSexuality -= mutationMagnitude;
			}
		}
		this.sSpeed = (p1.sSpeed + p2.sSpeed) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sSpeed += mutationMagnitude;
			} else {
				sSpeed -= mutationMagnitude;
			}
		}
		this.sSense = (p1.sSense + p2.sSense) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sSense += mutationMagnitude;
			} else {
				sSense -= mutationMagnitude;
			}
		}
		this.sSize = (p1.sSize + p2.sSize) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sSize += mutationMagnitude;
			} else {
				sSize -= mutationMagnitude;
			}
		}
		this.sAltruisticMagnitude = (p1.sAltruisticMagnitude + p2.sAltruisticMagnitude) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sAltruisticMagnitude += mutationMagnitude;
			} else {
				sAltruisticMagnitude -= mutationMagnitude;
			}
		}
		this.sAltruisticRange = (p1.sAltruisticRange + p2.sAltruisticRange) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sAltruisticRange += mutationMagnitude;
			} else {
				sAltruisticRange -= mutationMagnitude;
			}
		}
		this.sViolentMagnitude = (p1.sViolentMagnitude + p2.sViolentMagnitude) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sViolentMagnitude += mutationMagnitude;
			} else {
				sViolentMagnitude -= mutationMagnitude;
			}
		}
		this.sViolentRange = (p1.sViolentRange + p2.sViolentRange) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sViolentRange += mutationMagnitude;
			} else {
				sViolentRange -= mutationMagnitude;
			}
		}
		this.sStrength = (p1.sStrength + p2.sStrength) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sStrength += mutationMagnitude;
			} else {
				sStrength -= mutationMagnitude;
			}
		}
		this.sGestationPeriod = (p1.sGestationPeriod + p2.sGestationPeriod) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sGestationPeriod += mutationMagnitude;
			} else {
				sGestationPeriod -= mutationMagnitude;
			}
		}
		this.sMutationLikelihood = (p1.sMutationLikelihood + p2.sMutationLikelihood) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sMutationLikelihood += mutationMagnitude;
			} else {
				sMutationLikelihood -= mutationMagnitude;
			}
		}
		this.sMutationMagnitude = (p1.sMutationMagnitude + p2.sMutationMagnitude) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sMutationMagnitude += mutationMagnitude;
			} else {
				sMutationMagnitude -= mutationMagnitude;
			}
		}
		this.sRedColor = (p1.sRedColor + p2.sRedColor) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sRedColor += mutationMagnitude;
			} else {
				sRedColor -= mutationMagnitude;
			}
		}
		this.sGreenColor = (p1.sGreenColor + p2.sGreenColor) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sGreenColor += mutationMagnitude;
			} else {
				sGreenColor -= mutationMagnitude;
			}
		}
		this.sBlueColor = (p1.sBlueColor + p2.sBlueColor) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sBlueColor += mutationMagnitude;
			} else {
				sBlueColor -= mutationMagnitude;
			}
		}
		this.sGreed = (p1.sGreed + p2.sGreed) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sGreed += mutationMagnitude;
			} else {
				sGreed -= mutationMagnitude;
			}
		}
		this.sVore = (p1.sVore + p2.sVore) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sVore += mutationMagnitude;
			} else {
				sVore -= mutationMagnitude;
			}
		}
		this.sSexuality = (p1.sSexuality + p2.sSexuality) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sSexuality += mutationMagnitude;
			} else {
				sSexuality -= mutationMagnitude;
			}
		}
		this.energyCap = (p1.energyCap + p2.energyCap) / 2;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				energyCap = Math.min(180, energyCap + mutationMagnitude);
			} else {
				energyCap = Math.max(1, energyCap - mutationMagnitude);
			}
		}
		this.energy = Math.min(energyCap, (p1.energy / 3) + (p2.energy / 3));
		p1.energy *= 2;
		p1.energy /= 3;
		p2.energy *= 2;
		p2.energy /= 3;
		calculateEnergyCost();
		this.child = null;
		this.timeTillBirth = 0;
	}
	
	private Organism(Organism p1) {
		this.mutationLikelihood = p1.mutationLikelihood;
		this.mutationMagnitude = p1.mutationMagnitude;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				mutationMagnitude = Math.min(Integer.MAX_VALUE, mutationMagnitude + 1);
			} else {
				mutationMagnitude = Math.max(1, mutationMagnitude - 1);
			}
		}
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				mutationLikelihood = Math.min(Integer.MAX_VALUE, mutationLikelihood + mutationMagnitude);
			} else {
				mutationLikelihood = Math.max(1, mutationLikelihood - mutationMagnitude);
			}
		}
		this.speed = p1.speed;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				speed += mutationMagnitude;
			} else {
				speed = Math.max(0, speed - mutationMagnitude);
			}
		}
		this.sense = p1.sense;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sense += mutationMagnitude;
			} else {
				sense = Math.max(0, sense - mutationMagnitude);
			}
		}
		this.size = p1.size;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				size += mutationMagnitude;
			} else {
				size = Math.max(0, size - mutationMagnitude);
			}
		}
		this.altruisticMagnitude = p1.altruisticMagnitude;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				altruisticMagnitude += mutationMagnitude;
			} else {
				altruisticMagnitude = Math.max(0, altruisticMagnitude - mutationMagnitude);
			}
		}
		this.altruisticRange = p1.altruisticRange;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				altruisticRange += mutationMagnitude;
			} else {
				altruisticRange = Math.max(0, altruisticRange - mutationMagnitude);
			}
		}
		this.violentMagnitude = p1.violentMagnitude;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				violentMagnitude = Math.min(100, Math.max(0, violentMagnitude + mutationMagnitude));
			} else {
				violentMagnitude = Math.min(100, Math.max(0, violentMagnitude - mutationMagnitude));
			}
		}
		this.violentRange = p1.violentRange;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				violentRange += mutationMagnitude;
			} else {
				violentRange = Math.max(0, violentRange - mutationMagnitude);
			}
		}
		this.strength = p1.strength;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				strength += mutationMagnitude;
			} else {
				strength = Math.max(0, strength - mutationMagnitude);
			}
		}
		this.gestationPeriod = p1.gestationPeriod;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				gestationPeriod += mutationMagnitude;
			} else {
				gestationPeriod = Math.max(0, gestationPeriod - mutationMagnitude);
			}
		}
		this.redColor = p1.redColor;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				redColor += mutationMagnitude;
			} else {
				redColor -= mutationMagnitude;
			}
		}
		this.greenColor = p1.greenColor;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				greenColor += mutationMagnitude;
			} else {
				greenColor -= mutationMagnitude;
			}
		}
		this.blueColor = p1.blueColor;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				blueColor += mutationMagnitude;
			} else {
				blueColor -= mutationMagnitude;
			}
		}
		this.greed = p1.greed;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				greed += mutationMagnitude;
			} else {
				greed = Math.max(0, greed - mutationMagnitude);
			}
		}
		this.vore = p1.vore;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vore = Math.min(100, Math.max(0, vore + mutationMagnitude));
			} else {
				vore = Math.min(100, Math.max(0, vore - mutationMagnitude));
			}
		}
		this.sexuality = p1.sexuality;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sexuality = Math.min(100, Math.max(0, sexuality + mutationMagnitude));
			} else {
				sexuality = Math.min(100, Math.max(0, sexuality - mutationMagnitude));
			}
		}
		this.aSpeed = p1.aSpeed;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aSpeed += mutationMagnitude;
			} else {
				aSpeed -= mutationMagnitude;
			}
		}
		this.aSense = p1.aSense;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aSense += mutationMagnitude;
			} else {
				aSense -= mutationMagnitude;
			}
		}
		this.aSize = p1.aSize;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aSize += mutationMagnitude;
			} else {
				aSize -= mutationMagnitude;
			}
		}
		this.aAltruisticMagnitude = p1.aAltruisticMagnitude;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aAltruisticMagnitude += mutationMagnitude;
			} else {
				aAltruisticMagnitude -= mutationMagnitude;
			}
		}
		this.aAltruisticRange = p1.aAltruisticRange;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aAltruisticRange += mutationMagnitude;
			} else {
				aAltruisticRange -= mutationMagnitude;
			}
		}
		this.aViolentMagnitude = p1.aViolentMagnitude;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aViolentMagnitude += mutationMagnitude;
			} else {
				aViolentMagnitude -= mutationMagnitude;
			}
		}
		this.aViolentRange = p1.aViolentRange;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aViolentRange += mutationMagnitude;
			} else {
				aViolentRange -= mutationMagnitude;
			}
		}
		this.aStrength = p1.aStrength;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aStrength += mutationMagnitude;
			} else {
				aStrength -= mutationMagnitude;
			}
		}
		this.aGestationPeriod = p1.aGestationPeriod;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aGestationPeriod += mutationMagnitude;
			} else {
				aGestationPeriod -= mutationMagnitude;
			}
		}
		this.aMutationLikelihood = p1.aMutationLikelihood;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aMutationLikelihood += mutationMagnitude;
			} else {
				aMutationLikelihood -= mutationMagnitude;
			}
		}
		this.aMutationMagnitude = p1.aMutationMagnitude;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aMutationMagnitude += mutationMagnitude;
			} else {
				aMutationMagnitude -= mutationMagnitude;
			}
		}
		this.aRedColor = p1.aRedColor;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aRedColor += mutationMagnitude;
			} else {
				aRedColor -= mutationMagnitude;
			}
		}
		this.aGreenColor = p1.aGreenColor;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aGreenColor += mutationMagnitude;
			} else {
				aGreenColor -= mutationMagnitude;
			}
		}
		this.aBlueColor = p1.aBlueColor;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aBlueColor += mutationMagnitude;
			} else {
				aBlueColor -= mutationMagnitude;
			}
		}
		this.aGreed = p1.aGreed;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aGreed += mutationMagnitude;
			} else {
				aGreed -= mutationMagnitude;
			}
		}
		this.aVore = p1.aVore;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aVore += mutationMagnitude;
			} else {
				aVore -= mutationMagnitude;
			}
		}
		this.aSexuality = p1.aSexuality;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				aSexuality += mutationMagnitude;
			} else {
				aSexuality -= mutationMagnitude;
			}
		}
		this.vSpeed = p1.vSpeed;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vSpeed += mutationMagnitude;
			} else {
				vSpeed -= mutationMagnitude;
			}
		}
		this.vSense = p1.vSense;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vSense += mutationMagnitude;
			} else {
				vSense -= mutationMagnitude;
			}
		}
		this.vSize = p1.vSize;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vSize += mutationMagnitude;
			} else {
				vSize -= mutationMagnitude;
			}
		}
		this.vAltruisticMagnitude = p1.vAltruisticMagnitude;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vAltruisticMagnitude += mutationMagnitude;
			} else {
				vAltruisticMagnitude -= mutationMagnitude;
			}
		}
		this.vAltruisticRange = p1.vAltruisticRange;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vAltruisticRange += mutationMagnitude;
			} else {
				vAltruisticRange -= mutationMagnitude;
			}
		}
		this.vViolentMagnitude = p1.vViolentMagnitude;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vViolentMagnitude += mutationMagnitude;
			} else {
				vViolentMagnitude -= mutationMagnitude;
			}
		}
		this.vViolentRange = p1.vViolentRange;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vViolentRange += mutationMagnitude;
			} else {
				vViolentRange -= mutationMagnitude;
			}
		}
		this.vStrength = p1.vStrength;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vStrength += mutationMagnitude;
			} else {
				vStrength -= mutationMagnitude;
			}
		}
		this.vGestationPeriod = p1.vGestationPeriod;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vGestationPeriod += mutationMagnitude;
			} else {
				vGestationPeriod -= mutationMagnitude;
			}
		}
		this.vMutationLikelihood = p1.vMutationLikelihood;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vMutationLikelihood += mutationMagnitude;
			} else {
				vMutationLikelihood -= mutationMagnitude;
			}
		}
		this.vMutationMagnitude = p1.vMutationMagnitude;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vMutationMagnitude += mutationMagnitude;
			} else {
				vMutationMagnitude -= mutationMagnitude;
			}
		}
		this.vRedColor = p1.vRedColor;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vRedColor += mutationMagnitude;
			} else {
				vRedColor -= mutationMagnitude;
			}
		}
		this.vGreenColor = p1.vGreenColor;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vGreenColor += mutationMagnitude;
			} else {
				vGreenColor -= mutationMagnitude;
			}
		}
		this.vBlueColor = p1.vBlueColor;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vBlueColor += mutationMagnitude;
			} else {
				vBlueColor -= mutationMagnitude;
			}
		}
		this.vGreed = p1.vGreed;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vGreed += mutationMagnitude;
			} else {
				vGreed -= mutationMagnitude;
			}
		}
		this.vVore = p1.vVore;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vVore += mutationMagnitude;
			} else {
				vVore -= mutationMagnitude;
			}
		}
		this.vSexuality = p1.vSexuality;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				vSexuality += mutationMagnitude;
			} else {
				vSexuality -= mutationMagnitude;
			}
		}
		this.sSpeed = p1.sSpeed;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sSpeed += mutationMagnitude;
			} else {
				sSpeed -= mutationMagnitude;
			}
		}
		this.sSense = p1.sSense;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sSense += mutationMagnitude;
			} else {
				sSense -= mutationMagnitude;
			}
		}
		this.sSize = p1.sSize;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sSize += mutationMagnitude;
			} else {
				sSize -= mutationMagnitude;
			}
		}
		this.sAltruisticMagnitude = p1.sAltruisticMagnitude;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sAltruisticMagnitude += mutationMagnitude;
			} else {
				sAltruisticMagnitude -= mutationMagnitude;
			}
		}
		this.sAltruisticRange = p1.sAltruisticRange;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sAltruisticRange += mutationMagnitude;
			} else {
				sAltruisticRange -= mutationMagnitude;
			}
		}
		this.sViolentMagnitude = p1.sViolentMagnitude;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sViolentMagnitude += mutationMagnitude;
			} else {
				sViolentMagnitude -= mutationMagnitude;
			}
		}
		this.sViolentRange = p1.sViolentRange;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sViolentRange += mutationMagnitude;
			} else {
				sViolentRange -= mutationMagnitude;
			}
		}
		this.sStrength = p1.sStrength;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sStrength += mutationMagnitude;
			} else {
				sStrength -= mutationMagnitude;
			}
		}
		this.sGestationPeriod = p1.sGestationPeriod;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sGestationPeriod += mutationMagnitude;
			} else {
				sGestationPeriod -= mutationMagnitude;
			}
		}
		this.sMutationLikelihood = p1.sMutationLikelihood;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sMutationLikelihood += mutationMagnitude;
			} else {
				sMutationLikelihood -= mutationMagnitude;
			}
		}
		this.sMutationMagnitude = p1.sMutationMagnitude;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sMutationMagnitude += mutationMagnitude;
			} else {
				sMutationMagnitude -= mutationMagnitude;
			}
		}
		this.sRedColor = p1.sRedColor;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sRedColor += mutationMagnitude;
			} else {
				sRedColor -= mutationMagnitude;
			}
		}
		this.sGreenColor = p1.sGreenColor;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sGreenColor += mutationMagnitude;
			} else {
				sGreenColor -= mutationMagnitude;
			}
		}
		this.sBlueColor = p1.sBlueColor;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sBlueColor += mutationMagnitude;
			} else {
				sBlueColor -= mutationMagnitude;
			}
		}
		this.sGreed = p1.sGreed;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sGreed += mutationMagnitude;
			} else {
				sGreed -= mutationMagnitude;
			}
		}
		this.sVore = p1.sVore;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sVore += mutationMagnitude;
			} else {
				sVore -= mutationMagnitude;
			}
		}
		this.sSexuality = p1.sSexuality;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				sSexuality += mutationMagnitude;
			} else {
				sSexuality -= mutationMagnitude;
			}
		}
		this.energyCap = p1.energyCap;
		if (rng.nextInt(100) + 1 <= mutationLikelihood) {
			if (rng.nextBoolean()) {
				energyCap = Math.min(180, energyCap + mutationMagnitude);
			} else {
				energyCap = Math.max(1, energyCap - mutationMagnitude);
			}
		}
		this.energy = Math.min(energyCap, (p1.energy / 3));
		p1.energy *= 2;
		p1.energy /= 3;
		calculateEnergyCost();
		this.child = null;
		this.timeTillBirth = 0;
	}

	public int getSpeed() {
		return speed;
	}

	public int getSense() {
		return sense;
	}

	public int getSize() {
		return size;
	}

	public int getAltruisticMagnitude() {
		return altruisticMagnitude;
	}

	public int getAltruisticRange() {
		return altruisticRange;
	}

	public int getViolentMagnitude() {
		return violentMagnitude;
	}

	public int getViolentRange() {
		return violentRange;
	}

	public int getStrength() {
		return strength;
	}

	public int getGestationPeriod() {
		return gestationPeriod;
	}

	public int getMutationLikelihood() {
		return mutationLikelihood;
	}

	public int getMutationMagnitude() {
		return mutationMagnitude;
	}

	public int getRedColor() {
		return redColor;
	}

	public int getGreenColor() {
		return greenColor;
	}

	public int getBlueColor() {
		return blueColor;
	}

	public int getGreed() {
		return greed;
	}

	public int getVore() {
		return vore;
	}

	public int getSexuality() {
		return sexuality;
	}

	public int getaSpeed() {
		return aSpeed;
	}

	public int getaSense() {
		return aSense;
	}

	public int getaSize() {
		return aSize;
	}

	public int getaAltruisticMagnitude() {
		return aAltruisticMagnitude;
	}

	public int getaAltruisticRange() {
		return aAltruisticRange;
	}

	public int getaViolentMagnitude() {
		return aViolentMagnitude;
	}

	public int getaViolentRange() {
		return aViolentRange;
	}

	public int getaStrength() {
		return aStrength;
	}

	public int getaGestationPeriod() {
		return aGestationPeriod;
	}

	public int getaMutationLikelihood() {
		return aMutationLikelihood;
	}

	public int getaMutationMagnitude() {
		return aMutationMagnitude;
	}

	public int getaRedColor() {
		return aRedColor;
	}

	public int getaGreenColor() {
		return aGreenColor;
	}

	public int getaBlueColor() {
		return aBlueColor;
	}

	public int getaGreed() {
		return aGreed;
	}

	public int getaVore() {
		return aVore;
	}

	public int getaSexuality() {
		return aSexuality;
	}

	public int getvSpeed() {
		return vSpeed;
	}

	public int getvSense() {
		return vSense;
	}

	public int getvSize() {
		return vSize;
	}

	public int getvAltruisticMagnitude() {
		return vAltruisticMagnitude;
	}

	public int getvAltruisticRange() {
		return vAltruisticRange;
	}

	public int getvViolentMagnitude() {
		return vViolentMagnitude;
	}

	public int getvViolentRange() {
		return vViolentRange;
	}

	public int getvStrength() {
		return vStrength;
	}

	public int getvGestationPeriod() {
		return vGestationPeriod;
	}

	public int getvMutationLikelihood() {
		return vMutationLikelihood;
	}

	public int getvMutationMagnitude() {
		return vMutationMagnitude;
	}

	public int getvRedColor() {
		return vRedColor;
	}

	public int getvGreenColor() {
		return vGreenColor;
	}

	public int getvBlueColor() {
		return vBlueColor;
	}

	public int getvGreed() {
		return vGreed;
	}

	public int getvVore() {
		return vVore;
	}

	public int getvSexuality() {
		return vSexuality;
	}

	public int getsSpeed() {
		return sSpeed;
	}

	public int getsSense() {
		return sSense;
	}

	public int getsSize() {
		return sSize;
	}

	public int getsAltruisticMagnitude() {
		return sAltruisticMagnitude;
	}

	public int getsAltruisticRange() {
		return sAltruisticRange;
	}

	public int getsViolentMagnitude() {
		return sViolentMagnitude;
	}

	public int getsViolentRange() {
		return sViolentRange;
	}

	public int getsStrength() {
		return sStrength;
	}

	public int getsGestationPeriod() {
		return sGestationPeriod;
	}

	public int getsMutationLikelihood() {
		return sMutationLikelihood;
	}

	public int getsMutationMagnitude() {
		return sMutationMagnitude;
	}

	public int getsRedColor() {
		return sRedColor;
	}

	public int getsGreenColor() {
		return sGreenColor;
	}

	public int getsBlueColor() {
		return sBlueColor;
	}

	public int getsGreed() {
		return sGreed;
	}

	public int getsVore() {
		return sVore;
	}

	public int getsSexuality() {
		return sSexuality;
	}
	
	public boolean isPregnant() {
		return child != null;
	}
	
	public boolean canReproduce() {
		return energy >= energyCap / 2;
	}
	
	public Organism reproduceAsexually() {
		if (child != null) {
			timeTillBirth--;
			child.energy += this.energy / 3;
			this.energy *= 2;
			this.energy /= 3;
			if (timeTillBirth == 0) {
				Organism ret = child;
				child = null;
				return ret;
			}
			return null;
		}
		if (energy >= energyCap / 2) {
			child = new Organism(this);
			timeTillBirth = gestationPeriod;
			if (timeTillBirth == 0) {
				Organism ret = child;
				child = null;
				return ret;
			}
		}
		return null;
	}
	
	public Organism mate(Organism partner) {
		child = new Organism(this, partner);
		timeTillBirth = gestationPeriod;
		if (timeTillBirth == 0) {
			Organism ret = child;
			child = null;
			return ret;
		}
		return null;
	}
	
	public boolean testCompatibility(Organism partner) {
		return sSpeed <= Math.abs(speed - partner.speed) &&
		sSense <= Math.abs(sense - partner.sense) &&
		sSize <= Math.abs(size - partner.size) &&
		sAltruisticMagnitude <= Math.abs(altruisticMagnitude - partner.aAltruisticMagnitude) &&
		sAltruisticRange <= Math.abs(altruisticRange - partner.altruisticRange) &&
		sViolentMagnitude <= Math.abs(violentMagnitude - partner.violentMagnitude) &&
		sViolentRange <= Math.abs(violentRange - partner.violentRange) &&
		sStrength <= Math.abs(strength - partner.strength) &&
		sGestationPeriod <= Math.abs(gestationPeriod - partner.gestationPeriod) &&
		sMutationLikelihood <= Math.abs(mutationLikelihood - partner.mutationLikelihood) &&
		sMutationMagnitude <= Math.abs(mutationMagnitude - partner.mutationMagnitude) &&
		sRedColor <= Math.abs(redColor - partner.redColor) &&
		sGreenColor <= Math.abs(greenColor - partner.greenColor) &&
		sBlueColor <= Math.abs(blueColor - partner.blueColor) &&
		sGreed <= Math.abs(greed - partner.greed) &&
		sVore <= Math.abs(vore - partner.vore) &&
		sSexuality <= Math.abs(sexuality - partner.sexuality);
	}
	
	public boolean testAttackability(Organism enemy) {
		return vSpeed >= Math.abs(speed - enemy.speed) &&
		vSense >= Math.abs(sense - enemy.sense) &&
		vSize >= Math.abs(size - enemy.size) &&
		vAltruisticMagnitude >= Math.abs(altruisticMagnitude - enemy.aAltruisticMagnitude) &&
		vAltruisticRange >= Math.abs(altruisticRange - enemy.altruisticRange) &&
		vViolentMagnitude >= Math.abs(violentMagnitude - enemy.violentMagnitude) &&
		vViolentRange >= Math.abs(violentRange - enemy.violentRange) &&
		vStrength >= Math.abs(strength - enemy.strength) &&
		vGestationPeriod >= Math.abs(gestationPeriod - enemy.gestationPeriod) &&
		vMutationLikelihood >= Math.abs(mutationLikelihood - enemy.mutationLikelihood) &&
		vMutationMagnitude >= Math.abs(mutationMagnitude - enemy.mutationMagnitude) &&
		vRedColor >= Math.abs(redColor - enemy.redColor) &&
		vGreenColor >= Math.abs(greenColor - enemy.greenColor) &&
		vBlueColor >= Math.abs(blueColor - enemy.blueColor) &&
		vGreed >= Math.abs(greed - enemy.greed) &&
		vVore >= Math.abs(vore - enemy.vore) &&
		vSexuality >= Math.abs(sexuality - enemy.sexuality);
	}
	
	public boolean testFriendliness(Organism friend) {
		return aSpeed <= Math.abs(speed - friend.speed) &&
		aSense <= Math.abs(sense - friend.sense) &&
		aSize <= Math.abs(size - friend.size) &&
		aAltruisticMagnitude <= Math.abs(altruisticMagnitude - friend.aAltruisticMagnitude) &&
		aAltruisticRange <= Math.abs(altruisticRange - friend.altruisticRange) &&
		aViolentMagnitude <= Math.abs(violentMagnitude - friend.violentMagnitude) &&
		aViolentRange <= Math.abs(violentRange - friend.violentRange) &&
		aStrength <= Math.abs(strength - friend.strength) &&
		aGestationPeriod <= Math.abs(gestationPeriod - friend.gestationPeriod) &&
		aMutationLikelihood <= Math.abs(mutationLikelihood - friend.mutationLikelihood) &&
		aMutationMagnitude <= Math.abs(mutationMagnitude - friend.mutationMagnitude) &&
		aRedColor <= Math.abs(redColor - friend.redColor) &&
		aGreenColor <= Math.abs(greenColor - friend.greenColor) &&
		aBlueColor <= Math.abs(blueColor - friend.blueColor) &&
		aGreed <= Math.abs(greed - friend.greed) &&
		aVore <= Math.abs(vore - friend.vore) &&
		aSexuality <= Math.abs(sexuality - friend.sexuality);
	}
	
	public boolean payHalfCost() {
		energy -= Math.max((energyCost / 2), 1);
		return energy > 0;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public int getEnergyCap() {
		return energyCap;
	}
	
	public void receiveEnergy(int e) {
		energy = Math.min(energy + e, energyCap);
	}
	
	public boolean decreaseEnergy(int d) {
		energy -= d;
		return energy > 0;
	}
	
	public int getMeatEnergy() {
		return (size * strength) / 2;
	}
	
	public void calculateEnergyCost() {
		energyCost = speed + size + sense + greed;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("<html>Energy: " + energy + "<br/>");
		sb.append("<html>Energy Cap: " + energyCap + "<br/>");
		sb.append("<html>Speed: " + speed + "<br/>");
		sb.append("<html>Sense: " + sense + "<br/>");
		sb.append("<html>Size: " + size + "<br/>");
		sb.append("<html>Altruistic Magnitude: " + altruisticMagnitude + "<br/>");
		sb.append("<html>Altruistic Range: " + altruisticRange + "<br/>");
		sb.append("<html>Violent Tendency: " + violentMagnitude + "%<br/>");
		sb.append("<html>Violent Range: " + violentRange + "<br/>");
		sb.append("<html>Strength: " + strength + "<br/>");
		sb.append("<html>Gestation Period: " + gestationPeriod + "<br/>");
		sb.append("<html>Mutation Likelihood: " + mutationLikelihood + "%<br/>");
		sb.append("<html>Mutation Magnitude: " + mutationMagnitude + "<br/>");
		sb.append("<html>R: " + redColor + "<br/>");
		sb.append("<html>G: " + greenColor + "<br/>");
		sb.append("<html>B: " + blueColor + "<br/>");
		sb.append("<html>Greed: " + greed + "<br/>");
		sb.append("<html>Herbivorous Tendency: " + vore + "%<br/>");
		sb.append("<html>Sexual Tendency: " + sexuality + "%<br/>");
		return sb.toString();

	}
	
}
