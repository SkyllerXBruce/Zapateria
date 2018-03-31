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
import Negocio.ControlVenta;

@SuppressWarnings("serial")
public class VistaComiciones extends JFrame {

	// Variables Globales
	private JButton regresar;
	private JLabel lnombre, lventas, lcomicion;
	private ControlVenta control;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaComiciones frame = new VistaComiciones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaAdministrador
	public VistaComiciones() {
		// Propiedades de la Ventana
		setSize(480, 340);
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
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente Salir de la Comicion?",
						"¿Salir de la Comicion?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.muestraVistaAdministarVendedores();
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
		JLabel titulo, nombre, ventas, comicion;

		// Modificamos Propiedades de JPanel y lo Agregamos a la Ventana
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);

		// Imagen del Boton regresar
		ImageIcon imgIcon = new ImageIcon(VistaAgregarVendedor.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del JButton
		regresar = componente.creaBoton("", 40, 240, 50, 50);
		regresar.setIcon(imgIcon);
		regresar.setToolTipText("Cancela la Operacion y Regresa a la Ventana del Administrador");

		// Creamos y Agregamos las Propiedades del JLabel
		titulo = componente.creaEtiqueta("Comicion del Vendedor", 60, 40, 360, 35, 30);
		nombre = componente.creaEtiqueta("Vendedor:", 40, 100, 220, 25, 16);
		ventas = componente.creaEtiqueta("Ventas Realizadas:", 40, 140, 220, 25, 16);
		comicion = componente.creaEtiqueta("Comicion Obtenida:   $", 60, 200, 220, 25, 18);
		lnombre = componente.creaEtiqueta("Nombre", 200, 100, 220, 25, 16);
		lventas = componente.creaEtiqueta("Venta", 200, 140, 220, 25, 16);
		lcomicion = componente.creaEtiqueta("Comicion", 260, 200, 180, 25, 18);

		// Se Realiza Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(nombre);
		panel.add(ventas);
		panel.add(comicion);
		panel.add(lnombre);
		panel.add(lventas);
		panel.add(lcomicion);
		panel.add(regresar);
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Accion del boton Regresa
		regresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaVendedor();
				control.limpiarDatos("AgregarVendedor");
				dispose();
			}
		});
	}

	// Método para Obtener los Datos de la Comicion del Vendedor
	public void obtenDatosComicion(String nombre, double comicion, int vendidos) {
		lnombre.setText(nombre);
		lventas.setText(String.valueOf(vendidos));
		lcomicion.setText(String.format("%.2f", comicion));
	}

	// Metodo que limpia los TextFields
	public void limpiarDatosComiciones() {
		lnombre.setText("");
		lventas.setText("");
		lcomicion.setText("");
	}

	// Obtenemos la Instancia del Control Venta
	public void setControl(ControlVenta controlventa) {
		this.control = controlventa;
	}

}
