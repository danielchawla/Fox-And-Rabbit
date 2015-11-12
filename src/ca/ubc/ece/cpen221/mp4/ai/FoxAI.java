package ca.ubc.ece.cpen221.mp4.ai;

import java.util.List;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.animals.*;

/**
 * AI for foxes.
 */
public class FoxAI extends AbstractAI {

	private final int MAX_FOXES = 3;

	public FoxAI() {

	}

	@Override
	public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
	    List<Location> rabbitLocations = itemLocations(world, animal, "Rabbit");
	    List<Location> foxLocations = itemLocations(world, animal, "Fox");
	    Location currentLoc = animal.getLocation();
        
	    if (eatYourNeighbour(world, animal, "Rabbit") != null) {
	        return new EatCommand(animal,(eatYourNeighbour(world, animal, "Rabbit")));
	    }
	    
	    if (!rabbitLocations.isEmpty() && (getRandomLegalMoveLoc(world, animal, currentLoc) != null)){
	        return new MoveCommand(animal, towardsItem(world, animal, "Rabbit"));
	    }
	    
	    if ((foxLocations.size() < MAX_FOXES) && (animal.getEnergy() > animal.getMinimumBreedingEnergy())
                && (getRandomLegalMoveLoc(world, animal, currentLoc) != null)) {
            return new BreedCommand(animal,getRandomLegalMoveLoc(world, animal, currentLoc));
        }
	    
	    if (getRandomLegalMoveLoc(world, animal, currentLoc) != null){
            return new MoveCommand(animal,getRandomLegalMoveLoc(world, animal, currentLoc));
        }
        
        return new WaitCommand();
	}
}


