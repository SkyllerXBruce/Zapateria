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
import Modelo.Producto;
import Negocio.ControlAlmacen;

@SuppressWarnings("serial")
public class VistaConsultaProductoRealizada extends JFrame {

	// Variables Globales
	private JButton finaliza, nueva;
	private JLabel lcodigo, lmodelo, ltipo, lcolor, lcosto, ltalla, lcantidad;
	private ControlAlmacen control;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaConsultaProductoRealizada frame = new VistaConsultaProductoRealizada();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaConsultaProductoRealizada
	public VistaConsultaProductoRealizada() {
		// Propiedades de la Ventana
		setSize(440, 500);
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
				if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de Finalizar Consulta?", "¿Finaliza Consulta?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
					control.muestraVistaAlmacen();
					dispose();
				}
			}
		});
	}

	private void iniciarComponentes() {
		// Creamos la Instancia del JPanel Así como de Algunos Componentes
		JPanel panel = new JPanel(null);
		JLabel titulo, codigo, modelo, tipo, color, costo, talla, cantidad;
		Componentes componente = new Componentes();

		// Modificamos Propiedades de JPanel y lo Agregamos a la Ventana
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);

		// Creamos y Agregamos las Propiedades del JButton
		finaliza = componente.creaBoton("Finalizar Consulta", 260, 420, 160, 30);
		nueva = componente.creaBoton("Nueva Consulta", 20, 420, 150, 30);

		// Creamos y Agregamos las Propiedades del JLabel
		titulo = componente.creaEtiqueta("Producto", 140, 40, 340, 35, 30);
		codigo = componente.creaEtiqueta("Código:", 40, 120, 150, 25, 16);
		modelo = componente.creaEtiqueta("Modelo:", 40, 160, 150, 25, 16);
		tipo = componente.creaEtiqueta("Tipo:", 40, 200, 150, 25, 16);
		color = componente.creaEtiqueta("Color:", 40, 240, 150, 25, 16);
		costo = componente.creaEtiqueta("Costo Unitario:", 40, 280, 150, 25, 16);
		talla = componente.creaEtiqueta("Talla:", 40, 320, 150, 25, 16);
		cantidad = componente.creaEtiqueta("Cantidad:", 40, 360, 150, 25, 16);
		lcodigo = componente.creaEtiqueta("Codigo", 200, 120, 360, 25, 16);
		lmodelo = componente.creaEtiqueta("Modelo", 200, 160, 360, 25, 16);
		ltipo = componente.creaEtiqueta("Tipo", 200, 200, 360, 25, 16);
		lcolor = componente.creaEtiqueta("Color", 200, 240, 360, 25, 16);
		lcosto = componente.creaEtiqueta("Costo", 200, 280, 360, 25, 16);
		ltalla = componente.creaEtiqueta("Talla", 200, 320, 360, 25, 16);
		lcantidad = componente.creaEtiqueta("Cantidad", 200, 360, 150, 25, 16);

		// Se Realiza Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(codigo);
		panel.add(modelo);
		panel.add(tipo);
		panel.add(color);
		panel.add(costo);
		panel.add(talla);
		panel.add(cantidad);
		panel.add(lcodigo);
		panel.add(ltipo);
		panel.add(lcolor);
		panel.add(lcosto);
		panel.add(lmodelo);
		panel.add(ltalla);
		panel.add(lcantidad);
		panel.add(finaliza);
		panel.add(nueva);

	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Accion del boton Finaliza
		finaliza.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAlmacen();
				control.limpiarDatos("Consultar");
				dispose();
			}
		});

		// Accion del boton Nueva Consulta
		nueva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaConsultaProducto();
				control.limpiarDatos("Consultar");
				dispose();
			}
		});
	}

	// Método para Obtener los Datos del Producto y Mostrarlo en la Ventana
	public void obtenerDatosProducto(Producto producto) {
		lcodigo.setText(String.valueOf(producto.dameCodigo()));
		lmodelo.setText(producto.dameModelo());
		ltipo.setText(producto.dameTipo());
		lcolor.setText(producto.dameColor());
		lcosto.setText(String.valueOf(producto.dameCosto()));
		ltalla.setText(String.valueOf(producto.dameTalla()));
		lcantidad.setText(String.valueOf(producto.dameCantidad()));
	}

	// Metodo que limpia los TextFields
	public void limpiarDatosConsultaProducto() {
		lcodigo.setText("");
		lmodelo.setText("");
		ltipo.setText("");
		lcolor.setText("");
		lcosto.setText("");
		ltalla.setText("");
	}

	// Obtenemos la Instancia del Control Almacen
	public void setControl(ControlAlmacen controlalmacen) {
		this.control = controlalmacen;
	}
}
