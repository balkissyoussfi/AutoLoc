package tn.esprit.autoloc.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import tn.esprit.autoloc.service.IEquipementService;
import tn.esprit.autoloc.domain.Equipement;

@RestController
@RequestMapping("equipements")
@RequiredArgsConstructor
@Tag(name = "Equipements", description = "Gestion des équipements")
public class EquipementController {

    private final IEquipementService equipementService;

    @GetMapping
    public List<Equipement> findAll() { return equipementService.findAll(); }

    @GetMapping("{id}")
    public Equipement findById(@PathVariable Long id) { return equipementService.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipement create(@RequestBody Equipement equipement) { return equipementService.create(equipement); }

    @PutMapping("{id}")
    public Equipement update(@PathVariable Long id, @RequestBody Equipement equipement) { return equipementService.update(id, equipement); }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) { equipementService.deleteById(id); }
}
