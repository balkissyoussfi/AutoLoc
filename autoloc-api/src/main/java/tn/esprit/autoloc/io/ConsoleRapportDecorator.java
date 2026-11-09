package tn.esprit.autoloc.io;

import tn.esprit.autoloc.domain.Agence;

public class ConsoleRapportDecorator implements IRapport {
    private final IRapport rapport;

    public ConsoleRapportDecorator(IRapport rapport) {
        this.rapport = rapport;
    }

    @Override
    public String entete(Agence a) {
        String entete = rapport.entete(a);
        System.out.println(entete);
        return entete;
    }
}
