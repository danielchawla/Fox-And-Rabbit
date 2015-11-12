package ca.ubc.ece.cpen221.mp4.ai;

import java.util.List;
import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;


/**
 * Rabbit AI.
 * 
 * @author Annabelle Harvey and Daniel Chawla
 */
public class RabbitAI extends AbstractAI {

	private final int MAX_RABBITS = 2;
	private final int ENERGY_THRESH_EAT = 40;

	public RabbitAI() {
	}

	@Override
	public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
	    List<Location> foxLocations = itemLocations(world, animal, "Fox");
	    List<Location> grassLocations = itemLocations(world, animal, "grass");
	    List<Location> rabbitLocations = itemLocations(world, animal, "Rabbit");
	    Location currentLoc = animal.getLocation();
	    
	    if(!foxLocations.isEmpty() && (getRandomLegalMoveLoc(world, animal, currentLoc) != null)){
	        return new MoveCommand(animal, awayFromItem(world, animal, "Fox"));
	    }
	    
	    if(animal.getEnergy() < ENERGY_THRESH_EAT){
	        if (eatYourNeighbour(world, animal, "grass") != null) {
	            return new EatCommand(animal, eatYourNeighbour(world, animal, "grass"));
	        }
	      }
	    
	    if (!grassLocations.isEmpty() && (getRandomLegalMoveLoc(world, animal, currentLoc) != null)){
	        return new MoveCommand(animal, towardsItem(world, animal, "grass"));
	    }
	    
	    if ((rabbitLocations.size() < MAX_RABBITS) && (animal.getEnergy() > animal.getMinimumBreedingEnergy())
	            && (getRandomLegalMoveLoc(world, animal, currentLoc) != null)) {
	        return new BreedCommand(animal,getRandomLegalMoveLoc(world, animal, currentLoc));
	    }
	    
	    if (getRandomLegalMoveLoc(world, animal, currentLoc) != null){
	        return new MoveCommand(animal,getRandomLegalMoveLoc(world, animal, currentLoc));
	    }
	    
	    return new WaitCommand();
	}
}
