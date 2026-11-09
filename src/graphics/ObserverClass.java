package graphics;

import java.util.Objects;

public class ObserverClass implements Observer {
    public void notify(String msg) {
        if(Objects.equals(msg, "manegezoo"))
            ZooPanel.getInstance().manegezoo();
    }

}
