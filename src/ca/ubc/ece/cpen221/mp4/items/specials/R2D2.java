package ca.ubc.ece.cpen221.mp4.items.specials;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.SpecialsAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.animals.AbstractArenaAnimal;

public class R2D2 extends AbstractArenaAnimal {
    private static final ImageIcon R2D2IMAGE = Util.loadImage("R2D2.gif");
    private static final int STRENGTH = 10;
    private static final int COOLDOWN = 5;
    private static final int INITIAL_ENERGY = 1000;
    private static final int MAX_ENERGY = 1000;
    private static final int VIEW_RANGE = 2;
    private static final String name = "R2D2";
    private final SpecialsAI specialsAI;

    public R2D2 (SpecialsAI ai, Location initialLocation){
        this.specialsAI = ai;
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
        Command nextAction = specialsAI.getNextAction(world, this);
        setEnergy(getEnergy() -1 ); // Loses 1 energy regardless of action.
        return nextAction;
    }

}
