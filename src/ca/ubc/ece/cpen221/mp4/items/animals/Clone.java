package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Clone implements ArenaAnimal {
    
    private static final int INITIAL_ENERGY = 100;
    private static final int MAX_ENERGY = 120;
    private static final int STRENGTH = 10;
    private static final int VIEW_RANGE = 5;
    private static final int MOVING_RANGE = 1;
    private static final int MIN_BREEDING_ENERGY = 20;
    private static final int COOLDOWN = 3;
    private static final ImageIcon cloneImage = Util.loadImage("clone.gif");

    private final AI ai;

    private Location location;
    private int energy;

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
        this.location = initialLocation;

        this.energy = INITIAL_ENERGY;
    }
    
    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public LivingItem breed() {
        Clone child = new Clone(ai, location);
        return child;
    }

    @Override
    public void eat(Food food) {
        
    }

    @Override
    public int getMovingRange() {
        return MOVING_RANGE;
    }

    @Override
    public ImageIcon getImage() {
        return cloneImage;
    }

    @Override
    public String getName() {
        return "Clone";
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public void loseEnergy(int energyLoss) {
        this.energy = this.energy - energyLoss;
    }

    @Override
    public boolean isDead() {
        return energy <= 0;
    }

    @Override
    public int getPlantCalories() {
        return 0;
    }

    @Override
    public int getMeatCalories() {
        return energy;
    }

    @Override
    public int getCoolDownPeriod() {
        return COOLDOWN;
    }

    @Override
    public Command getNextAction(World world) {
        Command nextAction = ai.getNextAction(world, this);
        return nextAction;
    }

    @Override
    public int getMaxEnergy() {
        return MAX_ENERGY;
    }

    @Override
    public int getViewRange() {
        return VIEW_RANGE;
    }

    @Override
    public int getMinimumBreedingEnergy() {
        return MIN_BREEDING_ENERGY;
    }
    
    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;   
    }

}
