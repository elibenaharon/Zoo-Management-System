package graphics;

import java.awt.*;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

public interface IDrawable {
    String PICTURE_PATH = System.getProperty("user.dir");
    void loadImages(String nm);
    void drawObject (Graphics g);
    String getColor();
}
