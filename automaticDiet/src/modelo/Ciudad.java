package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CIUDAD database table.
 * 
 */
@Entity
@Table(name="CIUDAD")
@NamedQuery(name="Ciudad.findAll", query="SELECT c FROM Ciudad c")
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="NOMBRE")
	private String nombre;

	//bi-directional many-to-one association to Pai
	@ManyToOne
	@JoinColumn(name="PAIS")
	private Pais pais;

	public Ciudad() {
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

	public Pais getPai() {
		return this.pais;
	}

	public void setPai(Pais pais) {
		this.pais = pais;
	}

}