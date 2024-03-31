package org.example;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class JeuTest {
    //**********************************exo4***********************
    @Test(expected = JeuFermeException.class)
    public void testJouerWhenGameIsClosed() throws JeuFermeException {
        // Arrange
        Banque mockBanque = mock(Banque.class);
        when(mockBanque.est_solvable()).thenReturn(true); // Suppose que la banque est solvable
        Joueur mockJoueur = mock(Joueur.class);
        when(mockJoueur.mise()).thenReturn(10); // Mise du joueur
        De mockDe1 = mock(De.class);
        when(mockDe1.lancer()).thenReturn(1); // Résultat du premier dé
        De mockDe2 = mock(De.class);
        when(mockDe2.lancer()).thenReturn(4); // Résultat du deuxième dé

        Jeu jeu = new Jeu(mockBanque);
        jeu.fermer(); // Fermer le jeu

        // Act
        jeu.jouer(mockJoueur, mockDe1, mockDe2); // Tenter de jouer quand le jeu est fermé
    }
    //*******************************exo5*********************************
    @Test // l'exception est capturer dans le code de la classe  jeu
    public void testJouerWhenPlayerIsInsolvent() throws DebitImpossibleException, JeuFermeException {
        // Arrange
        Banque mockBanque = mock(Banque.class);
        when(mockBanque.est_solvable()).thenReturn(true); // Suppose que la banque est solvable

        Joueur mockJoueur = mock(Joueur.class);
        when(mockJoueur.mise()).thenReturn(10); // Mise du joueur

        // Simulation d'un joueur insolvable
        doThrow(new DebitImpossibleException()).when(mockJoueur).debiter(anyInt());

        De mockDe1 = mock(De.class);
        De mockDe2 = mock(De.class);

        Jeu jeu = new Jeu(mockBanque);

        // Act
        jeu.jouer(mockJoueur, mockDe1, mockDe2); // Tenter de jouer avec un joueur insolvable

        // Assert
        verify(mockDe1, never()).lancer(); // Vérifier que la méthode lancer() n'est jamais appelée sur le premier dé
        verify(mockDe2, never()).lancer(); // Vérifier que la méthode lancer() n'est jamais appelée sur le deuxième dé
    }

    //****************************************exo6************************************
    @Test
    public void testPlayerWins() throws DebitImpossibleException, JeuFermeException {
        // Arrange
        Banque mockBanque = mock(Banque.class);
        when(mockBanque.est_solvable()).thenReturn(true); // Suppose que la banque est solvable

        Joueur mockJoueur = mock(Joueur.class);
        when(mockJoueur.mise()).thenReturn(10); // Mise du joueur

        De mockDe1 = mock(De.class);
        when(mockDe1.lancer()).thenReturn(3); // Résultat du premier dé
        De mockDe2 = mock(De.class);
        when(mockDe2.lancer()).thenReturn(4); // Résultat du deuxième dé

        Jeu jeu = new Jeu(mockBanque);

        // Act
        jeu.jouer(mockJoueur, mockDe1, mockDe2); // Tenter de jouer avec une somme des dés égale à 7

        // Assert
        verify(mockJoueur, times(1)).crediter(20); // Vérifier que le joueur est crédité du double de sa mise (10 * 2)
    }

    @Test
    public void testPlayerLoses() throws DebitImpossibleException, JeuFermeException {
        // Arrange
        Banque mockBanque = mock(Banque.class);
        when(mockBanque.est_solvable()).thenReturn(true); // Suppose que la banque est solvable

        Joueur mockJoueur = mock(Joueur.class);
        when(mockJoueur.mise()).thenReturn(10); // Mise du joueur

        De mockDe1 = mock(De.class);
        when(mockDe1.lancer()).thenReturn(2); // Résultat du premier dé
        De mockDe2 = mock(De.class);
        when(mockDe2.lancer()).thenReturn(3); // Résultat du deuxième dé

        Jeu jeu = new Jeu(mockBanque);

        // Act
        jeu.jouer(mockJoueur, mockDe1, mockDe2); // Tenter de jouer avec une somme des dés différente de 7

        // Assert
        verify(mockJoueur, times(1)).debiter(10); // Vérifier que le joueur est débité de sa mise
    }
}
