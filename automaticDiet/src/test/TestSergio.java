package test;
import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import persistencia.UsuarioDAO;
public class TestSergio {


	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
	
//		CaracteristicaDAO caracteristicaDao= new CaracteristicaDAO();
//		List<Caracteristica> pl=caracteristicaDao.getCaracteristicas();
//		Iterator<Caracteristica> itCaracteristica = pl.iterator();
//		while(itCaracteristica.hasNext()){
//			Caracteristica c = itCaracteristica.next();
//			System.out.println(c.getNombre());
//		}
		
//		InteresDAO interesDao= new InteresDAO();
//		List<Interes> pl2=interesDao.getIntereses();
//		Iterator<Interes> itIntereses = pl2.iterator();
//		while(itIntereses.hasNext()){
//			Interes i = itIntereses.next();
//			System.out.println(i.getNombre());
//		}
		
//		Grupo prueba = new Grupo();
//		prueba.setNombre("AlimentacionEsVida");
//		GrupoDAO grupo = new GrupoDAO();
//		List<Grupo> pl3 = grupo.getGrupos();
		//Grupo pl4 = grupo.addCaracteristicas(pl3.get(0));
		//System.out.println(pl4.getCaracteristicas().size());
		
//		Caracteristica c = new Caracteristica();
//		c.setNombre("FUTBOL");
//		List<Grupo> pl3 = grupo.getGrupos("AlimentacionEsVida",c);
//		Iterator<Grupo> itGrupos = pl4.iterator();
//		while(itGrupos.hasNext()){
//			Grupo g = itGrupos.next();
//			System.out.println(g.getCaracteristicas().size());
//		}
		
		//UsuarioDAO u = new UsuarioDAO();
		//System.out.println(u.getIdUsuario("Sergio", "Campoy Arnau"));
		
//		PlatoDAO p = new PlatoDAO();
//		Usuario u = new Usuario();
//		u.setId(1);
//		List<Plato> l = p.buscarPlatos("Cereales con leche", u);
//		System.out.println(l.size());
		
		//CiudadDAO ciudad = new CiudadDAO();
		//System.out.println(ciudad.getCiudades("Italia").get(0).getNombre());
//		Plato p = new Plato();
//		p.setId(1);
//		PlatoDAO pd = new PlatoDAO();
//		System.out.println(pd.getCaloriasPorPlato(p));
//		PlatoDAO pl = new PlatoDAO();
//		Plato p = pl.platoByNombre("Macedonia");
//		System.out.println(p.getNombre());
		
//		DietaDAO d = new DietaDAO();
//		Usuario u = new Usuario();
//		DateTime dt = new DateTime();
//		u.setId(1);
//		System.out.println("Platos "+d.getSemanasDieta(, u ).size());
		
		Calendar calendar = new GregorianCalendar(2013,9,21); 
		Calendar calendar2 = new GregorianCalendar(2013,9,27); 
		Date d = new Date(calendar.getTimeInMillis());
		Date d2 = new Date(calendar2.getTimeInMillis());
		System.out.println(d.toString());
		System.out.println(d2.toString());
		UsuarioDAO dD = new UsuarioDAO();
		System.out.println("platos "+dD.dietaSemanal(1, d, d2).size());
		
	}

}
