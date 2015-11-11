package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.Item;

public abstract class AbstractArenaVehicle implements Vehicle {
    
    protected int MAX_FUEL;
    protected int STRENGTH;
    protected int MIN_SPEED;
    protected int TURNING_SPEED;
    protected int VIEW_RANGE;
    protected final int MOVING_RANGE = 1; // all vehicles can only move one space at a time
    protected Location location;
    protected ImageIcon image;
    
    protected Direction direction;
    protected int fuel;
    protected String name;
    protected int speed; // the lower the number for speed, the faster object moves
    
    @Override
    public abstract Command getNextAction(World world);

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
        this.fuel-= energy; // fuel is energy for vehicles
        
    }

    @Override
    public boolean isDead() {
        return this.fuel <= 0;
    }

    @Override
    public int getFuelLevel() {
        return this.fuel;
    }

    @Override
    public int getMaxFuel() {
        return this.MAX_FUEL;
    }


    @Override
    public void speedUp() {
       if (this.speed > TURNING_SPEED){
           this.speed--;
       } 
    }

    @Override
    public void slowDown() {
        if (this.speed < MIN_SPEED){
            this.speed++;
        } 
    }
    
    @Override
    public int getPlantCalories() {
        // Vehicles are not plants
        return 0;
    }

    @Override
    public int getMeatCalories() {
     // Vehicles are not meat
        return 0;
    }

    @Override
    public int getCoolDownPeriod() {
        return this.speed;
    }
    
    @Override
    public int getTurningSpeed(){
        return this.TURNING_SPEED;
    }
    
    @Override
    public int getMINSpeed(){
        return this.MIN_SPEED;
    }
    
    @Override
    public Direction getDirection() {
        return this.direction;
    }
    
    @Override
    public void collision(Item collided){
        // dies if hits stronger object
        if (this.STRENGTH < collided.getStrength()){
            this.fuel = 0;
        }
        else {
            // kills weaker animals
            if(collided.getMeatCalories() > 0){
                this.fuel += collided.getMeatCalories();
                collided.loseEnergy(collided.getMeatCalories());
            }
            // kills weaker plants
            else if(collided.getPlantCalories() > 0){
                this.fuel += collided.getPlantCalories();
                collided.loseEnergy(collided.getPlantCalories());
            }
            // kills other weaker vehicles
            // TODO: tweak this to avoid casting
            else if(((AbstractArenaVehicle) collided).getFuelLevel() > 0){
                this.fuel += ((AbstractArenaVehicle) collided).getFuelLevel();
                collided.loseEnergy( ((AbstractArenaVehicle) collided).getFuelLevel());
            }
        }       
    }
    
}
