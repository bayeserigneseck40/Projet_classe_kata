package re.forestier.edu;
import java.util.ArrayList;

import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Item;
import re.forestier.edu.rpg.PlayerAdventurer;
import re.forestier.edu.rpg.PlayerGoblin;

public class Main {
    public static void main(String[] args) {
        PlayerAdventurer firstPlayer = new PlayerAdventurer("Florian", "Ruzberg de Rivehaute",200, new ArrayList<>());
        firstPlayer.addMoney(400);

        firstPlayer.addXp(0xf);
        System.out.println(firstPlayer.afficherJoueur());
        System.out.println("------------------");
        firstPlayer.addXp(20);
        System.out.println(firstPlayer.afficherJoueur());
        System.out.println("------------------");
        System.out.println(Affichage.afficherEnMarkdown(firstPlayer));

        PlayerGoblin player = new PlayerGoblin("Florian", "Gnognak l'Aventurier", 100, new ArrayList<>());
        player.money = 50; 
        Item sword = new Item("Sword", "A sharp blade", 10, 100);
        Item shield = new Item("Shield", "A sturdy shield", 15, 150);
        player.inventory.add(sword);
        player.inventory.add(shield);
        System.out.println("Inventaire initial :");
        player.inventory.forEach(item -> System.out.println("- " + item.getName()));
        System.out.println("Argent initial : " + player.money);
        System.out.println("\nTentative de vendre l'objet 'Sword'...");
        boolean success = player.sellItem("Sword");
        System.out.println("Résultat de la vente : " + (success ? "Succès" : "Échec"));
        System.out.println("Argent après vente : " + player.money);
        System.out.println("\nInventaire après la vente de 'Sword' :");
        player.inventory.forEach(item -> System.out.println("- " + item.getName()));
        System.out.println("\nTentative de vendre l'objet 'Bow'...");
        success = player.sellItem("Bow");
        System.out.println("Résultat de la vente : " + (success ? "Succès" : "Échec"));
        System.out.println("Argent après tentative de vente : " + player.money);
        System.out.println("\nInventaire final :");
        player.inventory.forEach(item -> System.out.println("- " + item.getName()));
    }
}
