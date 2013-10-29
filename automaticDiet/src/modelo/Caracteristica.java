package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CARACTERISTICAS database table.
 * 
 */
@Entity
@Table(name="CARACTERISTICAS")
@NamedQuery(name="Caracteristica.findAll", query="SELECT c FROM Caracteristica c")
public class Caracteristica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="NOMBRE")
	private String nombre;

	//bi-directional many-to-many association to Grupo
	@ManyToMany(mappedBy="caracteristicas")
	private List<Grupo> grupos;

	//bi-directional many-to-one association to GrupoCaracteristica
	@OneToMany(mappedBy="caracteristica")
	private List<GrupoCaracteristica> grupoCaracteristicas;

	public Caracteristica() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Grupo> getGrupos() {
		return this.grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<GrupoCaracteristica> getGrupoCaracteristicas() {
		return this.grupoCaracteristicas;
	}

	public void setGrupoCaracteristicas(List<GrupoCaracteristica> grupoCaracteristicas) {
		this.grupoCaracteristicas = grupoCaracteristicas;
	}

	public GrupoCaracteristica addGrupoCaracteristica(GrupoCaracteristica grupoCaracteristica) {
		getGrupoCaracteristicas().add(grupoCaracteristica);
		grupoCaracteristica.setCaracteristica(this);

		return grupoCaracteristica;
	}

	public GrupoCaracteristica removeGrupoCaracteristica(GrupoCaracteristica grupoCaracteristica) {
		getGrupoCaracteristicas().remove(grupoCaracteristica);
		grupoCaracteristica.setCaracteristica(null);

		return grupoCaracteristica;
	}

}