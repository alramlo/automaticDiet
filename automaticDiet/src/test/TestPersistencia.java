package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import modelo.Caracteristica;
import modelo.Ingrediente;
import modelo.Plato;
import persistencia.CaracteristicaDAO;
import persistencia.PlatoDAO;
import persistencia.UsuarioDAO;

public class TestPersistencia {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

//		CaracteristicaDAO dao = new CaracteristicaDAO();
//		Caracteristica c = new Caracteristica();
//		c.setNombre("Prueba");
//		dao.crearCaracteristica(c);
//		
//		
		//PlatoDAO platoDao= new PlatoDAO();
		//Plato pl=platoDao.platoByNombre("Macarrones");
		//System.out.println("Plato encontrado: "+pl);
		
//		PlatoDAO platoDao = new PlatoDAO();
//		List<Ingrediente> li = platoDao.IngredientesPorPlato("Macarrones");
//		if(li!=null){
//			System.out.println("Numero de ingredientes= "+li.size());
//			System.out.println("Ingredientes: "+li);
//		}
//		else
//			System.out.println("Numero de ingredientes= "+0);
//	
//		System.out.println("Lista de platos: "+platoDao.todosPlatos());
		
		UsuarioDAO usuarioDao = new UsuarioDAO();
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd");
		Date fechaI = f.parse("2013-10-21");
		
		SimpleDateFormat f2 = new SimpleDateFormat("yyyy-mm-dd");
		Date fechaF = f2.parse("2013-11-03");
		
		List<Plato> listaPlatos = usuarioDao.dietaSemanal(1, fechaI, fechaF);
		System.out.println("Lista de platos--> "+listaPlatos);
		
		
	}

}
