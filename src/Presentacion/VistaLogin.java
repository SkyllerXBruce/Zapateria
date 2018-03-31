package Presentacion;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Negocio.ControlLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.event.CaretListener;

import Modelo.Componentes;

import javax.swing.event.CaretEvent;

@SuppressWarnings("serial")
public class VistaLogin extends JFrame {

	// Variables Globales
	@SuppressWarnings("rawtypes")
	private JComboBox choice;
	private JTextField tuser, tpass;
	private JButton ingresar;
	private ControlLogin control;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLogin frame = new VistaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaLogin
	public VistaLogin() {
		// Propiedades de la Ventana
		setTitle("Ventana Principal");
		setBounds(100, 100, 460, 320);
		setResizable(false);
		setLocationRelativeTo(null);
		confirmarSalida();
		iniciarComponentes();
	}

	// Si Da Click en Cerrar (X) Pregunta si Desea Salir de la Aplicacion
	private void confirmarSalida() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente salir de la Aplicacion?",
						"Salir de la Aplicacion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
	}

	// Creamos y Agregamos los Componetes de la Ventana
	private void iniciarComponentes() {
		// Creamos la Instancia del JPanel Así como de Algunos Componentes
		JPanel panel = new JPanel();
		Componentes componente = new Componentes();
		String items[] = { "Selecciona...", "Vendedor", "Administrador" };
		JLabel zapateria, usuario, pass, cargo, imagen;

		// Modificamos Propiedades de JPanel y lo Agregamos a la Ventana
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);

		// Imagen del Login
		ImageIcon imgIcon = new ImageIcon(VistaLogin.class.getResource("userIcon.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del JButton
		ingresar = componente.creaBoton("Ingresar", 327, 243, 117, 29);

		// Creamos y Agregamos las Propiedades del JLabel
		zapateria = componente.creaEtiqueta("Zapateria el Ahorro", 140, 20, 280, 25, 26);
		usuario = componente.creaEtiqueta("Usuario:", 30, 150, 80, 20, 14);
		pass = componente.creaEtiqueta("Contraseña:", 30, 200, 100, 20, 14);
		cargo = componente.creaEtiqueta("Selecciona Cargo", 200, 70, 140, 20, 16);
		imagen = componente.creaEtiqueta("", 20, 30, 100, 100, 14);
		imagen.setIcon(imgIcon);

		// Creamos y Agregamos las Propiedades del JTextField y JPasswordField
		tuser = componente.creaCuadroTexto(130, 150, 214, 26, 14);
		tpass = componente.creaCuadroPassword(130, 200, 214, 26, 14);

		// Creamos y Agregamos las Propiedades del JCombobox
		choice = componente.creaComboBox(items, 200, 100, 140, 25, 14);

		// Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panel.add(zapateria);
		panel.add(imagen);
		panel.add(usuario);
		panel.add(pass);
		panel.add(cargo);
		panel.add(tuser);
		panel.add(tpass);
		panel.add(ingresar);
		panel.add(choice);
	}

	// Realiza las Acciones de los Componentes
	private void accionesComponentes() {
		// Agregamos un caretListener para que se actualice el textfield sobre cada
		// entrada
		tuser.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				control.recibeUsuario(tuser.getText());
			}
		});

		// Agregamos un caretListener para que se actualice el textfield sobre cada
		// entrada
		tpass.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				control.recibeContraseña(tpass.getText());
			}
		});

		// Accion del boton Ingresar
		ingresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tomamos parámetros para validar y dependiendo de esto mostramos errores o la
				// ventana siguiente.
				if (choice.getSelectedItem().equals("Vendedor")) {
					control.recibeTipo("Vendedor");
					if (control.validaIngreso()) {
						limpiaDatosLogin();
						dispose();
					}
				} else if (choice.getSelectedItem().equals("Administrador")) {
					control.recibeTipo("Administrador");
					if (control.validaIngreso()) {
						limpiaDatosLogin();
						dispose();
					}
				} else if (choice.getSelectedItem().equals("Selecciona..."))
					JOptionPane.showMessageDialog(null, "Selecciona tipo de ingreso, por favor");
			}
		});
	}

	// Limpia los Datos Ingresados para Acceder
	public void limpiaDatosLogin() {
		tuser.setText("");
		tpass.setText("");
		choice.setSelectedIndex(0);
	}

	// Obtenemos la Instancia del Control Login
	public void setControl(ControlLogin controllog) {
		this.control = controllog;
	}
}
