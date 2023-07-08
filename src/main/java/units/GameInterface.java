package units;

import java.util.ArrayList;

public interface GameInterface {
    void step(ArrayList<Unit> heroes, ArrayList<Unit> myOwnTeam);
    String getInfo();
}
