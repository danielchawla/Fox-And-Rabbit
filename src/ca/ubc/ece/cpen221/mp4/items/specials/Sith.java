package ca.ubc.ece.cpen221.mp4.items.specials;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.SpecialsAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.animals.AbstractArenaAnimal;

public class Sith extends AbstractArenaAnimal {
  private static final ImageIcon SITHIMAGE = Util.loadImage("sith.gif");
  private static final int MEAT_CALORIES = 1;
  private static final int STRENGTH = 100;
  private static final int COOLDOWN = 2;
  private static final int INITIAL_ENERGY = 300;
  private static final int MAX_ENERGY = 300;
  private static final int VIEW_RANGE = 3;
  private static final String name = "Sith";
  private final SpecialsAI specialsAI;
  
  private Location location;
  private boolean isDead;
  private int energy;

  public Sith (SpecialsAI ai, Location initialLocation){
      this.specialsAI = ai;
      this.isDead = false;
      this.setINITIAL_ENERGY(INITIAL_ENERGY);
      this.setMAX_ENERGY(MAX_ENERGY);
      this.setCOOLDOWN(COOLDOWN);
      this.setVIEW_RANGE(VIEW_RANGE);
      this.setSTRENGTH(STRENGTH);
      this.setEnergy(INITIAL_ENERGY);
      this.setLocation(initialLocation);   
  }
  
  @Override
  public LivingItem breed() {
      // Thankfully, Sith is incapable of breeding.
      return null;
  }
  
  @Override
  public ImageIcon getImage() {
      return SITHIMAGE;
  }

  @Override
  public String getName() {
      return Sith.name;
  }
  
  @Override
  public Command getNextAction(World world) {
      Command nextAction = specialsAI.getNextAction(world, this);
      setEnergy(getEnergy() -1 ); // Loses 1 energy regardless of action.
      return nextAction;
  }

}
