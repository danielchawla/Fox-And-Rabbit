package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.*;

/**
 * Your Fox AI.
 */
public class FoxAI extends AbstractAI {
	private int closest = 2; // max number; greater than fox's view range
	private final int MAX_FOXES = 1;

	public FoxAI() {

	}

	@Override
	public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
	    Set<Item> neighbours = world.searchSurroundings(animal);
	    List<Location> rabbitLocations = itemLocations(world, animal, "Rabbit");
	    Location currentLoc = animal.getLocation();
        
	    if (eatYourNeighbour(world, animal, "Rabbit") != null) {
	        return new EatCommand(animal,(eatYourNeighbour(world, animal, "Rabbit")));
	    }
	    
	    if (!rabbitLocations.isEmpty() && (getRandomLegalMoveLoc(world, animal, currentLoc) != null)){
	        return new MoveCommand(animal, towardsItem(world, animal, "Rabbit"));
	    }
	    
	    if ((rabbitLocations.size() < MAX_FOXES) && (animal.getEnergy() > animal.getMinimumBreedingEnergy())
                && (getRandomLegalMoveLoc(world, animal, currentLoc) != null)) {
            return new BreedCommand(animal,getRandomLegalMoveLoc(world, animal, currentLoc));
        }
	    
	    if (getRandomLegalMoveLoc(world, animal, currentLoc) != null){
            return new MoveCommand(animal,getRandomLegalMoveLoc(world, animal, currentLoc));
        }
        
        return new WaitCommand();
	}
}


