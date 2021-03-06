package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


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
	
	@Column(name="ROL")
	private String rol;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Lob
	@Column(name="IMAGEN")
	private byte[] imagen;

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
	
	public void setRol(String rol){
		this.rol = rol;
	}
	
	public String getRol(){
		return this.rol;
	}
	
	public void setUsername(String u){
		this.username = u;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public void setPassword(String p){
		this.password = p;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setImagen(byte[] img){
		this.imagen = img;
	}
	
	public byte[] getImagen(){
		return this.imagen;
	}

}