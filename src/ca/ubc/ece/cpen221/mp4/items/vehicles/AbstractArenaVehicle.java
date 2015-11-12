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
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.animals.AbstractArenaAnimal;

public abstract class AbstractArenaVehicle implements Vehicle{
    
    protected static final int MOVINGRANGE = 1; // all vehicles can only move one space at a time
    private int strength;
    private int currentCoolDown;
    private int minCoolDown; // corresponds with max speed
    private int changeDirectionCoolDown;
    private int maxFuel;
    private int fuel;
    private boolean isDead;
    
    private String name;
    private Location location;
    private ImageIcon image;
    private Direction direction;
    
    /**
     * Gets next action for vehicle. If at the world's end,  
     */
    @Override
    public Command getNextAction(World world){
        this.fuel--;
        Direction previousDirection = this.direction;
        Direction newDirection = Util.getRandomDirection();
        
        if (atWorldsEdge(world) == true) {    
            if (this.currentCoolDown >= this.changeDirectionCoolDown){ // if slow enough
                while (previousDirection.equals(newDirection)){
                    newDirection = Util.getRandomDirection(); // gets new direction if at world's edge
                }
                this.direction = newDirection;
                if (Util.isLocationEmpty(world, new Location(location, newDirection))) {
                    return new MoveCommand(this, new Location(location, this.direction)); // if nothing is in the way, move
                }
                else{
                    collide(world); // else, collide into object
                }
            } else {
                 currentCoolDown++; // if not slow enough to turn, slow down
                 return new WaitCommand();
            }
        }
        else {
            if(this.currentCoolDown > this.minCoolDown){
                this.currentCoolDown--; // speed up if not near world's edge and can travel faster
            } 
            collide(world); 
            Location nextStep = new Location(this.location, this.direction); 
            return new MoveCommand(this, nextStep);
        }
        return new WaitCommand();
    }
    
    
    /**
     * Test to see if at the world's edge.
     * @param current world.
     * @return true if at world's edge, false otherwise.
     */
    private boolean atWorldsEdge(World world){
        if (location.getX() == 0 || location.getY() == 0){
            return true;
        } else if (location.getX() == world.getWidth() - 1 || location.getY() == world.getHeight() - 1){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * If there is an item in front of vehicle with weaker strength, removes item. Takes energy from item.
     * If item in front of vehicle is stronger than vehicle, removes vehicle.
     * @param world
     * @param collided
     */
    @Override
    public Command collide(World world){
        Set<Item> neighbors = world.searchSurroundings(this.location, 1);
        
        for (Item neighbor : neighbors) {
            if (neighbor.getLocation().equals(new Location(location, this.direction))) {

                if (this.getStrength() > neighbor.getStrength()) {
                    this.fuel += neighbor.getMeatCalories() + neighbor.getPlantCalories();
                    if (this.fuel > maxFuel) {
                        this.fuel = maxFuel;
                    }
                    neighbor.loseEnergy(neighbor.getMeatCalories());
                    neighbor.loseEnergy(neighbor.getPlantCalories());
                    return new WaitCommand();
                } else if (this.getStrength() < neighbor.getStrength()) {
                    this.loseEnergy(this.fuel);
                    this.isDead = true;
                    return new WaitCommand();
                }
            }
        }
        return new WaitCommand();     
    }
    
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
        return this.isDead;
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
    
    //
    // Methods to set initial values
    //
    protected void setInitialCoolDown (int vehicleCoolDown){
        this.currentCoolDown = vehicleCoolDown;
    }
    
    protected void setLocation (Location vehicleLocation){
        this.location = vehicleLocation;
    }
    
    protected void setStrength (int vehicleStrength){
        this.strength = vehicleStrength;
    }
    
    protected void setChangeDirectionCoolDown (int vehicleChangeDirectionCoolDown){
        this.changeDirectionCoolDown = vehicleChangeDirectionCoolDown;
    }
    
    protected void setFuel(int vehicleFuel){
        this.fuel = vehicleFuel;
    }
    
    protected void setMaxFuel (int vehicleMaxFuel){
        this.maxFuel = vehicleMaxFuel;
    }
    
    protected void setImage (ImageIcon vehicleImage){
        this.image = vehicleImage;
    }
    
    protected void setName (String vehicleName){
        this.name = vehicleName; 
    }
    
    protected void setMinCoolDown (int vehicleMinCoolDown){
        this.minCoolDown = vehicleMinCoolDown;
    }
    
    protected void setDirection(Direction vehicleDirection){
        this.direction = vehicleDirection;
    }
    
}
