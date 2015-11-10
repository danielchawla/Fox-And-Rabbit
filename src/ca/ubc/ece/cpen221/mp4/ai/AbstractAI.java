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
	
	public Boolean itemFound(ArenaWorld world, ArenaAnimal animal, String itemName) {
	    Set<Item> neighbours = world.searchSurroundings(animal);
	    Boolean itemFound = false;
	    
	    for (Item item : neighbours) {    
            if (item.getName().equals(itemName)) itemFound = true;
        }
	    
	    return itemFound;
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
	
	public Location rove(ArenaWorld world, ArenaAnimal animal){
	    Location targetLoc;
	    Location currentLoc = animal.getLocation();
	    Direction dir = Direction.WEST;
	    int index = animal.getEnergy() % 20;
	    
	    if (itemFound(world, animal, animal.getName())){
	    return Util.getRandomLegalMoveLoc((World) world, currentLoc);  
	    }
	    
	    switch (index){
	    case 13: case 11: case 9: case 7: case 5: case 3: case 1:
	        dir = Direction.NORTH;
	    break;
	    case 6: case 4: case 2: case 0:
	        dir = Direction.EAST;
	        break;
	    case 19: case 17: case 15:
	        dir = Direction.SOUTH;
	        break;
	    case 18: case 16: case 14: case 12: case 10: case 8:
	        dir = Direction.WEST;
	        break;
	    }
	    
	    targetLoc = new Location(currentLoc, dir);
	    if (Util.isValidLocation(world, targetLoc) && Util.isLocationEmpty((World) world, targetLoc)) { return targetLoc;}
	    else {return Util.getRandomLegalMoveLoc((World) world, currentLoc);}
	}
	}

