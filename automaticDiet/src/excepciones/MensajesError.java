package excepciones;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MensajesError {
	
	private Properties prop;
	
	public MensajesError(){
	prop = new Properties();
	 
	try {
           //load a properties file
		prop.load(new FileInputStream("bin\\excepciones\\mensajes_error.properties"));

	} catch (IOException ex) {
		System.out.println("Error: No se encontro fichero de mensajes de error");
    }
	}
	public String getMensajeError(String key){
		return prop.getProperty(key);
	}
}
