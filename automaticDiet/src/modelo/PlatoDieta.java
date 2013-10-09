package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PLATO_DIETAS database table.
 * 
 */
@Entity
@Table(name="PLATO_DIETAS")
@NamedQuery(name="PlatoDieta.findAll", query="SELECT p FROM PlatoDieta p")
public class PlatoDieta implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlatoDietaPK id;

	@Column(name="DIA")
	private String dia;

	//bi-directional many-to-one association to Dieta
	@ManyToOne
	@JoinColumn(name="ID_DIETAS")
	private Dieta dieta;

	//bi-directional many-to-one association to Plato
	@ManyToOne
	@JoinColumn(name="ID_PLATO")
	private Plato plato;

	public PlatoDieta() {
	}

	public PlatoDietaPK getId() {
		return this.id;
	}

	public void setId(PlatoDietaPK id) {
		this.id = id;
	}

	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
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