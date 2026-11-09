package graphics;

import animals.Animal;

public  abstract class AbstractFactory {
    abstract Animal getAnimal(String typeAnimal,String name,float weight , String color,ZooPanel panel);
}
