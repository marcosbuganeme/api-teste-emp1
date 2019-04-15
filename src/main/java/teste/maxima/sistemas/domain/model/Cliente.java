package teste.maxima.sistemas.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import teste.maxima.sistemas.domain.model.shared.DomainModel;

@Entity
@Table(name = "cliente")
public class Cliente extends DomainModel<Long> {

	private String email;
	private String razaoSocial;

	public Cliente() {}

	public Cliente(String email, String razaoSocial) {
		this.email = email;
		this.razaoSocial = razaoSocial;
	}

	@NotBlank(message = "Nome é obrigatório")
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	@Email(message = "E-mail inválido")
	@NotBlank(message = "E-mail é obrigatório")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}