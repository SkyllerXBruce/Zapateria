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

import Modelo.Producto;
import Negocio.ControlAlmacen;

@SuppressWarnings("serial")
public class VistaConsultaProductoRealizada extends JFrame {

	private JButton finaliza, nueva;
	private JLabel getcodigo, getmodelo, gettipo, getcolor, getcosto, gettalla, getcantidad;
	private ControlAlmacen control;

	/**
	 * Launch the application.
	 */
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
	
	public VistaConsultaProductoRealizada() {
		// Tamaño de la Ventana
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
		// creamos el panel y lo agregamos a la ventana
		JPanel panel = new JPanel(null);
		setContentPane(panel);
		JLabel titulo, codigo, modelo, tipo, color, costo, talla, cantidad;

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		finaliza = creaBoton("Finalizar Consulta", 260, 420, 160, 30);
		nueva = creaBoton("Nueva Consulta", 20, 420, 150, 30);
		finaliza.setToolTipText("Termina la Consulta y Regresa a la Ventana de Administrar Vendedores");
		nueva.setToolTipText("Realiza una Nueva Consulta");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo = creaEtiqueta("Producto", 140, 40, 340, 35, 30);
		codigo = creaEtiqueta("Código:", 40, 120, 150, 25, 16);
		modelo = creaEtiqueta("Modelo:", 40, 160, 150, 25, 16);
		tipo = creaEtiqueta("Tipo:", 40, 200, 150, 25, 16);
		color = creaEtiqueta("Color:", 40, 240, 150, 25, 16);
		costo = creaEtiqueta("Costo Unitario:", 40, 280, 150, 25, 16);
		talla = creaEtiqueta("Talla:", 40, 320, 150, 25, 16);
		cantidad = creaEtiqueta("Cantidad:", 40, 360, 150, 25, 16);
		getcodigo = creaEtiqueta("Codigo", 200, 120, 360, 25, 16);
		getmodelo = creaEtiqueta("Modelo", 200, 160, 360, 25, 16);
		gettipo = creaEtiqueta("Tipo", 200, 200, 360, 25, 16);
		getcolor = creaEtiqueta("Color", 200, 240, 360, 25, 16);
		getcosto = creaEtiqueta("Costo", 200, 280, 360, 25, 16);
		gettalla = creaEtiqueta("Talla", 200, 320, 360, 25, 16);
		getcantidad = creaEtiqueta("Cantidad", 200, 360, 150, 25, 16);
		getcodigo.setToolTipText("Codigo del Producto");
		gettipo.setToolTipText("Tipo del Producto");
		getcolor.setToolTipText("Color del Producto");
		getcosto.setToolTipText("Costo Unitario del Producto");
		getmodelo.setToolTipText("Modelo del Producto");
		gettalla.setToolTipText("Talla del Producto");

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
		panel.add(getcodigo);
		panel.add(gettipo);
		panel.add(getcolor);
		panel.add(getcosto);
		panel.add(getmodelo);
		panel.add(gettalla);
		panel.add(getcantidad);
		panel.add(finaliza);
		panel.add(nueva);

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
		finaliza.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAlmacen();
				control.limpiarDatos("Consultar");
				dispose();
			}
		});

		// Accion del boton Comiciones
		nueva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//control.muestraVistaConsultarVendedor();
				control.limpiarDatos("Consultar");
				dispose();
			}
		});
	}

	public void obtenerDatosProducto(Producto producto) {
		getcodigo.setText(String.valueOf(producto.dameCodigo()));
		getmodelo.setText(producto.dameModelo());
		gettipo.setText(producto.dameTipo());
		getcolor.setText(producto.dameColor());
		getcosto.setText(String.valueOf(producto.dameCosto()));
		gettalla.setText(String.valueOf(producto.dameTalla()));
		getcantidad.setText(String.valueOf(producto.dameCantidad()));
	}

	public void limpiarDatosConsultaProducto() {
		getcodigo.setText("");
		getmodelo.setText("");
		gettipo.setText("");
		getcolor.setText("");
		getcosto.setText("");
		gettalla.setText("");
	}

	public void setControl(ControlAlmacen controlalmacen) {
		this.control = controlalmacen;
	}

}
