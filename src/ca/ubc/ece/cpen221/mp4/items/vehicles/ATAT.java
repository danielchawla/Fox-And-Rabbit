package ca.ubc.ece.cpen221.mp4.items.vehicles;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;

public class ATAT extends AbstractArenaVehicle {
    
    public ATAT (Location startPoint) {
        this.MAX_FUEL = 1000;
        this.STRENGTH = 1000;
        this.MIN_COOLDOWN = 7;
        this.MAX_COOLDOWN = 10;
        this.VIEW_RANGE = 100;

        this.speed = 0;
        this.location = startPoint;
        this.direction = Util.getRandomDirection();
        this.fuel_level = 1000;  
        this.image = Util.loadImage("trucks.gif");
        this.name = "ATAT";
    }
}
