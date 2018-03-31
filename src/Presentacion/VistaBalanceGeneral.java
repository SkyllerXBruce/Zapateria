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
public class VistaBalanceGeneral extends JFrame {

	// Variables Globales
	private JButton regresar;
	private JLabel lvendidos, lganancias, lcomiciones, ltotal;
	private ControlVendedores control;

	// Muestra Solo la Presentacion de la Vista.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaBalanceGeneral frame = new VistaBalanceGeneral();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaAdministrador
	public VistaBalanceGeneral() {
		// Propiedades de la Ventana
		setSize(460, 380);
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
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente Salir del Balance?",
						"¿Salir del Balance?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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
		JLabel titulo, vendidos, ganancias, comiciones, total;

		// Modificamos Propiedades de JPanel y lo Agregamos a la Ventana
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);

		// Imagen del Boton regresar
		ImageIcon imgIcon = new ImageIcon(VistaAgregarVendedor.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		regresar = componente.creaBoton("", 40, 280, 50, 50);
		regresar.setIcon(imgIcon);
		regresar.setToolTipText("Cancela la Operacion y Regresa a la Ventana del Administrador");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo = componente.creaEtiqueta("Balance General", 100, 40, 340, 35, 30);
		vendidos = componente.creaEtiqueta("Productos Vendidos:", 40, 100, 220, 25, 16);
		ganancias = componente.creaEtiqueta("Ganacias por Ventas:       $", 40, 140, 220, 25, 16);
		comiciones = componente.creaEtiqueta("Pagos de Comiciones:      $", 40, 180, 220, 25, 16);
		total = componente.creaEtiqueta("Total Neto:  $", 120, 240, 180, 25, 18);
		lvendidos = componente.creaEtiqueta("Vendidos", 240, 100, 140, 25, 16);
		lganancias = componente.creaEtiqueta("Ganacias", 250, 140, 140, 25, 16);
		lcomiciones = componente.creaEtiqueta("Comiciones", 250, 180, 140, 25, 16);
		ltotal = componente.creaEtiqueta("Total", 240, 240, 180, 25, 18);
		vendidos.setToolTipText("Muestra los Productos Vendidos en Total");
		ganancias.setToolTipText("Muestra las Ganancias por las Ventas Realizadas");
		comiciones.setToolTipText("Muestra los pados de Comiciones de los Vendedores");
		total.setToolTipText("Muestra las Ganancias Netas Obtenidas en Total");
		lvendidos.setToolTipText("Muestra los Productos Vendidos en Total");
		lganancias.setToolTipText("Muestra las Ganancias por las Ventas Realizadas");
		lcomiciones.setToolTipText("Muestra los pados de Comiciones de los Vendedores");
		ltotal.setToolTipText("Muestra las Ganancias Netas Obtenidas en Total");

		// Se Realiza Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(vendidos);
		panel.add(ganancias);
		panel.add(comiciones);
		panel.add(total);
		panel.add(lvendidos);
		panel.add(lganancias);
		panel.add(lcomiciones);
		panel.add(ltotal);
		panel.add(regresar);
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {

		// Accion del boton Regresa
		regresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAdministarVendedores();
				control.limpiarDatos("AgregarVendedor");
				dispose();
			}
		});
	}

	public void obtenDatosBalanceGeneral(int vendidos, double comicion, double ganancia) {
		double total = ganancia - comicion;
		lvendidos.setText(String.valueOf(vendidos));
		lganancias.setText(String.format("%.2f", ganancia));
		lcomiciones.setText(String.format("%.2f", comicion));
		ltotal.setText(String.format("%.2f", total));
	}

	// Metodo que limpia los TextFields
	public void limpiarDatosBalanceGeneral() {
		lvendidos.setText("");
		lganancias.setText("");
		lcomiciones.setText("");
		ltotal.setText("");
	}

	// Obtenemos la Instancia del Control Vendedor
	public void setControl(ControlVendedores controlvendedores) {
		this.control = controlvendedores;
	}

}
