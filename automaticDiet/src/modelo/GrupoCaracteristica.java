package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GRUPO_CARACTERISTICAS database table.
 * 
 */
@Entity
@Table(name="GRUPO_CARACTERISTICAS")
@NamedQuery(name="GrupoCaracteristica.findAll", query="SELECT g FROM GrupoCaracteristica g")
public class GrupoCaracteristica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	//bi-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="ID_GRUPO")
	private Grupo grupo;

	//bi-directional many-to-one association to Caracteristica
	@ManyToOne
	@JoinColumn(name="ID_CARACTERISTICAS")
	private Caracteristica caracteristica;

	public GrupoCaracteristica() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Caracteristica getCaracteristica() {
		return this.caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

}