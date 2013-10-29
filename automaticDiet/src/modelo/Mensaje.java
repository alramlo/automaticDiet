package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the MENSAJES database table.
 * 
 */
@Entity
@Table(name="MENSAJES")
@NamedQuery(name="Mensaje.findAll", query="SELECT m FROM Mensaje m")
public class Mensaje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="CONTENIDO")
	private String contenido;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA")
	private Date fecha;

	//uni-directional many-to-one association to Foro
	@ManyToOne
	@JoinColumn(name="ID_FORO")
	private Foro foro;

	//uni-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="ID_GRUPO")
	private Grupo grupo;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;

	public Mensaje() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenido() {
		return this.contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Foro getForo() {
		return this.foro;
	}

	public void setForo(Foro foro) {
		this.foro = foro;
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}