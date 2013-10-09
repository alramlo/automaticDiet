package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SEGUIMIENTO database table.
 * 
 */
@Embeddable
public class SeguimientoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID", insertable=false, updatable=false)
	private int id;

	@Column(name="PESO")
	private long peso;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA")
	private java.util.Date fecha;

	public SeguimientoPK() {
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getPeso() {
		return this.peso;
	}
	public void setPeso(long peso) {
		this.peso = peso;
	}
	public java.util.Date getFecha() {
		return this.fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SeguimientoPK)) {
			return false;
		}
		SeguimientoPK castOther = (SeguimientoPK)other;
		return 
			(this.id == castOther.id)
			&& (this.peso == castOther.peso)
			&& this.fecha.equals(castOther.fecha);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + ((int) (this.peso ^ (this.peso >>> 32)));
		hash = hash * prime + this.fecha.hashCode();
		
		return hash;
	}
}