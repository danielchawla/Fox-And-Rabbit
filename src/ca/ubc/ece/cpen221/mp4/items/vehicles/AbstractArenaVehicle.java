package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.VehicleAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.Item;

public abstract class AbstractArenaVehicle implements Vehicle {
    
    protected int MAX_FUEL;
    protected int STRENGTH;
    protected int MIN_SPEED;
    protected int TURNING_SPEED;
    protected static int VIEW_RANGE;
    protected final static int MOVING_RANGE = 1; // all vehicles can only move one space at a time
    protected Location location;
    protected static ImageIcon image;
    
    protected VehicleAI ai;
    protected Direction direction;
    protected int fuel;
    protected int speed; // the lower the number for speed, the faster object moves

    public abstract void collisionRemoval(Item collided);

    @Override
    public abstract ImageIcon getImage();

    @Override
    public abstract String getName();
    
    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
    }

    @Override
    public int getMovingRange() {
        return MOVING_RANGE;
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
    public Command getNextAction(World world) {
        this.fuel--; // vehicles use fuel for every action
        return ai.getNextAction(world, this);
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
    
    public int getTurningSpeed(){
        return this.TURNING_SPEED;
    }
    
    public int getMINSpeed(){
        return this.MIN_SPEED;
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public void setDirection(Direction travelDirection) {
        this.direction = travelDirection;
    }
    
}
