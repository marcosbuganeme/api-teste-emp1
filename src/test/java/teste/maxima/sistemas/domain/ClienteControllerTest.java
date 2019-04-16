
package teste.maxima.sistemas.domain;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import teste.maxima.sistemas.domain.model.Cliente;
import teste.maxima.sistemas.domain.repository.ClienteRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerTest {

    private @LocalServerPort int port;
    private @Autowired TestRestTemplate restTemplate;
    private @MockBean ClienteRepository clienteRepository;

    public @Before void setup() {
        Cliente cliente = new Cliente(1L, "legolas@lotr.com", "Legolas LTDA");
        BDDMockito
        	.when(clienteRepository
        				.getOne(cliente.getId()))
        	.thenReturn(cliente);
    }

    @Test
    public void retornaClienteCadastradoCodigoStatus200() {
        ResponseEntity<Cliente> response = restTemplate.getForEntity("/api/produtos/{id}/", Cliente.class, 1L);

        Assertions
        	.assertThat(response.getStatusCodeValue())
        	.isEqualTo(200);
    }

    @Test
    public void listarClientesCodigoStatus200() {
        List<Cliente> clientes = Arrays.asList(new Cliente(1L, "legolas@lotr.com", "Legolas LTDA"),
        									   new Cliente(2L, "aragorn@lotr.com", "Aragorn LTDA"));

        BDDMockito
        	.when(clienteRepository.findAll())
        	.thenReturn(clientes);

        ResponseEntity<String> response = restTemplate.getForEntity("/api/clientes/", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void salvarCliente() throws Exception {
    	Cliente cliente = new Cliente(3L, "sam@lotr.com", "Sam");

        BDDMockito
        	.when(clienteRepository.save(cliente))
        	.thenReturn(cliente);

        ResponseEntity<Cliente> response = restTemplate.postForEntity("/api/produtos", cliente, Cliente.class);
        Assertions.assertThat(response.getBody().getId()).isNull();
    }
}