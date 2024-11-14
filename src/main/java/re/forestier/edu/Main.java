package re.forestier.edu;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.player;

import java.util.ArrayList;

import re.forestier.edu.rpg.PlayerDwarf;

public class Main {
    public static void main(String[] args) {
        PlayerDwarf firstPlayer = new PlayerDwarf("Florian", "Ruzberg de Rivehaute", 200, new ArrayList<>());
        firstPlayer.addMoney(400);

        firstPlayer.addXp(0xf);
        System.out.println(Affichage.afficherJoueur(firstPlayer));
        System.out.println("------------------");
        firstPlayer.addXp(20);
        System.out.println(Affichage.afficherJoueur(firstPlayer));
    }
}