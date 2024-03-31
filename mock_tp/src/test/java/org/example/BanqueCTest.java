package org.example;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
public class BanqueCTest {
    @Test
    public void testBanqueDevientInsolvableApresGain() throws DebitImpossibleException, SoldeInsuffisantException {
        // Arrange
        BanqueC banque = new BanqueC(100, 50); // Initialiser la banque avec un fond de 100 et un fond minimum de 50

        Joueur joueur = mock(Joueur.class);
        when(joueur.mise()).thenReturn(30); // Mise du joueur

        // Simuler un gain du joueur
        int gain = 60; // Montant du gain
        joueur.crediter(gain);

        // Act
        try {
            banque.debiter(gain); // Tenter de débiter le gain de la banque
        } catch (SoldeInsuffisantException e) {
            // Assert
            assertEquals("La banque est devenue insolvable.", e.getMessage()); // Vérifier que l'exception indique une insolvabilité de la banque
            assertEquals(40, banque.getFond()); // Vérifier que le fond de la banque est devenu 40 après le débit du gain
        }

        // Vérifier que le joueur a bien été crédité du gain même si la banque est devenue insolvable
        verify(joueur, times(1)).crediter(gain);
    }

}