package ca.ubc.ece.cpen221.mp4.ai;

import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.vehicles.AbstractArenaVehicle;

public class VehicleAI {

    public Command getNextAction(World world, AbstractArenaVehicle abstractArenaVehicle) {
        // TODO Auto-generated method stub
        return new WaitCommand();
    }

}
