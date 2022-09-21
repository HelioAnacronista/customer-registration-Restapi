package io.github.helioanacronista.customerRegistrerFullCliente.rest.Controllers;

import io.github.helioanacronista.customerRegistrerFullCliente.domain.entity.Cliente;
import io.github.helioanacronista.customerRegistrerFullCliente.domain.repository.ClientesRepository;
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
    private ClientesRepository repositoryCliente;

    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente save (@RequestBody @Valid Cliente cliente) {
        return repositoryCliente.save(cliente);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid Cliente clienteAtualizado) {
        repositoryCliente.findById(id)
                .map(clienteExistente -> {
                    clienteAtualizado.setId(clienteExistente.getId());
                    repositoryCliente.save(clienteAtualizado);
                    return clienteExistente;
                } ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repositoryCliente.findById(id)
                .map( cliente -> {
                    repositoryCliente.delete(cliente);
                    return cliente;
                } )
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @GetMapping
    public List<Cliente> find (Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
        Example example = Example.of(filtro, matcher);
        return repositoryCliente.findAll(example);
    }
}
