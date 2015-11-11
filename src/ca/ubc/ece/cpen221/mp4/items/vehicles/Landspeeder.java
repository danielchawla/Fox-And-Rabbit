package ca.ubc.ece.cpen221.mp4.items.vehicles;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;

public class Landspeeder extends AbstractArenaVehicle {

    public Landspeeder(Location startPoint) {
        this.MAX_FUEL = 200;
        this.STRENGTH = 100;
        this.MIN_SPEED = 3;
        this.TURNING_SPEED = 6;
        this.VIEW_RANGE = 40;

        this.speed = 3;
        this.location = startPoint;
        this.direction = Util.getRandomDirection();
        this.fuel = 200;  
        this.image = Util.loadImage("landspeeder.gif");
        this.name = "Landspeeder";
    }

    @Override
    public Command getNextAction(World world) {
        // TODO Fill this in
        return null;
    }
}
