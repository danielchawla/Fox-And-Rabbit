package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;

/**
 * A landspeeder based off the Star Wars series. Medium speed vehicle. 
 *  
 * @author Annabelle Harvey and Daniel Chawla
 */
public class Landspeeder extends AbstractArenaVehicle {
    private static final ImageIcon LANDSPEEDERIMAGE = Util.loadImage("landspeeder.gif");
    private static final int STRENGTH = 100;
    private static final int CHANGE_DIRECTION_COOLDOWN = 6; // speed vehicle needs to slow down to in order to turn
    private static final int MINCOOLDOWN = 3;
    private static final int INITIAL_FUEL = 300;
    private static final int MAX_FUEL = 300;
    private static final String NAME = "Landspeeder";

    /**
     * Creates a new Landspeeder with an initial start location
     * @param startPoint - the initial location Landspeeder will be placed in the world
     */
    public Landspeeder(Location startPoint) {
        this.setLocation(startPoint);  
        this.setInitialCoolDown(CHANGE_DIRECTION_COOLDOWN); 
        this.setChangeDirectionCoolDown(CHANGE_DIRECTION_COOLDOWN); 
        this.setStrength(STRENGTH);
        this.setFuel(INITIAL_FUEL);
        this.setMaxFuel(MAX_FUEL);
        this.setMinCoolDown(MINCOOLDOWN);
        this.setImage(LANDSPEEDERIMAGE);
        this.setName(Landspeeder.NAME);
        this.setDirection(Util.getRandomDirection());
    }

}
