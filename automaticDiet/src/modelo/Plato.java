package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="ELABORACION")
	private String elaboracion;

	@Lob
	@Column(name="IMAGEN")
	private byte[] imagen;

	@Column(name="NOMBRE")
	private String nombre;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TIEMPO")
	private Date tiempo;

	@Column(name="TIPO")
	private String tipo;
	
	@Column(name="VALORACION")
	private String valoracion;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;

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

	public Date getTiempo() {
		return this.tiempo;
	}

	public void setTiempo(Date tiempo) {
		this.tiempo = tiempo;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getValoracion() {
		return this.valoracion;
	}

	public void setValoracion(String valor) {
		this.valoracion = valor;
	}


}