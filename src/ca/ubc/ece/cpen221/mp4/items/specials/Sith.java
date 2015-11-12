package ca.ubc.ece.cpen221.mp4.items.specials;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.RandomMovementAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.animals.AbstractArenaAnimal;

public class Sith extends AbstractArenaAnimal {
  private static final ImageIcon SITHIMAGE = Util.loadImage("sith.gif");
  private static final int STRENGTH = 100;
  private static final int COOLDOWN = 2;
  private static final int INITIAL_ENERGY = 300;
  private static final int MAX_ENERGY = 300;
  private static final int VIEW_RANGE = 3;
  private static final String name = "Sith";
  private final RandomMovementAI randomAI;

  public Sith (RandomMovementAI ai, Location initialLocation){
      this.randomAI = ai;
      this.setINITIAL_ENERGY(INITIAL_ENERGY);
      this.setMAX_ENERGY(MAX_ENERGY);
      this.setCOOLDOWN(COOLDOWN);
      this.setVIEW_RANGE(VIEW_RANGE);
      this.setSTRENGTH(STRENGTH);
      this.setEnergy(INITIAL_ENERGY);
      this.setLocation(initialLocation); 
      this.setName(Sith.name);
      this.setImage(SITHIMAGE);
  }
  
  @Override
  public LivingItem breed() {
      // Thankfully, Sith is incapable of breeding.
      return null;
  }
  
  @Override
  public Command getNextAction(World world) {
      Command nextAction = randomAI.getNextAction(world, this);
      setEnergy(getEnergy() -1 ); // Loses 1 energy regardless of action.
      return nextAction;
  }

}
