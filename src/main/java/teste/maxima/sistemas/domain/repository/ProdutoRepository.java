package teste.maxima.sistemas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import teste.maxima.sistemas.domain.model.Produto;

public @Repository interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Produto findFirstByOrderByIdDesc();
}