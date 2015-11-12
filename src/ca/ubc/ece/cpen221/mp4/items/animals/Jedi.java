package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Jedi extends AbstractArenaAnimal {
    
    private static final int INITIAL_ENERGY = 100;
    private static final int MAX_ENERGY = 100;
    private static final int MIN_BREEDING_ENERGY = 100;
    private static final int STRENGTH = 200;
    private static final int VIEW_RANGE = 50;
    private static final int COOLDOWN = 1;
    private static final ImageIcon jediImage = Util.loadImage("jedi.gif");

    private AI ai;
    private int energy;
    private Location location;
    
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
        
//        this.setINITIAL_ENERGY(INITIAL_ENERGY);
//        this.setMAX_ENERGY(MAX_ENERGY);
//        this.setCOOLDOWN(COOLDOWN);
//        this.setVIEW_RANGE(VIEW_RANGE);
//        this.setSTRENGTH(STRENGTH);
//        this.setEnergy(INITIAL_ENERGY);
//        this.setMIN_BREEDING_ENERGY(MIN_BREEDING_ENERGY);
//        this.setLocation(initialLocation);  
//        this.setImage(jediImage);
//        this.setName("Jedi");
    }

    @Override
    public LivingItem breed() {
        return null;
    }

    @Override
    public void eat(Food food) {
  
    }

    @Override
    public Command getNextAction(World world) {
        Command nextAction = ai.getNextAction(world, this);
        return nextAction;
    }
    
    //DO I NEED THESE

    @Override
    public int getCoolDownPeriod() {
        return COOLDOWN;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public ImageIcon getImage() {
        return jediImage;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getMaxEnergy() {
        return MAX_ENERGY;
    }

    @Override
    public int getMeatCalories() {
        return energy;
    }

    @Override
    public int getMinimumBreedingEnergy() {
        return MIN_BREEDING_ENERGY;
    }

    @Override
    public int getMovingRange() {
        return 1; // Can only move to adjacent locations.
    }

    @Override
    public String getName(){
        return "Jedi";
    }

    @Override
    public int getPlantCalories() { // arena animals dont eat plants
        return 0;
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public int getViewRange() {
        return VIEW_RANGE;
    }

    @Override
    public boolean isDead() {
        return energy <= 0;
    }

    @Override
    public void loseEnergy(int energyLoss) {
        energy = this.energy - energyLoss;
    }

    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
    }

}
