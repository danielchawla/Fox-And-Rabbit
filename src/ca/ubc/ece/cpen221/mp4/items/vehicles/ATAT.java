package ca.ubc.ece.cpen221.mp4.items.vehicles;

import java.util.*;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;

public class ATAT extends AbstractArenaVehicle {
    
    public ATAT (Location startPoint) {
        this.MAX_FUEL = 1000;
        this.STRENGTH = 1000;
        this.MIN_SPEED = 7;
        this.TURNING_SPEED = 10;
        this.VIEW_RANGE = 100;

        this.speed = 7;
        this.location = startPoint;
        this.direction = Util.getRandomDirection();
        this.fuel = 1000;  
        this.image = Util.loadImage("ATAT.gif");
        this.name = "ATAT";
    }
    
    @Override
    public Command getNextAction(World world) {
        
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
}
