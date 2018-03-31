package Presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Componentes;
import Modelo.Usuario;
import Negocio.ControlAlmacen;
import Negocio.ControlVenta;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VistaVendedor extends JFrame {

	// Variables Globales
	private JButton venta, cambio, comicion, productos, cerrar;
	private JLabel nombre, correo, telefono;
	private ControlVenta control;
	private ControlAlmacen controlalmacen;
	private boolean vendedor = false;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaVendedor frame = new VistaVendedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaVendedor
	public VistaVendedor() {
		// Propiedades de la Ventana
		setTitle("Vendedor");
		setSize(450, 320);
		setResizable(false);
		setLocationRelativeTo(null);
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
					control.muestraVistaLogin();
					dispose();
				}
			}
		});
	}

	// Creamos y Agregamos los Componetes de la Ventana
	private void iniciarComponentes() {
		// Creamos e Instanciamos los Componentes de la Ventana
		JPanel panel = new JPanel();
		JLabel titulo, subtitulo;
		Componentes componente = new Componentes();

		// Propiedades del Panel y se Agrega a la Ventana
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		venta = componente.creaBoton("Venta de Calzado", 250, 80, 180, 30);
		cambio = componente.creaBoton("Cambio de Calzado", 250, 130, 180, 30);
		comicion = componente.creaBoton("Comiciones", 250, 180, 180, 30);
		productos = componente.creaBoton("Lista de Productos", 250, 230, 180, 30);
		cerrar = componente.creaBoton("Cerrar Sesión", 20, 240, 160, 30);

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo = componente.creaEtiqueta("Ventas y Cambios", 120, 20, 280, 25, 26);
		subtitulo = componente.creaEtiqueta("Vendedor", 60, 60, 150, 25, 18);
		nombre = componente.creaEtiqueta("Nombre", 20, 100, 280, 25, 16);
		correo = componente.creaEtiqueta("Correo", 20, 140, 180, 25, 16);
		telefono = componente.creaEtiqueta("Telefono", 20, 180, 180, 25, 16);

		// Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(subtitulo);
		panel.add(nombre);
		panel.add(correo);
		panel.add(telefono);
		panel.add(venta);
		panel.add(cambio);
		panel.add(comicion);
		panel.add(productos);
		panel.add(cerrar);
	}

	// Realiza las Acciones de los Componentes
	private void accionesComponentes() {
		// Mostramos la ventana de Venta de Calzado
		venta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario vendedor = control.buscaVendedor(nombre.getText(), "Vendedor");
				control.setVendedor(vendedor);
				control.muestraVentadeCalzado();
				dispose();
			}
		});

		// Mostramos la ventana de Cambio de Calzado
		cambio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario vendedor = control.buscaVendedor(nombre.getText(), "Vendedor");
				control.setVendedor(vendedor);
				control.muestraCambioCalzado();
				dispose();
			}
		});

		// Mostramos la ventana de Comiciones
		comicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario vendedor = control.buscaVendedor(nombre.getText(), "Vendedor");
				control.setVendedor(vendedor);
				control.obtenDatosComiciones();
				control.muestraVistaComiciones();
				dispose();
			}
		});

		// Mostramos la ventana de Cambio de Calzado
		productos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vendedor = true;
				controlalmacen.cargarDatosProductos();
				controlalmacen.muestraVistaMostrarProductos();
				dispose();
			}
		});

		// Cerramos la Ventana Actual y Regresa a la Ventana VistaLogin
		cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente Cerrar Sesión?", "Cerrar Sesión",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.muestraVistaLogin();
					dispose();
				}
			}
		});
	}

	public void actualizarDatos(Usuario user) {
		this.nombre.setText(user.getNombre());
		this.correo.setText(user.getCorreo());
		this.telefono.setText(user.getTelefono());
	}

	public void setVendedor(boolean vendedor) {
		this.vendedor = vendedor;
	}

	public boolean esVendedor() {
		return vendedor;
	}

	// Instanciamos control a nuestra vista.
	public void setControl(ControlVenta controlvent) {
		this.control = controlvent;
	}

	public void setControl(ControlAlmacen controlalmacen) {
		this.controlalmacen = controlalmacen;
	}
}
