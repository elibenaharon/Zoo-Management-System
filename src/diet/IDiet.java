package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

public interface IDiet {
    boolean canEat(EFoodType food);
    double eat(Animal animal, IEdible food);
}
