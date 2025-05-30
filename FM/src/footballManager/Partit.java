package footballManager;


public class Partit {
    // equipo visitant, equipo local
    private Equip equipLocal;
    private Equip equipVisitant;

    private int golsLocal;
    private int golsVisitant;

    public Partit(Equip equipLocal, Equip equipVisitant) {
        this.equipLocal = equipLocal;
        this.equipVisitant = equipVisitant;
        this.golsLocal = 0;
        this.golsVisitant = 0;
    }

    public void jugarPartido(int golsLocal, int golsVisitant) {
        this.golsLocal = golsLocal;
        this.golsVisitant = golsVisitant;
    }


//
    public Equip getEquipLocal() {
        return this.equipLocal;
    }

    public void setEquipLocal(Equip equipLocal) {
        this.equipLocal = equipLocal;
    }

    public Equip getEquipVisitant() {
        return this.equipVisitant;
    }

    public void setEquipVisitant(Equip equipVisitant) {
        this.equipVisitant = equipVisitant;
    }

    public int getGolsLocal() {
        return this.golsLocal;
    }

    public void setGolsLocal(int golsLocal) {
        this.golsLocal = golsLocal;
    }

    public int getGolsVisitant() {
        return this.golsVisitant;
    }

    public void setGolsVisitant(int golsVisitant) {
        this.golsVisitant = golsVisitant;
    }


}
