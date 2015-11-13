package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public abstract class AbstractArenaAnimal implements ArenaAnimal { 
	private int initialEnergy;
	private int meatCalories;
	private int maxEnergy;
	private int strength;
	private int viewRange;
	private int minBreedingEnergy;
	private int coolDown;
	private int energy;
	private ImageIcon image;
	private String name;

	private AI ai;
    private boolean isDead;
	private Location location;

    /**
    * Sets the initial energy of the animal.
    * @param initial energy of animal
    */
	protected void setInitialEnergy(int i) {
		this.initialEnergy = i;
	}

    /**
     * Sets energy of the animal at any point in time.
     * @param energy of animal
     */
	protected void setEnergy(int i) {
		this.energy = i;
	}
	
    /**
     * Sets whether animal is dead or not.
     * @param isDead - true if animal is dead, false otherwise 
     */
    protected void setIsDead (boolean isDead){
        this.isDead = isDead;
    }
	
    /**
     * Sets max energy an animal can have.
     * @param max energy of animal.
     */
	protected void setMaxEnergy(int i) {
		this.maxEnergy = i;
	}

	 /**
     * Sets the strength of the animal.
     * @param strength of animal
     */
	protected void setStrength(int i) {
		this.strength = i;
	}
	
	/**
	 * Sets view range of animal.
	 * @param view range of animal.
	 */
	protected void setViewRange(int i) {
		this.viewRange = i;
	}
	
    /**
     * Sets the min breeding energy of the animal.
     * 
     * @param min breeding energy of animal.
     */
	protected void setMinBreedingEnergy(int i) {
		this.minBreedingEnergy = i;
	}
	
	/**
	 * Sets the meat calories of the animal.
	 * @param meat calories of animal.
	 */
    protected void setMeatCalories(int i) {
        this.meatCalories = i;
    }

    /**
     * Sets animal's cool down rate.
     * @param cooldown rate
     */
	protected void setCoolDown(int i) {
		this.coolDown = i;
	}

    /**
     * Sets animal's initial location.
     * @param location
     */
	protected void setLocation(Location l) {
		this.location = l;
	}
	
    /**
     * Sets animal's image.
     * @param image of animal.
     */
	protected void setImage(ImageIcon i){
	    this.image = i;
	}
	
    /**
     * Sets the name of the animal.
     * @param animal's name
     */
	protected void setName(String n){
	    this.name = n;
	}

	
	/**
	 * 
	 * NOTE: Specifications have already been written for all the methods below in superclass or implented class.
	 * 
	 */
	
	
	@Override
	public int getCoolDownPeriod() {
		return coolDown;
	}

	@Override
	public int getEnergy() {
		return energy;
	}

	@Override
	public ImageIcon getImage() {
		return image;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public int getMaxEnergy() {
		return maxEnergy;
	}

	@Override
	public int getMeatCalories() {
		return energy;
	}

	@Override
	public int getMinimumBreedingEnergy() {
		return minBreedingEnergy;
	}

	@Override
	public int getMovingRange() {
		return 1; // Can only move to adjacent locations.
	}

	@Override
	public String getName(){
	    return name;
	}

	@Override
	public Command getNextAction(World world) {
		Command nextAction = ai.getNextAction(world, this);
		energy--; // Loses 1 energy regardless of action.
		return nextAction;
	}

	@Override
	public int getPlantCalories() { // arena animals don't eat plants
		return 0;
	}

	@Override
	public int getStrength() {
		return strength;
	}

	@Override
	public int getViewRange() {
		return viewRange;
	}

	@Override
	public boolean isDead() {
		return energy <= 0;
	}

	@Override
    public void loseEnergy(int energyLoss) {
        energy -= energyLoss;
    }

	@Override
	public void moveTo(Location targetLocation) {
		location = targetLocation;
	}
	
    @Override
    public abstract LivingItem breed();

    @Override
    public void eat(Food food) {
        energy = Math.min(maxEnergy, energy + food.getMeatCalories());
    }
}
