package units;

import java.util.ArrayList;

public interface GameInterface {
    void step(ArrayList<Unit> heroes);
    String getInfo();
}
