package persistencia;

import java.util.List;

import javax.persistence.Query;

import modelo.Mensaje;

public class MensajeDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<Mensaje> getMensajesByIdGrupo(int idGrupo) {
		try {
			Query q;
			q = entityManager.createQuery("SELECT m FROM Mensaje m WHERE m.grupo.id =:ident");
			q.setParameter("ident", idGrupo);
			
			return (List<Mensaje>) q.getResultList();
			
//			List<Mensaje> lm = new ArrayList<Mensaje>();
//			Mensaje m = new Mensaje();
//			Grupo g = new Grupo();
//			g.setId(idGrupo);
//			g.setNombre("Grupo cinco");
//			m.setContenido("Hola esto es el contenido");
//			m.setId(999);	
//			m.setGrupo(g);
//			lm.add(m);
//			return lm;
			
		} catch (Exception e) {
			System.out.println("Error: "+e);
			return null;

		}
	}
}
