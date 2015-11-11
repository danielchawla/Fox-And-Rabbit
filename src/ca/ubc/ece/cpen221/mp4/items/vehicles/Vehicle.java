package ca.ubc.ece.cpen221.mp4.items.vehicles;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.Item;
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
     * @return
     */
    int getMinCoolDown();
    
    /**
     * 
     * @return
     */
    int getMaxCoolDown();
    
    
    /**
     * 
     * @return
     */
    Direction getDirection();
    
    /**
     * 
     * @param collided
     */
    void collide(World world);

}
