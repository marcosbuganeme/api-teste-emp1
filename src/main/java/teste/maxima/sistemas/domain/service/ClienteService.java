package teste.maxima.sistemas.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import teste.maxima.sistemas.domain.model.Cliente;
import teste.maxima.sistemas.domain.repository.ClienteRepository;
import teste.maxima.sistemas.domain.service.exception.ResourceDuplicateException;

public @Service class ClienteService {

	private @Autowired ClienteRepository clienteRepository;

	@Transactional
	public Cliente save(Cliente cliente) {

		return verificaRegraDeEmailDo(cliente)
					.e()
				.salva(cliente);
	}

	public Optional<Cliente> findById(Long id) {

		return clienteRepository.findById(id);
	}

	public List<Cliente> findAll() {

		return clienteRepository.findAll();
	}

	public Page<Cliente> findAll(Pageable pageable) {

		return clienteRepository.findAll(pageable);
	}

	private ClienteService verificaRegraDeEmailDo(Cliente cliente) {

		clienteRepository
			.findByEmail(cliente.getEmail())
			.filter(apenasEmailsValidosDo(cliente))
			.orElseThrow(() -> new ResourceDuplicateException("E-mail já cadastrado"));

		return this;
	}

	private Cliente salva(Cliente cliente) {

		return clienteRepository.save(cliente);
	}

	private Predicate<Cliente> apenasEmailsValidosDo(Cliente clienteFormulario) {

		return clientePesquisado -> StringUtils.compareIgnoreCase(clientePesquisado.getEmail(), 
							   									  clienteFormulario.getEmail()) == 0;
	}

	private ClienteService e() { return this; }
}