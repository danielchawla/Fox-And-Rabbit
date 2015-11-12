package ca.ubc.ece.cpen221.mp4.items.specials;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.SpecialsAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.animals.AbstractArenaAnimal;

public class C3P0 extends AbstractArenaAnimal {
    private static final ImageIcon C3P0IMAGE = Util.loadImage("C3P0.gif");
    private static final int STRENGTH = 50;
    private static final int COOLDOWN = 4;
    private static final int INITIAL_ENERGY = 200;
    private static final int MAX_ENERGY = 200;
    private static final int VIEW_RANGE = 1;
    private static final String name = "C3P0";
    private final SpecialsAI specialsAI;

    public C3P0 (SpecialsAI ai, Location initialLocation){
        this.specialsAI = ai;
        this.setINITIAL_ENERGY(INITIAL_ENERGY);
        this.setMAX_ENERGY(MAX_ENERGY);
        this.setCOOLDOWN(COOLDOWN);
        this.setVIEW_RANGE(VIEW_RANGE);
        this.setSTRENGTH(STRENGTH);
        this.setEnergy(INITIAL_ENERGY);
        this.setLocation(initialLocation);
        this.setName(C3P0.name);
        this.setImage(C3P0IMAGE);
    }
    
    @Override
    public LivingItem breed() {
        // C3P0 is a robot incapable of breeding
        return null;
    }
    
    @Override
    public Command getNextAction(World world) {
        Command nextAction = specialsAI.getNextAction(world, this);
        setEnergy(getEnergy() -1 ); // Loses 1 energy regardless of action.
        return nextAction;
    }

}
