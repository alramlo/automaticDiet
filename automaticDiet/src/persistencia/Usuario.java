package persistencia;

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

	//bi-directional many-to-many association to Grupo
	@ManyToMany(mappedBy="usuarios")
	private List<Grupo> grupos;

	//bi-directional many-to-one association to Seguimiento
	@OneToMany(mappedBy="usuario")
	private List<Seguimiento> seguimientos;

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

	public List<Grupo> getGrupos() {
		return this.grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<Seguimiento> getSeguimientos() {
		return this.seguimientos;
	}

	public void setSeguimientos(List<Seguimiento> seguimientos) {
		this.seguimientos = seguimientos;
	}

	public Seguimiento addSeguimiento(Seguimiento seguimiento) {
		getSeguimientos().add(seguimiento);
		seguimiento.setUsuario(this);

		return seguimiento;
	}

	public Seguimiento removeSeguimiento(Seguimiento seguimiento) {
		getSeguimientos().remove(seguimiento);
		seguimiento.setUsuario(null);

		return seguimiento;
	}

}