package persistencia;

import javax.persistence.Query;

import modelo.Plato;

public class PlatoDAO extends AbstractDAO {
	
	public Plato platoByNombre(String nombre){
		try{
		Query q;
		q=entityManager.createQuery("Select p from Plato p where p.nombre=:nom");
		q.setParameter("nom", nombre);
		return (Plato) q.getSingleResult();}
		catch(Exception e){
			return null;
			
		}
		
	}

}
