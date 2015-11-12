package ca.ubc.ece.cpen221.mp4.items.vehicles;

import java.util.Set;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
/**
 * Abstract Vehicle superclass that most land vehicles can extend from.
 * 
 * @author Annabelle Harvey and Daniel Chawla
 *
 */
public abstract class AbstractArenaVehicle implements Vehicle{
    
    private static final int MOVINGRANGE = 1; // all vehicles can only move one space at a time
    private int strength;
    private int currentCoolDown; // corresponds with current speed, faster when lower num
    private int minCoolDown; // corresponds with max speed
    private int changeDirectionCoolDown; // speed to slow down to in order to turn
    private int maxFuel; 
    private int fuel;
    private boolean isDead;
    
    private String name;
    private Location location;
    private ImageIcon image;
    private Direction direction;
    
    
    /**
     * Sets vehicle initial cool down level. The lower the number, the faster object travels.
     * @param vehicleCoolDown rate
     */
    protected void setInitialCoolDown (int vehicleCoolDown){
        this.currentCoolDown = vehicleCoolDown;
    }
    
    /**
     * Sets vehicle initial cool down level. The lower the number, the faster object travels.
     * @param vehicleCoolDown rate
     */
    protected void setIsDead (boolean isDead){
        this.isDead = isDead;
    }
    
    /**
     * Sets vehicles initial location.
     * @param vehicle's location
     */
    protected void setLocation (Location vehicleLocation){
        this.location = vehicleLocation;
    }
    
    /**
     * Sets the strength of the vehicle.
     * @param vehicleStrength
     */
    protected void setStrength (int vehicleStrength){
        this.strength = vehicleStrength;
    }
    
    /**
     * Sets the cool down rate at which vehicles needs to be at in
     * order to turn.
     * @param ChangeDirectionCoolDown rate
     */
    protected void setChangeDirectionCoolDown (int vehicleChangeDirectionCoolDown){
        this.changeDirectionCoolDown = vehicleChangeDirectionCoolDown;
    }
    
    /**
     * Sets the fuel level, or energy, of a vehicle.
     * @param vehicle's fuel level
     */
    protected void setFuel(int vehicleFuel){
        this.fuel = vehicleFuel;
    }
    
    /**
     * Set's max fuel level a vehicle can contain.
     * @param vehicle's max fuel level
     */
    protected void setMaxFuel (int vehicleMaxFuel){
        this.maxFuel = vehicleMaxFuel;
    }
    
    /**
     * Sets vehicle's image.
     * @param image of vehicle.
     */
    protected void setImage (ImageIcon vehicleImage){
        this.image = vehicleImage;
    }
    
    /**
     * Sets the name of the vehicle.
     * @param vehicle name
     */
    protected void setName (String vehicleName){
        this.name = vehicleName; 
    }
    
    /**
     * Sets the minimum cool down rate of a vehicle. This is practically
     * the same as the vehicle's max speed.
     * @param vehicle MinCoolDown rate
     */
    protected void setMinCoolDown (int vehicleMinCoolDown){
        this.minCoolDown = vehicleMinCoolDown;
    }
    
    /**
     * Sets the direction vehicle faces.
     * @param vehicle direction
     */
    protected void setDirection(Direction vehicleDirection){
        this.direction = vehicleDirection;
    }
    
    
    /**
     * Test to see if at the world's edge.
     * @param current world.
     * @return true if at world's edge, false otherwise.
     */
    public boolean atWorldsEdge(World world){
        if (location.getX() == 0 || location.getY() == 0){
            return true;
        } else if (location.getX() == world.getWidth() - 1 || location.getY() == world.getHeight() - 1){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * If there is an item in front of vehicle with weaker strength, removes item. Takes all energy from item.
     * If item in front of vehicle is stronger than vehicle, removes all energy from vehicle.
     * @param world object is in
     */
    @Override
    public Command collide(World world){
        Set<Item> neighbors = world.searchSurroundings(this.location, 1); 
        
        for (Item neighbor : neighbors) {
            // if there is an item in front of vehicle
            if (neighbor.getLocation().equals(new Location(location, this.direction))) { 
                

                if (this.getStrength() > neighbor.getStrength()) {
                    // vehicle gets fueled when running over items
                    this.fuel += neighbor.getMeatCalories() + neighbor.getPlantCalories(); 
                    neighbor.loseEnergy(Integer.MAX_VALUE);
                    
                    if (this.fuel > maxFuel) {
                        this.fuel = maxFuel; 
                    }
                    currentCoolDown++; // slows down when hits an object
                    return new WaitCommand();
                    
                } else if (this.getStrength() < neighbor.getStrength()) {
                    // dies when hitting something strong than it
                    this.loseEnergy(this.fuel);
                    this.isDead = true;
                    return new WaitCommand();
                }
            }
        }
        return new WaitCommand();     
    }
    
    /**
     * Gets next action for vehicle. If at the world's edge, slows down and switches direction.
     * Collides with anything that comes in its path. Accelerates if nothing is in it's path. 
     */
    @Override
    public Command getNextAction(World world){
        
        this.fuel--; // fuel is used up each time this is called
        
        Direction previousDirection = this.direction; 
        Direction newDirection = Util.getRandomDirection();
        
        if (atWorldsEdge(world) == true) {    
            if (this.currentCoolDown >= this.changeDirectionCoolDown){ // if slow enough to change direction
                while (previousDirection.equals(newDirection)){
                    newDirection = Util.getRandomDirection(); 
                }
                this.direction = newDirection;
                
                // if nothing in the way move
                if (Util.isLocationEmpty(world, new Location(location, newDirection))) {
                    return new MoveCommand(this, new Location(location, this.direction)); 
                }
                else{
                    // if something is in the way, collide into it
                    collide(world); 
                }
            } else {
                 currentCoolDown++; // if not slow enough to turn, slow down
                 return new WaitCommand();
            }
        }
        
        // when not at the world's edge
        else {
            if (this.currentCoolDown > this.minCoolDown) { 
                this.currentCoolDown--; // speed up if not at max speed
            }
            collide(world); 
            
            Location nextStep = new Location(this.location, this.direction);
            if (Util.isLocationEmpty(world, new Location(location, this.direction))) {
                return new MoveCommand(this, nextStep);
            } else {
                collide(world); // if object is in way, collide with it
            }
        }
        return new WaitCommand();
    }
    
    
    
    
    /**
     *  NOTE: All the following methods already have specs from classes they implement.
     */
    
    @Override
    public int getCoolDownPeriod() {
        return currentCoolDown;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public ImageIcon getImage() {
        return this.image;
    }
    
    @Override
    public void moveTo(Location targetLocation) {
        this.location = targetLocation;
    }

    @Override
    public int getMovingRange() {
        return MOVINGRANGE;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public void loseEnergy(int energy) {
       this.fuel -= energy;
    }

    @Override
    public boolean isDead() {
        return this.fuel <= 0;
    }

    @Override
    public int getPlantCalories() {
        // Vehicles run off biodiesel and thus have plant calories
        return this.fuel;
    }

    @Override
    public int getMeatCalories() {
        // Vehicles have no meat
        return 0;
    }
    
}
