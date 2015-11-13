package ca.ubc.ece.cpen221.mp4.items.vehicles;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;

/**
 * The Vehicle interface for all vehicles. Includes methods that all vehicles need.
 * @author Annabelle Harvey and Daniel Chawla
 */
public interface Vehicle extends MoveableItem, Actor {
    
    
    /**
     * If there is an item in front of vehicle with weaker strength, removes item. Takes all energy from item.
     * If item in front of vehicle is stronger than vehicle, removes all energy from vehicle.
     * @param world object is in
     */
   Command collide(World world);
   
   /**
    * Test to see if at the world's edge.
    * @param current world.
    * @return true if at world's edge, false otherwise.
    */
   boolean atWorldsEdge(World world);
    

}
