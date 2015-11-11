package ca.ubc.ece.cpen221.mp4.items.vehicles;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;

public class Landspeeder extends AbstractArenaVehicle {

    public Landspeeder(Location startPoint) {
        this.MAX_FUEL = 200;
        this.STRENGTH = 100;
        this.MIN_COOLDOWN = 3;
        this.MAX_COOLDOWN = 6;
        this.VIEW_RANGE = 40;

        this.speed = 0;
        this.location = startPoint;
        this.direction = Util.getRandomDirection();
        this.fuel_level = 200;  
        this.image = Util.loadImage("landspeeder.gif");
        this.name = "Landspeeder";
    }
}
