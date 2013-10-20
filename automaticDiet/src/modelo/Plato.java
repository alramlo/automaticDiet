package modelo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Arrays;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Override
	public String toString() {
		return "Plato [id=" + id + ", elaboracion=" + elaboracion + ", imagen="
				+ Arrays.toString(imagen) + ", nombre=" + nombre + "]";
	}
	
	

}