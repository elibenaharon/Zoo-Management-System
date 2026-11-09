package graphics;

import animals.Animal;
import animals.Elephant;
import animals.Giraffe;
import animals.Turtle;

public class HerbivorFactory extends AbstractFactory{
    @Override
    Animal getAnimal(String typeAnimal, String name, float weight, String color, ZooPanel panel) {
        if(typeAnimal.equalsIgnoreCase("Elephant"))
            return new Elephant(name,weight,color,panel);
        if (typeAnimal.equalsIgnoreCase("Turtle"))
            return new Turtle(name,weight,color,panel);
        if (typeAnimal.equalsIgnoreCase("Giraffe"))
            return new Giraffe(name,weight,color,panel);
        return null;
    }
}
