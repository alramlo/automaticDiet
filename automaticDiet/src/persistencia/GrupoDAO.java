package persistencia;

import java.util.List;

import javax.persistence.Query;

import modelo.Grupo;

public class GrupoDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<Grupo> getGrupos(Grupo grupo) {
		try {
			Query q;
			q = entityManager
					.createQuery("SELECT g FROM Grupo g WHERE g.nombre=:nom");
			q.setParameter("nom", grupo.getNombre());
			return (List<Grupo>) q.getResultList();
		} catch (Exception e) {
			System.out.println("Error: "+e);
			return null;

		}
	}
	
	 

}
