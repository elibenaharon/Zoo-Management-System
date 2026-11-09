package plants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import food.IEdible;
import food.EFoodType;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

public abstract class Plant implements IEdible, ILocatable, IDrawable{
	protected static Plant plant = null;
	private Point location;
	private final int height;
	private final int width;
	private BufferedImage plant_image = null;
	protected static Boolean sngelton = false;
	/**
	 * @param zoopanel ZooPanel OBTECT
	 */
	protected Plant(ZooPanel zoopanel) {
		sngelton = true;
		int x = 400;
		int y = 260;
		this.location = new Point(x, y);
		this.height = 40;
		this.width = 40;
		//this.zoopanel = zoopanel;
	}
	public static Plant getInstance(ZooPanel zooPanel,String s) {

		if (plant == null && sngelton==false)
		{
			switch (s)
			{
				case "Meat" -> {
					sngelton = true;
					return plant = new Meat(zooPanel);
				}
				case "Cabbage" -> {
					sngelton = true;
					return plant = new Cabbage(zooPanel);
				}
				case "Lettuce" -> {
					sngelton = true;
					return plant=  new Lettuce(zooPanel);
				}
			}
		}
		System.out.println("Getting the Singelton plant ");
		return plant;
	}
	public void setSngelton(Boolean s)
	{
		sngelton = s;
	}
	public void setnul()
	{
		this.plant = null;
	}

	/**
	 * get Width
	 * @return width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * get Height
	 * @return Height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @return Returns vegetable type
	 */
	@Override
	public EFoodType getFoodType() {
		return EFoodType.VEGETABLE;
	}
	/**
	 * get Location
	 * @return Returns location
	 */
	@Override
	public Point getLocation() {
		return this.location;
	}
	/**
	 * set Location
	 * @param newLocation new Location
	 */
	@Override
	public void setLocation(Point newLocation) {
		if (Point.checkBoundaries(newLocation))
			this.location = newLocation;
    }
	/**
	 * @return Class Name
	 */
	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] ";
	}
	/**
	 * Draws a picture
	 * @param g
	 */
	public void drawObject(Graphics g) {
		if (plant_image != null)
			g.drawImage(plant_image, this.location.getX(), this.location.getY(), this.getWidth(), this.getHeight(), ZooPanel.getInstance());
	}
	/**
	 * Loading image
	 * @param plant_name plant name
	 */
	public void loadImages(String plant_name) {
		try { plant_image = ImageIO.read(new File(PICTURE_PATH +"\\images\\"+ plant_name)); }
		catch (IOException e) {e.printStackTrace();}
	}
	/**
	 * @return NULL
	 */
	@Override
	public String getColor() {
		return null;
	}
	public boolean getsingelton()
	{
		return sngelton;
	}



}
