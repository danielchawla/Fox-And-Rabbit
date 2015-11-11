package ca.ubc.ece.cpen221.mp4.commands;

import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class KillCommand implements Command{
    private final LivingItem item;
    private final Item food;
    private final int range;

    /**
     * Construct a {@link EatCommand}, where <code> item </code> is the eater
     * and <code> food </code> is the food. Remember that the food must be
     * adjacent to the eater, and the eater must have greater strength than the
     * food.
     *
     * @param item
     *            the eater
     * @param food
     *            : the food
     */
    public KillCommand(LivingItem item, Item food, int range) {
        this.item = item;
        this.food = food;
        this.range = range;
    }

    @Override
    public void execute(World w) throws InvalidCommandException {
        if (item.getStrength() <= food.getStrength())
            throw new InvalidCommandException("Invalid EatCommand: Food possesses too much strength");
        if (food.getLocation().getDistance(item.getLocation()) > range)
            throw new InvalidCommandException("Invalid EatCommand: Food is not adjacent");

        item.eat(food);
        food.loseEnergy(Integer.MAX_VALUE);
    }

}
