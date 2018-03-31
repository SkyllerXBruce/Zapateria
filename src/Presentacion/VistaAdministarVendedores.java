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
import javax.swing.border.EmptyBorder;

import Modelo.Componentes;
import Negocio.ControlVendedores;

@SuppressWarnings("serial")
public class VistaAdministarVendedores extends JFrame {

	// Variable Globales
	private JButton agregar, eliminar, consultar, listar, regresar;
	private ControlVendedores control;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAdministarVendedores frame = new VistaAdministarVendedores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaAdministrarVendedores
	public VistaAdministarVendedores() {
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
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente Salir del Administrador de Vendedores?",
						"¿Salir del Administrador de Vendedores?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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
		ImageIcon imgIcon = new ImageIcon(VistaAdministarVendedores.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del JButton
		agregar = componente.creaBoton("Agregar Vendedor", 40, 100, 180, 30);
		eliminar = componente.creaBoton("Eliminar Vendedor", 40, 150, 180, 30);
		consultar = componente.creaBoton("Consultar Vendedor", 260, 100, 180, 30);
		listar = componente.creaBoton("Listar Vendedores", 260, 150, 180, 30);
		regresar = componente.creaBoton("", 20, 200, 50, 50);
		regresar.setIcon(imgIcon);

		// Creamos y Agregamos las Propiedades del JLabel
		titulo = componente.creaEtiqueta("Administrar Vendedores", 80, 40, 340, 28, 28);

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
		agregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAgregarVendedor();
				dispose();
			}
		});

		// Accion del boton Eliminar
		eliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaEliminarVendedor();
				dispose();
			}
		});

		// Accion del boton Consultar
		consultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaConsultarVendedor();
				dispose();
			}
		});

		// Accion del boton Listar
		listar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.cargarDatosVendedores();
				control.muestraVistaMuestraVendedores();
				dispose();
			}
		});

		// Accion de Boton Regresar
		regresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAdministrador();
				dispose();
			}
		});
	}

	// Obtenemos la Instancia del Control Vendedor
	public void setControl(ControlVendedores control) {
		this.control = control;
	}
}
