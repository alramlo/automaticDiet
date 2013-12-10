package persistencia;

import java.util.List;

import javax.persistence.Query;

import modelo.Caracteristica;
import modelo.Grupo;
import modelo.Interes;
import modelo.Usuario;

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
	
	public List<Interes> addIntereses(Grupo grupo){
		try {
			Query q;
			q = entityManager.createQuery("SELECT i "
					+ "FROM Interes i, GrupoInteres gi "
					+ "WHERE gi.interes.id=i.id AND "
					+ "gi.grupo.id=:ident");
				q.setParameter("ident", grupo.getId());
				
				return (List<Interes>)q.getResultList(); 
		} catch (Exception e) {
			System.out.println("Error: "+e);
			return null;
		}
	}

	public List<Grupo> getGrupos(Usuario user) {
		try {
			Query q;
			q = entityManager.createQuery("SELECT g "
					+ "FROM Usuario u, UsuarioGrupo ug, Grupo g "
					+ "WHERE ug.usuario.id=u.id AND "
					+ "ug.grupo.id=g.id AND u.id=:ident");
				q.setParameter("ident", user.getId());
				
				return (List<Grupo>)q.getResultList(); 
		} catch (Exception e) {
			System.out.println("Error: "+e);
			return null;
		}
	}
}
