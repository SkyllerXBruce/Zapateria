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
	private JTextField textUser, textPassword;
	private JButton btnIngresar;
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
		// creamos el panel y lo agregamos a la ventana
		JPanel panel = new JPanel();
		Componentes componente = new Componentes();
		String items[] = { "Selecciona...", "Vendedor", "Administrador" };
		JLabel lblZapateria, lblUsuario, lblpass, lblCargo, lblIcon;

		// Propiedades del Panel y se Agrega a la Ventana
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);

		// Imagen del Login
		ImageIcon imgIcon = new ImageIcon(VistaLogin.class.getResource("userIcon.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del Método creaBoton
		btnIngresar = componente.creaBoton("Ingresar", 327, 243, 117, 29);

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		lblZapateria = componente.creaEtiqueta("Zapateria el Ahorro", 140, 20, 280, 25, 26);
		lblUsuario = componente.creaEtiqueta("Usuario:", 30, 150, 80, 20, 14);
		lblpass = componente.creaEtiqueta("Contraseña:", 30, 200, 100, 20, 14);
		lblCargo = componente.creaEtiqueta("Selecciona Cargo", 200, 70, 140, 20, 16);
		lblIcon = componente.creaEtiqueta("", 20, 30, 100, 100, 14);
		lblIcon.setIcon(imgIcon);

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de los TextFields
		// Como de la Letra
		textUser = componente.creaCuadroTexto(130, 150, 214, 26, 14);
		textPassword = componente.creaCuadroPassword(130, 200, 214, 26, 14);

		// Propiedades del Componente JComboBox
		choice = componente.creaComboBox(items, 200, 100, 140, 25, 14);

		// Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panel.add(lblZapateria);
		panel.add(lblIcon);
		panel.add(lblUsuario);
		panel.add(lblpass);
		panel.add(lblCargo);
		panel.add(textUser);
		panel.add(textPassword);
		panel.add(btnIngresar);
		panel.add(choice);
	}

	// Realiza las Acciones de los Componentes
	private void accionesComponentes() {
		// Agregamos un caretListener para que se actualice el textfield sobre cada
		// entrada
		textUser.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				control.recibeUsuario(textUser.getText());
			}
		});

		// Agregamos un caretListener para que se actualice el textfield sobre cada
		// entrada
		textPassword.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				control.recibeContraseña(textPassword.getText());
			}
		});

		// Accion del boton Ingresar
		btnIngresar.addActionListener(new ActionListener() {
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
		textUser.setText("");
		textPassword.setText("");
		choice.setSelectedIndex(0);
	}

	// Obtenemos la Instancia del Control Login
	public void setControl(ControlLogin controllog) {
		this.control = controllog;
	}
}
