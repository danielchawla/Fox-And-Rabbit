package ca.ubc.ece.cpen221.mp4.items.vehicles;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;

public interface Vehicle extends MoveableItem, Actor {
    
    /**
     * Returns the current fuel of vehicle
     * @return
     */
    int getFuelLevel();
    
    /**
     * @return the vehicles maximum fuel
     */
    int getMaxFuel();
    
    /**
     * Returns vehicles view range
     * @return
     */
    int getViewRange();
    
    /**
     * 
     * @return num of square vehicle may move
     */
    int getCoolDownPeriod();
    
    /**
     * 
     */
    void speedUp();
    
    /**
     * The vehicle also must be able to slow down, as it can only change
     * direction at low speeds.
     */
    void slowDown();
    

    

}
