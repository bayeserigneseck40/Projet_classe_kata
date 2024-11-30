package re.forestier.edu.rpg;
import java.util.ArrayList;
public class PlayerGoblin extends player {

    public PlayerGoblin(String playerName, String avatarName, int money, ArrayList<Item> inventory) {
        super(playerName, avatarName, "GOBLIN", money, inventory);
    }

    @Override
    public void majFinDeTour() {
        if (currenthealthpoints < healthpoints / 2) {
            currenthealthpoints += 1;
        }
    }
}
