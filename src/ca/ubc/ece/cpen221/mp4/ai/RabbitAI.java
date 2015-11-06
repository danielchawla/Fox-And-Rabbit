package ca.ubc.ece.cpen221.mp4.ai;

import java.util.HashSet;
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
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;
import ca.ubc.ece.cpen221.mp4.items.animals.Fox;
import ca.ubc.ece.cpen221.mp4.items.animals.Rabbit;

/**
 * Your Rabbit AI.
 */
public class RabbitAI extends AbstractAI {

	private int closest = 10; // max number; greater than rabbit's view range
	private int temp;
	private boolean foxFound;
	private final int MAX_RABBITS = 1;

	public RabbitAI() {
	}

	@Override
	public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
	    Set<Item> neighbours = world.searchSurroundings(animal);
	    Set<Item> immediateNeighbours = new HashSet<Item>();
	    Location currentLoc = animal.getLocation();
	    foxFound = false;
	   // int rabbitCount = 0;
	    
	    for (Item item : neighbours) {
	        if (currentLoc.getDistance(item.getLocation()) == 1){
	            immediateNeighbours.add(item);
	            if (item instanceof Fox) foxFound = true;
	       //     if (item instanceof Rabbit) rabbitCount++;
	        }
	    }
	    
	    if(foxFound && (immediateNeighbours.size() < 4) && (towardsClosestFood(world, animal, "grass") != null )){
                return new MoveCommand(animal, towardsClosestFood(world, animal, "grass"));
	    }
	 
	       for ( Item item : immediateNeighbours) {
	           if(item instanceof Grass)
	               return new EatCommand(animal, item);
	           }
	       
	    if((animal.getMinimumBreedingEnergy() <= animal.getEnergy())&& (immediateNeighbours.size() < 4)){
	        return new BreedCommand(animal, Util.getRandomEmptyAdjacentLocation((World) world, currentLoc));
	    }
	    
	    
	    if ((immediateNeighbours.size() < 4) && (towardsClosestFood(world, animal, "grass") != null )){
                return new MoveCommand(animal, towardsClosestFood(world, animal, "grass"));
	    }

		return new WaitCommand();
	}
}
