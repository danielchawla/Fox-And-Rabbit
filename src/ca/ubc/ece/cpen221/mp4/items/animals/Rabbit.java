package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

/**
 * The {@link Rabbit} is an {@link ArenaAnimal} that eats {@link Grass} and can
 * be eaten by {@link Fox}.
 */
public class Rabbit extends AbstractArenaAnimal {

	private static final int INITIAL_ENERGY = 40;
	private static final int MAX_ENERGY = 60;
	private static final int STRENGTH = 60;
	private static final int MIN_BREEDING_ENERGY = 10;
	private static final int VIEW_RANGE = 3;
	private static final int COOLDOWN = 2;
	private static final ImageIcon rabbitImage = Util.loadImage("rabbit.gif");

	private Location location;
	private int energy;
	
	private AI ai;

	/**
	 * Create a new {@link Rabbit} with an {@link AI} at
	 * <code> initialLocation </code>. The <code> initialLoation
	 * </code> must be valid and empty.
	 *
	 * @param rabbitAI
	 *            : The AI designed for rabbits
	 * @param initialLocation
	 *            : the location where this rabbit will be created
	 */
	public Rabbit(AI rabbitAI, Location initialLocation) {
        this.ai = rabbitAI;
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
//        this.setImage(rabbitImage);
//        this.setName("Rabbit");
	}

	@Override
	public LivingItem breed() {
		Rabbit child = new Rabbit(ai, location);
		child.energy = energy / 2;
		this.energy = energy / 2;
		return child;
	}

	@Override
	public void eat(Food food) {
		// Note that energy does not exceed energy limit.
		this.energy = Math.min(MAX_ENERGY, energy + food.getPlantCalories());
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
        return rabbitImage;
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
        return "Rabbit";
    }

    @Override
    public Command getNextAction(World world) {
        Command nextAction = ai.getNextAction(world, this);
        this.energy--; // Loses 1 energy regardless of action.
        return nextAction;
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
