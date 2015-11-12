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
 * Rabbits try and survive as long as possible, eating grass, keeping away from
 * foxes, and breeding when possible. 
 * 
 * @author Annabelle Harvey and Daniel Chawla
 */
public class RabbitAI extends AbstractAI {

	private static final int MAX_RABBITS = 2;
	private static final int ENERGY_THRESH_EAT = 40;

	public RabbitAI() {
	}

	@Override
	public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
	    List<Location> foxLocations = itemLocations(world, animal, "Fox");
	    List<Location> grassLocations = itemLocations(world, animal, "grass");
	    List<Location> rabbitLocations = itemLocations(world, animal, "Rabbit");
	    Location currentLoc = animal.getLocation();
	    
	    if(!foxLocations.isEmpty() && (getRandomLegalMoveLoc(world, animal, currentLoc) != null)){
	        return new MoveCommand(animal, awayFromItem(world, animal, "Fox")); // dodges foxes
	    }
	    
	    if(animal.getEnergy() < ENERGY_THRESH_EAT){
	        if (eatYourNeighbour(world, animal, "grass") != null) {
	            return new EatCommand(animal, eatYourNeighbour(world, animal, "grass")); // eats grass if hungry
	        }
	      }
	    
	    if (!grassLocations.isEmpty() && (getRandomLegalMoveLoc(world, animal, currentLoc) != null)){
	        return new MoveCommand(animal, towardsItem(world, animal, "grass")); // moves towards grass
	    }
	    
	    if ((rabbitLocations.size() < MAX_RABBITS) && (animal.getEnergy() > animal.getMinimumBreedingEnergy())
	            && (getRandomLegalMoveLoc(world, animal, currentLoc) != null)) { 
	        return new BreedCommand(animal,getRandomLegalMoveLoc(world, animal, currentLoc)); //breeds if possible
	    }
	    
	    if (getRandomLegalMoveLoc(world, animal, currentLoc) != null){
	        return new MoveCommand(animal,getRandomLegalMoveLoc(world, animal, currentLoc));
	    }
	    
	    return new WaitCommand();
	}
}
