package re.forestier.edu;


import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;

import static re.forestier.edu.rpg.Affichage.afficherEnMarkdown;
import re.forestier.edu.rpg.Item;
import re.forestier.edu.rpg.PlayerAdventurer;
import re.forestier.edu.rpg.player;


public class GlobalTest {

    @Test
    void testAffichageBase() {
        PlayerAdventurer player = new PlayerAdventurer("Florian", "Gnognak le Barbare", 200, new ArrayList<>());
       player.addXp(20);
        player.inventory = new ArrayList<>();
       // verify(Affichage.afficherJoueur(player));
    }

    // @Test
    // void testAffichage() {
    //     PlayerAdventurer player = new PlayerAdventurer("Florian", "Gnognak le Barbare",  200, new ArrayList<>());
    //     player.addXp(20);
    //     player.inventory = new ArrayList<>();
    //     //player.inventory.add("Sword");
    //     String resultat=player.afficherJoueur();
    //     assertThat((resultat),containsString("Sword"));
    // }

    @Test
void testAffichage() {
    PlayerAdventurer player = new PlayerAdventurer("Florian", "Gnognak le Barbare", 200, new ArrayList<>());
    player.addXp(20);
    Item sword = new Item("Sword", "A sharp blade", 2, 100);
    player.inventory.add(sword);
    String resultat = player.afficherJoueur();
    assertThat(resultat, containsString("Sword"));
}
@Test
void testafficherEnMarkdown() {
    player player = new PlayerAdventurer("Florian", "Gnognak le Barbare", 200, new ArrayList<>());
    player.addXp(20);
    Item sword = new Item("Sword", "A sharp blade", 2, 100);
    player.inventory.add(sword);
    String resultat =afficherEnMarkdown(player);
    assertThat(resultat, containsString("Sword"));
}

}
