package plants;

import food.EFoodType;
import graphics.ZooPanel;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

public class Lettuce extends Plant {
	/**
	 * @param  panel ZooPanel
	 */

	public Lettuce(ZooPanel panel) {
		super(panel);
		System.out.println("lettuce");
		this.loadImages("Lettuce imag");
	}
	/**
	 * @return Returns vegetable type
	 */
	@Override
	public EFoodType getFoodType() { return EFoodType.VEGETABLE; }
	public static Plant getInstance(ZooPanel zooPanel) {
		System.out.println("Getting the Singelton");
		if (plant == null)
			plant = new Lettuce(zooPanel);
		return plant;
	}

}
