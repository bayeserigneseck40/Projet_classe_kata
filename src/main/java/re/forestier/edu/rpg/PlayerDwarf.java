package re.forestier.edu.rpg;

import java.util.ArrayList;

public class PlayerDwarf extends player {
    public PlayerDwarf(String playerName, String avatar_name, int money, ArrayList<Item> inventory) {
        super(playerName, avatar_name, "DWARF", money, inventory);
    }

    @Override
    public void majFinDeTour() {
        if (currenthealthpoints == 0) {
            System.out.println("Le joueur est KO !");
            return;
        }
        if (currenthealthpoints < healthpoints / 2) {
            currenthealthpoints += 1;
            boolean hasHolyElixir = inventory.stream()
                                             .anyMatch(item -> item.getName().equals("Holy Elixir"));
            if (hasHolyElixir) {
                currenthealthpoints += 1;
            }
        }
    
        // Assure que les points de vie ne dépassent pas le maximum
        currenthealthpoints = Math.min(currenthealthpoints, healthpoints);
    }
    
}
