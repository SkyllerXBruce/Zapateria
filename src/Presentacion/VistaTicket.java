package Presentacion;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Negocio.ControlVenta;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		JLabel lblFolio = new JLabel("Folio de venta");
		JLabel lblTitulo = new JLabel("Zapateria \"El Ahorro\"");
		JLabel lblTicketDeVenta = new JLabel("Ticket de venta");
		JLabel lblFecha = new JLabel("Fecha");
		JLabel lblModelo = new JLabel("Modelo");
		JLabel lblTipo = new JLabel("Tipo");
		JLabel lblColor = new JLabel("Color");
		JLabel lblTalla = new JLabel("Talla");
		JLabel lblCantidad = new JLabel("Cantidad");
		JLabel lblPrecio = new JLabel("Precio Unitario         $");
		JLabel lblIva = new JLabel("IVA (16%)                  $");
		lblanterior = new JLabel("");
		JLabel lblTotal = new JLabel("Total a pagar (MXN)   $");
		JLabel lblIcon = new JLabel("");
		lbldFolio = new JLabel("Folio");
		lbldFecha = new JLabel("Fecha");
		lbldModelo = new JLabel("Modelo");
		lbldTipo = new JLabel("Tipo");
		lbldColor = new JLabel("Color");
		lbldTalla = new JLabel("Talla");
		lbldCantidad = new JLabel("Cantidad");
		lbldPrecioUnitario = new JLabel("Precio");
		lbldIVA = new JLabel("iva");
		lbldTotal = new JLabel("Total");
		lbldanterior = new JLabel("");
		regresar = new JButton("");
		imprimir = new JButton("Imprimir");

		ImageIcon imgIconregresa = new ImageIcon(VistaLogin.class.getResource("return.png"));
		Image user = imgIconregresa.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIconregresa = new ImageIcon(userScaled);
		ImageIcon imgIconticket = new ImageIcon(VistaTicket.class.getResource("Ticket.png"));
		Image ticket = imgIconticket.getImage();
		Image ticketScaled = ticket.getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
		imgIconticket = new ImageIcon(ticketScaled);

		// Propiedades del Panel y se Agrega a la Ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// Propiedades del Panel "Ticket" y se Agrega al Panel
		Ticket.setBackground(Color.WHITE);
		Ticket.setBounds(10, 10, 430, 510);
		Ticket.setLayout(null);
		contentPane.add(Ticket);

		// Propiedades de la Imagen y se Agrega al Panel
		lblIcon.setBounds(320, 20, 100, 100);
		Ticket.add(lblIcon);
		lblIcon.setIcon(imgIconticket);

		// Propiedades de la Etiqueta "Zapateria el Ahorro" y se Agrega al Panel
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitulo.setBounds(40, 20, 280, 50);
		Ticket.add(lblTitulo);

		// Propiedades de la Etiqueta "Ticket de Venta" y se Agrega al Panel
		lblTicketDeVenta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTicketDeVenta.setBounds(100, 60, 140, 30);
		Ticket.add(lblTicketDeVenta);

		// Propiedades de la Etiqueta "Folio" y se Agrega al Panel
		lblFolio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFolio.setBounds(40, 140, 140, 26);
		Ticket.add(lblFolio);

		// Propiedades de la Etiqueta con los Datos del "Folio" y se Agrega al Panel
		lbldFolio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldFolio.setBounds(200, 140, 160, 26);
		Ticket.add(lbldFolio);

		// Propiedades de la Etiqueta "Fecha" y se Agrega al Panel
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFecha.setBounds(40, 170, 140, 26);
		Ticket.add(lblFecha);

		// Propiedades de la Etiqueta con los Datos de la "Fecha" y se Agrega al Panel
		lbldFecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldFecha.setBounds(200, 170, 160, 26);
		Ticket.add(lbldFecha);

		// Propiedades de la Etiqueta "Modelo" y se Agrega al Panel
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblModelo.setBounds(40, 200, 140, 26);
		Ticket.add(lblModelo);

		// Propiedades de la Etiqueta con los Datos del "Modelo" y se Agrega al Panel
		lbldModelo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldModelo.setBounds(200, 200, 160, 26);
		Ticket.add(lbldModelo);

		// Propiedades de la Etiqueta "Modelo" y se Agrega al Panel
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTipo.setBounds(40, 230, 140, 26);
		Ticket.add(lblTipo);

		// Propiedades de la Etiqueta con los Datos del "Modelo" y se Agrega al Panel
		lbldTipo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldTipo.setBounds(200, 230, 160, 26);
		Ticket.add(lbldTipo);

		// Propiedades de la Etiqueta "Color" y se Agrega al Panel
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblColor.setBounds(40, 260, 140, 26);
		Ticket.add(lblColor);

		// Propiedades de la Etiqueta con los Datos del "Color" y se Agrega al Panel
		lbldColor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldColor.setBounds(200, 260, 160, 26);
		Ticket.add(lbldColor);

		// Propiedades de la Etiqueta "Talla" y se Agrega al Panel
		lblTalla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTalla.setBounds(40, 290, 140, 26);
		Ticket.add(lblTalla);

		// Propiedades de la Etiqueta con los Datos de la "Talla" y se Agrega al Panel
		lbldTalla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldTalla.setBounds(200, 290, 160, 26);
		Ticket.add(lbldTalla);

		// Propiedades de la Etiqueta "Cantidad" y se Agrega al Panel
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCantidad.setBounds(40, 320, 140, 26);
		Ticket.add(lblCantidad);

		// Propiedades de la Etiqueta con los Datos de la "Cantidad" y se Agrega al
		// Panel
		lbldCantidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldCantidad.setBounds(200, 320, 160, 26);
		Ticket.add(lbldCantidad);

		// Propiedades de la Etiqueta "Precio Unitario" y se Agrega al Panel
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrecio.setBounds(40, 350, 180, 26);
		Ticket.add(lblPrecio);

		// Propiedades de la Etiqueta con los Datos del "Precio Unitario" y se Agrega al
		// Panel
		lbldPrecioUnitario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldPrecioUnitario.setBounds(210, 350, 160, 26);
		Ticket.add(lbldPrecioUnitario);

		// Propiedades de la Etiqueta "Iva" y se Agrega al Panel
		lblIva.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIva.setBounds(40, 380, 180, 26);
		Ticket.add(lblIva);

		// Propiedades de la Etiqueta con los Datos del "Iva" y se Agrega al Panel
		lbldIVA.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldIVA.setBounds(210, 380, 160, 26);
		Ticket.add(lbldIVA);

		// Propiedades de la Etiqueta "Iva" y se Agrega al Panel
		lblanterior.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblanterior.setBounds(40, 410, 180, 26);
		Ticket.add(lblanterior);

		// Propiedades de la Etiqueta "Iva" y se Agrega al Panel
		lbldanterior.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldanterior.setBounds(220, 410, 180, 26);
		Ticket.add(lbldanterior);

		// Propiedades de la Etiqueta "Total" y se Agrega al Panel
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotal.setBounds(60, 460, 190, 26);
		Ticket.add(lblTotal);

		// Propiedades de la Etiqueta con los Datos del "Total" y se Agrega al Panel
		lbldTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldTotal.setBounds(240, 460, 140, 26);
		Ticket.add(lbldTotal);

		// Propiedades del Boton Imprimir y se Agrega al Panel
		imprimir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		imprimir.setBounds(260, 540, 140, 26);
		contentPane.add(imprimir);

		// Propiedades del Boton Cancelar y se Agrega al Panel
		regresar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		regresar.setBounds(60, 530, 50, 50);
		regresar.setIcon(imgIconregresa);
		contentPane.add(regresar);

		// Acciones de los Componentes
		accionesComponentes();
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
					cambio = control.realizaCambio();
					if (!cambio) {
						control.guardarDatosTicket(obtenerDatosTicket());
						JOptionPane.showMessageDialog(null, "Venta Realizada con Exito");
					}else
						JOptionPane.showMessageDialog(null, "Cambio Realizado con Exito");
					control.muestraVistaVendedor();
					control.limpiarDatos("Venta");
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

	public String[] obtenerDatosTicket() {
		String[] datos = { lbldFolio.getText(), lbldFecha.getText(), lbldModelo.getText(), lbldTipo.getText(),
				lbldIVA.getText(), lbldTotal.getText(), lbldCantidad.getText() };
		return datos;
	}

	// Instanciamos control a nuestra vista.
	public void setControl(ControlVenta control) {
		this.control = control;
	}

	// Estos métodos nos sirven para agregar datos al ticket.
	public void setFolio(String txt) {
		lbldFolio.setText(txt);
	}

	public void setFecha(String txt) {
		lbldFecha.setText(txt);
	}

	public void setModelo(String txt) {
		lbldModelo.setText(txt);
	}

	public void setTipo(String txt) {
		lbldTipo.setText(txt);
	}

	public void setColor(String txt) {
		lbldColor.setText(txt);
	}

	public void setTalla(String txt) {
		lbldTalla.setText(txt);
	}

	public void setCantidad(String txt) {
		lbldCantidad.setText(txt);
	}

	public void setIva(String txt) {
		lbldIVA.setText(txt);
	}

	public void setPrecioUnitario(String txt) {
		lbldPrecioUnitario.setText(txt);
	}

	public void setTotal(String txt) {
		lbldTotal.setText(txt);
	}

	public void setTotalAnterior(String txt) {
		lblanterior.setText("Total Anterior            $");
		lbldanterior.setText(txt);
	}

	// Retornamos el componente del Jframe que queremos imprimir, para evitar que
	// los botones nos aparezcan en la impresión
	public JComponent getTicket() {
		return Ticket;
	}
}
