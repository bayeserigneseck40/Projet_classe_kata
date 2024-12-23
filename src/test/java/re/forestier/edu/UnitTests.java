package re.forestier.edu;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Item;
import re.forestier.edu.rpg.PlayerAdventurer;
import re.forestier.edu.rpg.PlayerArcher;
import re.forestier.edu.rpg.PlayerDwarf;
import re.forestier.edu.rpg.PlayerGoblin;
import re.forestier.edu.rpg.player;

public class UnitTests {

    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        PlayerAdventurer player = new PlayerAdventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        PlayerAdventurer p = new PlayerAdventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());

        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }
    @Test
    void testremoveMoney() {
        PlayerAdventurer player = new PlayerAdventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.removeMoney(20);
        assertThat(player.money,is(80));
    }
    @Test
    void testremoveMoneyMut() {
        player player = new PlayerAdventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.removeMoney(100);
        assertThat(player.money,is(0));
    }
    @Test
    void testAddMoney() {
        player player = new PlayerAdventurer("Florian", "Grogit gnak le barbare",  100, new ArrayList<>());
        player.addMoney(20);
        assertThat(player.money,is(120));
    }
 
    @Test
    void testgetXp() {
        player player = new PlayerAdventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        assertThat(player.getXp(),is(0));
    }
    @Test
    void testgetXp2() {
        PlayerAdventurer player = new PlayerAdventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.setXp(30);
        player.majFinDeTour();
        assertThat(player.retrieveLevel(),is(3));
    }
    @Test
    void testgetXp2Mut() {
        PlayerAdventurer player = new PlayerAdventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.setXp(27);
        player.majFinDeTour();
        assertThat(player.retrieveLevel(),is(3));
    }
    @Test
    void testgetXp2Mut1() {
        player player = new PlayerAdventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.setXp(57);
        assertThat(player.retrieveLevel(),is(4));
    }
    @Test
    void testgetXp2Mut2() {
        player player = new PlayerAdventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.setXp(111);
        assertThat(player.retrieveLevel(),is(5));
    }
    @Test
    void testgetXp2Mut3() {
        player player = new PlayerAdventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.setXp(10);
        assertThat(player.retrieveLevel(),is(2));
    }
    @Test
    public void testPlayerKOAdventurer() {
        player player = new PlayerAdventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.currenthealthpoints = 0; 
        player.majFinDeTour();
        assertEquals(0, player.currenthealthpoints);
       
    }
    @Test
    public void testPlayeKODwarf() {
        player player = new PlayerDwarf("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.currenthealthpoints = 0; 
        player.majFinDeTour();
        assertEquals(0, player.currenthealthpoints);
       
    }
    @Test
    public void testPlayeKOArcher() {
        player player = new PlayerArcher("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.currenthealthpoints = 0; 
        player.majFinDeTour();
        assertEquals(0, player.currenthealthpoints);
       
    }
    
    @Test
public void testDwarfWithElixir() {
    PlayerDwarf player = new PlayerDwarf("Florian", "Grognak le barbare", 100, new ArrayList<>());
    player.currenthealthpoints = 4;
    player.healthpoints = 10;
    Item holyElixir = new Item("Holy Elixir", "A sacred elixir that restores health.", 1, 50);
    player.inventory.add(holyElixir);
    player.majFinDeTour();
    assertEquals(6, player.currenthealthpoints);
}

    @Test
    public void testDwarfWithElixirMut() {

        PlayerAdventurer player = new PlayerAdventurer("Florian", "Gnognak le Barbare", 100, new ArrayList<>());
        player.healthpoints = 10;
        player.currenthealthpoints = 4; 
        player.addXp(40);
        player.majFinDeTour(); 
        assertEquals(3, player.retrieveLevel()); 
        assertEquals(6, player.currenthealthpoints); 
    }
    @Test
    public void testAdventurerLowLevel() {
        PlayerAdventurer player = new PlayerAdventurer("Florian", "Gnognak le Barbare", 200, new ArrayList<>());
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
    player.inventory.add(new Item("Magic Bow", "A powerful bow that boosts healing", 4, 150));
    player.majFinDeTour();
    int expectedHealthPoints = 8 + 1 + (8 / 8 - 1);
    assertEquals(expectedHealthPoints, player.currenthealthpoints);
}



    @Test
    public void testMaxHealthCap() {
        PlayerAdventurer player = new PlayerAdventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
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

     @Test
    public void testPlayer_ValidAvatarClass() {
        ArrayList<Item> inventory = new ArrayList<>();
        // inventory.add("Sword");
        PlayerArcher player1 = new PlayerArcher("Florian", "Grognak le barbare", 100, inventory);
        assertEquals("ARCHER", player1.getAvatarClass());
        assertEquals(100, player1.money);
        assertEquals(inventory, player1.inventory);
        PlayerAdventurer player2 = new PlayerAdventurer("Florian", "Grognak le barbare", 100, inventory);
        assertEquals("ADVENTURER", player2.getAvatarClass());
        PlayerDwarf player3 = new PlayerDwarf("Florian", "Grognak le barbare", 100, inventory);
        assertEquals("DWARF", player3.getAvatarClass());
    }

   @Test
public void testMajFinDeTourPlayerGoblin() {
    PlayerGoblin goblin = new PlayerGoblin("Florian", "Grognak le Gobelin", 50, new ArrayList<>());
    goblin.healthpoints = 10; 
    goblin.currenthealthpoints = 4; 
    goblin.inventory.add(new Item("Poisonous Elixir", "An elixir with harmful effects", 5, 20)); // Objet spécifique
    goblin.majFinDeTour();
    int expectedHealthPoints = 5; 
    assertEquals(expectedHealthPoints, goblin.currenthealthpoints);
}
@Test
    public void testAddItemToInventory_Success() {
        
        player player = new PlayerAdventurer("Florian", "Gnognak l'Aventurier", 100, new ArrayList<>());
        player.maxWeight = 10; 
        Item item = new Item("Sword", "A sharp blade", 5, 50);

        boolean result = player.addItemToInventory(item);
        assertTrue(result, "L'ajout de l'objet devrait réussir.");
        assertTrue(player.inventory.contains(item), "L'inventaire devrait contenir l'objet ajouté.");
    }

     @Test
    public void testAddItemToInventory_Failure() {
        player player = new PlayerAdventurer("Florian", "Gnognak l'Aventurier", 100, new ArrayList<>());
        player.maxWeight = 10; 
        Item item1 = new Item("Shield", "A protective shield", 7, 40);
        player.addItemToInventory(item1);
        Item item2 = new Item("Helmet", "A sturdy helmet", 5, 30);
        boolean result = player.addItemToInventory(item2);
        assertFalse(result, "L'ajout de l'objet devrait échouer car le poids maximal est dépassé.");
        assertFalse(player.inventory.contains(item2), "L'inventaire ne devrait pas contenir l'objet non ajouté.");
    }
    @Test
    public void testSellItem_CorrectItemSold() {
        player player = new PlayerAdventurer("Florian", "Gnognak l'Aventurier", 100, new ArrayList<>());
        player.money = 50;
        Item sword = new Item("Sword", "A sharp blade", 5, 100);
        Item shield = new Item("Shield", "A sturdy shield", 10, 150);
        player.inventory.add(sword);
        player.inventory.add(shield);
        boolean result = player.sellItem("Shield");
        assertTrue(result, "La vente de l'objet devrait réussir.");
        assertFalse(player.inventory.contains(shield), "L'objet vendu ne devrait plus être dans l'inventaire.");
        assertTrue(player.inventory.contains(sword), "Les autres objets devraient rester dans l'inventaire.");
        assertEquals(200, player.money, "La balance devrait être augmentée de la valeur de l'objet vendu.");
    }

    @Test
public void testSellItem_Failure_EmptyInventory() {
    player player = new PlayerAdventurer("Florian", "Gnognak l'Aventurier", 100, new ArrayList<>());
    player.money = 50;
    boolean result = player.sellItem("Sword");
    assertFalse(result, "La vente devrait échouer car l'inventaire est vide.");
    assertEquals(50, player.money, "La balance ne doit pas changer si l'inventaire est vide.");
}



}

