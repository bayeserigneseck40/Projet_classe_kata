package re.forestier.edu;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.PlayerAdventurer;
import re.forestier.edu.rpg.PlayerArcher;
import re.forestier.edu.rpg.PlayerDwarf;
import re.forestier.edu.rpg.player;

public class UnitTests {

    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        PlayerAdventurer player = new PlayerAdventurer("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        PlayerAdventurer p = new PlayerAdventurer("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }
    @Test
    void testremoveMoney() {
        PlayerAdventurer player = new PlayerAdventurer("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.removeMoney(20);
        assertThat(player.money,is(80));
    }
    @Test
    void testremoveMoneyMut() {
        player player = new PlayerAdventurer("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.removeMoney(100);
        assertThat(player.money,is(0));
    }
    @Test
    void testAddMoney() {
        player player = new PlayerAdventurer("Florian", "Grogit gnak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.addMoney(20);
        assertThat(player.money,is(120));
    }
 
    @Test
    void testgetXp() {
        player player = new PlayerAdventurer("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.getXp(),is(0));
    }
    @Test
    void testgetXp2() {
        player player = new PlayerAdventurer("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.setXp(30);
        assertThat(player.retrieveLevel(),is(3));
    }
    @Test
    void testgetXp2Mut() {
        player player = new PlayerAdventurer("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.setXp(27);
        assertThat(player.retrieveLevel(),is(3));
    }
    @Test
    void testgetXp2Mut1() {
        player player = new PlayerAdventurer("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.setXp(57);
        assertThat(player.retrieveLevel(),is(4));
    }
    @Test
    void testgetXp2Mut2() {
        player player = new PlayerAdventurer("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.setXp(111);
        assertThat(player.retrieveLevel(),is(5));
    }
    @Test
    void testgetXp2Mut3() {
        player player = new PlayerAdventurer("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.setXp(10);
        assertThat(player.retrieveLevel(),is(2));
    }
    @Test
    public void testPlayerKO() {
        player player = new PlayerAdventurer("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.currenthealthpoints = 0; 
        player.majFinDeTour();
        assertEquals(0, player.currenthealthpoints);
    }
    @Test
    public void testDwarfWithElixir() {

        PlayerDwarf player = new PlayerDwarf("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.healthpoints = 10;
        player.currenthealthpoints = 4; 
        player.inventory.add("Holy Elixir");
        player.majFinDeTour();
        assertEquals(6, player.currenthealthpoints); 
    }
    @Test
    public void testDwarfWithElixirMut() {

        PlayerAdventurer player = new PlayerAdventurer("Florian", "Gnognak le Barbare", "ADVENTURER", 100, new ArrayList<>());
        player.healthpoints = 10;
        player.currenthealthpoints = 4; 
        player.addXp(40);
        player.majFinDeTour(); 
        assertEquals(3, player.retrieveLevel()); 
        assertEquals(6, player.currenthealthpoints); 
    }
    @Test
    public void testAdventurerLowLevel() {
        PlayerAdventurer player = new PlayerAdventurer("Florian", "Gnognak le Barbare", "ADVENTURER", 200, new ArrayList<>());
        player.healthpoints = 10;
        player.currenthealthpoints = 4;
        player.majFinDeTour();
        assertEquals(5, player.currenthealthpoints);
    }
 
 
    @Test
    public void testArcherWithMagicBow() {
        PlayerArcher player = new PlayerArcher("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.healthpoints = 20;
        player.currenthealthpoints = 8;
        player.inventory.add("Magic Bow");
        player.majFinDeTour();
        int expectedHealthPoints = 8 + 1 + (8 / 8 - 1); 
        assertEquals(expectedHealthPoints, player.currenthealthpoints);
    }

    @Test
    public void testMaxHealthCap() {
        PlayerAdventurer player = new PlayerAdventurer("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.healthpoints = 9;
        player.currenthealthpoints = 9; 
        player.majFinDeTour();
        assertEquals(9, player.currenthealthpoints);
    }
        @Test
    public void testAddXpMultipleLevelUp() {
        PlayerArcher player = new PlayerArcher("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.setXp(10);
        int xpToAdd = 100;
        player.addXp(xpToAdd);
        assertEquals(110, player.getXp());
        assertEquals(4, player.retrieveLevel()); 
    }
   
    @Test
    public void testAddXpMultipleLevelUp2() {
        PlayerArcher player = new PlayerArcher("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.setXp(20);  
        int xpToAdd = 100;   
        player.addXp(xpToAdd);
        assertNotEquals(4, player.retrieveLevel());
    }
    // @Test
    // public void testAddXpMultipleLevelUp3() {
    //     PlayerArcher player = new PlayerArcher("Florian", "Grognak le barbare", 100, new ArrayList<>());
    //     player.setXp(20);  
    //     int xpToAdd = 0; 
    //    assertThat(player.addXp(xpToAdd),is(false));
    // }
     @Test
    public void testPlayer_ValidAvatarClass() {
        ArrayList<String> inventory = new ArrayList<>();
        inventory.add("Sword");
        PlayerArcher player1 = new PlayerArcher("Florian", "Grognak le barbare", 100, inventory);
        assertEquals("ARCHER", player1.getAvatarClass());
        assertEquals(100, player1.money);
        assertEquals(inventory, player1.inventory);
        PlayerAdventurer player2 = new PlayerAdventurer("Florian", "Grognak le barbare", "ADVENTURER", 100, inventory);
        assertEquals("ADVENTURER", player2.getAvatarClass());
        PlayerDwarf player3 = new PlayerDwarf("Florian", "Grognak le barbare", 100, inventory);
        assertEquals("DWARF", player3.getAvatarClass());
    }

    // @Test
    // public void testPlayer_InvalidAvatarClass() {
    //     ArrayList<String> inventory = new ArrayList<>();
    //     inventory.add("Shield");
    //    player playerInvalid = new player("Florian", "Grognak le barbare", "MAGE", 100,inventory);
    //     assertNull(playerInvalid.getAvatarClass());
    //     assertNull(playerInvalid.inventory);
    //     assertNull(playerInvalid.money);
    // }

   
}
