package ca.ubc.ece.cpen221.mp4.items.specials;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.R2D2AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.animals.AbstractArenaAnimal;

/**
 * R2D2 robot based off Star Wars Series. R2D2 is a robotic animal.
 * 
 * @author Annabelle Harvey and Daniel Chawla.
 */
public class R2D2 extends AbstractArenaAnimal {
    private static final ImageIcon R2D2IMAGE = Util.loadImage("R2D2.gif");
    private static final int STRENGTH = 10;
    private static final int COOLDOWN = 2;
    private static final int INITIAL_ENERGY = 1000;
    private static final int MAX_ENERGY = 1000;
    private static final int VIEW_RANGE = 200;
    private static final String name = "R2D2";
    private final R2D2AI r2d2AI;

    /**
     * Constructs a R2D2 animal robot. The initialLocation must be
     * valid and empty.
     * @param ai that cause R2D2 to move around randomly
     * @param initialLocation to be placed
     */
    public R2D2 (R2D2AI ai, Location initialLocation){
        this.r2d2AI = ai;
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
    
    /**
     * Methods below all have specs already.
     */
    
    @Override
    public LivingItem breed() {
        // R2D2 is incapable of breeding.
        return null;
    }
    
    @Override
    public Command getNextAction(World world) {
        Command nextAction = r2d2AI.getNextAction(world, this);        
        return nextAction;
    }

}
