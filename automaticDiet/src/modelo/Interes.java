package modelo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the INTERESES database table.
 * 
 */
@Entity
@Table(name="INTERESES")
@NamedQuery(name="Interes.findAll", query="SELECT i FROM Interes i")
public class Interes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="NOMBRE")
	private String nombre;

	//bi-directional many-to-one association to GrupoInteres
	@OneToMany(mappedBy="interes")
	private List<GrupoInteres> grupoIntereses;

	public Interes() {
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

	public List<GrupoInteres> getGrupoIntereses() {
		return this.grupoIntereses;
	}

	public void setGrupoIntereses(List<GrupoInteres> grupoIntereses) {
		this.grupoIntereses = grupoIntereses;
	}

	public GrupoInteres addGrupoInteres(GrupoInteres grupoInteres) {
		getGrupoIntereses().add(grupoInteres);
		grupoInteres.setInteres(this);

		return grupoInteres;
	}

	public GrupoInteres removeGrupoInteres(GrupoInteres grupoInteres) {
		getGrupoIntereses().remove(grupoInteres);
		grupoInteres.setInteres(null);

		return grupoInteres;
	}

	@Override
	public String toString() {
		return "Interes [id=" + id + ", nombre=" + nombre + "]";
	}

}