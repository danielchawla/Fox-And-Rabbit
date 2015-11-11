package ca.ubc.ece.cpen221.mp4.items.vehicles;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;

public class SpeederBike extends AbstractArenaVehicle {

    public SpeederBike(Location startPoint) {
        this.MAX_FUEL = 100;
        this.STRENGTH = 50;
        this.MIN_SPEED = 2;
        this.TURNING_SPEED = 5;
        this.VIEW_RANGE = 15;

        this.speed = 1;
        this.location = startPoint;
        this.direction = Util.getRandomDirection();
        this.fuel = 100;  
        this.image = Util.loadImage("motorcycles.gif");
        this.name = "SpeederBike";
    }

    @Override
    public Command getNextAction(World world) {
        // TODO Fill this in
        return null;
    }
}
