package ca.ubc.ece.cpen221.mp4.ai;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;
/**
 * AI Class that finds random legal moves and creates Move comands towards them.
 * 
 * @author Annabelle Harvey and Daniel Chawla
 */
public class RandomMovementAI extends AbstractAI {
    
    public RandomMovementAI(){
        
    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {

        Location currentLocation = animal.getLocation();
  
        if (getRandomLegalMoveLoc(world, animal, currentLocation) != null){
            // if random move is possible, move there.
            return new MoveCommand(animal,getRandomLegalMoveLoc(world, animal, currentLocation)); 
        }
        
        return new WaitCommand();
    }
}