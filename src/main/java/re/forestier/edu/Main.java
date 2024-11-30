package re.forestier.edu;
import java.util.ArrayList;

import re.forestier.edu.rpg.PlayerAdventurer;

public class Main {
    public static void main(String[] args) {
        PlayerAdventurer firstPlayer = new PlayerAdventurer("Florian", "Ruzberg de Rivehaute",200, new ArrayList<>());
        firstPlayer.addMoney(400);

        firstPlayer.addXp(0xf);
        System.out.println(firstPlayer.afficherJoueur());
        System.out.println("------------------");
        firstPlayer.addXp(20);
        System.out.println(firstPlayer.afficherJoueur());
    }
}