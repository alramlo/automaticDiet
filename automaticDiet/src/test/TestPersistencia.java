package test;

import modelo.Caracteristica;
import persistencia.CaracteristicaDAO;

public class TestPersistencia {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CaracteristicaDAO dao = new CaracteristicaDAO();
		Caracteristica c = new Caracteristica();
		c.setNombre("Prueba");
		dao.crearCaracteristica(c);
		System.out.println("Terminado");
		
	}

}
