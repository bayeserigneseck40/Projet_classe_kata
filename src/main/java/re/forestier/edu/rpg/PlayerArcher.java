package re.forestier.edu.rpg;

import java.util.ArrayList;

public class PlayerArcher extends player {
    public PlayerArcher(String playerName, String avatar_name, int money, ArrayList<Item> inventory) {
        super(playerName, avatar_name, "ARCHER", money, inventory);
    }

    @Override
    public void majFinDeTour() {
        if (currenthealthpoints == 0) {
            System.out.println("Le joueur est KO !");
            return;
        }
        currenthealthpoints += 1;
        if (inventory.contains("Magic Bow")) {
            currenthealthpoints += currenthealthpoints / 8 - 1;
        }
        currenthealthpoints = Math.min(currenthealthpoints, healthpoints);
    }
}
