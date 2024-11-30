package re.forestier.edu;


import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.PlayerAdventurer;


public class GlobalTest {

    @Test
    void testAffichageBase() {
        PlayerAdventurer player = new PlayerAdventurer("Florian", "Gnognak le Barbare", "ADVENTURER", 200, new ArrayList<>());
       player.addXp(20);
        player.inventory = new ArrayList<>();
       // verify(Affichage.afficherJoueur(player));
    }

    @Test
    void testAffichage() {
        PlayerAdventurer player = new PlayerAdventurer("Florian", "Gnognak le Barbare", "ADVENTURER", 200, new ArrayList<>());
        player.addXp(20);
        player.inventory = new ArrayList<>();
        //player.inventory.add("Sword");
        String resultat=player.afficherJoueur();
        assertThat((resultat),containsString("Sword"));
    }

    
}
