package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the GRUPO_INTERESES database table.
 * 
 */
@Embeddable
public class GrupoInteresPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_GRUPO", insertable=false, updatable=false)
	private int idGrupo;

	@Column(name="ID_INTERESES", insertable=false, updatable=false)
	private int idIntereses;

	public GrupoInteresPK() {
	}
	public int getIdGrupo() {
		return this.idGrupo;
	}
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	public int getIdIntereses() {
		return this.idIntereses;
	}
	public void setIdIntereses(int idIntereses) {
		this.idIntereses = idIntereses;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GrupoInteresPK)) {
			return false;
		}
		GrupoInteresPK castOther = (GrupoInteresPK)other;
		return 
			(this.idGrupo == castOther.idGrupo)
			&& (this.idIntereses == castOther.idIntereses);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idGrupo;
		hash = hash * prime + this.idIntereses;
		
		return hash;
	}
}