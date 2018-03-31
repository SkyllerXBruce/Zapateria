package Presentacion;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Modelo.Componentes;
import Modelo.Usuario;
import Negocio.ControlVendedores;

@SuppressWarnings("serial")
public class VistaAgregarUsuario extends JFrame {

	// Variables Globales
	private JButton agregar, regresar;
	private JTextField tusuario;
	private JPasswordField tcontrasena;
	private String[] datosVendedor;
	private ControlVendedores control;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAgregarUsuario frame = new VistaAgregarUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaAgregarUsuario
	public VistaAgregarUsuario() {
		// Propiedades de la Ventana
		setSize(480, 320);
		setLocationRelativeTo(null);
		setResizable(false);
		confirmarSalida();
		iniciarComponentes();
	}

	// Si Da Click en Cerrar (X) Pregunta si Desea Cerrar la Sesion de la Aplicacion
	private void confirmarSalida() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente Desea Cancelar el Agregar un Vendedor?",
						"¿Cancelar Agregar Vendedor?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.muestraVistaAdministarVendedores();
					control.limpiarDatos("AgregarVendedor");
					control.limpiarDatos("AgregarUsuario");
					dispose();
				}
			}
		});
	}

	// Creamos y Agregamos los Componetes de la Ventana
	private void iniciarComponentes() {
		// Creamos la Instancia del JPanel Así como de Algunos Componentes
		JPanel panel = new JPanel();
		Componentes componente = new Componentes();
		JLabel titulo, subtitulo, usuario, contrasena;

		// Modificamos Propiedades de JPanel y lo Agregamos a la Ventana
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);

		// Imagen del Boton regresar
		ImageIcon imgIcon = new ImageIcon(VistaAgregarUsuario.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del JButton
		agregar = componente.creaBoton("Agregar", 260, 240, 150, 30);
		regresar = componente.creaBoton("", 40, 230, 50, 50);
		regresar.setIcon(imgIcon);

		// Creamos y Agregamos las Propiedades del JLabel
		titulo = componente.creaEtiqueta("Asignar Usuario y Cotraseña", 30, 40, 480, 35, 30);
		subtitulo = componente.creaEtiqueta("al Vendedor", 130, 70, 480, 35, 30);
		usuario = componente.creaEtiqueta("Usuario:", 40, 140, 140, 25, 16);
		contrasena = componente.creaEtiqueta("Contraseña:", 40, 180, 140, 25, 16);

		// Creamos y Agregamos las Propiedades del JTextField
		tusuario = componente.creaCuadroTexto(190, 140, 270, 25, 14);
		tcontrasena = componente.creaCuadroPassword(190, 180, 270, 25, 14);

		// Se Realiza Acciones de los Componentes
		accionesComponentes();
		
		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(subtitulo);
		panel.add(usuario);
		panel.add(tusuario);
		panel.add(contrasena);
		panel.add(tcontrasena);
		panel.add(agregar);
		panel.add(regresar);
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Accion del boton Aceptar
		agregar.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent e) {
				String user, pass, id, nombre, curp, correo, telefono;
				user = tusuario.getText();
				pass = tcontrasena.getText();
				id = control.generaId();
				nombre = datosVendedor[0] + " " + datosVendedor[1] + " " + datosVendedor[2];
				curp = datosVendedor[3];
				correo = datosVendedor[4];
				telefono = datosVendedor[5];
				if (!user.isEmpty())
					if (!pass.isEmpty()) {
						if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de Agregar Vendedor " + nombre,
								"¿Agregar?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
							Usuario vendedor = new Usuario(id, user, pass, "Vendedor", nombre, curp, correo, telefono);
							if (control.agregaVendedor(vendedor)) {
								JOptionPane.showMessageDialog(null,
										"El Vendedor " + nombre + " Fue Agregado Correctamente");
								control.muestraVistaAdministarVendedores();
								control.limpiarDatos("AgregarVendedor");
								control.limpiarDatos("AgregarUsuario");
								dispose();
							}
						}
					} else
						JOptionPane.showMessageDialog(null, "Es Necesario Ingresar el Usuario");
				else
					JOptionPane.showMessageDialog(null, "Es Necesario Ingresar la Contraseña");
			}
		});

		// Accion de Boton Regresar
		regresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAgregarVendedor();
				control.limpiarDatos("AgregarUsuario");
				dispose();
			}
		});
	}

	// Metodo para llenar la tabla con los Datos del Vendedor
	public void obtenerDatosVendedor(String[] datosvendedor) {
		this.datosVendedor = datosvendedor;
	}

	// Metodo que limpia los TextFields
	public void limpiarDatosAgregarUsuario() {
		tusuario.setText("");
		tcontrasena.setText("");
	}

	// Obtenemos la Instancia del Control Vendedor
	public void setControl(ControlVendedores controlvendedores) {
		this.control = controlvendedores;
	}
}
