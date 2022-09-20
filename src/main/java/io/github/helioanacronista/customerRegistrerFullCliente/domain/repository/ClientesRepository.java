package io.github.helioanacronista.customerRegistrerFullCliente.domain.repository;

import io.github.helioanacronista.customerRegistrerFullCliente.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Long> {
}
