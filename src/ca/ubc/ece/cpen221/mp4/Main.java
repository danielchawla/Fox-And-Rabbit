package ca.ubc.ece.cpen221.mp4;

import javax.swing.SwingUtilities;

import ca.ubc.ece.cpen221.mp4.ai.*;
import ca.ubc.ece.cpen221.mp4.items.Gardener;
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.animals.*;
import ca.ubc.ece.cpen221.mp4.items.specials.C3P0;
import ca.ubc.ece.cpen221.mp4.items.specials.R2D2;
import ca.ubc.ece.cpen221.mp4.items.specials.Sith;
import ca.ubc.ece.cpen221.mp4.items.vehicles.ATAT;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Landspeeder;
import ca.ubc.ece.cpen221.mp4.items.vehicles.SpeederBike;
import ca.ubc.ece.cpen221.mp4.staff.WorldImpl;
import ca.ubc.ece.cpen221.mp4.staff.WorldUI;

/**
 * The Main class initialize a world with some {@link Grass}, {@link Rabbit}s,
 * {@link Fox}es, {@link Gnat}s, {@link Gardener}, etc.
 *
 * You may modify or add Items/Actors to the World.
 *
 */
public class Main {

	static final int X_DIM = 40;
	static final int Y_DIM = 40;
	static final int SPACES_PER_GRASS = 7;
	static final int INITIAL_GRASS = X_DIM * Y_DIM / SPACES_PER_GRASS;
	static final int INITIAL_GNATS = INITIAL_GRASS / 4;
	static final int INITIAL_RABBITS = INITIAL_GRASS / 4;
	static final int INITIAL_FOXES = INITIAL_GRASS / 32;
	static final int INITIAL_TIGERS = INITIAL_GRASS / 32;
	static final int INITIAL_BEARS = INITIAL_GRASS / 40;
	static final int INITIAL_HYENAS = INITIAL_GRASS / 32;
	static final int INITIAL_LANDSPEEDER = INITIAL_GRASS / 100;
	static final int INITIAL_ATAT = INITIAL_GRASS / 150;
	static final int INITIAL_SPEEDERBIKE = INITIAL_GRASS / 64;
	static final int INITIAL_MANS = INITIAL_GRASS / 150;
	static final int INITIAL_CLONES = 4;
	static final int INITIAL_JEDIS = 7;
	static final int INITIAL_WOOKIES = 4;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main().createAndShowWorld();
			}
		});
	}
	
	/**
	 * Creates and displays world. 
	 */
	public void createAndShowWorld() {
		World world = new WorldImpl(X_DIM, Y_DIM);
		initialize(world);
		new WorldUI(world).show();
	}
	
	/**
	 * Initializes world
	 * @param world to initialize.
	 */
	public void initialize(World world) {
		addGrass(world);
		world.addActor(new Gardener());

    	addGnats(world);
		addRabbits(world);
		addFoxes(world);

		addClones(world);
		addJedis(world);
		addWookies(world);
		
		addR2D2(world);
		addC3P0(world);
		addSith(world);

		addATATs(world);
		addSpeederBikes(world);
		addLandSpeeders(world);
	}
	
	
	/**
	 * Adds grass to world. 
	 * @param world
	 */
	private void addGrass(World world) {
		for (int i = 0; i < INITIAL_GRASS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			world.addItem(new Grass(loc));
		}
	}
	
	/**
	 * Adds gnats to world.
	 * @param world
	 */
	private void addGnats(World world) {
		for (int i = 0; i < INITIAL_GNATS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Gnat gnat = new Gnat(loc);
			world.addItem(gnat);
			world.addActor(gnat);
		}
	}
	
	/**
	 * Add foxes to world.
	 * @param world
	 */
	private void addFoxes(World world) {
		FoxAI foxAI = new FoxAI();
		for (int i = 0; i < INITIAL_FOXES; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Fox fox = new Fox(foxAI, loc);
			world.addItem(fox);
			world.addActor(fox);
		}
	}
	
	/**
	 * Adds rabbits to world.
	 * @param world
	 */
	private void addRabbits(World world) {
		RabbitAI rabbitAI = new RabbitAI();
		for (int i = 0; i < INITIAL_RABBITS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Rabbit rabbit = new Rabbit(rabbitAI, loc);
			world.addItem(rabbit);
			world.addActor(rabbit);
		}
	}
	
    /**
     * Add Jedis to world
     * 
     * @param world
     */
    private void addJedis(World world) {
        JediAI jediAI = new JediAI();
        for (int i = 0; i < INITIAL_JEDIS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Jedi jedi = new Jedi(jediAI, loc);
            world.addItem(jedi);
            world.addActor(jedi);
        }
    }
    
    /** 
     * Adds clones to world.
     * @param world
     */
    private void addClones(World world) {
        CloneAI cloneAI = new CloneAI();
        for (int i = 0; i < INITIAL_CLONES; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Clone clone = new Clone(cloneAI, loc);
            world.addItem(clone);
            world.addActor(clone);
        }
    }
    
    /**
     * Adds wookies to world. 
     * @param world
     */
    private void addWookies(World world) {
        RandomMovementAI randomAI = new RandomMovementAI();
        for (int i = 0; i < INITIAL_WOOKIES; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Wookie wookie = new Wookie(randomAI, loc);
            world.addItem(wookie);
            world.addActor(wookie);
        }
    }
    
    /**
     * Adds R2D2 to world.
     * @param world
     */
    private void addR2D2(World world) {
        R2D2AI r2d2AI = new R2D2AI();
        Location loc = Util.getRandomEmptyLocation(world);
        R2D2 R2D2 = new R2D2(r2d2AI, loc);
        world.addItem(R2D2);
        world.addActor(R2D2);
    }
    
    /**
     * Adds C3P0 to world.
     * @param world
     */
    private void addC3P0(World world) {
        C3P0AI c3p0AI = new C3P0AI();
        Location loc = Util.getRandomEmptyLocation(world);
        C3P0 C3P0 = new C3P0(c3p0AI, loc);
        world.addItem(C3P0);
        world.addActor(C3P0);
    }
    
    /**
     * Adds Sith to world.
     * @param world
     */
    private void addSith(World world) {
        SithAI sithAI = new SithAI();
        Location loc = Util.getRandomEmptyLocation(world);
        Sith sith = new Sith(sithAI, loc);
        world.addItem(sith);
        world.addActor(sith);
    }
    
    /**
     * Adds Speeder Bikes to world. 
     * @param world
     */
    private void addSpeederBikes(World world) {
        for (int i = 0; i < INITIAL_SPEEDERBIKE; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            SpeederBike speederBike = new SpeederBike(loc);
            world.addItem(speederBike);
            world.addActor(speederBike);
        }
    }
    
    /** 
     * Adds Landspeeders to world.
     * @param world
     */
    private void addLandSpeeders(World world) {
        for (int i = 0; i < INITIAL_LANDSPEEDER; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Landspeeder landspeeder = new Landspeeder(loc);
            world.addItem(landspeeder);
            world.addActor(landspeeder);
        }
    }
    
    /**
     * Adds ATATs to world. 
     * @param world
     */
    private void addATATs(World world) {
        for (int i = 0; i < INITIAL_ATAT; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            ATAT atat = new ATAT(loc);
            world.addItem(atat);
            world.addActor(atat);
        }
    }
}