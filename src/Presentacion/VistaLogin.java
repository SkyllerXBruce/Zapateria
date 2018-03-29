package Presentacion;

import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Negocio.ControlLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

@SuppressWarnings("serial")
public class VistaLogin extends JFrame {

	private JTextField textUser, textPassword;
	private JButton btnIngresar;
	private ControlLogin control;
	private Choice choice;

	/**
	 * Launch the application.
	 */
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

	// Crea la Ventana VistaLogin
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
		// Creamos e Instanciamos los Componentes de la Ventana
		JPanel contentPane = new JPanel();
		JLabel lblZapateria = new JLabel("Zapateria el Ahorro");
		JLabel lblUsuario = new JLabel("Usuario:");
		JLabel lblpass = new JLabel("Contraseña:");
		JLabel lblCargo = new JLabel("Selecciona Cargo");
		JLabel lblIcon = new JLabel("");
		btnIngresar = new JButton("Ingresar");
		textUser = new JTextField();
		textPassword = new JPasswordField();
		choice = new Choice();

		// Propiedades del Panel y se Agrega a la Ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Propiedades de la Etiqueta "Zapateria el Ahorro" y se Agrega al Panel
		lblZapateria.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		lblZapateria.setBounds(140, 20, 280, 25);
		contentPane.add(lblZapateria);

		// Propiedades de la Imagen y se Agrega al Panel
		lblIcon.setBounds(20, 30, 100, 100);
		contentPane.add(lblIcon);
		ImageIcon imgIcon = new ImageIcon(VistaLogin.class.getResource("userIcon.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);
		lblIcon.setIcon(imgIcon);

		// Propiedades de la Etiqueta "Usuario" y se Agrega al Panel
		lblUsuario.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblUsuario.setBounds(30, 150, 80, 20);
		contentPane.add(lblUsuario);

		// Propiedades de la Etiqueta "Contraseña" y se Agrega al Panel
		lblpass.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblpass.setBounds(30, 200, 100, 20);
		contentPane.add(lblpass);

		// Propiedades de la Etiqueta "Selecciona Cargo" y se Agrega al Panel
		lblCargo.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblCargo.setBounds(200, 70, 140, 20);
		contentPane.add(lblCargo);

		// Propiedades del textfield "Usuario" y se Agrega al Panel
		textUser.setBounds(130, 150, 214, 26);
		textUser.setColumns(10);
		contentPane.add(textUser);

		// Propiedades del textfield "Contraseña" y se Agrega al Panel
		textPassword.setBounds(130, 200, 214, 26);
		textPassword.setColumns(10);
		contentPane.add(textPassword);

		// Propiedades del Boton Ingresar y se Agrega al Panel
		btnIngresar.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		btnIngresar.setBounds(327, 243, 117, 29);
		contentPane.add(btnIngresar);

		// Propiedades del Componente Choice "Selecciona Cargo" y se Agrega al Panel
		choice.setForeground(Color.BLACK);
		choice.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		choice.setBounds(200, 100, 140, 25);
		contentPane.add(choice);

		// Se Agregan los Items al Componente choice
		choice.addItem("Selecciona...");
		choice.addItem("Vendedor");
		choice.addItem("Administrador");

		// Acciones de los Componentes
		accionesComponentes();
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
				control.recibeContrase�a(textPassword.getText());
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
						textUser.setText("");
						textPassword.setText("");
						choice.select(0);
						dispose();
					}
				} else if (choice.getSelectedItem().equals("Administrador")) {
					control.recibeTipo("Administrador");
					if(control.validaIngreso()) {
						textUser.setText("");
						textPassword.setText("");
						choice.select(0);
						dispose();
					}
				} else if (choice.getSelectedItem().equals("Selecciona..."))
					JOptionPane.showMessageDialog(null, "Selecciona tipo de ingreso, por favor");
			}
		});
	}

	// Instanciamos control a nuestra vista.
	public void setControl(ControlLogin controllog) {
		this.control = controllog;
	}
}
