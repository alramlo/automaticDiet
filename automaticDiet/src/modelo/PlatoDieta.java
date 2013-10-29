package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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

	//bi-directional many-to-one association to Plato
	@ManyToOne
	@JoinColumn(name="ID_PLATO")
	private Plato plato;

	//bi-directional many-to-one association to Dieta
	@ManyToOne
	@JoinColumn(name="ID_DIETAS")
	private Dieta dieta;

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

	public Plato getPlato() {
		return this.plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	public Dieta getDieta() {
		return this.dieta;
	}

	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}

}