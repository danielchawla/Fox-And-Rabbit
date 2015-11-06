package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

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

public class AbstractAI implements AI {

	public Direction oppositeDir(Direction dir) { 
		if (dir == Direction.EAST) {
			return Direction.WEST;
		} else if (dir == Direction.WEST) {
			return Direction.EAST;
		} else if (dir == Direction.SOUTH) {
			return Direction.NORTH;
		} else {
			return Direction.SOUTH;
		}
	}

	public boolean isLocationEmpty(ArenaWorld world, ArenaAnimal animal, Location location) { 
		if (!Util.isValidLocation(world, location)) {
			return false;
		}
		Set<Item> possibleMoves = world.searchSurroundings(animal);
		Iterator<Item> it = possibleMoves.iterator();
		while (it.hasNext()) {
			Item item = it.next();
			if (item.getLocation().equals(location)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
		return new WaitCommand();
	}
	
	public Location towardsClosestFood(ArenaWorld world, ArenaAnimal animal, String foodSource){
	    Set<Item> neighbours = world.searchSurroundings(animal);
	    TreeMap<Integer, Item> closeFood = new TreeMap<Integer, Item>();
	    Location foodLoc;
	    Location currentLoc = animal.getLocation();
	    Location targetLoc;
	    
	    targetLoc = Util.getRandomLegalMoveLoc((World) world, currentLoc);
	    
	    
	    for (Item item : neighbours){
	        if (item.getName().equals(foodSource)) {
	            closeFood.put(currentLoc.getDistance(item.getLocation()), item);
	        }
	    }
	    
	    if(!closeFood.isEmpty()){
	    foodLoc = closeFood.firstEntry().getValue().getLocation();
	    Direction dir = Util.getDirectionTowards(currentLoc, foodLoc);
	    Location targetLocBeta = new Location(currentLoc, dir);
	    if (Util.isLocationEmpty((World) world, targetLocBeta)) targetLoc = new Location(targetLocBeta);
	    }
	    
	    return targetLoc;
	    }
	}

