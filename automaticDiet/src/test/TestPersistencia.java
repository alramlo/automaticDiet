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
		System.out.println("------------PRUEBA PLATOBYNOMBRE--------------------");
		PlatoDAO platoDao= new PlatoDAO();
		Plato pl=platoDao.platoByNombre("Macedonia");
		System.out.println("Plato encontrado: "+pl.getNombre());
		
		System.out.println("------------PRUEBA INGREDIENTESPORPLATOS--------------------");
		platoDao = new PlatoDAO();
		List<Ingrediente> li = platoDao.IngredientesPorPlato("Macedonia");
		if(li!=null){
			System.out.println("Numero de ingredientes= "+li.size());
		}
		else
			System.out.println("Numero de ingredientes= "+0);
	
		System.out.println("------------PRUEBA TODOSPLATOS--------------------");

		System.out.println("Todos los platos: "+platoDao.todosPlatos().size());
		
		System.out.println("------------PRUEBA DIETA SEMANAL--------------------");
		UsuarioDAO usuarioDao = new UsuarioDAO();
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
		Date fechaI = f.parse("2013/10/21");
		System.out.println("Fecha inicial: "+fechaI);
		
		SimpleDateFormat f2 = new SimpleDateFormat("yyyy/MM/dd");
		Date fechaF = f2.parse("2013/10/22");
		System.out.println("Fecha final: "+fechaF);
		
		List<Plato> listaPlatos = usuarioDao.dietaSemanal(1, fechaI, fechaF);
		System.out.println("Numero de platos--> "+listaPlatos.size());
		for(Plato p:listaPlatos){
			System.out.println("Plato: "+p.getId());
			
		}
		
		
	}

}
