package plants;

import food.EFoodType;
import graphics.ZooPanel;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

public class Cabbage extends Plant {
	/**
	 * @param panel ZooPanel OBTECT
	 */

	public Cabbage(ZooPanel panel) {
		super(panel);
		System.out.println("cabbege");
		this.loadImages("Cabbage imag");
	}
	/**
	 * @return Returns vegetable type
	 */
	@Override
	public EFoodType getFoodType() { return EFoodType.VEGETABLE; }
	public static Plant getInstance(ZooPanel zooPanel) {
		System.out.println("Getting the Singelton");
		if (plant == null)
			plant = new Cabbage(zooPanel);
		return plant;
	}



}
