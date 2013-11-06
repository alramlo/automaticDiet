package persistencia;

import java.util.List;

import javax.persistence.Query;

import modelo.Ciudad;

public class CiudadDAO extends AbstractDAO{

	public List<Ciudad> getCiudades(String p){
		try{
			Query q;
			q = entityManager.createQuery("SELECT c FROM Ciudad c WHERE c.pais.nombre=:p");
			q.setParameter("p", p);
			return (List<Ciudad>)q.getResultList();
			}catch(Exception e){
				System.out.println("Error: "+e);
				return null;
				
			}
	}
}
