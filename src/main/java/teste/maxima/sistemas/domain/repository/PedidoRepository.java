package teste.maxima.sistemas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import teste.maxima.sistemas.domain.model.Pedido;

public @Repository interface PedidoRepository extends JpaRepository<Pedido, Long> {

}