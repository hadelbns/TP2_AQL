package org.example;

public interface Jeu_Interface {




    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException;


    public void fermer();


    public boolean estOuvert();
}

