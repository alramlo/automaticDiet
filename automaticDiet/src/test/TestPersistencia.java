package test;

import modelo.Caracteristica;
import modelo.Plato;
import persistencia.CaracteristicaDAO;
import persistencia.PlatoDAO;

public class TestPersistencia {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**CaracteristicaDAO dao = new CaracteristicaDAO();
		Caracteristica c = new Caracteristica();
		c.setNombre("Prueba");
		dao.crearCaracteristica(c);
		**/
		PlatoDAO platoDao= new PlatoDAO();
		Plato pl=platoDao.platoByNombre("Paella");
		System.out.println("Plato encontrado: "+pl);
		
	}

}
