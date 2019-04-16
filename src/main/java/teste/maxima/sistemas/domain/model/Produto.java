package teste.maxima.sistemas.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import teste.maxima.sistemas.domain.model.shared.DomainModel;

@Entity
@Table(name = "produto")
public class Produto extends DomainModel<Long> {

	private String descricao;
	private Double preco;

	public Produto() {}
	
	// facilitar testes
	public Produto(Long id, String descricao, Double preco) {
		setId(id);
		this.descricao = descricao;
		this.preco = preco;
	}

	@NotBlank(message = "Descrição é obrigatório")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@NotNull(message = "Preço é obrigatório")
	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
}