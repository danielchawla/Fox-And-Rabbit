package ca.ubc.ece.cpen221.mp4.items.vehicles;

import java.util.*;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;

public class ATAT implements MoveableItem, Actor {
    private static final int MAX_ENERGY = 100;
    private static final int STRENGTH = 1000;    
    private static final int COOLDOWN = 20;
    private static final int INITIAL_ENERGY = 40;
    private static final int STARTING_FUEL = 500;
    private static final int VIEW_RANGE = 40;
    private static final int MAX_MOMENTUM = 3;
    private static final ImageIcon ATAT_IMAGE = Util.loadImage("motorcycles.gif");
    
    private Location location;
    private int momentum;
    private int fuel;
    
    public ATAT(Location startPoint){
        this.location = startPoint;
        this.fuel = STARTING_FUEL;
        this.momentum = MAX_MOMENTUM;
    }

    @Override
    public ImageIcon getImage() {
        return ATAT_IMAGE;
    }

    @Override
    public String getName() {
        return "ATAT";
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public void loseEnergy(int energy) {
        this.fuel -= energy;
    }

    @Override
    public boolean isDead() {
        return (fuel <= 0);
    }

    @Override
    public int getPlantCalories() {
        // none 
        return 0;
    }

    @Override
    public int getMeatCalories() {
        // none
        return 0;
    }

    @Override
    public int getCoolDownPeriod() {
        return COOLDOWN;
    }

    @Override
    public Command getNextAction(World world) {
        
        // TODO: Add momentum
        Location nextPoint;
        Direction currentDirection;
        
        Set<Item> surroundings = new HashSet<Item>();
        surroundings = world.searchSurroundings(this.location, VIEW_RANGE);
        
        for(Item item : surroundings){
            
            // Kills things of less strength if collide. Dies if collides with thing of more strength
            if(location.getDistance(item.getLocation())==1){
                
                if(item.getStrength() < this.getStrength() && item.getMeatCalories() > 0){
                    item.loseEnergy(item.getMeatCalories()); 
                } else if(item.getStrength() < this.getStrength() && item.getPlantCalories() > 0){
                    item.loseEnergy(item.getPlantCalories());
                } else if(item.getStrength() > this.getStrength()){
                    this.loseEnergy(this.fuel); 
                }
            }
            
            if(item.getName().equals("Jedi")){
                currentDirection = Util.getDirectionTowards(this.location, item.getLocation());
                nextPoint = new Location(this.getLocation(), currentDirection);
                if (Util.isValidLocation(world, nextPoint) && Util.isLocationEmpty(world, nextPoint)){
                    return new MoveCommand(this, nextPoint);
                }
            }
        }
        
      
        return new WaitCommand();
    }

    @Override
    public void moveTo(Location targetLocation) {
        this.location = targetLocation;
    }

    @Override
    public int getMovingRange() {
        // Can only move one space at a time
        return 1;
    }
    
 
}
