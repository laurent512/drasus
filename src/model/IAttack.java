package model;

/**
 * @author Aurel
 * 
 */
public interface IAttack {

    /**
     * Attack between two units.
     * 
     * @param att the attacking unit
     * @param deff the defending unit
     * @return the string containing the result of the fight
     * @throws DeadUnitException if the defending unit is dead
     */
    String attack(Unit att, Unit deff) throws DeadUnitException;

    /**
     * Returns if the behavior of attack can attack from a certain range.
     * 
     * @param i
     *            the range
     * @return true if the behavior of attack can attack or false otherwise
     */
    boolean canAttackFromRange(int i);
    
    /**
     * Returns the range of the behavior of attack.
     * 
     * @return the range
     */
    int getRange();
}
