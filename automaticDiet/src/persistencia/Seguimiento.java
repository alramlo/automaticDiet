package persistencia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SEGUIMIENTO database table.
 * 
 */
@Entity
@Table(name="SEGUIMIENTO")
@NamedQuery(name="Seguimiento.findAll", query="SELECT s FROM Seguimiento s")
public class Seguimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SeguimientoPK id;

	@Column(name="CUMPLIDO")
	private String cumplido;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID")
	private Usuario usuario;

	public Seguimiento() {
	}

	public SeguimientoPK getId() {
		return this.id;
	}

	public void setId(SeguimientoPK id) {
		this.id = id;
	}

	public String getCumplido() {
		return this.cumplido;
	}

	public void setCumplido(String cumplido) {
		this.cumplido = cumplido;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}