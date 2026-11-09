package mobility;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * @param other Point
     */
    public Point(Point other) {
        if (Point.checkBoundaries(other)) {
            this.x = other.getX();
            this.y = other.getY();
        }
    }
    /**
     * Checks if the point is within the allowable limits
     * @param p Point OBJECT
     * @return TRUE
     */
    public static boolean checkBoundaries(Point p) { return p.getX() <= p.getMaxX() && p.getX() >= p.getMinX() && p.getY() <= p.getMaxY() && p.getY() >= p.getMinY(); }
    /**
     * set X
     * @param x INTEGER
     */
    public void setX(int x) {
        if (x <= getMaxX() && x >= getMinX())
            this.x = x;
    }
    /**
     * set Y
     * @param y INTEGER
     */
    public void setY(int y) {
        if (y <= getMaxY() && y >= getMinY())
            this.y = y;
    }
    /**
     * @return X
     */
    public int getX() {
        return this.x;
    }
    /**
     * @return Y
     */
    public int getY() {
        return this.y;
    }
    /**
     * get MinX
     * @return minimum X
     */
    public int getMinX() { return 0; }
    /**
     * @return minimum Y
     */
    public int getMinY() { return 0; }
    /**
     * @return maximum X
     */
    public int getMaxX() { return 800; }
    /**
     * @return maximum Y
     */
    public int getMaxY() { return 600; }
}
