package ca.ubc.ece.cpen221.mp4.items.vehicles;

import java.util.Set;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;

public abstract class AbstractArenaVehicle implements Vehicle {
    
    protected int MAX_FUEL;
    protected int STRENGTH;
    protected int MAX_COOLDOWN;
    protected int MIN_COOLDOWN;
    protected int VIEW_RANGE;
    protected final int MOVING_RANGE = 1; // all vehicles can only move one space at a time
    protected Location location;
    protected ImageIcon image;
    
    protected Direction direction;
    protected int fuel_level;
    protected String name;
    protected int speed;
    
    
    @Override
    public ImageIcon getImage(){
        return this.image;
    }

    @Override
    public String getName(){
        return this.name;
    }
    
    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
    }
    
    @Override
    public int getViewRange() {
        return this.getViewRange();
    }

    @Override
    public int getMovingRange() {
        return this.VIEW_RANGE;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public int getStrength() {
        return this.STRENGTH;
    }

    @Override
    public void loseEnergy(int energy) {
        this.fuel_level-= energy; // fuel is energy for vehicles
        
    }

    @Override
    public boolean isDead() {
        return this.fuel_level <= 0;
    }

    @Override
    public int getFuelLevel() {
        return this.fuel_level;
    }

    @Override
    public int getMaxFuel() {
        return this.MAX_FUEL;
    }
    
    @Override
    public int getPlantCalories() {
        // assume vehicles run on biodiesel and thus plant calories = fuel level
        return this.fuel_level; 
    }

    @Override
    public int getMeatCalories() {
     // Vehicles are not meat
        return 0;
    }

    @Override
    public int getCoolDownPeriod() {
        return 10 / this.speed;
    }
    
    @Override
    public int getMaxCoolDown(){
        return this.MAX_COOLDOWN;
    }
    
    @Override
    public int getMinCoolDown(){
        return this.MIN_COOLDOWN;
    }
    
    @Override
    public Direction getDirection() {
        return this.direction;
    }
    
    /**
     * 
     * @param world
     */
    protected void setDirection(World world) {
        Direction previousDirection = this.direction;
        while (!Util.isValidLocation(world, new Location(this.location, this.direction)) || this.direction == previousDirection)
            this.direction = Util.getRandomDirection();
    }
    
    
    /**
     * 
     */
    @Override
    public Command getNextAction(World world){
        if (distanceToEdge(world) <= (this.MAX_COOLDOWN - this.speed)){
            //this.slowDown();
            if (this.speed == MAX_COOLDOWN){
                this.setDirection(world);
            }
        }
        else if (speed > MIN_COOLDOWN){
            //this.speedUp();
        }


        collide(world);
        return new MoveCommand(this, new Location(location, this.direction));
    }
    
    /**
     * 
     * @param world
     * @return
     */
    public int distanceToEdge(World world){
        
      Location location = this.getLocation();
      
      if(this.direction == Direction.WEST){
          return location.getY();
      } else if (this.direction == Direction.NORTH){
          return location.getY();
      } else if (this.direction == Direction.EAST) {
          return (-1)*(location.getX()-(world.getWidth()-1));
      } else if (this.direction == Direction.SOUTH){
          return (-1)*(location.getY()-(world.getHeight()-1));
      } else
        return -1;
    }
    
    /**
     * 
     * @param world
     * @param collided
     */
    @Override
    public void collide(World world){
        Set<Item> neighbors = world.searchSurroundings(this.location, 1);
        
        for (Item neighbor : neighbors) {
            if (neighbor.getLocation().equals(new Location(location, this.direction)))

                if (this.getStrength() > neighbor.getStrength()){
                    this.fuel_level += neighbor.getMeatCalories() + neighbor.getPlantCalories();
                    if (this.fuel_level > MAX_FUEL){
                        this.fuel_level = MAX_FUEL;
                    }
                    neighbor.loseEnergy(neighbor.getMeatCalories());
                    neighbor.loseEnergy(neighbor.getPlantCalories());
                }
                else if (this.getStrength() < neighbor.getStrength()){
                    this.loseEnergy(this.fuel_level);
                }
        }       
    }    
    
}
