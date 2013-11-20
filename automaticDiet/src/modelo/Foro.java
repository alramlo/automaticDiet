package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the FORO database table.
 * 
 */
@Entity
@Table(name="FORO")
@NamedQuery(name="Foro.findAll", query="SELECT f FROM Foro f")
public class Foro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA")
	private Date fecha;

	@Column(name="TEMA")
	private String tema;

	@Column(name="VISITAR")
	private int visitar;
	
	@ManyToOne
	@JoinColumn(name="CREADOR")
	private Usuario creador;

	public Foro() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTema() {
		return this.tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public int getVisitar() {
		return this.visitar;
	}

	public void setVisitar(int visitar) {
		this.visitar = visitar;
	}

	public Usuario getCreador() {
		return creador;
	}

	public void setCreador(Usuario creador) {
		this.creador = creador;
	}

}