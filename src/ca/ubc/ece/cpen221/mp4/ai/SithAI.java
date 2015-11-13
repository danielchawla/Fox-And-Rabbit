package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;

/**
 * The Sith tries to find and kill R2D2 before is destroyed. 
 * 
 * @author Annabelle Harvey and Daniel Chawla.
 */
public class SithAI extends AbstractAI {
    
    public SithAI(){
        
    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        Set<Item> neighbours = world.searchSurroundings(animal);
        Location currentLocation = animal.getLocation();
      
       if (!neighbours.isEmpty()){
        for(Item item : neighbours){
            
            if ((item.getName().equals("R2D2") && (item.getLocation().getDistance(currentLocation) < animal.getViewRange()))){
                item.loseEnergy(Integer.MAX_VALUE); // Tries to kill R2D2
            }
        }
       }

        if (getRandomLegalMoveLoc(world, animal, currentLocation) != null){
            return new MoveCommand(animal,towardsItem(world, animal, "R2D2")); // moves towards R2D2. 
        }
        
        return new WaitCommand();
    }
}
