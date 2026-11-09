package tn.esprit.autoloc.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import tn.esprit.autoloc.service.IPaiementService;
import tn.esprit.autoloc.domain.Paiement;

@RestController
@RequestMapping("paiements")
@RequiredArgsConstructor
@Tag(name = "Paiements", description = "Consultation des paiements")
public class PaiementController {

    private final IPaiementService paiementService;

    @GetMapping
    public List<Paiement> findAll() { return paiementService.findAll(); }

    @GetMapping("{id}")
    public Paiement findById(@PathVariable Long id) { return paiementService.findById(id); }
}
