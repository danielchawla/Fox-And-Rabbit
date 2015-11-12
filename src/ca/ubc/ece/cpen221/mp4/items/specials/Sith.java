package ca.ubc.ece.cpen221.mp4.items.specials;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.SithAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.animals.AbstractArenaAnimal;

/**
 * Evil Sith Lord based off Star Wars Series. Sith is a cruel animal.
 * 
 * @author Annabelle Harvey and Daniel Chawla.
 */
public class Sith extends AbstractArenaAnimal {
  private static final ImageIcon SITHIMAGE = Util.loadImage("sith.gif");
  private static final int STRENGTH = 600;
  private static final int COOLDOWN = 5;
  private static final int INITIAL_ENERGY = 300;
  private static final int MAX_ENERGY = 300;
  private static final int VIEW_RANGE = 100;
  private static final String name = "Sith";
  private final SithAI sithAI;

  /**
   * Constructs a wicked Sith animal. The initialLocation must be
     * valid and empty. 
   * @param ai that cause Sith to move around randomly
   * @param initialLocation to be placed
   */
  public Sith (SithAI ai, Location initialLocation){
      this.sithAI = ai;
      this.setInitialEnergy(INITIAL_ENERGY);
      this.setMaxEnergy(MAX_ENERGY);
      this.setCoolDown(COOLDOWN);
      this.setViewRange(VIEW_RANGE);
      this.setStrength(STRENGTH);
      this.setEnergy(INITIAL_ENERGY);
      this.setLocation(initialLocation); 
      this.setName(Sith.name);
      this.setImage(SITHIMAGE);
  }
  
  /**
   * Methods below all have specs already.
   */
  
  @Override
  public LivingItem breed() {
      // Thankfully, Sith is incapable of breeding.
      return null;
  }
  
  @Override
  public Command getNextAction(World world) {
      Command nextAction = sithAI.getNextAction(world, this);
      setEnergy(getEnergy() -1 ); // Loses 1 energy regardless of action.
      return nextAction;
  }

}
