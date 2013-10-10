package excepciones;

public class DominioExcepcion extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DominioExcepcion(DAOExcepcion e){
		super(e);
	}
	public DominioExcepcion(String e){
		super(e);
	}
	
	
}
