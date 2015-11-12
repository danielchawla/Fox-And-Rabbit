package ca.ubc.ece.cpen221.mp4.items.specials;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.RandomMovementAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.animals.AbstractArenaAnimal;

public class R2D2 extends AbstractArenaAnimal {
    private static final ImageIcon R2D2IMAGE = Util.loadImage("R2D2.gif");
    private static final int STRENGTH = 10;
    private static final int COOLDOWN = 10;
    private static final int INITIAL_ENERGY = 1000;
    private static final int MAX_ENERGY = 1000;
    private static final int VIEW_RANGE = 200;
    private static final String name = "R2D2";
    private final RandomMovementAI randomAI;

    public R2D2 (RandomMovementAI ai, Location initialLocation){
        this.randomAI = ai;
        this.setINITIAL_ENERGY(INITIAL_ENERGY);
        this.setMAX_ENERGY(MAX_ENERGY);
        this.setCOOLDOWN(COOLDOWN);
        this.setVIEW_RANGE(VIEW_RANGE);
        this.setSTRENGTH(STRENGTH);
        this.setEnergy(INITIAL_ENERGY);
        this.setLocation(initialLocation); 
        this.setName(R2D2.name);
        this.setImage(R2D2IMAGE);
    }
    
    @Override
    public LivingItem breed() {
        // R2D2 is incapable of breeding.
        return null;
    }
    
    @Override
    public Command getNextAction(World world) {
<<<<<<< HEAD
        Command nextAction = specialsAI.getNextAction(world, this);
        
=======
        Command nextAction = randomAI.getNextAction(world, this);
        setEnergy(getEnergy() -1 ); // Loses 1 energy regardless of action.
>>>>>>> 6c14825b7566e7bef9d9785a919b854cee8c4870
        return nextAction;
    }

}
