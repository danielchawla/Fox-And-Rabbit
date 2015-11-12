package ca.ubc.ece.cpen221.mp4.ai;

import java.util.List;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;

public class CloneAI extends AbstractAI {

    public CloneAI() {
    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        List<Location> jediLocations = itemLocations(world, animal, "Jedi");
        List<Location> cloneLocations = itemLocations(world, animal, "Clone");
        Location currentLoc = animal.getLocation();
        
        if(!jediLocations.isEmpty() && (getRandomLegalMoveLoc(world, animal, currentLoc) != null)){
            return new MoveCommand(animal, awayFromItem(world, animal, "Jedi"));
        }
        
        if (getRandomLegalMoveLoc(world, animal, currentLoc) != null) {
            return new BreedCommand(animal,getRandomLegalMoveLoc(world, animal, currentLoc));
        }
        
        
        if (!cloneLocations.isEmpty() && (getRandomLegalMoveLoc(world, animal, currentLoc) != null)){
            return new MoveCommand(animal, awayFromItem(world, animal, "Clone"));
        }
        
        
        if (getRandomLegalMoveLoc(world, animal, currentLoc) != null){
            return new MoveCommand(animal,getRandomLegalMoveLoc(world, animal, currentLoc));
        }
        
        return new WaitCommand();
    }
}
