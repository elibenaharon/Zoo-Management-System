package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

public class Omnivore extends Carnivore implements IDiet {
    /** The function checks if the animal can eat
     * @param food The food she receives
     * @return If he succeeded
     */
    @Override
    public boolean canEat(EFoodType food) { return food == EFoodType.MEAT || food == EFoodType.VEGETABLE; }
    /**
     * The function checks if the animal can eat and the island the food it received is an animal
     * @param animal The eating animal
     * @param food The animal being eaten
     * @return Returns the animal's weight after eating
     */
    @Override
    public double eat(Animal animal, IEdible food) {
        EFoodType Temp_Food = food.getFoodType();
        if(canEat(Temp_Food) && Temp_Food == EFoodType.MEAT && !(animal == food))
            return super.eat(animal, food);
        else if(canEat(Temp_Food) && Temp_Food == EFoodType.VEGETABLE && !(animal == food))
            return (animal.getWeight()*0.07);
        return 0;
    }
    /**
     * @return Returns the name of the class
     */
    public String toString() { return "[Omnivore]"; }
}
