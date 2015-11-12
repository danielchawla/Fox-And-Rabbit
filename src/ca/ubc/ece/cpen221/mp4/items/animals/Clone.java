package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.CloneAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

/**
 * Clone is based off Star Wars series. It is a rapidly cloning animal-like
 * character with. If you aren't careful, it'll take over everything.  
 * 
 * @author Annabelle Harvey and Daniel Chawla
 */
public class Clone extends AbstractArenaAnimal {
    
    private static final int INITIAL_ENERGY = 100;
    private static final int MAX_ENERGY = 120;
    private static final int STRENGTH = 10;
    private static final int VIEW_RANGE = 5;
    private static final int MIN_BREEDING_ENERGY = 0;
    private static final int COOLDOWN = 10;
    private static final ImageIcon CLONEIMAGE = Util.loadImage("clone.gif");
    private static final String NAME = "Clone";

    private final CloneAI cloneAI;
    
    
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
    public Clone(CloneAI cloneAI, Location initialLocation) {
        this.cloneAI = cloneAI;
        this.setInitialEnergy(INITIAL_ENERGY);
        this.setMaxEnergy(MAX_ENERGY);
        this.setCoolDown(COOLDOWN);
        this.setViewRange(VIEW_RANGE);
        this.setStrength(STRENGTH);
        this.setEnergy(INITIAL_ENERGY);
        this.setMinBreedingEnergy(MIN_BREEDING_ENERGY);
        this.setLocation(initialLocation);  
        this.setImage(CLONEIMAGE);
        this.setName(Clone.NAME);
    }

    /**
     * Methods below all have specs already.
     */
    
    @Override
    public LivingItem breed() {
        Clone child = new Clone(cloneAI, this.getLocation());
        return child;
    }

    @Override
    public Command getNextAction(World world) {
        Command nextAction = cloneAI.getNextAction(world, this);
        return nextAction;
    }

}