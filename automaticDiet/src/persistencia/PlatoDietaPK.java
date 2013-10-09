package persistencia;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PLATO_DIETAS database table.
 * 
 */
@Embeddable
public class PlatoDietaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_PLATO", insertable=false, updatable=false)
	private int idPlato;

	@Column(name="ID_DIETAS", insertable=false, updatable=false)
	private int idDietas;

	public PlatoDietaPK() {
	}
	public int getIdPlato() {
		return this.idPlato;
	}
	public void setIdPlato(int idPlato) {
		this.idPlato = idPlato;
	}
	public int getIdDietas() {
		return this.idDietas;
	}
	public void setIdDietas(int idDietas) {
		this.idDietas = idDietas;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PlatoDietaPK)) {
			return false;
		}
		PlatoDietaPK castOther = (PlatoDietaPK)other;
		return 
			(this.idPlato == castOther.idPlato)
			&& (this.idDietas == castOther.idDietas);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPlato;
		hash = hash * prime + this.idDietas;
		
		return hash;
	}
}