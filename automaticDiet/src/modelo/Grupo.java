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

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="CREADOR")
	private Usuario usuario;

	//bi-directional many-to-one association to GrupoCaracteristica
	@OneToMany(mappedBy="grupo")
	private List<GrupoCaracteristica> grupoCaracteristicas;

	//bi-directional many-to-one association to GrupoInteres
	@OneToMany(mappedBy="grupo")
	private List<GrupoInteres> grupoIntereses;

	//bi-directional many-to-one association to Mensaje
	@OneToMany(mappedBy="grupo")
	private List<Mensaje> mensajes;

	//bi-directional many-to-one association to UsuarioGrupo
	@OneToMany(mappedBy="grupo")
	private List<UsuarioGrupo> usuarioGrupos;

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

	public List<GrupoCaracteristica> getGrupoCaracteristicas() {
		return this.grupoCaracteristicas;
	}

	public void setGrupoCaracteristicas(List<GrupoCaracteristica> grupoCaracteristicas) {
		this.grupoCaracteristicas = grupoCaracteristicas;
	}

	public GrupoCaracteristica addGrupoCaracteristica(GrupoCaracteristica grupoCaracteristica) {
		getGrupoCaracteristicas().add(grupoCaracteristica);
		grupoCaracteristica.setGrupo(this);

		return grupoCaracteristica;
	}

	public GrupoCaracteristica removeGrupoCaracteristica(GrupoCaracteristica grupoCaracteristica) {
		getGrupoCaracteristicas().remove(grupoCaracteristica);
		grupoCaracteristica.setGrupo(null);

		return grupoCaracteristica;
	}

	public List<GrupoInteres> getGrupoIntereses() {
		return this.grupoIntereses;
	}

	public void setGrupoIntereses(List<GrupoInteres> grupoIntereses) {
		this.grupoIntereses = grupoIntereses;
	}

	public GrupoInteres addGrupoInteres(GrupoInteres grupoInteres) {
		getGrupoIntereses().add(grupoInteres);
		grupoInteres.setGrupo(this);

		return grupoInteres;
	}

	public GrupoInteres removeGrupoInteres(GrupoInteres grupoInteres) {
		getGrupoIntereses().remove(grupoInteres);
		grupoInteres.setGrupo(null);

		return grupoInteres;
	}

	public List<Mensaje> getMensajes() {
		return this.mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public Mensaje addMensaje(Mensaje mensaje) {
		getMensajes().add(mensaje);
		mensaje.setGrupo(this);

		return mensaje;
	}

	public Mensaje removeMensaje(Mensaje mensaje) {
		getMensajes().remove(mensaje);
		mensaje.setGrupo(null);

		return mensaje;
	}

	public List<UsuarioGrupo> getUsuarioGrupos() {
		return this.usuarioGrupos;
	}

	public void setUsuarioGrupos(List<UsuarioGrupo> usuarioGrupos) {
		this.usuarioGrupos = usuarioGrupos;
	}

	public UsuarioGrupo addUsuarioGrupo(UsuarioGrupo usuarioGrupo) {
		getUsuarioGrupos().add(usuarioGrupo);
		usuarioGrupo.setGrupo(this);

		return usuarioGrupo;
	}

	public UsuarioGrupo removeUsuarioGrupo(UsuarioGrupo usuarioGrupo) {
		getUsuarioGrupos().remove(usuarioGrupo);
		usuarioGrupo.setGrupo(null);

		return usuarioGrupo;
	}

}