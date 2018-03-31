package Presentacion;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Componentes;
import Negocio.ControlAlmacen;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VistaAlmacen extends JFrame {

	// Variables Globales
	private JButton agregar, eliminar, consultar, listar, regresar;
	private ControlAlmacen control;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAlmacen frame = new VistaAlmacen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaAlmacen
	public VistaAlmacen() {
		// Propiedades de la Ventana
		setSize(480, 300);
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
				if (JOptionPane.showConfirmDialog(rootPane, "¿Realmente Desea Salir del Almacen?",
						"¿Salir del Almacen?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.muestraVistaAdministrador();
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

		// Imagen del Boton regresar
		ImageIcon imgIcon = new ImageIcon(VistaLogin.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		agregar = componente.creaBoton("Agregar Producto", 40, 100, 180, 30);
		eliminar = componente.creaBoton("Quitar Producto", 40, 150, 180, 30);
		consultar = componente.creaBoton("Consultar Producto", 260, 100, 180, 30);
		listar = componente.creaBoton("Listar Productos", 260, 150, 180, 30);
		regresar = componente.creaBoton("", 20, 200, 50, 50);
		regresar.setIcon(imgIcon);
		agregar.setToolTipText("Agrega un Producto");
		eliminar.setToolTipText("Quita un Producto");
		consultar.setToolTipText("Consulta un Producto");
		listar.setToolTipText("Muestra Todos los Productos");
		regresar.setToolTipText("Regresa a la Ventana del Administrador");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo = componente.creaEtiqueta("Administrar Almacen", 80, 40, 340, 28, 28);

		// Se Realiza Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(agregar);
		panel.add(eliminar);
		panel.add(consultar);
		panel.add(regresar);
		panel.add(listar);
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Accion del boton Agregar
		agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.muestraVistaAgregarProducto();
				dispose();
			}
		});

		// Accion del boton Eliminar
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.muestraVistaEliminarProducto();
				dispose();

			}
		});

		// Accion del boton Consultar
		consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.muestraVistaConsultaProducto();
				dispose();
			}
		});

		// Accion del boton Listar
		listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.cargarDatosProductos();
				control.muestraVistaMostrarProductos();
				dispose();
			}
		});

		// Accion del boton Regresar
		regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.muestraVistaAdministrador();
				dispose();
			}
		});
	}

	// Obtenemos la Instancia del Control Vendedor
	public void setControl(ControlAlmacen controlalmacen) {
		this.control = controlalmacen;
	}

}
