package persistencia;

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
	@Column(name="ID")
	private int id;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="NUM_PARTICIPANTES")
	private int numParticipantes;

	@Column(name="POBLACION")
	private String poblacion;

	@Column(name="PRIVADO")
	private String privado;

	//bi-directional many-to-many association to Caracteristica
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

	//bi-directional many-to-many association to Usuario
	@ManyToMany
	@JoinTable(
		name="GRUPO_USUARIO"
		, joinColumns={
			@JoinColumn(name="ID_GRUPO")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_USUARIO")
			}
		)
	private List<Usuario> usuarios;

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

	public int getNumParticipantes() {
		return this.numParticipantes;
	}

	public void setNumParticipantes(int numParticipantes) {
		this.numParticipantes = numParticipantes;
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

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}