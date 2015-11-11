package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
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
    public static final Random RAND = new Random(2013);
    
    /**
     * 
     * @param dir
     * @return
     */
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

	/**
	 * 
	 * @param world
	 * @param animal
	 * @param location
	 * @return
	 */
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
	/**
	 * 
	 */
	@Override
	public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
		return new WaitCommand();
	}
	
	/**
	 * 
	 * @param world
	 * @param animal
	 * @param loc
	 * @return
	 */
    public Location getRandomLegalMoveLoc(ArenaWorld world, ArenaAnimal animal, Location loc){
        List<Location> neighbours = new LinkedList<Location>();
        Location targetLoc;
        for (Direction dir : Direction.values()){
            targetLoc = new Location(loc, dir);
            if (isLocationEmpty(world, animal, targetLoc)){
                neighbours.add(targetLoc);
            }
        }
        if (neighbours.isEmpty()) return null;
        return neighbours.get(RAND.nextInt(neighbours.size()));

    }
    /**
     * 
     * @param world
     * @param animal
     * @param itemName
     * @return
     */
	public List<Location> itemLocations (ArenaWorld world, ArenaAnimal animal, String itemName){
	    Set<Item> neighbours = world.searchSurroundings(animal);
	    List<Location> itemLocs = new LinkedList<Location>();
	    for (Item item : neighbours){
	        if (item.getName().equals(itemName)){
	            itemLocs.add(item.getLocation());
	        }
	    }
	    return itemLocs;
	}
	
	/**
	 * 
	 * @param world
	 * @param animal
	 * @param preyName
	 * @return
	 */
	public Item eatYourNeighbour (ArenaWorld world, ArenaAnimal animal, String preyName){
	    Set<Item> neighbours = world.searchSurroundings(animal);
	    Location currentLoc = animal.getLocation();
	    
        for (Item item : neighbours){
            if (item.getName().equals(preyName) && (item.getLocation().getDistance(currentLoc) ==1)){
                return item;
            }
        }
        
        return null;
	}
	/**
	 * 
	 * @param world
	 * @param animal
	 * @param itemName
	 * @return
	 */
	public Location towardsItem(ArenaWorld world, ArenaAnimal animal, String itemName){
	    List<Location> itemLocs = itemLocations(world, animal, itemName);
	    TreeMap<Integer, Direction> closeItem = new TreeMap<Integer, Direction>();
	    Direction itemDir;
	    Location currentLoc = animal.getLocation();
	    Location targetLoc = getRandomLegalMoveLoc(world, animal, currentLoc);
	    Location targetLocBeta;
	    
	    for (int i = 0; i < itemLocs.size(); i++){
	            closeItem.put(currentLoc.getDistance(itemLocs.get(i)), Util.getDirectionTowards(currentLoc, itemLocs.get(i)));
	    }
	    
	    if(!closeItem.isEmpty()){
	    itemDir = closeItem.firstEntry().getValue();
	    targetLocBeta = new Location(currentLoc, itemDir);
	    if (isLocationEmpty( world, animal, targetLocBeta)) return targetLocBeta;
	    }
	    
	    return targetLoc;
	    }
	
	/**
	 * 
	 * @param world
	 * @param animal
	 * @param itemName
	 * @return
	 */
	public Location awayFromItem(ArenaWorld world, ArenaAnimal animal, String itemName){
	    List<Location> itemLocs = itemLocations(world, animal, itemName);
	    Location currentLoc = animal.getLocation();
	    Location targetLoc = getRandomLegalMoveLoc(world,animal,currentLoc);
	    Location targetLocBeta;
	    List<Direction> awayDirs = new LinkedList<Direction>();
	    
	    for(int i = 0; i < itemLocs.size(); i++){
	        Direction testDir = Util.getDirectionTowards(currentLoc, itemLocs.get(i));
	        if (awayDirs.contains(testDir)) awayDirs.remove(testDir);
	        else awayDirs.add(oppositeDir(testDir));
	    }
	    for (int i = 0; i < awayDirs.size() ; i++){
	    targetLocBeta = new Location(currentLoc, awayDirs.get(i));
	    if (isLocationEmpty(world, animal, targetLocBeta)) return targetLocBeta;
	    }
	    
	    return targetLoc;
	}
	
}

