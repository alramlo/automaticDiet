package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modelo.Mensaje;
import persistencia.MensajeDAO;

public class VerMensajeDenunciado extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private MensajeDAO mensajeDAO;
	private Mensaje mensaje;
//	private GestionGruposPorAdmin gga;
	

	/**
	 * Create the dialog.
	 */
	public VerMensajeDenunciado(Mensaje m) {
		mensajeDAO = new MensajeDAO();
		this.mensaje = m;
//		this.gga = gga;
		
		setTitle("Post Denunciado");
		setBounds(100, 100, 450, 300);
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
				JLabel lblPepeSancho = new JLabel(mensaje.getUsuario().getNombre()+" "+mensaje.getUsuario().getApellidos());
				lblPepeSancho.setFont(new Font("Tahoma", Font.BOLD, 11));
				panel.add(lblPepeSancho);
			}
			{
				JLabel lblFecha = new JLabel("                            ");
				panel.add(lblFecha);
			}
			{
				JLabel lblForoOGrupo = new JLabel("");
				lblForoOGrupo.setForeground(Color.DARK_GRAY);
				lblForoOGrupo.setFont(new Font("Tahoma", Font.BOLD, 11));
				if(mensaje.getForo()!= null)
					lblForoOGrupo = new JLabel(mensaje.getForo().getTema());
				//if(mensaje.getGrupo()!= null)
				else
					lblForoOGrupo = new JLabel(mensaje.getGrupo().getNombre());
				
				panel.add(lblForoOGrupo);
			}
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(panel, BorderLayout.SOUTH);
			{
				JLabel lblFecha_1 = new JLabel(mensaje.getFecha().toString());
				lblFecha_1.setHorizontalAlignment(SwingConstants.LEFT);
				lblFecha_1.setForeground(Color.BLUE);
				lblFecha_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
				panel.add(lblFecha_1);
			}
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setBorder(new LineBorder(new Color(64, 64, 64), 1, true));
			textArea.setEditable(false);
			textArea.setText(mensaje.getContenido());
			contentPanel.add(textArea, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Eliminar Post");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						Object[] options = {"SI","NO"};
						
						Icon icon = new ImageIcon("src/iconos/eliminar-icono-4912-32.png");
	
						int respuesta = JOptionPane.showOptionDialog(null, "¿Desea eliminar definitivamente el post?", "CONFIRMACIÓN", JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, icon, options, options[0]);
						if (respuesta == 0 )
						{
							Boolean ok = mensajeDAO.deleteMensaje(mensaje);
							if(ok)
							{
								JOptionPane.showMessageDialog(null, "Eliminado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "No se ha podido eliminar el mensaje", "Error", JOptionPane.ERROR_MESSAGE);
								dispose();
							}
						}
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				cancelButton.setIcon(new ImageIcon(VerMensajeDenunciado.class.getResource("/iconos/remove24.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
