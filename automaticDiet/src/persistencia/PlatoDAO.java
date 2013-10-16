package persistencia;

import java.util.List;

import javax.persistence.Query;

import modelo.Ingrediente;
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
	
	public List<Ingrediente> IngredientesPorPlato(String nombre){
		
		try{
			List result;
			List<Ingrediente> resultIn= null;
			Query q;
			q=entityManager.createQuery("Select i "
					+ "from Plato p, Ingrediente i, PlatoIngrediente pi "
					+ "where p.nombre=:nom and pi.idPlato=p.id and pi.idIngrediente=i.id ");
			q.setParameter("nom", nombre);
			result = q.getResultList();
			for(Object i:result){
				resultIn.add((Ingrediente)i);
			}
			return resultIn; }
			catch(Exception e){
				return null;
				
			}
		
	}

}
