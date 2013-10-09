package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PLATO database table.
 * 
 */
@Entity
@Table(name="PLATO")
@NamedQuery(name="Plato.findAll", query="SELECT p FROM Plato p")
public class Plato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private int id;

	@Column(name="ELABORACION")
	private String elaboracion;

	@Lob
	@Column(name="IMAGEN")
	private byte[] imagen;

	@Column(name="NOMBRE")
	private String nombre;

	//bi-directional many-to-one association to PlatoDieta
	@OneToMany(mappedBy="plato")
	private List<PlatoDieta> platoDietas;

	//bi-directional many-to-one association to PlatoIngrediente
	@OneToMany(mappedBy="plato")
	private List<PlatoIngrediente> platoIngredientes;

	//bi-directional many-to-many association to TipoPlato
	@ManyToMany(mappedBy="platos")
	private List<TipoPlato> tipoPlatos;

	public Plato() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getElaboracion() {
		return this.elaboracion;
	}

	public void setElaboracion(String elaboracion) {
		this.elaboracion = elaboracion;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PlatoDieta> getPlatoDietas() {
		return this.platoDietas;
	}

	public void setPlatoDietas(List<PlatoDieta> platoDietas) {
		this.platoDietas = platoDietas;
	}

	public PlatoDieta addPlatoDieta(PlatoDieta platoDieta) {
		getPlatoDietas().add(platoDieta);
		platoDieta.setPlato(this);

		return platoDieta;
	}

	public PlatoDieta removePlatoDieta(PlatoDieta platoDieta) {
		getPlatoDietas().remove(platoDieta);
		platoDieta.setPlato(null);

		return platoDieta;
	}

	public List<PlatoIngrediente> getPlatoIngredientes() {
		return this.platoIngredientes;
	}

	public void setPlatoIngredientes(List<PlatoIngrediente> platoIngredientes) {
		this.platoIngredientes = platoIngredientes;
	}

	public PlatoIngrediente addPlatoIngrediente(PlatoIngrediente platoIngrediente) {
		getPlatoIngredientes().add(platoIngrediente);
		platoIngrediente.setPlato(this);

		return platoIngrediente;
	}

	public PlatoIngrediente removePlatoIngrediente(PlatoIngrediente platoIngrediente) {
		getPlatoIngredientes().remove(platoIngrediente);
		platoIngrediente.setPlato(null);

		return platoIngrediente;
	}

	public List<TipoPlato> getTipoPlatos() {
		return this.tipoPlatos;
	}

	public void setTipoPlatos(List<TipoPlato> tipoPlatos) {
		this.tipoPlatos = tipoPlatos;
	}

}