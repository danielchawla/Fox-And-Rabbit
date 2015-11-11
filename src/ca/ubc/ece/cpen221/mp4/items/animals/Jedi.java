package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Jedi implements ArenaAnimal {
    
    private static final int INITIAL_ENERGY = 100;
    private static final int STRENGTH = 200;
    private static final int VIEW_RANGE = 50;
    private static final int MOVING_RANGE = 5;
    private static final int COOLDOWN = 1;
    private static final int KILL_RANGE = 5;
    private static final ImageIcon jediImage = Util.loadImage("hunter.gif");

    private final AI ai;

    private Location location;
    private int energy;

    /**
     * Create a new jedi with an AI at
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
        this.location = initialLocation;

        this.energy = INITIAL_ENERGY;
    }
    
    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public LivingItem breed() {
        return null;
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
        return jediImage;
    }

    @Override
    public String getName() {
        return "Jedi";
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
    public int getViewRange() {
        return VIEW_RANGE;
    }

    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;   
    }

    @Override
    public int getMaxEnergy() {
        return INITIAL_ENERGY;
    }

    @Override
    public int getMinimumBreedingEnergy() {
        return 0;
    }
    
    public int getKillRange(){
        return KILL_RANGE;
    }

}
