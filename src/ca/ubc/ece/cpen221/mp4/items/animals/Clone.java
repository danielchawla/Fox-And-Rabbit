package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Clone extends AbstractArenaAnimal {
    
    private static final int INITIAL_ENERGY = 100;
    private static final int MAX_ENERGY = 120;
    private static final int STRENGTH = 10;
    private static final int VIEW_RANGE = 5;
    private static final int MIN_BREEDING_ENERGY = 0;
    private static final int COOLDOWN = 3;
    private static final ImageIcon CLONEIMAGE = Util.loadImage("clone.gif");
    private static final String NAME = "Clone";

    private Location location;
    private AI ai;
    
    
    /**
     * Create a new clone with an AI at
     * initialLocation. The initialLocation must be
     * valid and empty
     *
     * @param cloneAI
     *            the AI designed for clones
     * @param initialLocation
     *            the location where this clone will be created
     */
    public Clone(AI cloneAI, Location initialLocation) {
        this.ai = cloneAI;
        this.setINITIAL_ENERGY(INITIAL_ENERGY);
        this.setMAX_ENERGY(MAX_ENERGY);
        this.setCOOLDOWN(COOLDOWN);
        this.setVIEW_RANGE(VIEW_RANGE);
        this.setSTRENGTH(STRENGTH);
        this.setEnergy(INITIAL_ENERGY);
        this.setMIN_BREEDING_ENERGY(MIN_BREEDING_ENERGY);
        this.setLocation(initialLocation);  
        this.setImage(CLONEIMAGE);
        this.setName(Clone.NAME);
    }

    @Override
    public LivingItem breed() {
        Clone child = new Clone(ai, location);
        return child;
    }

    @Override
    public Command getNextAction(World world) {
        Command nextAction = ai.getNextAction(world, this);
        return nextAction;
    }

}