package persistencia;

import java.util.List;

import javax.persistence.Query;

import modelo.Ingrediente;

public class IngredienteDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public List<Ingrediente> getIngredientes(){
		
		try{
			Query q=entityManager.createQuery("SELECT i "
					+ "FROM Ingrediente i ");
			return (List<Ingrediente>)q.getResultList();
			
		}catch(Exception e){
			System.out.println("Error en getIngrediente: "+e);
			return null;
		}
		
	}

}
