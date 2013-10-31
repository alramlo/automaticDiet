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
 * The persistent class for the PLATO_DIETA database table.
 * 
 */
@Entity
@Table(name="PLATO_DIETA")
@NamedQuery(name="PlatoDieta.findAll", query="SELECT p FROM PlatoDieta p")
public class PlatoDieta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="DIA")
	private Date dia;

	//uni-directional many-to-one association to Dieta
	@ManyToOne
	@JoinColumn(name="ID_DIETAS")
	private Dieta dieta;

	//uni-directional many-to-one association to Plato
	@ManyToOne
	@JoinColumn(name="ID_PLATO")
	private Plato plato;

	public PlatoDieta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDia() {
		return this.dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public Dieta getDieta() {
		return this.dieta;
	}

	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}

	public Plato getPlato() {
		return this.plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

}