package re.forestier.edu.rpg;

public class Affichage {

    public static String afficherEnMarkdown(player player) {
        StringBuilder markdown = new StringBuilder();
        markdown.append("# Joueur ").append(player.Avatar_name)
                .append(" joué par **").append(player.playerName).append("**\n");
        markdown.append("## Niveau : ").append(player.retrieveLevel())
                .append(" (XP totale : ").append(player.getXp()).append(")\n\n");
        
        markdown.append("## Capacités :\n");
        player.abilities.forEach((name, level) -> 
            markdown.append("* **").append(name).append("** : ").append(level).append("\n")
        );
        
        markdown.append("\n## Inventaire :\n");
        player.inventory.forEach(item -> 
            markdown.append("* ").append(item.getName()).append(" (")
                    .append(item.getWeight()).append(" kg, ")
                    .append(item.getValue()).append(" pièces)\n")
        );

        return markdown.toString();
    }
}
