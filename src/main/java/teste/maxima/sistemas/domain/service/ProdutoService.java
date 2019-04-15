package teste.maxima.sistemas.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import teste.maxima.sistemas.domain.model.Produto;
import teste.maxima.sistemas.domain.repository.ProdutoRepository;
import teste.maxima.sistemas.domain.service.exception.ResourceNotFoundException;

public @Service class ProdutoService {

	private @Autowired ProdutoRepository produtoRepository;

	@Transactional
	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Transactional
	public Produto update(Long id, Produto produto) {

		findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Produto não existe"));

		produto.setId(id);
		return produtoRepository.save(produto);
	}

	@Transactional
	public void delete(Long id) {

		findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Produto não existe"));

		produtoRepository.deleteById(id);
	}

	public Optional<Produto> findById(Long id) {

		return produtoRepository.findById(id);
	}

	public List<Produto> findAll() {

		return produtoRepository.findAll();
	}

	public Page<Produto> findAll(Pageable pageable) {

		return produtoRepository.findAll(pageable);
	}
}