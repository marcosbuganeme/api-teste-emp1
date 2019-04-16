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

import teste.maxima.sistemas.domain.model.Produto;
import teste.maxima.sistemas.domain.repository.ProdutoRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoControllerTests {

    private @LocalServerPort int port;
    private @Autowired TestRestTemplate restTemplate;
    private @MockBean ProdutoRepository produtoRepository;

    public @Before void setup() {
        Produto produto = new Produto(1L, "bola de futsal max 500", 88.00D);
        BDDMockito
        	.when(produtoRepository
        				.getOne(produto.getId()))
        	.thenReturn(produto);
    }

    @Test
    public void retornaProdutoCadastradoCodigoStatus200() {
        ResponseEntity<Produto> response = restTemplate.getForEntity("/api/clientes/{id}/", Produto.class, 1L);

        Assertions
        	.assertThat(response.getStatusCodeValue())
        	.isEqualTo(200);
    }

    @Test
    public void listarProdutosCodigoStatus200() {
        List<Produto> produtos = Arrays.asList(new Produto(1L, "bola de futsal max 500", 88.00D),
        									   new Produto(2L, "xícara de porcelana", 33.33D));

        BDDMockito
        	.when(produtoRepository.findAll())
        	.thenReturn(produtos);

        ResponseEntity<String> response = restTemplate.getForEntity("/api/produtos/", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void salvarProduto() throws Exception {
    	Produto produto = new Produto(1L, "bola de futsal max 500", 88.00D);

        BDDMockito
        	.when(produtoRepository.save(produto))
        	.thenReturn(produto);

        ResponseEntity<Produto> response = restTemplate.postForEntity("/api/produtos", produto, Produto.class);
        Assertions.assertThat(response.getBody().getId()).isNull();
    }
}
