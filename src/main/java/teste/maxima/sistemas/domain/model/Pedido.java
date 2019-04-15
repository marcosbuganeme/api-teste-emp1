package teste.maxima.sistemas.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import teste.maxima.sistemas.domain.model.shared.DomainModel;

@Entity
@Table(name = "pedido")
public class Pedido extends DomainModel<Long> {

	private BigDecimal total;
	private LocalDateTime dataPedido;

	private Cliente cliente;
	private Set<ItemPedido> itens;

	public Pedido() {
		total = BigDecimal.ZERO;
		itens = new HashSet<>(5);
	}

	private @PrePersist void trigger() {
		this.dataPedido = LocalDateTime.now();
	}

	public void adiciona(ItemPedido itemPedido) {
		itens.add(itemPedido);
	}

	public void fecharPedido() {

		itens
			.stream()
			.map(ItemPedido::getPrecoTotal)
			.forEach(totalItemPedido -> total = total.add(totalItemPedido));
	}

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "pedido")
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
}