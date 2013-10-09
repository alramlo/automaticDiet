package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TIPO_PLATO database table.
 * 
 */
@Entity
@Table(name="TIPO_PLATO")
@NamedQuery(name="TipoPlato.findAll", query="SELECT t FROM TipoPlato t")
public class TipoPlato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private int id;

	@Column(name="NOMBRE")
	private String nombre;

	//bi-directional many-to-many association to Plato
	@ManyToMany
	@JoinTable(
		name="PLATO_TIPO"
		, joinColumns={
			@JoinColumn(name="ID_TIPO")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_PLATO")
			}
		)
	private List<Plato> platos;

	public TipoPlato() {
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

	public List<Plato> getPlatos() {
		return this.platos;
	}

	public void setPlatos(List<Plato> platos) {
		this.platos = platos;
	}

}