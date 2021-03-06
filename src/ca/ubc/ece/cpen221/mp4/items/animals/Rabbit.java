package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.ai.RabbitAI;
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
	private static final int MEAT_CALORIES = 60;
	private static final int MIN_BREEDING_ENERGY = 10;
	private static final int VIEW_RANGE = 3;
	private static final int COOLDOWN = 2;
	private static final ImageIcon RABBITIMAGE = Util.loadImage("rabbit.gif");
	private static final String NAME = "Rabbit";

	private final RabbitAI rabbitAI;

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
	public Rabbit(RabbitAI rabbitAI, Location initialLocation) {
        this.rabbitAI = rabbitAI;
        this.setInitialEnergy(INITIAL_ENERGY);
        this.setMaxEnergy(MAX_ENERGY);
        this.setCoolDown(COOLDOWN);
        this.setViewRange(VIEW_RANGE);
        this.setIsDead(false);
        this.setStrength(STRENGTH);
        this.setEnergy(INITIAL_ENERGY);
        this.setMinBreedingEnergy(MIN_BREEDING_ENERGY);
        this.setLocation(initialLocation);  
        this.setImage(RABBITIMAGE);
        this.setName(Rabbit.NAME);
        this.setMeatCalories(MEAT_CALORIES);
	}
	
    /**
     * Methods below all have specs already.
     */

	@Override
	public LivingItem breed() {
		Rabbit child = new Rabbit(rabbitAI, this.getLocation());
		child.setEnergy(this.getEnergy()/ 2);
		this.setEnergy(this.getEnergy()/ 2);
		return child;
	}

    @Override
    public Command getNextAction(World world) {
        Command nextAction = rabbitAI.getNextAction(world, this);
        this.setEnergy(this.getEnergy() - 1); // Loses 1 energy regardless of action.
        return nextAction;
    }

}
