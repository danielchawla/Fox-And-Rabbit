package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.RandomMovementAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

/**
 * Wookie animal based off Star Wars series. 
 * 
 * @author Annabelle Harvey and Daniel Chawla
 *
 */
public class Wookie extends AbstractArenaAnimal {
    
    private static final int INITIAL_ENERGY = 100;
    private static final int MAX_ENERGY = 120;
    private static final int STRENGTH = 20;
    private static final int VIEW_RANGE = 6;
    private static final int MIN_BREEDING_ENERGY = 1;
    private static final int COOLDOWN = 3;
    private static final ImageIcon WOOKIEIMAGE = Util.loadImage("wookie.gif");
    private static final String WOOKIENAME = "Wookie";
    
    private final RandomMovementAI randomAI;
    
    
    /**
     * Create a new Wookie with an AI that causes wookie to trot around randomly.
     * The initialLocation must be valid and empty
     *
     * @param RandomMovementAI - causes wookie to move randomly throughout world
     * @param initialLocation - the location where this clone will be created
     */
    public Wookie(RandomMovementAI ai, Location initialLocation) {
        this.randomAI = ai;
        this.setINITIAL_ENERGY(INITIAL_ENERGY);
        this.setMAX_ENERGY(MAX_ENERGY);
        this.setCOOLDOWN(COOLDOWN);
        this.setVIEW_RANGE(VIEW_RANGE);
        this.setSTRENGTH(STRENGTH);
        this.setEnergy(INITIAL_ENERGY);
        this.setMIN_BREEDING_ENERGY(MIN_BREEDING_ENERGY);
        this.setLocation(initialLocation);  
        this.setImage(WOOKIEIMAGE);
        this.setName(WOOKIENAME);
    }

    /**
     * Methods below all have specs already.
     */
    
    @Override
    public LivingItem breed() {
        return null; // These wookies are too hairy to breed here
    }

    @Override
    public Command getNextAction(World world) {
        Command nextAction = randomAI.getNextAction(world, this);
        return nextAction;
    }

}
