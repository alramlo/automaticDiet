package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the GRUPO_CARACTERISTICAS database table.
 * 
 */
@Entity
@Table(name="GRUPO_CARACTERISTICAS")
@NamedQuery(name="GrupoCaracteristica.findAll", query="SELECT g FROM GrupoCaracteristica g")
public class GrupoCaracteristica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	//uni-directional many-to-one association to Caracteristica
	@ManyToOne
	@JoinColumn(name="ID_CARACTERISTICAS")
	private Caracteristica caracteristica;

	//uni-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="ID_GRUPO")
	private Grupo grupo;

	public GrupoCaracteristica() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Caracteristica getCaracteristica() {
		return this.caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}