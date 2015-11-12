package ca.ubc.ece.cpen221.mp4.ai;

import java.util.List;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;

public class JediAI extends AbstractAI {
    
    private int helperRange = 7;
    
    public JediAI() {

    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        List<Location> cloneLocations = itemLocations(world, animal, "Clone");
        List<Location> jediLocations = itemLocations(world, animal, "Jedi");
        Location currentLoc = animal.getLocation();
        Set<Item> neighbours = world.searchSurroundings(animal);
        int killRange = 5;
        
        if (!jediLocations.isEmpty()) {
            for (Item item : neighbours){
                if (item.getName().equals("Jedi") && (item.getLocation().getDistance(currentLoc) < helperRange)){
                    killRange += 3;
                }
            }
        }
        
        if (!cloneLocations.isEmpty()) {
            for (Item item : neighbours){
                if (item.getName().equals("Clone") && (item.getLocation().getDistance(currentLoc) < killRange)){
                    item.loseEnergy(9999);
                }
            }
        }
        
        if (!cloneLocations.isEmpty() && (getRandomLegalMoveLoc(world, animal, currentLoc) != null)){
            return new MoveCommand(animal, towardsItem(world, animal, "Clone"));
        }
        
        
        if (getRandomLegalMoveLoc(world, animal, currentLoc) != null){
            return new MoveCommand(animal,getRandomLegalMoveLoc(world, animal, currentLoc));
        }
        
        return new WaitCommand();
    }
}
