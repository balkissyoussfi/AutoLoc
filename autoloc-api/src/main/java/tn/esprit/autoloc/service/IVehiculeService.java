package tn.esprit.autoloc.service;

import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.domain.Agence;

public interface IVehiculeService extends IGenericService<Vehicule, Long> {
    double calculTarif(Vehicule vehicule, int nbJours, VehiculeOffre offre);
    void desaffecter(Long idVehicule);
    void affecterAgence(Long idVehicule, Long idAgence);
    void affecterEquipement(Long idVehicule, Long idEquipement);
}