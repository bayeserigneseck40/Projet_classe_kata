package re.forestier.edu.rpg;

import java.util.ArrayList;

public class PlayerAdventurer extends player {
    public PlayerAdventurer(String playerName, String avatar_name, String dwarf, int money, ArrayList<String> inventory) {
        super(playerName, avatar_name, "ADVENTURER", money, inventory);
    }
  

    @Override
    public void majFinDeTour() {
        if (currenthealthpoints == 0) {
            System.out.println("Le joueur est KO !");
            return;
        }
        currenthealthpoints += 2;
        if (retrieveLevel() < 3) {
            currenthealthpoints -= 1;
        }
        currenthealthpoints = Math.min(currenthealthpoints, healthpoints);
    }
}