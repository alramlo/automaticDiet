package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the FORO database table.
 * 
 */
@Entity
@Table(name="FORO")
@NamedQuery(name="Foro.findAll", query="SELECT f FROM Foro f")
public class Foro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA")
	private Date fecha;

	@Column(name="TEMA")
	private String tema;

	@Column(name="VISITAR")
	private int visitar;

	//bi-directional many-to-one association to Mensaje
	@OneToMany(mappedBy="foro")
	private List<Mensaje> mensajes;

	public Foro() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTema() {
		return this.tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public int getVisitar() {
		return this.visitar;
	}

	public void setVisitar(int visitar) {
		this.visitar = visitar;
	}

	public List<Mensaje> getMensajes() {
		return this.mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public Mensaje addMensaje(Mensaje mensaje) {
		getMensajes().add(mensaje);
		mensaje.setForo(this);

		return mensaje;
	}

	public Mensaje removeMensaje(Mensaje mensaje) {
		getMensajes().remove(mensaje);
		mensaje.setForo(null);

		return mensaje;
	}

}