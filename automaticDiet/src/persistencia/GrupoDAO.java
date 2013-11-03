package persistencia;

import java.util.List;

import javax.persistence.Query;

import modelo.Caracteristica;
import modelo.Grupo;

public class GrupoDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
//	public List<Grupo> getGrupos(String nombre, Caracteristica car) {
//		try {
//			Query q;
//			q = entityManager
//					.createQuery("SELECT g FROM Grupo g, Caracteristica c, Grupo_Caracteristicas gc"
//							+ "  WHERE g.nombre=:nom AND gc.id_caracteristicas=c.id "
//							+ " AND gc.id_grupo=g.id AND c.nombre=:carac");
//			q.setParameter("nom", nombre);
//			q.setParameter("carac", car.getNombre());
//			return (List<Grupo>) q.getResultList();
//		} catch (Exception e) {
//			System.out.println("Error: "+e);
//			return null;
//
//		}
//	}
	
	public List<Grupo> getGrupos() {
		try {
			Query q;
			q = entityManager
					.createQuery("SELECT g FROM Grupo g");
			return (List<Grupo>) q.getResultList();
		} catch (Exception e) {
			System.out.println("Error: "+e);
			return null;

		}
	}
	
	public List<Caracteristica> addCaracteristicas(Grupo grupo){
		try {
			Query q;
			q = entityManager.createQuery("SELECT c "
					+ "FROM Caracteristica c, GrupoCaracteristica gc "
					+ "WHERE gc.caracteristica.id=c.id AND "
					+ "gc.grupo.id=:ident");
				q.setParameter("ident", grupo.getId());
				
				return (List<Caracteristica>)q.getResultList(); 
		} catch (Exception e) {
			System.out.println("Error: "+e);
			return null;
		}
	}
}
