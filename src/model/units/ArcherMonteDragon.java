package model.units;

import model.attack.AttackDistancePoisoned;
import model.attack.AttackDistancePoisonedLonger;

/**
 * @author Aurel
 * 
 */
public class ArcherMonteDragon extends Unit {

	public ArcherMonteDragon() {
		super(new AttackDistancePoisoned(3), 220, 45, 75, 7, 20, 10, 2,
				Weapon.BOW, "ArcherMonte");

	}

	@Override
	public void activatePower() {
		pow = true;
		setIAttack(new AttackDistancePoisonedLonger(3));
	}

}
