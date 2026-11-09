package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

public class Carnivore implements IDiet {
    /** The function checks if the animal can eat
     * @param food The food she receives
     * @return If he succeeded
     */
    @Override
    public boolean canEat(EFoodType food) { return food == EFoodType.MEAT; }
    /**
     * The function checks if the animal can eat and the island the food it received is an animal
     * @param animal The eating animal
     * @param food The animal being eaten
     * @return Returns the animal's weight after eating
     */
    @Override
    public double eat(Animal animal, IEdible food) {
        if(canEat(food.getFoodType()) && !(animal == food))
            return (animal.getWeight()*0.1);
        return 0;
    }
    /**
     * @return Returns the name of the class
     */
    public String toString() { return "[Carnivore]"; }
}