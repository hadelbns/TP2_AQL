package org.example;

public class Jeu implements Jeu_Interface {
    private Banque banque;
    private boolean ouvert;

    public Jeu(Banque labanque) {
        this.banque = labanque;
        this.ouvert = true;
    }

    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException {
        if (!estOuvert()) {
            throw new JeuFermeException("Le jeu est fermé.");
        }

        int mise = joueur.mise();
        try {
            joueur.debiter(mise);
        } catch (DebitImpossibleException e) {

            System.out.println("Le joueur n'a pas suffisamment d'argent pour jouer.");

            return;
        }

        int resultat = de1.lancer() + de2.lancer();
        if (resultat == 7) {
            joueur.crediter(mise * 2);
        }

        if (!banque.est_solvable()) {
            fermer();
        }
    }

    public void fermer() {
        ouvert = false;
    }

    public boolean estOuvert() {
        return ouvert;
    }
}
