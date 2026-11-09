package animals;

import diet.IDiet;
import diet.Omnivore;
import food.EFoodType;
import food.IEdible;
import graphics.ZooPanel;
import graphics.Observer;
import mobility.Point;

import java.util.Vector;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

public class Bear extends Roar {
    private static final Point starting_location = new Point(100,5);
    private static final Omnivore diet = new Omnivore();

    /**
     * class constructor
     * @param name A string of the bear's name
     * @param weight A float of the weight
     * @param color A string of the color
     * @param panel A "ZooPanel" object
     */
    public Bear(String name,float weight,String color,ZooPanel panel) {
        super(name,starting_location,color, panel);
        super.setWeight(weight);
        super.setDiet(diet);
        this.loadImages("bea");
        panel.repaint();
    }

    /**
     * @param food the food hi can eat
     * @return true if hi eat
     */
    @Override
    public boolean eat(IEdible food) {
        double W = diet.eat(this, food);
        if(W > 0) {
            super.setWeight(super.getWeight()+W);
            super.makeSound();
            return true;
        }
        return false;
    }
    /**
     * @return the food type hi is
     */
    @Override
    public EFoodType getFoodType() {
        return EFoodType.MEAT;
    }
    public void roar() { }
    /**
     * @return diet
     */
    public IDiet getDiet() { return diet; }
    @Override
    public Object clone() throws CloneNotSupportedException {
        Bear c = (Bear) new Bear("Bear", (float) getWeight(), getColor(), ZooPanel.getInstance());
        c.setSize(this.getSize());
        c.setX_dir(this.getXdir());
        c.setY_dir(this.getYdir());
        c.setLocation(this.getLocation());
        c.setEatCount(getEatCount());
        c.setoldHspeed(getHspeed());
        c.setVspeed(getVspeed());
        c.setWeight(getWeight());
        Vector<Observer> obs = getObserversList();
        for (Observer obs1 : obs)
            c.registerObserver(obs1);
        return c;
    }
}