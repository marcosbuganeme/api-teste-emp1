package teste.maxima.sistemas.domain.model.shared;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DomainModel<ID> {

	private ID id;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
}
