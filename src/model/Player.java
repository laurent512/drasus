package model;

import java.util.Hashtable;
import java.util.Iterator;

/**
 * @author Aurel
 * 
 */
public class Player {
    UnitFactory factory;
    private Hashtable<String, Unit> units;
    private boolean isTurn = true;

    /**
	 * 
	 */
    public Player() {
	units = new Hashtable<String, Unit>();
	factory = new UnitFactoryPegasus();
    }

    public void addUnit(String name) {
	units.put(name, factory.getUnit(name));
    }

    public void setTurn(boolean b) {
	isTurn = b;
    }

    public boolean getTurn() {
	return isTurn;
    }

    public void delUnit(String name) {
	units.remove(name);
    }

    public Unit getUnit(String name) {
	return units.get(name);
    }

    public Unit getUnit(int x, int y) {
	Iterator<Unit> it = units.values().iterator();
	Unit temp;
	while (it.hasNext()) {
	    temp = it.next();
	    if (temp.getTile().x == x && temp.getTile().y == y)
		return temp;
	}
	return null;
    }

    public String[] getNamesOfUnits() {
	return factory.getNamesOfUnits();
    }

    public Hashtable<String, Unit> getUnits() {
	return units;
    }

    public String attackWith(Unit att, Unit def) throws DeadUnitException,
	    DeadBossException {
	return att.attack(def);
    }

}
