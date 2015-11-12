package ca.ubc.ece.cpen221.mp4.ai;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;

public class R2D2AI extends AbstractAI {
    
    public R2D2AI(){
        
    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {

        Location currentLocation = animal.getLocation();
        
//        for(Item item : world.searchSurroundings(animal)){
//            if ((item.getLocation().getX() == currentLocation.getX())
//                    || (item.getLocation().getY() == currentLocation.getY())){
//                item.loseEnergy(9999);
//            }
//        }
//        
        if (getRandomLegalMoveLoc(world, animal, currentLocation) != null){
            return new MoveCommand(animal,getRandomLegalMoveLoc(world, animal, currentLocation));
        }
        
        return new WaitCommand();
    }
}
