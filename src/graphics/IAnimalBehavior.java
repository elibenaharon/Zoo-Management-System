package graphics;

/**
 * A class represents a Zoo Simulation V2.0 (reworked)
 * @Eli ben aharon 311614853
 * @Vladislav Kelar 320467335
 */

public interface IAnimalBehavior {
    String getAnimalName();
    //int getSize(); useless
    void eatInc();
    int getEatCount();
    void setChanges (boolean state);
    void setSuspended();
    void setResumed();
}
