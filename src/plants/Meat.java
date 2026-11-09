package plants;

import food.EFoodType;
import graphics.ZooPanel;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

public class Meat extends Plant {
    /**
     * @param panel ZooPanel OBTECT
     */

    public Meat(ZooPanel panel) {
        super(panel);
        System.out.println("Meat");
        this.loadImages("meat.gif");
    }
    /**
     * @return Returns meat type
     */
    @Override
    public EFoodType getFoodType() {
        return EFoodType.MEAT;
    }
    public static Plant getInstance(ZooPanel zooPanel) {
        System.out.println("Getting the Singelton");
        if (plant == null)
            plant = new Meat(zooPanel);
        return plant;
    }




}
