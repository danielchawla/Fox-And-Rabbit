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
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.animals.AbstractArenaAnimal;

public abstract class AbstractArenaVehicle implements Vehicle{
    
    protected static final int MOVINGRANGE = 1; // all vehicles can only move one space at a time
    
    private int viewRange;
    private int strength;
    private int initialCoolDown;
    private int changeDirectionCoolDown;
    private int maxFuel;
    private int fuel;
    
    private boolean isDead;
    private String name;
    private Location location;
    private ImageIcon image;
    private Direction direction;

        
    protected void setInitialCoolDown (int vehicleCoolDown){
        this.initialCoolDown = vehicleCoolDown;
    }
    
    protected void setLocation (Location vehicleLocation){
        this.location = vehicleLocation;
    }
    
    protected void setViewRange (int vehicleViewRange){
        this.viewRange = vehicleViewRange;
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
        return 1; // vehicles can only move one space at a time
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
        // Vehicles are not food
        return 0;
    }

    @Override
    public int getMeatCalories() {
        // Vehicles have no meat
        return 0;
    }


    @Override
    public int getCoolDownPeriod() {
        return 10;
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
                    this.fuel += neighbor.getMeatCalories() + neighbor.getPlantCalories();
                    if (this.fuel > maxFuel){
                        this.fuel = maxFuel;
                    }
                    neighbor.loseEnergy(neighbor.getMeatCalories());
                    neighbor.loseEnergy(neighbor.getPlantCalories());
                }
                else if (this.getStrength() < neighbor.getStrength()){
                    this.loseEnergy(this.fuel);
                }
        }       
    }    
    
}
