package Presentacion;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Componentes;
import Modelo.Producto;
import Negocio.ControlAlmacen;
import Negocio.ControlVenta;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VistaTicket extends JFrame {

	// Variables Globales
	private ControlVenta control;
	private ControlAlmacen controlalmacen;
	private JPanel panelticket;
	private JButton regresar, imprimir;
	private boolean cambio = false;
	private JLabel lfolio, lfecha, lmodelo, ltipo, lcolor, ltalla, lcantidad, lprecio, liva, ltotal, anterior,
			lanterior;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaTicket frame = new VistaTicket();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaTicket
	public VistaTicket() {
		// Propiedades de la Ventana
		setTitle("Ventana Ticket");
		setBounds(100, 100, 450, 620);
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
				if (JOptionPane.showConfirmDialog(rootPane, "¿Realmente Desea Cancelar la Venta?", "¿Cancelar?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.muestraVistaVendedor();
					control.limpiarDatos("Venta");
					dispose();
				}
			}
		});
	}

	private void iniciarComponentes() {
		// Creamos la Instancia del JPanel Así como de Algunos Componentes
		panelticket = new JPanel();
		JPanel panel = new JPanel();
		Componentes componente = new Componentes();
		JLabel titulo, ticket, folio, fecha, modelo, tipo, color, talla, cantidad, precio, iva, total, imagen;

		// Modificamos Propiedades de JPanel del Ticket
		panelticket.setBackground(Color.WHITE);
		panelticket.setBounds(10, 10, 430, 510);
		panelticket.setLayout(null);

		// Modificamos Propiedades de JPanel y lo Agregamos a la Ventana
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);

		// Imagen del Boton regresar
		ImageIcon imgIconregresa = new ImageIcon(VistaLogin.class.getResource("return.png"));
		Image user = imgIconregresa.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIconregresa = new ImageIcon(userScaled);

		// Imagen del Ticket
		ImageIcon imgIconticket = new ImageIcon(VistaTicket.class.getResource("Ticket.png"));
		Image imagenticket = imgIconticket.getImage();
		Image ticketScaled = imagenticket.getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
		imgIconticket = new ImageIcon(ticketScaled);

		// Creamos y Agregamos las Propiedades del JButton
		imprimir = componente.creaBoton("Imprimir", 260, 540, 140, 26);
		regresar = componente.creaBoton("", 60, 530, 50, 50);
		regresar.setIcon(imgIconregresa);

		// Creamos y Agregamos las Propiedades del JLabel
		titulo = componente.creaEtiqueta("Zapateria \"El Ahorro\"", 40, 20, 280, 50, 26);
		ticket = componente.creaEtiqueta("Ticket de venta", 100, 60, 140, 30, 18);
		folio = componente.creaEtiqueta("Folio de venta", 40, 140, 140, 26, 16);
		fecha = componente.creaEtiqueta("Fecha", 40, 170, 140, 26, 16);
		modelo = componente.creaEtiqueta("Modelo", 40, 200, 140, 26, 16);
		tipo = componente.creaEtiqueta("Tipo", 40, 230, 140, 26, 16);
		color = componente.creaEtiqueta("Color", 40, 260, 140, 26, 16);
		talla = componente.creaEtiqueta("Talla", 40, 290, 140, 26, 16);
		cantidad = componente.creaEtiqueta("Cantidad", 40, 320, 140, 26, 16);
		precio = componente.creaEtiqueta("Precio Unitario         $", 40, 350, 180, 26, 16);
		iva = componente.creaEtiqueta("IVA (16%)                  $", 40, 380, 180, 26, 16);
		total = componente.creaEtiqueta("Total a pagar (MXN)   $", 60, 460, 190, 26, 16);
		imagen = componente.creaEtiqueta("", 320, 20, 100, 100, 16);
		imagen.setIcon(imgIconticket);
		anterior = componente.creaEtiqueta("", 40, 410, 180, 26, 16);
		lfolio = componente.creaEtiqueta("Folio", 200, 140, 160, 26, 16);
		lfecha = componente.creaEtiqueta("Fecha", 200, 170, 160, 26, 16);
		lmodelo = componente.creaEtiqueta("Modelo", 200, 200, 160, 26, 16);
		ltipo = componente.creaEtiqueta("Tipo", 200, 230, 160, 26, 16);
		lcolor = componente.creaEtiqueta("Color", 200, 260, 160, 26, 16);
		ltalla = componente.creaEtiqueta("Talla", 200, 290, 160, 26, 16);
		lcantidad = componente.creaEtiqueta("Cantidad", 200, 320, 160, 26, 16);
		lprecio = componente.creaEtiqueta("Precio", 210, 350, 160, 26, 16);
		liva = componente.creaEtiqueta("Iva", 210, 380, 160, 26, 16);
		ltotal = componente.creaEtiqueta("Total", 240, 460, 140, 26, 16);
		lanterior = componente.creaEtiqueta("", 220, 410, 180, 26, 16);

		// Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panelticket.add(titulo);
		panelticket.add(ticket);
		panelticket.add(folio);
		panelticket.add(fecha);
		panelticket.add(modelo);
		panelticket.add(tipo);
		panelticket.add(color);
		panelticket.add(talla);
		panelticket.add(cantidad);
		panelticket.add(precio);
		panelticket.add(iva);
		panelticket.add(total);
		panelticket.add(imagen);
		panelticket.add(anterior);
		panelticket.add(lfolio);
		panelticket.add(lfecha);
		panelticket.add(lmodelo);
		panelticket.add(ltipo);
		panelticket.add(lcolor);
		panelticket.add(ltalla);
		panelticket.add(lcantidad);
		panelticket.add(lprecio);
		panelticket.add(liva);
		panelticket.add(ltotal);
		panelticket.add(lanterior);
		panel.add(panelticket);
		panel.add(imprimir);
		panel.add(regresar);
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Accion del boton Imprimir
		imprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nl = System.getProperty("line.separator");
				if (JOptionPane.showConfirmDialog(rootPane,
						"Se Realizará la Compra Aunque el Ticket NO Se Imprima" + nl + "¿Esta Seguro de Continuar?",
						"Realizar Compra", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.imprimeTicket();
					cambio = control.esCambio();
					if (cambio) {
						String modelo, tipo, color;
						double talla, iva, total;
						int folio;
						modelo = lmodelo.getText();
						tipo = ltipo.getText();
						color = lcolor.getText();
						talla = Double.valueOf(ltalla.getText());
						iva = Double.valueOf(liva.getText());
						total = Double.valueOf(lanterior.getText());
						folio = Integer.valueOf(lfolio.getText());
						Producto productocambio = controlalmacen.buscaProducto(modelo, tipo, color, talla);
						if (control.realizaCambioProducto(folio, productocambio, iva, total))
							JOptionPane.showMessageDialog(null, "Cambio Realizado con Exito");
						else
							JOptionPane.showMessageDialog(null, "No Se Pudo Realizar el Cambio");
						control.limpiarDatos("Cambio");
					} else {
						control.guardarDatosTicket(obtenerDatosVentaTicket());
						JOptionPane.showMessageDialog(null, "Venta Realizada con Exito");
						control.limpiarDatos("Venta");
					}
					control.muestraVistaVendedor();
					dispose();
				}
			}
		});

		// Accion del boton Regresar
		regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.muestraVistaVendedor();
				control.limpiarDatos("Venta");
				dispose();
			}
		});
	}

	// Método para Obtener los Datos del Ticket, Regresa un Arreglo de Tipo String
	public String[] obtenerDatosVentaTicket() {
		String[] datos = { lfolio.getText(), lfecha.getText(), lmodelo.getText(), ltipo.getText(), lcolor.getText(),
				ltalla.getText(), liva.getText(), ltotal.getText(), lcantidad.getText() };
		return datos;
	}

	// Método para Modificar el Total del Ticket Anterior Cuando se Realiza un
	// Cambio
	public void setTotalAnterior(String txt) {
		anterior.setText("Total Anterior            $");
		lanterior.setText(txt);
	}

	// Método para Modificar los Datos del Ticket en los JTextField
	public void setDatosTicket(String[] datosventa) {
		cambio = control.esCambio();
		lfolio.setText(datosventa[0]);
		lfecha.setText(datosventa[1]);
		lmodelo.setText(datosventa[2]);
		ltipo.setText(datosventa[3]);
		lcolor.setText(datosventa[4]);
		ltalla.setText(datosventa[5]);
		lcantidad.setText(datosventa[6]);
		liva.setText(datosventa[7]);
		lprecio.setText(datosventa[8]);
		ltotal.setText(datosventa[9]);
	}

	// Métodos para Obtener la Instancia de los Controles Correspondientes
	public void setControl(ControlVenta control) {
		this.control = control;
	}

	public void setControl(ControlAlmacen controlalmacen) {
		this.controlalmacen = controlalmacen;
	}

	// Obtiene el Panel del Ticket para Mandarlo a Imprimir
	public JComponent getTicket() {
		return panelticket;
	}
}
