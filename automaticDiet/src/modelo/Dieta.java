package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the DIETAS database table.
 * 
 */
@Entity
@Table(name="DIETAS")
@NamedQuery(name="Dieta.findAll", query="SELECT d FROM Dieta d")
public class Dieta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="CALORIAS_MAX")
	private int caloriasMax;

	@Column(name="CALORIAS_MIN")
	private int caloriasMin;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_FINAL")
	private Date fechaFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_INICIAL")
	private Date fechaInicial;

	@Column(name="NOMBRE")
	private String nombre;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;

	public Dieta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCaloriasMax() {
		return this.caloriasMax;
	}

	public void setCaloriasMax(int caloriasMax) {
		this.caloriasMax = caloriasMax;
	}

	public int getCaloriasMin() {
		return this.caloriasMin;
	}

	public void setCaloriasMin(int caloriasMin) {
		this.caloriasMin = caloriasMin;
	}

	public Date getFechaFinal() {
		return this.fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicial() {
		return this.fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}