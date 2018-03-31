package Presentacion;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Componentes;
import Modelo.Usuario;
import Negocio.ControlAlmacen;
import Negocio.ControlLogin;
import Negocio.ControlVendedores;

@SuppressWarnings("serial")
public class VistaAdministrador extends JFrame {

	// Variables Globales
	private JButton vendedores, almacen, balance, cerrarsesion;
	private JLabel nombre, correo, telefono;
	private ControlVendedores control;
	private ControlLogin controllogin;
	private ControlAlmacen controlalmacen;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAdministrador frame = new VistaAdministrador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaAdministrador
	public VistaAdministrador() {
		// Propiedades de la Ventana
		setSize(500, 320);
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
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente Cerrar Sesión?", "Cerrar Sesión",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					controllogin.muestraVistaLogin();
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
		JLabel titulo;

		// Modificamos Propiedades de JPanel y lo Agregamos a la Ventana
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);

		// Creamos y Agregamos las Propiedades del JButton
		vendedores = componente.creaBoton("Vendedores", 360, 100, 120, 30);
		almacen = componente.creaBoton("Almacen", 360, 150, 120, 30);
		balance = componente.creaBoton("Balance", 360, 200, 120, 30);
		cerrarsesion = componente.creaBoton("Cerrar Sesión", 20, 240, 140, 30);

		// Creamos y Agregamos las Propiedades del JLabel
		titulo = componente.creaEtiqueta("Menú Administrador", 120, 40, 280, 28, 28);
		nombre = componente.creaEtiqueta("Nombre", 20, 100, 300, 50, 16);
		correo = componente.creaEtiqueta("Correo", 20, 140, 300, 50, 16);
		telefono = componente.creaEtiqueta("Telefono", 20, 180, 300, 50, 16);

		// Se Realiza Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(nombre);
		panel.add(correo);
		panel.add(telefono);
		panel.add(vendedores);
		panel.add(almacen);
		panel.add(balance);
		panel.add(cerrarsesion);
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Accion del boton vendedores
		vendedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAdministarVendedores();
				dispose();
			}
		});

		// Accion del boton vendedores
		almacen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlalmacen.muestraVistaAlmacen();
				dispose();
			}
		});

		// Accion del boton Comiciones
		balance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.obtenDatosBalanceGeneral();
				control.muestraVistaBalanceGeneral();
				dispose();
			}
		});

		// Accion de Boton Cerrar Sesion
		cerrarsesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente Cerrar Sesión?", "Cerrar Sesión",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					controllogin.muestraVistaLogin();
					dispose();
				}
			}
		});
	}

	// Métodos para Obtener la Instancia de los Controles Correspondientes
	public void setControl(ControlVendedores control) {
		this.control = control;
	}

	public void setControl(ControlLogin controllogin) {
		this.controllogin = controllogin;
	}

	public void setControl(ControlAlmacen controlalmacen) {
		this.controlalmacen = controlalmacen;
	}

	// Se Obtiene los Datos del Administrador Ingresado
	public void actualizarDatos(Usuario user) {
		this.nombre.setText(user.getNombre());
		this.correo.setText(user.getCorreo());
		this.telefono.setText(user.getTelefono());
	}
}
