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
        List<Item> immediateNeighbours = new LinkedList<Item>();
        Location currentLoc = animal.getLocation();
        int foxCount = 0;
        Boolean foodFound = false;

        for (Item item : neighbours) {      
            if (currentLoc.getDistance(item.getLocation()) == 1){
                immediateNeighbours.add(item);
                if (item instanceof Fox) foxCount++;
                if (item instanceof Rabbit) foodFound = true;
            }
        }

           for (int i = 0; i < immediateNeighbours.size(); i++) {
               if(immediateNeighbours.get(i).getName().equals("Rabbit"))
                   return new EatCommand(animal, immediateNeighbours.get(i));
              }
           
        if (Math.random() > 0.9){
        if((foxCount < MAX_FOXES) && (animal.getMinimumBreedingEnergy() <= animal.getEnergy()) && (Util.getRandomEmptyAdjacentLocation((World) world, currentLoc) != null)){
            return new BreedCommand(animal, Util.getRandomEmptyAdjacentLocation((World) world, currentLoc));
        }
        }
        
        if ((Util.getRandomLegalMoveLoc((World) world, currentLoc) != null) && foodFound) {
               return new MoveCommand(animal, towardsClosestFood(world, animal, "Rabbit"));
        }
        
        if (Util.getRandomLegalMoveLoc((World) world, currentLoc) != null) {
            return new MoveCommand(animal, rove(world, animal));
        }

        return new WaitCommand();
    }
	}


