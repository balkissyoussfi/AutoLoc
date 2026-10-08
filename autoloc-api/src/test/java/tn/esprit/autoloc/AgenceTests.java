package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.domain.*;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class AgenceTests {

    @Autowired
    private AgenceRepositoryMock basicAgenceRepository;

    @Autowired
    private IAgenceRepository fullAgenceRepository;

    // ---------- AJOUT ----------

    @Test
    void basicAddAgence() {
        addAgence(basicAgenceRepository);
    }

    @Test
    void fullAddAgence() {
        addAgence(fullAgenceRepository);
    }

    private void addAgence(CrudRepository<Agence, Long> repository) {
        // Valeur différente à chaque exécution pour éviter les doublons d'immatriculation
        int suffixe = Math.abs((int) System.currentTimeMillis());

        Agence agence = new Agence();
        agence.setNom("Agence ariana");
        agence.setAdresse("1 Rue Hedi");
        agence.setTelephone("71585874");
        agence.setVille("Tunis");

        Vehicule v1 = new Vehicule();
        v1.setCategorie(CategorieVehicule.SUV);
        v1.setImmatriculation("785414TU96" + suffixe);
        v1.setMarque("Isuzu");
        v1.setModele("DMax");
        v1.setStatut(StatutVehicule.MAINTENANCE);
        v1.setTarifJournalier(new BigDecimal("100"));
        v1.setAgence(agence);

        Vehicule v2 = new Vehicule();
        v2.setCategorie(CategorieVehicule.UTILITAIRE);
        v2.setImmatriculation("785414TU95" + suffixe);
        v2.setMarque("Toyota");
        v2.setModele("Yaris");
        v2.setStatut(StatutVehicule.DISPONIBLE);
        v2.setTarifJournalier(new BigDecimal("80"));
        v2.setAgence(agence);

        Set<Vehicule> vehicules = new HashSet<>();
        vehicules.add(v1);
        vehicules.add(v2);
        agence.setVehicules(vehicules);

        repository.save(agence);
    }

    // ---------- CHARGEMENT ----------

    @Test
    void basicLoadAgence() {
        loadAgence(basicAgenceRepository, "Basic (CrudRepository)");
    }

    @Test
    void fullLoadAgence() {
        loadAgence(fullAgenceRepository, "Full (JpaRepository)");
    }

    private void loadAgence(CrudRepository<Agence, Long> repository, String typeDepot) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nType de dépôt : ").append(typeDepot);
        for (Agence a : repository.findAll()) {
            sb.append("\n").append(a.getIdAgence()).append(" | ").append(a.getNom())
                    .append("\nVehicules Count : ").append(a.getVehicules().size());
            for (Vehicule v : a.getVehicules())
                sb.append("\n=== ").append(v.getIdVehicule()).append("|").append(v.getImmatriculation());
        }
        org.junit.jupiter.api.Assertions.fail(sb.toString());
    }

    @Test
    void loadSortedAgences() {
        Sort sort = Sort.by(Sort.Direction.DESC, "idAgence");
        List<Agence> agences = fullAgenceRepository.findAll(sort);

        StringBuilder sb = new StringBuilder();
        sb.append("\nAgences triées par id décroissant :");
        for (Agence a : agences) {
            sb.append("\n").append(a.getIdAgence()).append(" | ").append(a.getNom())
                    .append(" | ").append(a.getVille()).append(" | ").append(a.getAdresse());
        }
        org.junit.jupiter.api.Assertions.fail(sb.toString());
    }

    @Test
    void loadPagedAgences() {
        Sort sort = Sort.by(Sort.Direction.DESC, "idAgence");
        Pageable pageable = PageRequest.of(0, 2, sort);   // page 0, lot de 2
        Page<Agence> page = fullAgenceRepository.findAll(pageable);


        StringBuilder sb = new StringBuilder();
        sb.append("\nNombre total de pages : ").append(page.getTotalPages());
        sb.append("\nPage en cours : ").append(page.getNumber() + 1)
                .append(" / ").append(page.getTotalPages());
        sb.append("\nAgences de cette page :");
        for (Agence a : page.getContent()) {
            sb.append("\n").append(a.getIdAgence()).append(" | ").append(a.getNom())
                    .append(" | ").append(a.getVille()).append(" | ").append(a.getAdresse());
        }
        org.junit.jupiter.api.Assertions.fail(sb.toString());
    }
}

interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}