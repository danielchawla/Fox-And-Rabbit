package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;

public class ATAT extends AbstractArenaVehicle {
    private static final ImageIcon ATATIMAGE = Util.loadImage("trucks.gif");
    private static final int STRENGTH = 1000;
    private static final int MINCOOLDOWN = 10;
    private static final int CHANGE_DIRECTION_COOLDOWN = 15; // speed vehicle needs to slow down to in order to turn
    private static final int INITIAL_FUEL = 1000;
    private static final int MAX_FUEL = 1000;
    private static final String NAME = "ATAT";

    public ATAT(Location startPoint) {
        this.setLocation(startPoint);  
        this.setInitialCoolDown(CHANGE_DIRECTION_COOLDOWN); // initial speed
        this.setChangeDirectionCoolDown(CHANGE_DIRECTION_COOLDOWN); // speed at which it can turn
        this.setMinCoolDown(MINCOOLDOWN);
        this.setStrength(STRENGTH);
        this.setFuel(INITIAL_FUEL);
        this.setMaxFuel(MAX_FUEL);
        this.setImage(ATATIMAGE);
        this.setName(ATAT.NAME);
        this.setDirection(Util.getRandomDirection());
    }

}
