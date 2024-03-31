package org.example;

public class BanqueC  implements Banque{
    private int fond;
    private final int fondMinimum;

    public BanqueC(int fondInitial, int fondMinimum) {
        this.fond = fondInitial;
        this.fondMinimum = fondMinimum;
    }

    public void crediter(int somme) {
        fond += somme;
    }

    public void debiter(int somme) throws SoldeInsuffisantException {
        if (fond - somme < fondMinimum) {
            fond -= somme;
            throw new SoldeInsuffisantException("La banque est devenue insolvable.");
        }
        fond -= somme;
    }

    public boolean est_solvable() {
        return fond >= fondMinimum;
    }

    public int getFond() {
        return fond;
    }

    public int getFondMinimum() {
        return fondMinimum;
    }
}
