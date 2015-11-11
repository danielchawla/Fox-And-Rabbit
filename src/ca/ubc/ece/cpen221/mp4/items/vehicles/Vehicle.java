package ca.ubc.ece.cpen221.mp4.items.vehicles;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;

public interface Vehicle extends MoveableItem, Actor {
    
    /**
     * The cool down periods for vehicles changes as they build up
     * speed. A class of vehicles must implement a method to change
     * the vehicles speed appropriately
     */
    void accelerate();
    
    /**
     * The vehicle also must be able to slow down, as it can only change
     * direction at low speeds.
     */
    void decelerate();
    
    /**
     * The speed of a vehicle at any given time varies. This method returns
     * the range of the vehicles movement at the next opportunity.
     * 
     * @return the number of squares of movement in a single direction the
     *              vehicle may move
     */
    int getCoolDownPeriod();
    
    /**
     * Returns the vehicles energy
     * 
     * @return
     */
    int getEnergy();
    
    /**
     * @return the vehicle's max energy
     */
    int getMaxEnergy();
    
    /**
     * Returns the vehicles view range
     * 
     * @return
     */
    int getViewRange();
}
