package test;

import java.text.ParseException;
import java.util.List;

import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;
import persistencia.PlatoDAO;
import persistencia.UsuarioDAO;
import presentacion.NuevoPlato2;
import modelo.Plato;
import modelo.PlatoDieta;
import modelo.Usuario;

public class TestAlberto {

	
	public static void main(String[] args) throws Exception {
		PlatoDAO platoDAO = new PlatoDAO();
		Plato p = platoDAO.platoByNombre("Macedonia");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> u = usuarioDAO.getUsuarioPorNombre("Alberto");
		NuevoPlato2 ventana = new NuevoPlato2(p,u.get(0));
		//NuevoPlato2 ventana = new NuevoPlato2(null,u.get(0));
		ventana.setVisible(true);
	}
}
