package io.github.helioanacronista.customerRegistrerFullCliente.domain.repository;

import io.github.helioanacronista.customerRegistrerFullCliente.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
