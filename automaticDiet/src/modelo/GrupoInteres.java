package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GRUPO_INTERESES database table.
 * 
 */
@Entity
@Table(name="GRUPO_INTERESES")
@NamedQuery(name="GrupoInteres.findAll", query="SELECT g FROM GrupoInteres g")
public class GrupoInteres implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	//bi-directional many-to-one association to Interes
	@ManyToOne
	@JoinColumn(name="ID_INTERESES")
	private Interes interes;

	//bi-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="ID_GRUPO")
	private Grupo grupo;

	public GrupoInteres() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Interes getInteres() {
		return this.interes;
	}

	public void setInteres(Interes interes) {
		this.interes = interes;
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}