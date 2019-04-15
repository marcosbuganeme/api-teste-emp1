package teste.maxima.sistemas.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import teste.maxima.sistemas.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByEmail(String email);
}