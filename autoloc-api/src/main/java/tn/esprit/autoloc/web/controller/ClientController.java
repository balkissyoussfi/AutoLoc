package tn.esprit.autoloc.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {

    private final IClientService clientService;

    @GetMapping
    public List<Client> findAll() { return clientService.findAll(); }

    @GetMapping("{id}")
    public Client findById(@PathVariable Long id) { return clientService.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client) { return clientService.create(client); }

    @PutMapping("{id}")
    public Client update(@PathVariable Long id, @RequestBody Client client) { return clientService.update(id, client); }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) { clientService.deleteById(id); }
}
