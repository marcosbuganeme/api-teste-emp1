package teste.maxima.sistemas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import teste.maxima.sistemas.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}