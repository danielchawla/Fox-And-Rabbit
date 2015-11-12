package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class ATAT extends AbstractArenaVehicle {
    private static final ImageIcon ATATIMAGE = Util.loadImage("truck.gif");
    private static final int STRENGTH = 10;
    private static final int INTIAL_COOLDOWN = 5;
    private static final int CHANGE_DIRECTION_COOLDOWN = 8;
    private static final int VIEW_RANGE = 3;
    private static final int INITIAL_FUEL = 100;
    private static final int MAX_FUEL = 100;
    private static final String NAME = "ATAT";

    public ATAT(Location startPoint) {
        this.setLocation(startPoint);  
        this.setViewRange(VIEW_RANGE);
        this.setInitialCoolDown(INTIAL_COOLDOWN); // initial speed
        this.setChangeDirectionCoolDown(CHANGE_DIRECTION_COOLDOWN); // speed at which it can turn
        this.setStrength(STRENGTH);
        this.setFuel(INITIAL_FUEL);
        this.setMaxFuel(MAX_FUEL);
    }

    @Override
    public Command getNextAction(World world) {
        // TODO Fill this in
        return null;
    }
    
    @Override
    public ImageIcon getImage() {
        return ATATIMAGE;
    }

    @Override
    public String getName() {
        return ATAT.NAME;
    }

}
