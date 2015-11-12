package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;

public class Landspeeder extends AbstractArenaVehicle {
    private static final ImageIcon LANDSPEEDERIMAGE = Util.loadImage("cars.gif");
    private static final int STRENGTH = 100;
    private static final int CHANGE_DIRECTION_COOLDOWN = 7; // speed vehicle needs to slow down to in order to turn
    private static final int MINCOOLDOWN = 4;
    private static final int INITIAL_FUEL = 300;
    private static final int MAX_FUEL = 300;
    private static final String NAME = "Landspeeder";

    public Landspeeder(Location startPoint) {
        this.setLocation(startPoint);  
        this.setInitialCoolDown(CHANGE_DIRECTION_COOLDOWN); // initial speed
        this.setChangeDirectionCoolDown(CHANGE_DIRECTION_COOLDOWN); // speed at which it can turn
        this.setStrength(STRENGTH);
        this.setFuel(INITIAL_FUEL);
        this.setMaxFuel(MAX_FUEL);
        this.setMinCoolDown(MINCOOLDOWN);
        this.setImage(LANDSPEEDERIMAGE);
        this.setName(Landspeeder.NAME);
        this.setDirection(Util.getRandomDirection());
    }

}
