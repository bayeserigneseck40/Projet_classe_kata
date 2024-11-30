package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class player {
    public String playerName;
    public String Avatar_name;
    private String AvatarClass;
    public Integer money;
    protected int level;
    public int healthpoints;
    public int currenthealthpoints;
    protected int xp;
    public int maxWeight = 50;

    public HashMap<String, Integer> abilities;
    public ArrayList<Item> inventory;

    public player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<Item> inventory) {
        this.playerName = playerName;
        this.Avatar_name = avatar_name;
        this.AvatarClass = avatarClass;
        this.money = money;
        this.inventory = inventory;
        this.abilities =  abilitiesPerTypeAndLevel().get(avatarClass).get(1);
    }

    public String getAvatarClass() {
        return AvatarClass;
    }

    public abstract void majFinDeTour(); // Méthode abstraite

    public int retrieveLevel() {
        HashMap<Integer, Integer> levels = new HashMap<>();
        levels.put(2, 10);
        levels.put(3, 27);
        levels.put(4, 57);
        levels.put(5, 111);
        if (xp < levels.get(2)) return 1;
        else if (xp < levels.get(3)) return 2;
        else if (xp < levels.get(4)) return 3;
        else if (xp < levels.get(5)) return 4;
        return 5;
    }

    public void addXp(int xp) {
        int currentLevel = retrieveLevel();
        this.xp += xp;
        int newLevel = retrieveLevel();
        if (newLevel != currentLevel) {
            addRandomObject();
            upgradeAbilities(newLevel);
        }
    }
private void addRandomObject() {
    List<Item> objectList = List.of(
        new Item("Lookout Ring", "Prevents surprise attacks", 1, 100),
        new Item("Scroll of Stupidity", "INT-2 when applied to an enemy", 2, 50),
        new Item("Draupnir", "Increases XP gained by 100%", 3, 200),
        new Item("Magic Charm", "Magic +10 for 5 rounds", 1, 150),
        new Item("Rune Staff of Curse", "May burn your enemies... or yourself.", 5, 120),
        new Item("Combat Edge", "A sharp combat weapon", 4, 80),
        new Item("Holy Elixir", "Recovers your HP", 2, 75)
    );
    Item randomItem = objectList.get((int) (Math.random() * objectList.size()));
    inventory.add(randomItem);
}


    private void upgradeAbilities(int newLevel) {
        HashMap<String, Integer> newAbilities = abilitiesPerTypeAndLevel().get(getAvatarClass()).get(newLevel);
        newAbilities.forEach((ability, level) -> abilities.put(ability, level));
    }

    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }
        money = money - amount;
    }

    public void addMoney(int amount) {
        if (amount > 0) {
            money += amount;
        }
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public static HashMap<String, HashMap<Integer, HashMap<String, Integer>>> abilitiesPerTypeAndLevel() {
        HashMap<String, HashMap<Integer, HashMap<String, Integer>>> abilitiesPerTypeAndLevel = new HashMap<>();
    
        // Capacités pour l'Aventurier
        HashMap<Integer, HashMap<String, Integer>> adventurerMap = new HashMap<>();
        adventurerMap.put(1, new HashMap<>(Map.of("INT", 1, "DEF", 1, "ATK", 3, "CHA", 2)));
        adventurerMap.put(2, new HashMap<>(Map.of("INT", 2, "CHA", 3)));
        adventurerMap.put(3, new HashMap<>(Map.of("ATK", 5, "ALC", 1)));
        adventurerMap.put(4, new HashMap<>(Map.of("DEF", 3)));
        adventurerMap.put(5, new HashMap<>(Map.of("VIS", 1, "DEF", 4)));
        abilitiesPerTypeAndLevel.put("ADVENTURER", adventurerMap);
    
        // Capacités pour l'Archer
        HashMap<Integer, HashMap<String, Integer>> archerMap = new HashMap<>();
        archerMap.put(1, new HashMap<>(Map.of("INT", 1, "ATK", 3, "CHA", 1, "VIS", 3)));
        archerMap.put(2, new HashMap<>(Map.of("DEF", 1, "CHA", 2)));
        archerMap.put(3, new HashMap<>(Map.of("ATK", 3)));
        archerMap.put(4, new HashMap<>(Map.of("DEF", 2)));
        archerMap.put(5, new HashMap<>(Map.of("ATK", 4)));
        abilitiesPerTypeAndLevel.put("ARCHER", archerMap);
    
        // Capacités pour le Nain
        HashMap<Integer, HashMap<String, Integer>> dwarfMap = new HashMap<>();
        dwarfMap.put(1, new HashMap<>(Map.of("ALC", 4, "INT", 1, "ATK", 3)));
        dwarfMap.put(2, new HashMap<>(Map.of("DEF", 1, "ALC", 5)));
        dwarfMap.put(3, new HashMap<>(Map.of("ATK", 4)));
        dwarfMap.put(4, new HashMap<>(Map.of("DEF", 2)));
        dwarfMap.put(5, new HashMap<>(Map.of("CHA", 1)));
        abilitiesPerTypeAndLevel.put("DWARF", dwarfMap);
    
        // Capacités pour le Gobelin
        HashMap<Integer, HashMap<String, Integer>> goblinMap = new HashMap<>();
        goblinMap.put(1, new HashMap<>(Map.of("INT", 2, "ATK", 2, "ALC", 1)));
        goblinMap.put(2, new HashMap<>(Map.of("ATK", 3, "ALC", 4)));
        goblinMap.put(3, new HashMap<>(Map.of("VIS", 1)));
        goblinMap.put(4, new HashMap<>(Map.of("DEF", 1)));
        goblinMap.put(5, new HashMap<>(Map.of("DEF", 2, "ATK", 4)));
        abilitiesPerTypeAndLevel.put("GOBLIN", goblinMap);
    
        return abilitiesPerTypeAndLevel;
    }
    

    public String afficherJoueur() {
        final String[] finalString = {"Joueur " + Avatar_name + " joué par " + playerName};
        finalString[0] += "\nNiveau : " + retrieveLevel() + " (XP totale : " + xp + ")";
        finalString[0] += "\n\nCapacités :";
        abilities.forEach((name, level) -> {
            finalString[0] += "\n   " + name + " : " + level;
        });
        finalString[0] += "\n\nInventaire :";
        inventory.forEach(item -> {
            finalString[0] += "\n   " + item;
        });

        return finalString[0];
    }
    public boolean addItemToInventory(Item item) {
        int currentWeight = inventory.stream().mapToInt(Item::getWeight).sum();
        if (currentWeight + item.getWeight() > maxWeight) {
            return false; // L'ajout échoue si le poids maximal est dépassé
        }
        inventory.add(item);
        return true;
    }

    public boolean sellItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equals(itemName)) {
                inventory.remove(item);
                addMoney(item.getValue());
                return true;
            }
        }
        return false; // Objet non trouvé
    }

}
