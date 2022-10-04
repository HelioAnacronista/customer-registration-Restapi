package io.github.helioanacronista.customerRegistrerFullCliente.Controllers;

import io.github.helioanacronista.customerRegistrerFullCliente.domain.entity.Client;
import io.github.helioanacronista.customerRegistrerFullCliente.domain.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    @Autowired
    private ClientsRepository repository;

    @PostMapping
    @ResponseStatus(CREATED)
    public Client save (@RequestBody @Valid Client cliente) {
        return repository.save(cliente);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid Client clienteAtualizado) {
        repository.findById(id)
                .map(clienteExistente -> {
                    clienteAtualizado.setId(clienteExistente.getId());
                    repository.save(clienteAtualizado);
                    return clienteExistente;
                } ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.findById(id)
                .map( cliente -> {
                    repository.delete(cliente);
                    return cliente;
                } )
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @GetMapping
    public List<Client> find (Client filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
        Example example = Example.of(filtro, matcher);
        return repository.findAll(example);
    }
}
