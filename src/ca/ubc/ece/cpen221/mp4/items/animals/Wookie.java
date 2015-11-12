package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Wookie extends AbstractArenaAnimal {

    private static final ImageIcon wookieImage = Util.loadImage("wookie.gif");

    @Override
    public LivingItem breed() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        return "Wookie";
    }





}
