package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class player {
    public String playerName;
    public String Avatar_name;
    private String AvatarClass;
    public Integer money;
    protected int level;
    protected int healthpoints;
    protected int currenthealthpoints;
    protected int xp;

    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;

    public player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<String> inventory) {
        this.playerName = playerName;
        this.Avatar_name = avatar_name;
        this.AvatarClass = avatarClass;
        this.money = money;
        this.inventory = inventory;
        this.abilities = abilitiesPerTypeAndLevel().get(avatarClass).get(1);
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
        String[] objectList = {"Lookout Ring", "Scroll of Stupidity", "Draupnir", "Magic Charm", "Rune Staff of Curse", "Combat Edge", "Holy Elixir"};
        inventory.add(objectList[(int) (Math.random() * objectList.length)]);
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

        HashMap<Integer, HashMap<String, Integer>> adventurerMap = new HashMap<>();
        adventurerMap.put(1, new HashMap<>(Map.of("INT", 1, "DEF", 1, "ATK", 3, "CHA", 2)));
        adventurerMap.put(2, new HashMap<>(Map.of("INT", 2, "CHA", 3)));
        adventurerMap.put(3, new HashMap<>(Map.of("ATK", 5, "ALC", 1)));
        adventurerMap.put(4, new HashMap<>(Map.of("DEF", 3)));
        adventurerMap.put(5, new HashMap<>(Map.of("VIS", 1, "DEF", 4)));

        HashMap<Integer, HashMap<String, Integer>> archerMap = new HashMap<>();
        archerMap.put(1, new HashMap<>(Map.of("INT", 1, "ATK", 3, "CHA", 1, "VIS", 3)));
        archerMap.put(2, new HashMap<>(Map.of("DEF", 1, "CHA", 2)));
        archerMap.put(3, new HashMap<>(Map.of("ATK", 3)));
        archerMap.put(4, new HashMap<>(Map.of("DEF", 2)));
        archerMap.put(5, new HashMap<>(Map.of("ATK", 4)));

        HashMap<Integer, HashMap<String, Integer>> dwarfMap = new HashMap<>();
        dwarfMap.put(1, new HashMap<>(Map.of("ALC", 4, "INT", 1, "ATK", 3)));
        dwarfMap.put(2, new HashMap<>(Map.of("DEF", 1, "ALC", 5)));
        dwarfMap.put(3, new HashMap<>(Map.of("ATK", 4)));
        dwarfMap.put(4, new HashMap<>(Map.of("DEF", 2)));
        dwarfMap.put(5, new HashMap<>(Map.of("CHA", 1)));

        abilitiesPerTypeAndLevel.put("ADVENTURER", adventurerMap);
        abilitiesPerTypeAndLevel.put("ARCHER", archerMap);
        abilitiesPerTypeAndLevel.put("DWARF", dwarfMap);

        return abilitiesPerTypeAndLevel;
    }
}
