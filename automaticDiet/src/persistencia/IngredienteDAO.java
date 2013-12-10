package persistencia;

import java.util.List;

import javax.persistence.Query;

import modelo.Ingrediente;

public class IngredienteDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public List<Ingrediente> getIngredientes(){
		
		try{
			Query q=entityManager.createQuery("SELECT i "
					+ "FROM Ingrediente i "
					+ "GROUP BY i.nombre");
			return (List<Ingrediente>)q.getResultList();
			
		}catch(Exception e){
			System.out.println("Error en getIngrediente: "+e);
			return null;
		}
		
	}
	
	public Ingrediente findIngrediente(int id){
		
		try{
			Query q=entityManager.createQuery("SELECT i "
					+ "FROM Ingrediente i "
					+ "WHERE i.id = :idIngrediente");
			q.setParameter("idIngrediente", id);
			return (Ingrediente) q.getSingleResult();
			
		}catch(Exception e){
			System.out.println("Error en findIngrediente(): "+e);
			return null;
		}
		
		
	}

}
