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
			System.out.println("Error en el método getDenuncias: "+e);
			return null;

		}
	}
}
