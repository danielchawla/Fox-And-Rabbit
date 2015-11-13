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
 * AI to help R2D2 kills massive amounts of clones and wander about.
 * 
 * @author Annabelle Harvey and Daniel Chawla.
 */
public class R2D2AI extends AbstractAI {
    private static final int MIN_KILLING_DISTANCE_AWAY = 10;
    
    public R2D2AI(){
        
    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        Set<Item> neighbours = world.searchSurroundings(animal);
        Location currentLocation = animal.getLocation();
      
       if (!neighbours.isEmpty()){
        for(Item item : neighbours){
            if (((item.getLocation().getX() == currentLocation.getX())
                    || (item.getLocation().getY() == currentLocation.getY())) 
                    && (item.getName().equals("Clone") || item.getName().equals("grass"))){
                item.loseEnergy(Integer.MAX_VALUE);
            }
        }
       }
       
       
       if (!neighbours.isEmpty() && (Math.random() > 0.75)){
           for(Item item : neighbours){
               if (((item.getLocation().getDistance(currentLocation) < animal.getViewRange())
                       && (item.getLocation().getDistance(currentLocation) > MIN_KILLING_DISTANCE_AWAY) 
                       && (item.getName().equals("Clone")))){ // kills all clones with specified range
                   item.loseEnergy(Integer.MAX_VALUE);
               }
           }
          }
        
        if (getRandomLegalMoveLoc(world, animal, currentLocation) != null){
            return new MoveCommand(animal,getRandomLegalMoveLoc(world, animal, currentLocation));
        }
        
        return new WaitCommand();
    }
}
