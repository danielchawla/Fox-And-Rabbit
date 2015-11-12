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
 * AI to help C3P0 tries to find R2D2 and protect him from the Sith, 
 * and he kills grass and clones within a small radius.
 * 
 * @author Annabelle Harvey and Daniel Chawla.
 */
public class C3P0AI extends AbstractAI{
    private static final int FINDING_R2D2_RANGE = 6;
    private static final int FINDING_SITH_RANGE = 4;
    
    public C3P0AI(){
        
    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        Set<Item> neighbours = world.searchSurroundings(animal);
        Location currentLocation = animal.getLocation();
        Boolean r2d2inRange = false;
      
       if (!neighbours.isEmpty()){
        for(Item item : neighbours){
            if (item.getName().equals("R2D2") && (item.getLocation().getDistance(currentLocation) < FINDING_R2D2_RANGE)){
                r2d2inRange = true;
            }
            if ((item.getName().equals("Clone") || item.getName().equals("grass")) 
                    && (item.getLocation().getDistance(currentLocation) < animal.getViewRange())){
                item.loseEnergy(Integer.MAX_VALUE);
            }
            if (item.getName().equals("Sith") && r2d2inRange 
                    && (item.getLocation().getDistance(currentLocation) < FINDING_SITH_RANGE)){ // can kill Sith if R2D2 is in range
                item.loseEnergy(Integer.MAX_VALUE);
            }
        }
       }
        
        if (getRandomLegalMoveLoc(world, animal, currentLocation) != null){
            return new MoveCommand(animal,towardsItem(world, animal, "R2D2")); // tries to move towards R2D2
        }
        
        return new WaitCommand();
    }
}
