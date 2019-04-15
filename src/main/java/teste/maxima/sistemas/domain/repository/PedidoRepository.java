package teste.maxima.sistemas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import teste.maxima.sistemas.domain.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}