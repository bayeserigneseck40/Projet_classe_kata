package re.forestier.edu.rpg;

public class Objetclasse {
    private nomObjet nObjet;
    private String Description;
    private int poids;
    private int valeur;

   public enum nomObjet{ 
        LookoutRing,
        ScrollofStupidity,
        Draupnir,
        MagicCharm ,
        RuneStaffofCurse,
        CombatEdge,
        
    }

public Objetclasse(nomObjet nObjet, String description, int poids, int valeur) {
    this.nObjet = nObjet;
    Description = description;
    this.poids = poids;
    this.valeur = valeur;
};
    private void sell(){
        
    }
    
}


