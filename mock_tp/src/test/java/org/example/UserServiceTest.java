package org.example;
import org.example.UserService;
import org.example.Utilisateur;
import org.example.UtilisateurApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UtilisateurApi utilisateurApiMock;
//*******************************************EXO2**************************************
    @Test
    public void testCreerUtilisateur() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Benseid", "hadil", "benseidhadil3@email.com");

       /* Configuration du comportement du mock UtilisateurApi pour la méthode creerUtilisateur
 Comme la méthode creerUtilisateur est void, nous utilisons doNothing() pour indiquer que rien ne doit être fait lors de son appel
doNothing().when(utilisateurApiMock).creerUtilisateur(utilisateur); dans l'exercice 3 j'ai changé la valeur de return
*/
        when(utilisateurApiMock.creerUtilisateur(utilisateur)).thenReturn(true);


        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);


        // Vérification de l'appel à l'API
        verify(utilisateurApiMock).creerUtilisateur(utilisateur);
        // Vérification qu'aucune autre méthode n'a été appelée sur utilisateurApiMock après l'appel de creerUtilisateur
        verifyNoMoreInteractions(utilisateurApiMock);
    }

    //*******************************EXO3**********************************************

   /*L'annotation @Test(expected = ServiceException.class) spécifie
   que le test doit réussir si une exception de type ServiceException
   est levée pendant l'exécution du test. Donc, si la méthode creerUtilisateur
   lève effectivement une ServiceException , le test réussira.*/
    @Test (expected = ServiceException.class)
    public void testCreerUtilisateur_Exception() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("nana", "Belhocine", "nanapotato@email.com");

        /*Configuration du comportement du mock UtilisateurApi pour lancer une exception lors de l'appel à creerUtilisateur
         on peut pas utiliser thenThrow ici car la methode creeutilisateur void don on utilise doThrow ,
         Mockito.when(utilisateurApiMock.creerUtilisateur(utilisateur)).thenThrow(new ServiceException("Echec de la création de l'utilisateur"));*/
       // doThrow(new ServiceException("Echec de la création de l'utilisateur")).when(utilisateurApiMock).creerUtilisateur(utilisateur);
        when(utilisateurApiMock.creerUtilisateur(utilisateur)).thenThrow(new ServiceException("Echec de la création de l'utilisateur"));


        UserService userService = new UserService(utilisateurApiMock);
        userService.creerUtilisateur(utilisateur);
    }



    @Test
    public void testCreerUtilisateur_ErreurValidation() throws ServiceException {
        // Création d'un utilisateur fictif pour les besoins du test
        Utilisateur utilisateur = new Utilisateur("nana", "anan", "pont@email.com");
        Utilisateur utilisateur2 = new Utilisateur("kasty", "kasty", "ka@email.com");
        // Configuration du mock pour simuler un échec de validation (lever une exception) seulement pour utilisateur
        when(utilisateurApiMock.creerUtilisateur(utilisateur)).thenReturn(false);
        when(utilisateurApiMock.creerUtilisateur(utilisateur2)).thenReturn(false);
        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);
       // userService.creerUtilisateur(utilisateur2);
        // Vérification que la méthode creerUtilisateur est appelée une seule fois avec utilisateur
        verify(utilisateurApiMock, times(1)).creerUtilisateur(utilisateur);

        // Vérification que la méthode creerUtilisateur n'a jamais été appelée par utilisateur2
        verify(utilisateurApiMock, never()).creerUtilisateur(utilisateur2);

    }


    @Test
    public void testCreerUtilisateurAvecID() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur("nana", "anan", "pont@email.com");
        // Définition d'un ID fictif
        int idUtilisateur = 123;

        // Configuration du mock pour retourner true lors de l'appel de la méthode creerUtilisateur
        when(utilisateurApiMock.creerUtilisateur(utilisateur)).thenReturn(true);

        // Configuration du mock pour renvoyer l'ID fictif lors de l'appel de la méthode getIdUtilisateur
        when(utilisateurApiMock.getIdUtilisateur()).thenReturn(idUtilisateur);
        UserService userService = new UserService(utilisateurApiMock);
        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);

        // Vérification de l'ID de l'utilisateur
        assertEquals(idUtilisateur, userService.getIdUtilisateur());
    }
    @Test // mli7
    public void testCaptureArguments_creerUtilisateur() throws ServiceException {
        // Création d'un captor pour capturer les arguments de type Utilisateur
        ArgumentCaptor<Utilisateur> argumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);
        Utilisateur utilisateur = new Utilisateur("nana", "anan", "pont@email.c");
                // Configuration du mock pour retourner true lors de l'appel de la méthode creerUtilisateur
        when(utilisateurApiMock.creerUtilisateur(any(Utilisateur.class))).thenReturn(true);
        UserService userService = new UserService(utilisateurApiMock);
        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);

        // Capturer les arguments passés à la méthode creerUtilisateur
        verify(utilisateurApiMock).creerUtilisateur(argumentCaptor.capture());

        // Récupérer l'Utilisateur capturé
        Utilisateur utilisateurCapture = argumentCaptor.getValue();

        // Maintenant, vous pouvez vérifier les attributs de l'utilisateur capturé en utilisant ses getters
        // Par exemple, si vous voulez vérifier le prénom de l'utilisateur capturé
        assertEquals("anan", utilisateurCapture.getPrenom());
        // Et ainsi de suite pour d'autres attributs que vous souhaitez vérifier
    }


}




