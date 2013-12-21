package persistencia;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import modelo.Dieta;
import modelo.PlatoDieta;

public class PlatoDietaDAO extends AbstractDAO{

	public void setPlatosDietas(List<Integer> codigoPlatoDieta,Integer codigoDieta, GregorianCalendar fechaIni, int numPlatos) {
		EntityTransaction trx = entityManager.getTransaction();
		PlatoDieta platoDieta; 
		try{
			trx.begin();
			GregorianCalendar fechaAux = (GregorianCalendar) fechaIni.clone();
			for(int i = 0; i < codigoPlatoDieta.size(); i++){
				platoDieta=entityManager.find(PlatoDieta.class, codigoPlatoDieta.get(i));
				fechaAux.add(fechaIni.DATE,+i/4); 
				platoDieta.setDia(fechaAux.getTime());
				fechaAux=(GregorianCalendar) fechaIni.clone();
			}
			
			trx.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(trx.isActive())
				trx.rollback();
		}
	}

	public List<Integer> getIdsPlatoDietaByIdDieta(Integer codigo) {
		try{
			Query q;
			q = entityManager.createQuery("SELECT pd.id "
					+ "FROM PlatoDieta pd "
					+ "WHERE pd.dieta.id=:cod "
					+ "AND pd.dia=null");
			q.setParameter("cod", codigo);
			return (List<Integer>) q.getResultList();
			
			
		}catch(Exception e){
			System.out.println("Error:"+e);
			return null;
			
		}
	}

	public List<PlatoDieta> getPlatoDietaAmodicar(Integer codigo) {
		try{
			Query q;
			q = entityManager.createQuery("SELECT pd "
					+ "FROM PlatoDieta pd "
					+ "WHERE pd.dieta.id=:cod "
					+ "AND pd.dia=null");
			q.setParameter("cod", codigo);
			return (List<PlatoDieta>) q.getResultList();
			
			
		}catch(Exception e){
			System.out.println("Error:"+e);
			return null;
			
		}
	}

	public void setPlatoDietaOriginal(PlatoDieta platoDieta) {
		EntityTransaction trx = entityManager.getTransaction();
		try{
			trx.begin();
			entityManager.persist(platoDieta);
			entityManager.flush();
			trx.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(trx.isActive())
				trx.rollback();
		}
	}

}
