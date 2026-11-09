package graphics;

import animals.Animal;
import animals.Bear;

public class Omnivorfactory extends AbstractFactory{
    @Override
    Animal getAnimal(String typeAnimal, String name, float weight, String color, ZooPanel panel) {
        if(typeAnimal.equalsIgnoreCase("Bear"))
            return new Bear( name, weight, color, panel);
        return null;
    }
}
