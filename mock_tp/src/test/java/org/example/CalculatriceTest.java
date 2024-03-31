package org.example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class CalculatriceTest {
    @Mock
    private Calculatrice calculatrice;
    @Test
    public void testAdditionner() {

        when(calculatrice.additionner(2, 3)).thenReturn(5);
        when(calculatrice.additionner(1, 1)).thenReturn(2);
        //TODO : Appel de la méthode à tester
        int resultat = calculatrice.additionner(2, 3);
        int resultat2 = calculatrice.additionner(1, 1);

        //TODO : Vérification du résultat
        assertEquals(5, resultat);

//TODO : Vérification que la méthode "additionner" a été appelée
// avec les arguments 2 et 3, utiliser la directive « verify »
        //dans ce cas il y une exception car on appeléé la methode deux fois
        verify(calculatrice).additionner(2, 3);
//TODO : Vérification qu'aucune autre méthode n'a été appelée sur


        verifyNoMoreInteractions(calculatrice);
// TODO : Vérification de l'état de l'objet après l'appel de la
        //a verfier avec kechaoui
//méthode "additionner", penser à utiliser la méthode
//« getState() » de la directive « verify » : // exemple :
        // verify(objet).getState()
    }
}