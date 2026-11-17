package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

public interface IAgenceService extends IBasicAgenceService {
    Agence updateAvecEmployes(Long id, Agence agence);
}