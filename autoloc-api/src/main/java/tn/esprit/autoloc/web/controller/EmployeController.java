package tn.esprit.autoloc.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import tn.esprit.autoloc.service.IEmployeService;
import tn.esprit.autoloc.domain.Employe;

@RestController
@RequestMapping("employes")
@RequiredArgsConstructor
@Tag(name = "Employes", description = "Gestion des employés")
public class EmployeController {

    private final IEmployeService employeService;

    @GetMapping
    public List<Employe> findAll() { return employeService.findAll(); }

    @GetMapping("{id}")
    public Employe findById(@PathVariable Long id) { return employeService.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employe create(@RequestBody Employe employe) { return employeService.create(employe); }

    @PutMapping("{id}")
    public Employe update(@PathVariable Long id, @RequestBody Employe employe) { return employeService.update(id, employe); }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) { employeService.deleteById(id); }
}
