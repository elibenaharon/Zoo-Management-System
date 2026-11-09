package animals;

import diet.IDiet;
import graphics.*;
import mobility.Mobile;
import mobility.Point;
import food.IEdible;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

public abstract class Animal extends Mobile implements IEdible,IAnimalBehavior,IDrawable,Runnable ,Observable ,Cloneable{

    private final String name;
    private double weight;
    private IDiet diet;
    private int size;
    private final String col;
    private int verSpeed;
    private int horSpeed;
    private int x_dir = 1;
    private int y_dir = 1;
    private int eatCount;

    private BufferedImage img1, img2;
    //protected Thread thread;
    protected boolean threadSuspended;
    private static ExecutorService mypool = new ThreadPoolExecutor(10, 10, 5000, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(5));
    ThreadPoolExecutor pool = (ThreadPoolExecutor) mypool;
    private Boolean stop = false;
    Vector<Observer> list = new Vector();



        /**
         * Animal class constructor.
         * @param name A string of the animal name
         * @param location A "point" type object, the position of the animal
         * @param col A string color of the animal
         * @param zoopanel A zoopanel object that describes the panel in which the animal is present
         */
    public Animal(String name, Point location , String col ,ZooPanel zoopanel) {
        super(location);
        threadSuspended = false;
        this.name = name;
        eatCount = 0 ;
        this.img1 = null;
        this.img2 =null;
        //this.panel = zoopanel;
        this.col = col;
        mypool.submit(this);

    }
    /**
     * abstract method
     */
    public abstract void makeSound();
    /**
     * abstract method
     */
    public abstract boolean eat(IEdible food);
    /**
     * @return Thread himself
     */
    /**
     * get Thread Suspended
     * @return threadSuspended The treade itself
     */
    public boolean getThreadSuspended() { return this.threadSuspended; }
    /**
     * set Diet
     * @param diet The type of food
     */
    public void setDiet(IDiet diet) { this.diet = diet; }
    /**
     * get Name
     * @return Returns the name of the animal
     */
    public String getName() { return this.name; }
    /**
     * get Weight
     * @return Returns the weight of the animal
     */
    public double getWeight() { return this.weight; }
    /**
     * get Animal Name class
     * @return Returns the class name of the animal
     */
    public String getAnimalName() { return this.getClass().getSimpleName(); }
    /**
     * get Size
     * @return Returns the size of the animal
     */
    public int getSize() { return this.size; }
    /**
     * Increases the number of times the animal has eaten by 1
     */
    public void eatInc() { this.eatCount++; }
    /**
     * get Eat Count
     * @return Returns the number of times the animal has eaten
     */
    public int getEatCount() { return this.eatCount; }
    /**
     * set Changes
     * @param state true false
     */
    public void setChanges (boolean state) { }
    /**
     * get Color
     * @return Returns the color of the animal
     */
    public String getColor() { return this.col; }
    /**
     * get Horizontal Speed
     * @return Returns Horizontal Of the animal
     */
    public int getHorSpeed() { return this.horSpeed; }
    /**
     * get vertical Speed
     * @return Returns vertical Of the animal
     */
    public int getVerSpeed() { return this.verSpeed; }
    /**
     * set Size
     * @param size size of the animal
     */
    public void setSize(int size) { this.size = size; }
    /**
     * set Horizontal Speed
     * @param hor Horizontal Speed
     */
    public void setHorSpeed(int hor) { this.horSpeed = hor; }
    /**
     * set vertical Speed
     * @param ver vertical Speed
     */
    public void setVerSpeed(int ver) { this.verSpeed = ver; }
    /**
     * get Image right
     * @return Returns the image of the right side of the animal
     */
    public BufferedImage getImage1() { return this.img1; }
    /**
     * get Image left
     * @return Returns the image of the right side of the animal
     */
    public BufferedImage getImage2() { return this.img2; }
    /**
     * Animal face right side
     * @return
     */
    public int getX_dir() { return this.x_dir; }
    /**
     * Animal face left side
     * @return
     */
    public int getY_dir() { return this.y_dir; }
    public IDiet getDiet() { return this.diet; }
    /**
     *set Weight
     * @param weight weight of the animal
     * @return true if Succeeded
     */
    public boolean setWeight(double weight) {
        if (weight > 0) {
            this.weight = weight;
            return true;
        }
        return false;
    }
    /**
     * load Images
     * A function that loads the animal images
     * @param ani_name Name of the animal
     */
    public void loadImages(String ani_name) {
        switch (this.col){
            case "Red"->{
                try {
                    this.img1 = ImageIO.read(new File(PICTURE_PATH +"\\images\\"+ ani_name + "_r_1.png"));
                    this.img2 = ImageIO.read(new File(PICTURE_PATH +"\\images\\"+ ani_name + "_r_2.png"));
                }
                catch (IOException e) { e.printStackTrace(); }
            }
            case "Blue"->{
                try {
                    this.img1 = ImageIO.read(new File(PICTURE_PATH +"\\images\\"+ ani_name + "_b_1.png"));
                    this.img2 = ImageIO.read(new File(PICTURE_PATH +"\\images\\"+ ani_name + "_b_2.png"));
                }
                catch (IOException e) { e.printStackTrace(); }
            }
            default -> {
                try {
                    this.img1 = ImageIO.read(new File(PICTURE_PATH +"\\images\\"+ ani_name + "_n_1.png"));
                    this.img2 = ImageIO.read(new File(PICTURE_PATH +"\\images\\"+ ani_name + "_n_2.png"));
                }
                catch (IOException e) { e.printStackTrace(); }
            }
        }
    }
    /**
     * Upload the image to ZooPanel
     * @param g
     */
    public void drawObject (Graphics g) {
        if(this.getX_dir() == 1)
            g.drawImage(this.getImage1(), this.getLocation().getX()-this.size/2, this.getLocation().getY()-this.size/10, this.size/2, this.size, ZooPanel.getInstance());
        else
            g.drawImage(this.getImage2(), this.getLocation().getX()-this.size/2, this.getLocation().getY()-this.size/10, this.size/2, this.size, ZooPanel.getInstance());
    }
    @Override
    public void setSuspended() { threadSuspended = true; }

