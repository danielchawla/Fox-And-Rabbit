package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;

/**
 * An ATAT based off the Star Wars series. Slow speed vehicle with great strength. 
 *
 * @author Annabelle Harvey and Daniel Chawla
 */
public class ATAT extends AbstractArenaVehicle {
    private static final ImageIcon ATATIMAGE = Util.loadImage("atat.gif");
    private static final int STRENGTH = 1000;
    private static final int MINCOOLDOWN = 7;
    private static final int CHANGE_DIRECTION_COOLDOWN = 10; // speed vehicle needs to slow down to in order to turn
    private static final int INITIAL_FUEL = 1000;
    private static final int MAX_FUEL = 1000;
    private static final String NAME = "ATAT";

    /**
     * Creates a new ATAT with an initial start location
     * @param startPoint - the initial location ATAT will be placed in the world
     */
    public ATAT(Location startPoint) {
        this.setLocation(startPoint);  
        this.setInitialCoolDown(CHANGE_DIRECTION_COOLDOWN); 
        this.setChangeDirectionCoolDown(CHANGE_DIRECTION_COOLDOWN); 
        this.setMinCoolDown(MINCOOLDOWN);
        this.setStrength(STRENGTH);
        this.setFuel(INITIAL_FUEL);
        this.setMaxFuel(MAX_FUEL);
        this.setImage(ATATIMAGE);
        this.setName(ATAT.NAME);
        this.setDirection(Util.getRandomDirection());
    }

}
