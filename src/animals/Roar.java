package animals;

import food.EFoodType;
import food.IEdible;
import graphics.ZooPanel;
import mobility.Point;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

public abstract class Roar extends Animal
{
    /**
     * Represents the animals that chew vegetables
     * @param name A string of the animal name
     * @param location A "point" type object, the position of the animal
     * @param color A string color of the animal
     * @param panel A zoopanel object that describes the panel in which the animal is present
     */
    public Roar(String name, Point location, String color, ZooPanel panel) {
        super(name, location,color,panel);
    }

    public void makeSound() {
        this.roar();
    }

    @Override
    public boolean eat(IEdible food) {
        return false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return null;
    }

    public abstract void roar();

    @Override
    public EFoodType getFoodType() {
        return null;
    }
}
