package org.example;

public interface Banque {
    void crediter(int somme);
    void debiter(int somme) throws SoldeInsuffisantException;
    boolean est_solvable();
}
