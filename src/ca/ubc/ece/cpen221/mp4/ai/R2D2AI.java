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
 * AI for R2D2.
 */
public class R2D2AI extends AbstractAI {
    
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
                item.loseEnergy(9999);
            }
        }
       }
       
       
       if (!neighbours.isEmpty() && (Math.random() > 0.75)){
           for(Item item : neighbours){
               if (((item.getLocation().getDistance(currentLocation) < 20)
                       && (item.getLocation().getDistance(currentLocation) > 10) 
                       && (item.getName().equals("Clone")))){
                   item.loseEnergy(9999);
               }
           }
          }
        
        if (getRandomLegalMoveLoc(world, animal, currentLocation) != null){
            return new MoveCommand(animal,getRandomLegalMoveLoc(world, animal, currentLocation));
        }
        
        return new WaitCommand();
    }
}
