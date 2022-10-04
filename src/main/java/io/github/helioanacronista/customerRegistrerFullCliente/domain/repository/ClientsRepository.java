package io.github.helioanacronista.customerRegistrerFullCliente.domain.repository;

import io.github.helioanacronista.customerRegistrerFullCliente.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {
}
