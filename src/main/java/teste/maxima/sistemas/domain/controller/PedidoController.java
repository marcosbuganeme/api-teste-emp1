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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import teste.maxima.sistemas.domain.model.Pedido;
import teste.maxima.sistemas.domain.service.PedidoService;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

	private @Autowired PedidoService pedidoService;

	@PostMapping
	public ResponseEntity<?> salvar(@Valid @RequestBody Pedido pedido, UriComponentsBuilder uriBuilder) {

		Pedido pedidoSalvo = pedidoService.criar(pedido);

        URI location = uriBuilder
					        .path("api/pedidos/{id:\\d+}")
					        .buildAndExpand(pedidoSalvo.getId())
					        .toUri();

		return ResponseEntity
					.created(location)
					.body(pedidoSalvo); 
	}

    @GetMapping("{id:\\d+}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id) {

		Optional<Pedido> pedido = pedidoService.findById(id);

		return ResponseEntity.of(pedido);
	}

	@GetMapping
	public ResponseEntity<?> buscarTodosPedidos() {

		List<Pedido> pedidos = pedidoService.findAll();

		return ResponseEntity.ok(pedidos);
	}

	@GetMapping("paginados")
	public ResponseEntity<?> buscarTodosPedidosPaginados(@PageableDefault Pageable pageable) {

		Page<Pedido> page = pedidoService.findAll(pageable);

		return ResponseEntity.ok(page);
	}
}