package org.example;

public class UserService  {
    private final UtilisateurApi utilisateurApi;
    public UserService(UtilisateurApi utilisateurApi) {
        this.utilisateurApi = utilisateurApi;
    }

    public boolean creerUtilisateur(Utilisateur utilisateur) throws ServiceException {
        if (utilisateurApi.creerUtilisateur(utilisateur)) {
            return true; // Retourne true si l'utilisateur est créé avec succès
        } else {
            return false; // Retourne false si l'utilisateur n'est pas créé avec succès
        }
    }

    public int getIdUtilisateur() {
        return utilisateurApi.getIdUtilisateur(); // Renvoie l'ID de l'utilisateur créé
    }

    }

