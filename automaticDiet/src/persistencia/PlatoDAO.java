package persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import modelo.Ingrediente;
import modelo.Plato;

public class PlatoDAO extends AbstractDAO {

	public Plato platoByNombre(String nombre) {
		try {
			Query q;
			q = entityManager
					.createQuery("SELECT p FROM Plato p WHERE p.nombre=:nom");
			q.setParameter("nom", nombre);
			return (Plato) q.getSingleResult();
		} catch (Exception e) {
			return null;

		}

	}
	
	public List<Plato> todosPlatos (){
		try{
		Query q;
		q = entityManager.createQuery("SELECT p FROM Plato p");
		return (List<Plato>)q.getResultList();
		}catch(Exception e){
			System.out.println("Error: "+e);
			return null;
			
		}
		
	}
	

	public List<Ingrediente> IngredientesPorPlato(String nombre) {

		try {
			List result;
			List<Ingrediente> resultIn = new ArrayList<Ingrediente>();
			Query q;
			Plato p = this.platoByNombre(nombre);
			if (p != null) {
				System.out.println("Plato: " + p.getNombre());
				q = entityManager.createQuery("SELECT i "
						+ "FROM Ingrediente i, PlatoIngrediente pi "
						+ "WHERE pi.id.idPlato=:idP AND "
						+ "pi.id.idIngrediente=i.id");
				q.setParameter("idP", p.getId());
				result = q.getResultList();
				for (Object i : result) {
					resultIn.add((Ingrediente) i);
				}
				return resultIn;

			} else {
				// System.out.println("No se encuentra el plato");
				return null;
			}
		} catch (Exception e) {
			// System.out.println("Excepción: "+e);

			return null;

		}

	}

}
