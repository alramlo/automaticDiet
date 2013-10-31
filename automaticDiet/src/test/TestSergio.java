package test;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import modelo.Caracteristica;
import persistencia.CaracteristicaDAO;
public class TestSergio {


	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
	
		System.out.println("------------PRUEBA PLATOBYNOMBRE--------------------");
		CaracteristicaDAO caracteristicaDao= new CaracteristicaDAO();
		List<Caracteristica> pl=caracteristicaDao.getCaracteristicas();
		Iterator<Caracteristica> itCaracteristica = pl.iterator();
		while(itCaracteristica.hasNext()){
		//for(int i=0; itCaracteristica.hasNext(); i++){
			Caracteristica c = itCaracteristica.next();
			System.out.println(c.getNombre());
		}
	}

}
