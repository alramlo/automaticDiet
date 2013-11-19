package persistencia;

import java.util.List;

import javax.persistence.Query;

import modelo.Denuncia;

public class DenunciaDAO extends AbstractDAO{


	@SuppressWarnings("unchecked")
	public List<Denuncia> getDenuncias() {
		try {
			Query q;
			q = entityManager.createQuery("SELECT d FROM Denuncia d, Mensaje m WHERE d.mensaje = m AND m.publicado = 0");
			
			return (List<Denuncia>) q.getResultList();
			
		} catch (Exception e) {
			System.out.println("Error en el método getDenuncias: "+e);
			return null;

		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Denuncia> getDenunciaPorGrupo(int idGrupo) {
		try {
			Query q;
			q = entityManager.createQuery("SELECT d FROM Denuncia d, Mensaje m WHERE d.mensaje.id = m.id AND m.publicado = 0 AND m.grupo.id =:ident");
			q.setParameter("ident", idGrupo);
			return (List<Denuncia>) q.getResultList();
			
		} catch (Exception e) {
			System.out.println("Error en el método getDenuncias: "+e);
			return null;

		}
	}
}
