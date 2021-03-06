package modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the INGREDIENTE database table.
 * 
 */
@Entity
@Table(name="INGREDIENTE")
@NamedQuery(name="Ingrediente.findAll", query="SELECT i FROM Ingrediente i")
public class Ingrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="CALORIAS")
	private int calorias;

	@Column(name="CARBOHIDRATOS")
	private int carbohidratos;

	@Column(name="COLESTEROL")
	private int colesterol;

	@Column(name="FIBRA")
	private int fibra;

	@Column(name="GRASA")
	private int grasa;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="ORIGEN")
	private String origen;

	@Column(name="PRECIO")
	private Double precio;

	@Column(name="PROTEINAS")
	private int proteinas;

	@Column(name="SODIO")
	private int sodio;

	@Column(name="VITAMINAS")
	private String vitaminas;

	public Ingrediente() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCalorias() {
		return this.calorias;
	}

	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}

	public int getCarbohidratos() {
		return this.carbohidratos;
	}

	public void setCarbohidratos(int carbohidratos) {
		this.carbohidratos = carbohidratos;
	}

	public int getColesterol() {
		return this.colesterol;
	}

	public void setColesterol(int colesterol) {
		this.colesterol = colesterol;
	}

	public int getFibra() {
		return this.fibra;
	}

	public void setFibra(int fibra) {
		this.fibra = fibra;
	}

	public int getGrasa() {
		return this.grasa;
	}

	public void setGrasa(int grasa) {
		this.grasa = grasa;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOrigen() {
		return this.origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public Double getPrecio() {
		return this.precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public int getProteinas() {
		return this.proteinas;
	}

	public void setProteinas(int proteinas) {
		this.proteinas = proteinas;
	}

	public int getSodio() {
		return this.sodio;
	}

	public void setSodio(int sodio) {
		this.sodio = sodio;
	}

	public String getVitaminas() {
		return this.vitaminas;
	}

	public void setVitaminas(String vitaminas) {
		this.vitaminas = vitaminas;
	}

	@Override
	public String toString() {
		return nombre;
	}

}