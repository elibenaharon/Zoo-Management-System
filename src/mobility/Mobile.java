package mobility;

import animals.Animal;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

public abstract class Mobile implements ILocatable {
    private Point location;
    private double totalDistance;
    public Point getLocation() {
        return this.location;
    }
    /**
     * @param location The location of the animal
     */
    public Mobile(Point location) { this.location = new Point(location.getX(),location.getY()); }
    /**
     * add TO THE Total Distance
     * @param distance
     */
    public void addTotalDistance(double distance) { this.totalDistance=this.totalDistance + distance; }
    /**
     * calc Distance
     * @param newLocation new Location
     * @return Distance
     */
    public double calcDistance(Point newLocation) { return Math.sqrt(Math.pow((location.getX()-newLocation.getX()) ,2) +Math.pow((location.getY()-newLocation.getY()) ,2)); }
    /**
     * The function moves the animal
     * @param newLocation new Location
     * @return  new Location
     */
    public double move(Point newLocation) {
        if(Point.checkBoundaries(newLocation)){
            addTotalDistance(calcDistance(newLocation));
            double distance = calcDistance(newLocation);
            this.location.setX(newLocation.getX());
            this.location.setY(newLocation.getY());
            if (this instanceof Animal) {
                double x = ((Animal) this).getWeight() - (distance * ((Animal) this).getWeight() * 0.00025);
                ((Animal) this).setWeight(x);
            }
            return this.calcDistance(newLocation);
        }
        return 0;
    }
    public void setLocation(Point point) {
        if(Point.checkBoundaries(point))
            this.location = new Point(point);
    }
}
