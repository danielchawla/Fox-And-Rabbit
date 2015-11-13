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
     * Returns opposite direction to inputted direction
     * @param direction 
     * @return opposite direction
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
	 * Checks if a specific location doesn't contain animal items in it.
	 * @param world world that contains the animal and its surroundings.
     * @param animal animal doing the moving.
     * @param loc current location of the animal.
     * @return true if location is empty. false if empty
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
	 * @param world world that contains the animal and its surroundings.
	 * @param animal animal doing the moving.
	 * @param loc current location of the animal.
	 * @return a legal location for the animal to move to (of distance one from the animal), or null if 
	 *         none exist.
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
     * @param world world to search through.
     * @param animal animal doing the searching. This defines the view range and current location.
     * @param itemName the string name of the item to search for eg "Fox" or "grass"
     * @return A list of locations where the type of item is found within the view range of the given animal.
     *         if type of item is the same as the animal, the current location of the animal will be included
     *         in this list.
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
	 * @param world world that contains both animal and prey.
	 * @param animal animal doing the eating.
	 * @param preyName they string name of the item type that the animal eats.
	 * @return an item within the range of the animal for it to eat. If there are no prey in the
	 *         animal's range the method will return null.
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
	 * @param world the world that contains the animal and its surroundings.
	 * @param animal the animal doing the moving.
	 * @param itemName the name of the type of item to search for and move towards.
	 * @return if there is an item to move towards within the animal's range, method returns the
	 *         a legal location in that direction. If there is no move in the correct direction available
	 *         it returns a random legal location, and null if there are none.
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
     * @param world the world that contains the animal and its surroundings.
     * @param animal the animal doing the moving.
     * @param itemName the name of the type of item to search for and move away from.
     * @return if there is an item to move away from within the animal's range, method returns the
     *         a legal location in that direction. If there is no move in the correct direction available
     *         it returns a random legal location, and null if there are none.
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
	
    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        return new WaitCommand();
    }
	
}

