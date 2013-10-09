package persistencia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PLATO_INGREDIENTE database table.
 * 
 */
@Entity
@Table(name="PLATO_INGREDIENTE")
@NamedQuery(name="PlatoIngrediente.findAll", query="SELECT p FROM PlatoIngrediente p")
public class PlatoIngrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlatoIngredientePK id;

	@Column(name="CANTIDAD")
	private int cantidad;

	//bi-directional many-to-one association to Ingrediente
	@ManyToOne
	@JoinColumn(name="ID_INGREDIENTE")
	private Ingrediente ingrediente;

	//bi-directional many-to-one association to Plato
	@ManyToOne
	@JoinColumn(name="ID_PLATO")
	private Plato plato;

	public PlatoIngrediente() {
	}

	public PlatoIngredientePK getId() {
		return this.id;
	}

	public void setId(PlatoIngredientePK id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Ingrediente getIngrediente() {
		return this.ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Plato getPlato() {
		return this.plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

}