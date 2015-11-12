package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;

/**
 * A speeder bike based off the Star Wars series. Fastest moving vehicle
 * in this world. 
 * @author Annabelle Harvey and Daniel Chawla
 */
public class SpeederBike extends AbstractArenaVehicle {
    private static final ImageIcon SPEEDERBIKEIMAGE = Util.loadImage("speederbike.gif");
    private static final int STRENGTH = 50;
    private static final int CHANGE_DIRECTION_COOLDOWN = 4; // speed vehicle needs to slow down to in order to turn
    private static final int INITIAL_FUEL = 10;
    private static final int MAX_FUEL = 10;
    private static final int MINCOOLDOWN = 1;
    private static final String NAME = "SpeederBike";
    private final boolean isDead;
    
    /**
     * Creates a new SpeederBike with an initial start location
     * @param startPoint - the initial location SpeederBike will be placed in the world
     */
    public SpeederBike(Location startPoint) {
        this.isDead = false;
        this.setLocation(startPoint);  
        this.setInitialCoolDown(CHANGE_DIRECTION_COOLDOWN); 
        this.setChangeDirectionCoolDown(CHANGE_DIRECTION_COOLDOWN); 
        this.setStrength(STRENGTH);
        this.setFuel(INITIAL_FUEL);
        this.setMaxFuel(MAX_FUEL);
        this.setMinCoolDown(MINCOOLDOWN);
        this.setImage(SPEEDERBIKEIMAGE);
        this.setName(SpeederBike.NAME);
        this.setDirection(Util.getRandomDirection());
    }
    
    @Override
    public boolean isDead() {
        return this.isDead;
    }

}
