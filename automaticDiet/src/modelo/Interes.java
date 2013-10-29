package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the INTERESES database table.
 * 
 */
@Entity
@Table(name="INTERESES")
@NamedQuery(name="Interes.findAll", query="SELECT i FROM Interes i")
public class Interes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="NOMBRE")
	private String nombre;

	public Interes() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}