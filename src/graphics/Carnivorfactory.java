package graphics;

import animals.Animal;
import animals.Lion;

public class Carnivorfactory extends AbstractFactory{

    @Override
    Animal getAnimal(String typeAnimal ,String name,float weight , String color,ZooPanel panel ) {
        if(typeAnimal.equalsIgnoreCase("Lion"))
            return new Lion( name, weight , color, panel);
        return null;
    }

}
