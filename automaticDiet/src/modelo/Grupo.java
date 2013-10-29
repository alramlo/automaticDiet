package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the GRUPO database table.
 * 
 */
@Entity
@Table(name="GRUPO")
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="POBLACION")
	private String poblacion;

	@Column(name="PRIVADO")
	private String privado;

	//uni-directional many-to-many association to Caracteristica
	@ManyToMany
	@JoinTable(
		name="GRUPO_CARACTERISTICAS"
		, joinColumns={
			@JoinColumn(name="ID_GRUPO")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_CARACTERISTICAS")
			}
		)
	private List<Caracteristica> caracteristicas;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="CREADOR")
	private Usuario usuario;

	public Grupo() {
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

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getPrivado() {
		return this.privado;
	}

	public void setPrivado(String privado) {
		this.privado = privado;
	}

	public List<Caracteristica> getCaracteristicas() {
		return this.caracteristicas;
	}

	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}