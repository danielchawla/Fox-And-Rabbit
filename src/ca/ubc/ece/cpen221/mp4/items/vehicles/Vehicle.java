package ca.ubc.ece.cpen221.mp4.items.vehicles;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;

public interface Vehicle extends MoveableItem, Actor {
    
    
    /**
     * 
     * @param collided
     */
    void collide(World world);
    

}
