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

import teste.maxima.sistemas.domain.model.Cliente;
import teste.maxima.sistemas.domain.service.ClienteService;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

	private @Autowired ClienteService clienteService;

	@PostMapping
	public ResponseEntity<?> salvar(@Valid @RequestBody Cliente cliente, UriComponentsBuilder uriBuilder) {

		Cliente clienteSalvo = clienteService.save(cliente);

        URI location = uriBuilder
					        .path("api/clientes/{id:\\d+}")
					        .buildAndExpand(clienteSalvo.getId())
					        .toUri();

		return ResponseEntity
					.created(location)
					.body(clienteSalvo);
	}

    @GetMapping("{id:\\d+}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id) {

		Optional<Cliente> cliente = clienteService.findById(id);

		return ResponseEntity.of(cliente);
	}

	@GetMapping
	public ResponseEntity<?> buscarTodos() {

		List<Cliente> clientes = clienteService.findAll();

		return ResponseEntity.ok(clientes);
	}

	@GetMapping("paginados")
	public ResponseEntity<?> buscarTodosPaginado(@PageableDefault Pageable pageable) {

		Page<Cliente> page = clienteService.findAll(pageable);

		return ResponseEntity.ok(page);
	}
}