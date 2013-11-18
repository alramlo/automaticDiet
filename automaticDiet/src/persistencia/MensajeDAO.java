package persistencia;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import modelo.Mensaje;

public class MensajeDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<Mensaje> getMensajesByIdGrupo(int idGrupo) {
		try {
			Query q;
			q = entityManager.createQuery("SELECT m FROM Mensaje m WHERE m.grupo.id =:ident ORDER BY m.fecha DESC");
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
	
	public Mensaje getMensajeById(int idMensaje) {
		try {
			Query q;
			q = entityManager.createQuery("SELECT m FROM Mensaje m WHERE m.id =:ident");
			q.setParameter("ident", idMensaje);
			
			return (Mensaje) q.getSingleResult();
			
		} catch (Exception e) {
			System.out.println("Error: "+e);
			return null;

		}
	}
	
	public Boolean deleteMensaje(Mensaje mensajeBorrado) {

		EntityTransaction trx = entityManager.getTransaction();
		try{
			
			trx.begin();
			Mensaje mens = entityManager.find(Mensaje.class, mensajeBorrado.getId());
			mens.setPublicado(1);
	        trx.commit();
	        return true;
	        
		}catch (Exception e) {
			trx.rollback();
	        e.printStackTrace();
	        return false;
	    }
	}
}
