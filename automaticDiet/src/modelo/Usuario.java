package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USUARIO database table.
 * 
 */
@Entity
@Table(name="USUARIO")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="APELLIDOS")
	private String apellidos;

	@Column(name="CORREO")
	private String correo;

	@Column(name="DIRECCION")
	private String direccion;

	@Column(name="DNI")
	private String dni;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="PAIS")
	private String pais;

	@Column(name="POBLACION")
	private String poblacion;

	//bi-directional many-to-one association to Dieta
	@OneToMany(mappedBy="usuario")
	private List<Dieta> dietas;

	//bi-directional many-to-one association to Plato
	@OneToMany(mappedBy="usuario")
	private List<Plato> platos;

	//bi-directional many-to-one association to Seguimiento
	@OneToMany(mappedBy="usuario1")
	private List<Seguimiento> seguimientos1;

	//bi-directional many-to-one association to Denuncia
	@OneToMany(mappedBy="usuario")
	private List<Denuncia> denuncias;

	//bi-directional many-to-one association to Grupo
	@OneToMany(mappedBy="usuario")
	private List<Grupo> grupos;

	//bi-directional many-to-one association to Mensaje
	@OneToMany(mappedBy="usuario")
	private List<Mensaje> mensajes;

	//bi-directional many-to-one association to Seguimiento
	@OneToMany(mappedBy="usuario2")
	private List<Seguimiento> seguimientos2;

	//bi-directional many-to-one association to UsuarioGrupo
	@OneToMany(mappedBy="usuario")
	private List<UsuarioGrupo> usuarioGrupos;

	public Usuario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public List<Dieta> getDietas() {
		return this.dietas;
	}

	public void setDietas(List<Dieta> dietas) {
		this.dietas = dietas;
	}

	public Dieta addDieta(Dieta dieta) {
		getDietas().add(dieta);
		dieta.setUsuario(this);

		return dieta;
	}

	public Dieta removeDieta(Dieta dieta) {
		getDietas().remove(dieta);
		dieta.setUsuario(null);

		return dieta;
	}

	public List<Plato> getPlatos() {
		return this.platos;
	}

	public void setPlatos(List<Plato> platos) {
		this.platos = platos;
	}

	public Plato addPlato(Plato plato) {
		getPlatos().add(plato);
		plato.setUsuario(this);

		return plato;
	}

	public Plato removePlato(Plato plato) {
		getPlatos().remove(plato);
		plato.setUsuario(null);

		return plato;
	}

	public List<Seguimiento> getSeguimientos1() {
		return this.seguimientos1;
	}

	public void setSeguimientos1(List<Seguimiento> seguimientos1) {
		this.seguimientos1 = seguimientos1;
	}

	public Seguimiento addSeguimientos1(Seguimiento seguimientos1) {
		getSeguimientos1().add(seguimientos1);
		seguimientos1.setUsuario1(this);

		return seguimientos1;
	}

	public Seguimiento removeSeguimientos1(Seguimiento seguimientos1) {
		getSeguimientos1().remove(seguimientos1);
		seguimientos1.setUsuario1(null);

		return seguimientos1;
	}

	public List<Denuncia> getDenuncias() {
		return this.denuncias;
	}

	public void setDenuncias(List<Denuncia> denuncias) {
		this.denuncias = denuncias;
	}

	public Denuncia addDenuncia(Denuncia denuncia) {
		getDenuncias().add(denuncia);
		denuncia.setUsuario(this);

		return denuncia;
	}

	public Denuncia removeDenuncia(Denuncia denuncia) {
		getDenuncias().remove(denuncia);
		denuncia.setUsuario(null);

		return denuncia;
	}

	public List<Grupo> getGrupos() {
		return this.grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Grupo addGrupo(Grupo grupo) {
		getGrupos().add(grupo);
		grupo.setUsuario(this);

		return grupo;
	}

	public Grupo removeGrupo(Grupo grupo) {
		getGrupos().remove(grupo);
		grupo.setUsuario(null);

		return grupo;
	}

	public List<Mensaje> getMensajes() {
		return this.mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public Mensaje addMensaje(Mensaje mensaje) {
		getMensajes().add(mensaje);
		mensaje.setUsuario(this);

		return mensaje;
	}

	public Mensaje removeMensaje(Mensaje mensaje) {
		getMensajes().remove(mensaje);
		mensaje.setUsuario(null);

		return mensaje;
	}

	public List<Seguimiento> getSeguimientos2() {
		return this.seguimientos2;
	}

	public void setSeguimientos2(List<Seguimiento> seguimientos2) {
		this.seguimientos2 = seguimientos2;
	}

	public Seguimiento addSeguimientos2(Seguimiento seguimientos2) {
		getSeguimientos2().add(seguimientos2);
		seguimientos2.setUsuario2(this);

		return seguimientos2;
	}

	public Seguimiento removeSeguimientos2(Seguimiento seguimientos2) {
		getSeguimientos2().remove(seguimientos2);
		seguimientos2.setUsuario2(null);

		return seguimientos2;
	}

	public List<UsuarioGrupo> getUsuarioGrupos() {
		return this.usuarioGrupos;
	}

	public void setUsuarioGrupos(List<UsuarioGrupo> usuarioGrupos) {
		this.usuarioGrupos = usuarioGrupos;
	}

	public UsuarioGrupo addUsuarioGrupo(UsuarioGrupo usuarioGrupo) {
		getUsuarioGrupos().add(usuarioGrupo);
		usuarioGrupo.setUsuario(this);

		return usuarioGrupo;
	}

	public UsuarioGrupo removeUsuarioGrupo(UsuarioGrupo usuarioGrupo) {
		getUsuarioGrupos().remove(usuarioGrupo);
		usuarioGrupo.setUsuario(null);

		return usuarioGrupo;
	}

}