package Presentacion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Modelo.Usuario;
import Negocio.ControlAlmacen;
import Negocio.ControlLogin;
import Negocio.ControlVendedores;

@SuppressWarnings("serial")
public class VistaAdministrador extends JFrame {

	private JButton vendedores, almacen, comiciones, cerrarsesion;
	private JLabel nombre, correo, telefono;
	private ControlVendedores control;
	private ControlLogin controllogin;
	private ControlAlmacen controlalmacen;

	/**
	 * Launch the application.
	 */
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

	public VistaAdministrador() {
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

	private void iniciarComponentes() {
		// creamos el panel y lo agregamos a la ventana
		JPanel panel = new JPanel(null);
		setContentPane(panel);
		JLabel titulo;

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		vendedores = creaBoton("Vendedores", 360, 100, 120, 30);
		almacen = creaBoton("Almacen", 360, 150, 120, 30);
		comiciones = creaBoton("Comiciones", 360, 200, 120, 30);
		cerrarsesion = creaBoton("Cerrar Sesión", 20, 240, 140, 30);
		vendedores.setToolTipText("Accesar a los Vendedores (Agregar, Consultar, Eliminar, Listar)");
		almacen.setToolTipText("Accesar al Almacen de Productos (Agregar, Consultar, Eliminar, Listar)");
		comiciones.setToolTipText("Accesar a las Comiciones (Asignar, Consultar,Modificar, Listar)");
		cerrarsesion.setToolTipText("Regresa a la Ventana de Acceso");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo = creaEtiqueta("Menú Administrador", 120, 40, 280, 28, 28);
		nombre = creaEtiqueta("Nombre", 20, 100, 300, 50, 16);
		correo = creaEtiqueta("Correo", 20, 140, 300, 50, 16);
		telefono = creaEtiqueta("Telefono", 20, 180, 300, 50, 16);

		// Se Realiza Acciones de los Componentes
		accionesComponentes();
		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(nombre);
		panel.add(correo);
		panel.add(telefono);
		panel.add(vendedores);
		panel.add(almacen);
		panel.add(comiciones);
		panel.add(cerrarsesion);
	}

	// Método Para Crear las Propiedades del boton
	private JButton creaBoton(String nombre, int posx, int posy, int ancho, int alto) {
		JButton boton = new JButton(nombre);
		boton.setBounds(posx, posy, ancho, alto);
		boton.setFont(new Font("Serif", Font.ITALIC, 14));
		return boton;
	}

	// Método Para Crear las Propiedades de las Etiquetas
	private JLabel creaEtiqueta(String nombre, int posx, int posy, int ancho, int alto, int tama�o) {
		JLabel etiqueta = new JLabel(nombre);
		etiqueta.setBounds(posx, posy, ancho, alto);
		etiqueta.setFont(new Font("Serif", Font.ITALIC, tama�o));
		return etiqueta;
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
		comiciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Click en Comiciones");
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

	public void setControl(ControlVendedores control) {
		this.control = control;
	}

	public void setControl(ControlLogin controllogin) {
		this.controllogin = controllogin;
	}
	
	public void setControl(ControlAlmacen controlalmacen) {
		this.controlalmacen = controlalmacen;
	}

	public void actualizarDatos(Usuario user) {
		this.nombre.setText(user.getNombre());
		this.correo.setText(user.getCorreo());
		this.telefono.setText(user.getTelefono());
	}
}
