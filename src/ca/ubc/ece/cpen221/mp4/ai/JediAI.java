package ca.ubc.ece.cpen221.mp4.ai;

import java.util.List;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;

/**
 * AI to help kill clones within a given radius, if two or more Jedi are close
 * enough together their killing radius increases.
 * 
 * @author Annabelle Harvey and Daniel Chawla.
 */
public class JediAI extends AbstractAI {
    
    private static final int FEEL_OTHER_JEDI_RANGE = 7;
    private static final int RANGE_INCREASE = 3;
    
    public JediAI() {

    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        List<Location> cloneLocations = itemLocations(world, animal, "Clone");
        List<Location> jediLocations = itemLocations(world, animal, "Jedi");
        Location currentLoc = animal.getLocation();
        Set<Item> neighbours = world.searchSurroundings(animal);
        int killRange = animal.getViewRange();
        
        if (!jediLocations.isEmpty()) {
            for (Item item : neighbours){
                if (item.getName().equals("Jedi") && (item.getLocation().getDistance(currentLoc) < FEEL_OTHER_JEDI_RANGE)){
                    killRange += RANGE_INCREASE; // if another Jedi is around, range increases
                }
            }
        }
        
        if (!cloneLocations.isEmpty()) {
            for (Item item : neighbours){
                if (item.getName().equals("Clone") && (item.getLocation().getDistance(currentLoc) < killRange)){
                    item.loseEnergy(Integer.MAX_VALUE); // kills clones if within range
                }
            }
        }
        
        if (!cloneLocations.isEmpty() && (getRandomLegalMoveLoc(world, animal, currentLoc) != null)){
            return new MoveCommand(animal, towardsItem(world, animal, "Clone")); // moves towards clones
        }
        
        
        if (getRandomLegalMoveLoc(world, animal, currentLoc) != null){
            return new MoveCommand(animal,getRandomLegalMoveLoc(world, animal, currentLoc));
        }
        
        return new WaitCommand();
    }
}
