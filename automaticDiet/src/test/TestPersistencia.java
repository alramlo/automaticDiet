package test;

import java.util.List;

import modelo.Caracteristica;
import modelo.Ingrediente;
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
		
		//PlatoDAO platoDao= new PlatoDAO();
		//Plato pl=platoDao.platoByNombre("Macarrones");
		//System.out.println("Plato encontrado: "+pl);
		
		PlatoDAO platoDao = new PlatoDAO();
		List<Ingrediente> li = platoDao.IngredientesPorPlato("Macarrones");
		if(li!=null){
			System.out.println("Numero de ingredientes= "+li.size());
			System.out.println("Ingredientes: "+li);
		}
		else
			System.out.println("Numero de ingredientes= "+0);
	}

}
