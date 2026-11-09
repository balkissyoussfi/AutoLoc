package tn.esprit.autoloc.io;

import tn.esprit.autoloc.domain.Agence;

@Console
public class RapportTexte implements IRapport {
    @Override
    public String entete(Agence a) {
        return "Id : " + a.getIdAgence() + ", Nom : " + a.getNom() + ", Tel : " + a.getTelephone();
    }
}
