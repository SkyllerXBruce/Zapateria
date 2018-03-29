package Presentacion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Negocio.ControlAlmacen;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VistaAlmacen extends JFrame {

	private JButton agregar, eliminar, consultar, listar, regresar;
	private ControlAlmacen control;

	/**
	 * Launch the application.
	 */
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

	public VistaAlmacen() {
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
				if (JOptionPane.showConfirmDialog(rootPane, "¿Realmente Desea Salir del Almacen?", "¿Salir del Almacen?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.muestraVistaAdministrador();
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
		
		ImageIcon imgIcon = new ImageIcon(VistaLogin.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		agregar = creaBoton("Agregar Producto", 40, 100, 180, 30);
		eliminar = creaBoton("Quitar Producto", 40, 150, 180, 30);
		consultar = creaBoton("Consultar Producto", 260, 100, 180, 30);
		listar = creaBoton("Listar Productos", 260, 150, 180, 30);
		regresar = creaBoton("", 20, 200, 50, 50);
		regresar.setIcon(imgIcon);
		agregar.setToolTipText("Agrega un Producto");
		eliminar.setToolTipText("Quita un Producto");
		consultar.setToolTipText("Consulta un Producto");
		listar.setToolTipText("Muestra Todos los Productos");
		regresar.setToolTipText("Regresa a la Ventana del Administrador");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo = creaEtiqueta("Administrar Almacen", 80, 40, 340, 28, 28);

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

	// Método Para Crear las Propiedades del boton
	private JButton creaBoton(String nombre, int posx, int posy, int ancho, int alto) {
		// Se Crea e Inicializa un boton de la Clase JButton
		JButton boton = new JButton(nombre);

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto del Boton Como la
		// Letra del Texto
		boton.setBounds(posx, posy, ancho, alto);
		boton.setFont(new Font("Serif", Font.ITALIC, 14));
		return boton;
	}

	// Método Para Crear las Propiedades de las Etiquetas
	private JLabel creaEtiqueta(String nombre, int posx, int posy, int ancho, int alto, int tama�o) {
		// Se Crea e Inicializa una Etiqueta de la Clase JLabel
		JLabel etiqueta = new JLabel(nombre);

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de la Etiqueta asi
		// Como la Letra del Texto
		etiqueta.setBounds(posx, posy, ancho, alto);
		etiqueta.setFont(new Font("Serif", Font.ITALIC, tama�o));
		return etiqueta;
	}

	private void accionesComponentes() {

		agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.muestraVistaAgregarProducto();
				dispose();
			}
		});

		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.muestraVistaEliminarProducto();
				dispose();

			}
		});
		
		consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.muestraVistaConsultaProducto();
				dispose();
			}
		});

		listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.cargarDatosProductos();
				control.muestraVistaMostrarProductos();
				dispose();
			}
		});
		
		regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.muestraVistaAdministrador();
				dispose();
			}
		});
	}

	public void setControl(ControlAlmacen controlalmacen) {
		this.control = controlalmacen;
	}

}
