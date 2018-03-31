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

	private ControlVenta control;
	private ControlAlmacen controlalmacen;
	private JPanel Ticket;
	private JButton regresar, imprimir;
	private boolean cambio = false;
	private JLabel lbldFolio, lbldFecha, lbldModelo, lbldTipo, lbldColor, lbldTalla, lbldCantidad, lbldPrecioUnitario,
			lbldIVA, lbldTotal, lblanterior, lbldanterior;

	/**
	 * Launch the application.
	 */
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

	// Crea Vista del Ticket
	public VistaTicket() {
		//
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
		Ticket = new JPanel();
		JPanel contentPane = new JPanel();
		Componentes componente = new Componentes();
		JLabel lblTitulo, lblTicketDeVenta, lblFolio, lblFecha, lblModelo, lblTipo, lblColor, lblTalla, lblCantidad,
				lblPrecio, lblIva, lblTotal, lblIcon;

		// Propiedades del Panel y se Agrega a la Ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// Propiedades del Panel "Ticket" y se Agrega al Panel
		Ticket.setBackground(Color.WHITE);
		Ticket.setBounds(10, 10, 430, 510);
		Ticket.setLayout(null);
		contentPane.add(Ticket);

		//
		ImageIcon imgIconregresa = new ImageIcon(VistaLogin.class.getResource("return.png"));
		Image user = imgIconregresa.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIconregresa = new ImageIcon(userScaled);
		ImageIcon imgIconticket = new ImageIcon(VistaTicket.class.getResource("Ticket.png"));
		Image ticket = imgIconticket.getImage();
		Image ticketScaled = ticket.getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
		imgIconticket = new ImageIcon(ticketScaled);

		// Propiedades del Boton Imprimir y se Agrega al Panel
		imprimir = componente.creaBoton("Imprimir", 260, 540, 140, 26);
		regresar = componente.creaBoton("", 60, 530, 50, 50);
		regresar.setIcon(imgIconregresa);

		// Propiedades de la Etiqueta "Zapateria el Ahorro" y se Agrega al Panel
		lblTitulo = componente.creaEtiqueta("Zapateria \"El Ahorro\"", 40, 20, 280, 50, 26);
		lblTicketDeVenta = componente.creaEtiqueta("Ticket de venta", 100, 60, 140, 30, 18);
		lblFolio = componente.creaEtiqueta("Folio de venta", 40, 140, 140, 26, 16);
		lblFecha = componente.creaEtiqueta("Fecha", 40, 170, 140, 26, 16);
		lblModelo = componente.creaEtiqueta("Modelo", 40, 200, 140, 26, 16);
		lblTipo = componente.creaEtiqueta("Tipo", 40, 230, 140, 26, 16);
		lblColor = componente.creaEtiqueta("Color", 40, 260, 140, 26, 16);
		lblTalla = componente.creaEtiqueta("Talla", 40, 290, 140, 26, 16);
		lblCantidad = componente.creaEtiqueta("Cantidad", 40, 320, 140, 26, 16);
		lblPrecio = componente.creaEtiqueta("Precio Unitario         $", 40, 350, 180, 26, 16);
		lblIva = componente.creaEtiqueta("IVA (16%)                  $", 40, 380, 180, 26, 16);
		lblTotal = componente.creaEtiqueta("Total a pagar (MXN)   $", 60, 460, 190, 26, 16);
		lblIcon = componente.creaEtiqueta("", 320, 20, 100, 100, 16);
		lblIcon.setIcon(imgIconticket);
		lblanterior = componente.creaEtiqueta("", 40, 410, 180, 26, 16);
		lbldFolio = componente.creaEtiqueta("Folio", 200, 140, 160, 26, 16);
		lbldFecha = componente.creaEtiqueta("Fecha", 200, 170, 160, 26, 16);
		lbldModelo = componente.creaEtiqueta("Modelo", 200, 200, 160, 26, 16);
		lbldTipo = componente.creaEtiqueta("Tipo", 200, 230, 160, 26, 16);
		lbldColor = componente.creaEtiqueta("Color", 200, 260, 160, 26, 16);
		lbldTalla = componente.creaEtiqueta("Talla", 200, 290, 160, 26, 16);
		lbldCantidad = componente.creaEtiqueta("Cantidad", 200, 320, 160, 26, 16);
		lbldPrecioUnitario = componente.creaEtiqueta("Precio", 210, 350, 160, 26, 16);
		lbldIVA = componente.creaEtiqueta("Iva", 210, 380, 160, 26, 16);
		lbldTotal = componente.creaEtiqueta("Total", 240, 460, 140, 26, 16);
		lbldanterior = componente.creaEtiqueta("", 220, 410, 180, 26, 16);

		// Acciones de los Componentes
		accionesComponentes();

		Ticket.add(lblTitulo);
		Ticket.add(lblTicketDeVenta);
		Ticket.add(lblFolio);
		Ticket.add(lblFecha);
		Ticket.add(lblModelo);
		Ticket.add(lblTipo);
		Ticket.add(lblColor);
		Ticket.add(lblTalla);
		Ticket.add(lblCantidad);
		Ticket.add(lblPrecio);
		Ticket.add(lblIva);
		Ticket.add(lblTotal);
		Ticket.add(lblIcon);
		Ticket.add(lblanterior);
		Ticket.add(lbldFolio);
		Ticket.add(lbldFecha);
		Ticket.add(lbldModelo);
		Ticket.add(lbldTipo);
		Ticket.add(lbldColor);
		Ticket.add(lbldTalla);
		Ticket.add(lbldCantidad);
		Ticket.add(lbldPrecioUnitario);
		Ticket.add(lbldIVA);
		Ticket.add(lbldTotal);
		Ticket.add(lbldanterior);
		contentPane.add(imprimir);
		contentPane.add(regresar);
	}

	private void accionesComponentes() {
		// Imprimimos el ticket.
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
						modelo = lbldModelo.getText();
						tipo = lbldTipo.getText();
						color = lbldColor.getText();
						talla = Double.valueOf(lbldTalla.getText());
						iva = Double.valueOf(lbldIVA.getText());
						total = Double.valueOf(lbldanterior.getText());
						folio = Integer.valueOf(lbldFolio.getText());
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

		// Cerramos la ventana en caso de que se pulse el botón.
		regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.muestraVistaVendedor();
				control.limpiarDatos("Venta");
				dispose();
			}
		});
	}

	public String[] obtenerDatosVentaTicket() {
		String[] datos = { lbldFolio.getText(), lbldFecha.getText(), lbldModelo.getText(), lbldTipo.getText(),
				lbldColor.getText(), lbldTalla.getText(), lbldIVA.getText(), lbldTotal.getText(),
				lbldCantidad.getText() };
		return datos;
	}

	public void setTotalAnterior(String txt) {
		lblanterior.setText("Total Anterior            $");
		lbldanterior.setText(txt);
	}

	public void setDatosTicket(String[] datosventa) {
		cambio = control.esCambio();
		lbldFolio.setText(datosventa[0]);
		lbldFecha.setText(datosventa[1]);
		lbldModelo.setText(datosventa[2]);
		lbldTipo.setText(datosventa[3]);
		lbldColor.setText(datosventa[4]);
		lbldTalla.setText(datosventa[5]);
		lbldCantidad.setText(datosventa[6]);
		lbldIVA.setText(datosventa[7]);
		lbldPrecioUnitario.setText(datosventa[8]);
		lbldTotal.setText(datosventa[9]);
	}

	// Instanciamos control a nuestra vista.
	public void setControl(ControlVenta control) {
		this.control = control;
	}

	public void setControl(ControlAlmacen controlalmacen) {
		this.controlalmacen = controlalmacen;
	}

	// Retornamos el componente del Jframe que queremos imprimir, para evitar que
	// los botones nos aparezcan en la impresión
	public JComponent getTicket() {
		return Ticket;
	}
}
