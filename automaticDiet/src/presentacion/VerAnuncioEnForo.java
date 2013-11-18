package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

public class VerAnuncioEnForo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private int anuncio;
	

	/**
	 * Create the dialog.
	 */
	public VerAnuncioEnForo(int i) {
		this.anuncio = i;
		
		setTitle("Anuncio");
		setBounds(100, 100, 683, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblCreadorDelPost = new JLabel("Creado por:");
				panel.add(lblCreadorDelPost);
			}
			{
				JLabel lblFecha = new JLabel("AutomaticDIET");
				panel.add(lblFecha);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			JTextArea textArea = new JTextArea();
			scrollPane.setViewportView(textArea);
			textArea.setBorder(new LineBorder(new Color(64, 64, 64), 1, true));
			textArea.setEditable(false);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			
			if (anuncio == 0)
				textArea.setText("Este foro representa un punto de encuentro entre todos los clientes de AutomaticDIET que quieran poner en común sus conocimientos, "
						+ "gustos, opiniones, etc., en cuanto a nuestros productos y servicios. Además, se trata de un medio para que podamos conoceros mejor a "
						+ "través de vuestras sugerencias y mejorar nuestra atención al cliente día a día.\n\nLos valores fundamentales que rigen este foro son:"
						+ "\n\nRespeto: Sé respetuoso con los miembros del Foro sin importar su edad, raza, religión o sexo.\n\nTolerancia: Sé tolerante con las "
						+ "opiniones que no compartas y habla de ello de forma constructiva.\n\nGestión de posts:\n\nA pesar de que intentaremos revisar todos "
						+ "los post, no siempre es posible debido al gran número de posts y respuestas publicadas a diario. Si en algún momento encuentras "
						+ "algún mensaje que incumple estas reglas del Foro, por favor reporta este post como inadecuado desde el botón \"informar\" con el "
						+ "comentario respecto a ese comentario. Con este método se enviará un mensaje privado al Moderador o Administrador incluyendo el enlace "
						+ "al mensaje.\n\nLos Moderadores y Administradores no siempre responderemos a todos los comentarios pero sí intentaremos leerlos todos."
						+ " Los mensajes serán respondidos en torno a las 24-48 horas aproximadamente desde el momento que son publicados. Por favor se paciente "
						+ "porque no podremos responder a todo inmediatamente, en especial fuera de nuestro horario de respuesta que será el siguiente: Lunes a "
						+ "Viernes laborables de 10 a 20 horas\n\nAdemás, nosotros somos humanos por lo que nos reservamos el derecho a cometer errores. Por favor,"
						+ " si ves que hemos cometido alguno notifícanoslo y lo corregiremos.\n\nEl acceso y el uso del foro de AutomaticDIET quedarán sujetos a las "
						+ "siguientes condiciones generales: El usuario deberá ser mayor de 14 años de edad para poder acceder al Foro de AutomaticDIET. En caso de no "
						+ "cumplir el requisito de edad, el usuario deberá  certificar/confirmar el consentimiento de sus padres o tutores legales para poder "
						+ "participar en esta comunidad\n\nSe requiere que todos los usuarios proporcionen su fecha de nacimiento verdadera para fomentar la "
						+ "autenticidad y permitir el acceso solamente a contenido apropiado a la edad del usuario. Si el equipo del foro observase incumplimiento"
						+ " de la misma por parte del usuario, le sería denegado inmediatamente el acceso por parte de un administrador.\n\nEn cualquier caso, "
						+ "asegúrate de confirmar dicho requisito, bien durante el proceso de registro o durante el acceso a Foro AutomaticDIET, si ya estuvieras "
						+ "registrado antes del 07/06/2012.");
			else if (anuncio == 1)
					textArea.setText("- El participante debe dejar claro el destinatario del mensaje, bien sea para el foro, bien para el anfitrión.\n\n- Conviene recordar "
							+ "que los mensajes van dirigidos a personas: aunque el medio es impersonal, sus participantes no lo son.\n\n- El foro se enriquece con las "
							+ "contribuciones de todos; por eso recomendamos la participación activa.\n\n- Hay que intentar resumir el contenido del mensaje en los "
							+ "campos «título» o «asunto» —según se conteste un mensaje o se inicie una nueva discusión—, para agilizar su lectura.\n\n- Si contesta a "
							+ "un mensaje publicado, no es necesario citarlo íntegramente, ya que todos los miembros del foro pueden leer el original.\n\n- Por cortesía"
							+ ", es recomendable agradecer la ayuda de aquellos participantes del foro que nos han proporcionado una información solicitada.\n\n- El "
							+ "participante puede escribir los mensajes en cualquiera de las lenguas oficiales del Estado español: español, gallego, catalán, "
							+ "valenciano y vasco; el CVC no se compromete a incluir mensajes en otras lenguas. Los anfitriones responderán siempre en español.\n\n- Un "
							+ "usuario puede contestar un mensaje o participar en un hilo que ya ha perdido vigencia. En esos casos, por cortesía y en beneficio "
							+ "de la propia discusión, se recomienda que el usuario, de modo paralelo a la publicación en el foro, avise mediante correo "
							+ "electrónico a la persona a la que responde de que hay un nuevo mensaje en ese viejo debate.\n\n- Varios usuarios se han interesado "
							+ "por el origen geográfico de otros participantes (que no siempre está indicado en la dirección electrónica). Recomendamos que, "
							+ "siempre que se firme un mensaje, al final se incluya el país de origen, con el fin de facilitar esa información a los contertulios.");
				else 
					textArea.setText("Hay dos maneras de publicar un mensaje: como tema nuevo o como respuesta a cualquiera de los mensajes ya existentes en el "
							+ "foro.\n\n- Para proponer un nuevo tema de discusión: pulse sobre el botón , o bien use el formulario que encontrará al pie de la "
							+ "página con la lista de mensajes.\n\n- Para responder a un mensaje cualquiera, elija el mensaje al que quiere contestar y "
							+ "ábralo (pulse el número que aparece a su izquierda y que lo identifica). Una vez abierto el mensaje, use el formulario que "
							+ "aparecerá al pie del mismo.\n\n- Todos los mensajes llegarán en primer lugar al moderador o anfitrión, que es quien decide "
							+ "su publicación en el foro. No obstante, si desea comunicarse con él exclusivamente, escriba «Anfitrión» en el asunto o en el "
							+ "título. De este modo, su mensaje no aparecerá publicado.\n\n- Para más información puede consultar la página de ayuda.");
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				buttonPane.add(btnCerrar);
			}
		}
	}

}
