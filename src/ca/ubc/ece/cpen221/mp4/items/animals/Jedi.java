package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.JediAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
/**
 * Jedi based off Star Wars series. Jedi kill clones within a given radius, if two or more Jedi are close
 * enough together their killing radius increases.
 * 
 * @author Annabelle Harvey and Daniel Chawla
 */
public class Jedi extends AbstractArenaAnimal {
    
    private static final int INITIAL_ENERGY = 100;
    private static final int MAX_ENERGY = 100;
    private static final int MIN_BREEDING_ENERGY = 100;
    private static final int STRENGTH = 200;
    private static final int VIEW_RANGE = 10;
    private static final int COOLDOWN = 1;
    private static final ImageIcon JEDIIMAGE = Util.loadImage("jedi.gif");
    private static final String NAME = "JEDI";

    private final JediAI jediAI;
    
    /**
     * Create a new Jedi with an AI at initialLocation. The initialLocation must be
     * valid and empty.
     *
     * @param jediAI
     *            the AI designed for clones
     * @param initialLocation
     *            the location where this clone will be created
     */
    public Jedi(JediAI jediAI, Location initialLocation) {
        this.jediAI = jediAI; 
        this.setIsDead(false);
        this.setInitialEnergy(INITIAL_ENERGY);
        this.setMaxEnergy(MAX_ENERGY);
        this.setCoolDown(COOLDOWN);
        this.setViewRange(VIEW_RANGE);
        this.setStrength(STRENGTH);
        this.setEnergy(INITIAL_ENERGY);
        this.setMinBreedingEnergy(MIN_BREEDING_ENERGY);
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
        Command nextAction = jediAI.getNextAction(world, this);
        return nextAction;
    }

}
