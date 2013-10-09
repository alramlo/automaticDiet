package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PLATO_INGREDIENTE database table.
 * 
 */
@Embeddable
public class PlatoIngredientePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_PLATO", insertable=false, updatable=false)
	private int idPlato;

	@Column(name="ID_INGREDIENTE", insertable=false, updatable=false)
	private int idIngrediente;

	public PlatoIngredientePK() {
	}
	public int getIdPlato() {
		return this.idPlato;
	}
	public void setIdPlato(int idPlato) {
		this.idPlato = idPlato;
	}
	public int getIdIngrediente() {
		return this.idIngrediente;
	}
	public void setIdIngrediente(int idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PlatoIngredientePK)) {
			return false;
		}
		PlatoIngredientePK castOther = (PlatoIngredientePK)other;
		return 
			(this.idPlato == castOther.idPlato)
			&& (this.idIngrediente == castOther.idIngrediente);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPlato;
		hash = hash * prime + this.idIngrediente;
		
		return hash;
	}
}