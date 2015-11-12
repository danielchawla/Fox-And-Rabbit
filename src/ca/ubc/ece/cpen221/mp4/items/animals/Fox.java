package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

/**
 * The {@link Fox} is an {@link ArenaAnimal} that tries to eat {@link Rabbit}s.
 */
public class Fox extends AbstractArenaAnimal {

	private static final int INITIAL_ENERGY = 100;
	private static final int MAX_ENERGY = 120;
	private static final int STRENGTH = 100;
	private static final int VIEW_RANGE = 5;
	private static final int MIN_BREEDING_ENERGY = 20;
	private static final int COOLDOWN = 3;
	private static final ImageIcon foxImage = Util.loadImage("fox.gif");

	private Location location;
	private int energy;
	private AI ai;

	/**
	 * Create a new {@link Fox} with an {@link AI} at
	 * <code>initialLocation</code>. The <code> initialLocation </code> must be
	 * valid and empty
	 *
	 * @param foxAI
	 *            the AI designed for foxes
	 * @param initialLocation
	 *            the location where this Fox will be created
	 */
	public Fox(AI foxAI, Location initialLocation) {
        this.ai = foxAI;
        this.location = initialLocation;
        this.energy = INITIAL_ENERGY;
        this.setINITIAL_ENERGY(INITIAL_ENERGY);
        this.setMAX_ENERGY(MAX_ENERGY);
        this.setCOOLDOWN(COOLDOWN);
        this.setVIEW_RANGE(VIEW_RANGE);
        this.setSTRENGTH(STRENGTH);
        this.setEnergy(INITIAL_ENERGY);
        this.setMIN_BREEDING_ENERGY(MIN_BREEDING_ENERGY);
        this.setLocation(initialLocation);  
        this.setImage(foxImage);
        this.setName("Fox");
	}

	@Override
	public LivingItem breed() {
		Fox child = new Fox(ai, location);
		child.energy = energy / 2;
		this.energy = energy / 2;
		return child;
	}

    @Override
    public Command getNextAction(World world) {
        Command nextAction = ai.getNextAction(world, this);
        this.energy--; // Loses 1 energy regardless of action.
        return nextAction;
    }

	
}
