package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
/**
 * Jedi based off Star Wars series. There Jedi are a unique animal in this world
 * with unique abilities. 
 * 
 * @author Annabelle Harvey and Daniel Chawla
 *
 */
public class Jedi extends AbstractArenaAnimal {
    
    private static final int INITIAL_ENERGY = 100;
    private static final int MAX_ENERGY = 100;
    private static final int MIN_BREEDING_ENERGY = 100;
    private static final int STRENGTH = 200;
    private static final int VIEW_RANGE = 50;
    private static final int COOLDOWN = 1;
    private static final ImageIcon JEDIIMAGE = Util.loadImage("jedi.gif");
    private static final String NAME = "JEDI";

    private AI ai;
    
    /**
     * Create a new Jedi with an AI at
     * initialLocation. The initialLocation must be
     * valid and empty
     *
     * @param jediAI
     *            the AI designed for clones
     * @param initialLocation
     *            the location where this clone will be created
     */
    public Jedi(AI jediAI, Location initialLocation) {
        this.ai = jediAI;        
        this.setINITIAL_ENERGY(INITIAL_ENERGY);
        this.setMAX_ENERGY(MAX_ENERGY);
        this.setCOOLDOWN(COOLDOWN);
        this.setVIEW_RANGE(VIEW_RANGE);
        this.setSTRENGTH(STRENGTH);
        this.setEnergy(INITIAL_ENERGY);
        this.setMIN_BREEDING_ENERGY(MIN_BREEDING_ENERGY);
        this.setLocation(initialLocation);  
        this.setImage(JEDIIMAGE);
        this.setName(Jedi.NAME);
    }
    
    /**
     * Methods below all have specs already.
     */
    
    @Override
    public LivingItem breed() {
        // I guess there's no females for male Jedi to breed with.
        return null;
    }

    @Override
    public Command getNextAction(World world) {
        Command nextAction = ai.getNextAction(world, this);
        return nextAction;
    }

}
