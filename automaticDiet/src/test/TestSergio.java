package test;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import modelo.Caracteristica;
import modelo.Grupo;
import persistencia.GrupoDAO;
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
		Grupo prueba = new Grupo();
		prueba.setNombre("AlimentacionEsVida");
		GrupoDAO grupo = new GrupoDAO();
		Caracteristica c = new Caracteristica();
		c.setNombre("FUTBOL");
		List<Grupo> pl3 = grupo.getGrupos("AlimentacionEsVida",c);
		Iterator<Grupo> itGrupos = pl3.iterator();
		while(itGrupos.hasNext()){
			Grupo g = itGrupos.next();
			System.out.println(g.getNombre());
		}
	}

}
