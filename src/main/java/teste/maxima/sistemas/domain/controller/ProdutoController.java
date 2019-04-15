package teste.maxima.sistemas.domain.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import teste.maxima.sistemas.domain.model.Produto;
import teste.maxima.sistemas.domain.service.ProdutoService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/produtos")
@Api("Endpoint para o recurso de produtos")
public class ProdutoController {

	private @Autowired ProdutoService produtoService;

	@PostMapping
    @ApiOperation(value = "Criar um produto", response = Produto.class)
	public ResponseEntity<?> salvar(@Valid @RequestBody Produto produto, UriComponentsBuilder uriBuilder) {

		Produto produtoSalvo = produtoService.save(produto);

        URI location = uriBuilder
					        .path("api/produtos/{id:\\d+}")
					        .buildAndExpand(produtoSalvo.getId())
					        .toUri();

		return ResponseEntity
					.created(location)
					.body(produtoSalvo);
	}
	
	@PutMapping("{id:\\d+}")
	@ApiOperation(value = "Editar um produto", response = Produto.class)
	public ResponseEntity<?> editar(@PathVariable("id") Long id, @Valid @RequestBody Produto produto) {

		Produto produtoAtualizado = produtoService.update(id, produto);

		return ResponseEntity.ok(produtoAtualizado);
	}

    @GetMapping("{id:\\d+}")
    @ApiOperation(value = "Recupera um produto pelo seu identificador", response = Produto.class)
	public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id) {

		Optional<Produto> produto = produtoService.findById(id);

		return ResponseEntity.of(produto);
	}

	@GetMapping
	@ApiOperation(value = "Recupera todos os produtos", response = Produto[].class)
	public ResponseEntity<?> buscarTodosPedidos() {

		List<Produto> produtos = produtoService.findAll();

		return ResponseEntity.ok(produtos);
	}

	@GetMapping("paginados")
	@ApiOperation(value = "Recupera todos os produtos de forma paginada", response = Page.class)
	public ResponseEntity<?> buscarTodosPedidosPaginados(@PageableDefault Pageable pageable) {

		Page<Produto> page = produtoService.findAll(pageable);

		return ResponseEntity.ok(page);
	}
}