package teste.maxima.sistemas.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import teste.maxima.sistemas.domain.model.Cliente;

public @Repository interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByEmail(String email);
}