    public void setResumed() {
        threadSuspended = false;
        synchronized (this) { this.notify(); }
    }
    /**
     * Moves thread the of the animals
     */
    @Override
    public void run() {
        while (!stop) {
            if(threadSuspended)
                synchronized (this) {
                    try { this.wait(); }
                    catch (InterruptedException ignored) {  }
                }
            if (ZooPanel.getInstance().getPlant() != null && this.diet.canEat(ZooPanel.getInstance().getPlant().getFoodType())) {
                if (this.getLocation().getY() !=250 || this.getLocation().getX()!=400) {
                    if (this.getLocation().getY() > 250)
                        this.y_dir = -1;
                    else
                        this.y_dir = 1;
                    if (this.getLocation().getX() > 400)
                        this.x_dir = -1;
                    else
                        this.x_dir = 1;

                    this.setLocation(new Point(this.getLocation().getX() + this.horSpeed * x_dir, this.getLocation().getY() + this.verSpeed * y_dir));

                    try { //Thread.sleep(100);
                        TimeUnit.MILLISECONDS.sleep(100); }
                    catch (InterruptedException ignored) {  }
                }
            }
            else {
                if (this.getLocation().getX() > 790)
                    this.x_dir =-1;
                else if (this.getLocation().getX() < 10)
                    this.x_dir =1;
                if (this.getLocation().getY() > 590)
                    this.y_dir =-1;
                else if (this.getLocation().getY() < 10)
                    this.y_dir =1;
                try {
                    int x = this.getLocation().getX() + this.getHorSpeed() * getX_dir();
                    if (x >= 750 || x <= 0) {
                        x_dir = x_dir * (-1);
                        x = this.getLocation().getX() + this.getHorSpeed() * getX_dir();
                    }
                    int y = this.getLocation().getY() + this.getVerSpeed() * getY_dir();
                    if (y >= 550 || y <= 0) {
                        y_dir = y_dir * (-1);
                        y = this.getLocation().getY() + this.getVerSpeed() * getY_dir();
                    }
                    this.move(new Point(x, y));
                    setChanges(true);
                    if(!(this.threadSuspended)) {
                        try { //Thread.sleep(100);
                            TimeUnit.MILLISECONDS.sleep(100);}
                        catch (InterruptedException ignored) {  }
                    }
                   // notifyObservers("manegezoo");

                }
                catch (Exception ignored) {  }
                try {// Thread.sleep(100);
                    TimeUnit.MILLISECONDS.sleep(100);}
                catch (InterruptedException ignored) {  }
            }
            notifyObservers("manegezoo");


        }
        System.out.println(1);
    }
    public static void goodbye()
    {
        mypool.shutdownNow();
    }
    public static void setMypool()
    {
        mypool = Executors.newFixedThreadPool(5);

    }
    public void setStop(Boolean s)
    {
        this.stop= s;
    }
    public void registerObserver(Observer ob){list.add(ob); }
    public void unregisterObserver(Observer ob){ list.remove(ob); }////////////////////////////--------------------------
    private void notifyObservers(String msg) {
        for(Observer ob : list)
            ob.notify (msg);
    }
    public abstract Object clone() throws CloneNotSupportedException;
    public void setEatCount( int c)
    {
        this.eatCount = c;
    }
    public void setoldHspeed (int s)
     {
         this.horSpeed = s;
     }
    public int getHspeed() { return this.horSpeed;}
    public void setVspeed(int s)
    {
        this.verSpeed = s;
    }
    public int getVspeed()
    {
        return this.verSpeed;
    }
    public Vector<Observer> getObserversList()
     {
         return this.list;
     }
    public void setX_dir(int x_dir) {
        this.x_dir = x_dir;
    }
    public void setY_dir(int y_dir)
    {
        this.y_dir = y_dir;
    }
    public int getXdir()
    {
        return this.x_dir;
    }
    public int getYdir()
    {
        return this.y_dir;
    }
    public static void addtomypool(Animal a)
    {
        mypool.submit(a);
    }
    public static void Execute(Animal a)
    {
        mypool.execute(a);
    }
}


