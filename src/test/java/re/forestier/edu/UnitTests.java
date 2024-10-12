package re.forestier.edu;

import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class UnitTests {

    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }
    @Test
    void testremoveMoney() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.removeMoney(20);
        assertThat(player.money,is(80));
    }
    @Test
    void testAddMoney() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.addMoney(20);
        assertThat(player.money,is(120));
    }
 
    @Test
    void testgetXp() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.getXp(),is(0));
    }
    @Test
    void testgetXp2() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.setXp(30);
        assertThat(player.retrieveLevel(),is(3));
    }
    @Test
    public void testPlayerKO() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.currenthealthpoints = 0; 
        UpdatePlayer.majFinDeTour(player);
        assertEquals(0, player.currenthealthpoints);
    }
    @Test
    public void testDwarfWithElixir() {

        player player = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        player.healthpoints = 10;
        player.currenthealthpoints = 4; 
        player.inventory.add("Holy Elixir");
        UpdatePlayer.majFinDeTour(player);
        assertEquals(6, player.currenthealthpoints); 
    }
    @Test
    public void testAdventurerLowLevel() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.healthpoints = 10;
        player.currenthealthpoints = 4;
        UpdatePlayer.majFinDeTour(player);
        assertEquals(5, player.currenthealthpoints);
    }
 
 
    @Test
    public void testArcherWithMagicBow() {
        player player = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        player.healthpoints = 20;
        player.currenthealthpoints = 8;
        player.inventory.add("Magic Bow");
        UpdatePlayer.majFinDeTour(player);
        int expectedHealthPoints = 8 + 1 + (8 / 8 - 1); 
        assertEquals(expectedHealthPoints, player.currenthealthpoints);
    }

    @Test
    public void testMaxHealthCap() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.healthpoints = 9;
        player.currenthealthpoints = 9; 
        UpdatePlayer.majFinDeTour(player);
        assertEquals(9, player.currenthealthpoints);
    }
        @Test
    public void testAddXpMultipleLevelUp() {
        player player = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        player.setXp(10);
        int xpToAdd = 100;
        boolean leveledUp = UpdatePlayer.addXp(player, xpToAdd);
        assertTrue(leveledUp); 
        assertEquals(110, player.getXp());
        assertEquals(4, player.retrieveLevel()); 
    }
    @Test
    public void testAddXpMultipleLevelUp2() {
        player player = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        player.setXp(20);  
        int xpToAdd = 100;   
        boolean leveledUp = UpdatePlayer.addXp(player, xpToAdd);
        assertTrue(leveledUp); 
        assertNotEquals(4, player.retrieveLevel());
    }
    @Test
    public void testAddXpMultipleLevelUp3() {
        player player = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        player.setXp(20);  
        int xpToAdd = 0; 
       assertThat(UpdatePlayer.addXp(player, xpToAdd),is(false));
    }
     @Test
    public void testPlayer_ValidAvatarClass() {
        ArrayList<String> inventory = new ArrayList<>();
        inventory.add("Sword");
        player player1 = new player("Florian", "Grognak le barbare", "ARCHER", 100, inventory);
        assertEquals("ARCHER", player1.getAvatarClass());
        assertEquals(100, player1.money);
        assertEquals(inventory, player1.inventory);
        player player2 = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, inventory);
        assertEquals("ADVENTURER", player2.getAvatarClass());
        player player3 = new player("Florian", "Grognak le barbare", "DWARF", 100, inventory);
        assertEquals("DWARF", player3.getAvatarClass());
    }

    @Test
    public void testPlayer_InvalidAvatarClass() {
        ArrayList<String> inventory = new ArrayList<>();
        inventory.add("Shield");
       player playerInvalid = new player("Florian", "Grognak le barbare", "MAGE", 100,inventory);
        assertNull(playerInvalid.getAvatarClass());
        assertNull(playerInvalid.inventory);
        assertNull(playerInvalid.money);
    }

    @Test
    public void testAfficherJoueur() {
        player player = new player("Florian", "Gnognak le Barbare", "ADVENTURER", 200, new ArrayList<>());
        UpdatePlayer.addXp(player, 20);
        player.inventory = new ArrayList<>();
        player.inventory.add("Sword");
        player.inventory.add("Shield");

        // Appeler la méthode afficherJoueur
        String expected = "Joueur Gnognak le Barbare joué par Florian\n" +
                          "Niveau : 2 (XP totale : 20)\n\n" +
                          "Capacités :\n" +
                          "   DEF : 1\n" +
                          "   ATK : 3\n" +
                          "   CHA : 3\n" +
                          "   INT : 2\n\n" +
                          "Inventaire :\n" +
                          "   Sword\n" +
                          "   Shield";
        
                          String actual = Affichage.afficherJoueur(player);
                          // Vérifier que la sortie correspond à ce qui est attendu
                          assertThat(expected, is(actual));
    }
}
