package Presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.Componentes;
import Negocio.ControlVenta;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Image;

@SuppressWarnings("serial")
public class VistaVentaCalzado extends JFrame {

	// Variables Globales
	private JTextField tcodigo;
	private JLabel ltotal, liva, lmonto;
	private JButton buscar, continuar, regresar, calcular;
	private ControlVenta control;
	private DefaultTableModel modelo;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaVentaCalzado frame = new VistaVentaCalzado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaVentaCalzado
	public VistaVentaCalzado() {
		// Propiedades de la Ventana
		setTitle("Venta de calzado");
		setSize(680, 360);
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
					control.limpiarDatos("Venta");
					control.muestraVistaVendedor();
					dispose();
				}
			}
		});
	}

	private void iniciarComponentes() {
		// Creamos la Instancia del JPanel Así como de Algunos Componentes
		JPanel panel = new JPanel();
		JLabel codigo, monto, iva, total;
		Componentes componente = new Componentes();
		JTable table = new JTable();
		JScrollPane scroll = new JScrollPane();
		String columnaventa[] = { "Modelo", "Tipo", "Color", "Talla", "Costo", "Disponibles", "Pares a Vender" };
		String datosventa[][] = {};

		// Modificamos Propiedades de JPanel y lo Agregamos a la Ventana
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);

		// Imagen del Boton regresar
		ImageIcon imgIcon = new ImageIcon(VistaVentaCalzado.class.getResource("return.png"));
		Image ticket = imgIcon.getImage();
		Image ticketScaled = ticket.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(ticketScaled);

		// Creamos y Agregamos las Propiedades del JButton
		buscar = componente.creaBoton("Buscar", 340, 25, 90, 26);
		continuar = componente.creaBoton("Continuar", 460, 290, 120, 26);
		calcular = componente.creaBoton("Calcular Total", 180, 200, 160, 26);
		regresar = componente.creaBoton("", 40, 260, 50, 50);
		regresar.setIcon(imgIcon);

		// Creamos y Agregamos las Propiedades del JLabel
		codigo = componente.creaEtiqueta("Codigo del Producto", 40, 25, 160, 26, 15);
		monto = componente.creaEtiqueta("Monto por unidad  $", 320, 170, 160, 26, 15);
		iva = componente.creaEtiqueta("IVA (16%)   $", 380, 200, 100, 26, 15);
		total = componente.creaEtiqueta("Total  $", 420, 250, 60, 26, 15);
		lmonto = componente.creaEtiqueta("       -", 480, 170, 90, 24, 15);
		liva = componente.creaEtiqueta("       -", 480, 200, 90, 24, 15);
		ltotal = componente.creaEtiqueta("       -", 480, 250, 90, 24, 15);

		// Creamos y Agregamos las Propiedades del JTextField
		tcodigo = componente.creaCuadroTexto(220, 25, 90, 24, 15);

		// Se Crean el Modelo y Se Agregan los Datos de los Vendedores
		modelo = new DefaultTableModel(datosventa, columnaventa) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 6;
			}
		};

		// Modificamos las Propiedades de la Tablas y Agregamos el Modelo de Tabla
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);

		// Modificamos Propiedades del ScrollPane Y Agregamos la Tabla
		scroll.setBounds(40, 80, 600, 74);
		scroll.setViewportView(table);

		// Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panel.add(buscar);
		panel.add(continuar);
		panel.add(calcular);
		panel.add(regresar);
		panel.add(codigo);
		panel.add(monto);
		panel.add(iva);
		panel.add(total);
		panel.add(tcodigo);
		panel.add(lmonto);
		panel.add(liva);
		panel.add(ltotal);
		panel.add(scroll);
	}

	// Realiza las Acciones de los Componentes
	private void accionesComponentes() {
		// Accion del boton Buscar
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String prod = tcodigo.getText();
				if (control.esNumeroReal(prod)) {
					if (modelo.getRowCount() != 0)
						control.limpiarDatos("Venta");
					control.buscaProducto(Integer.valueOf(prod));
					tcodigo.setText(prod);
				} else
					JOptionPane.showMessageDialog(null, "Ingrese id de producto a buscar");
			}
		});

		// Accion del boton Continuar
		continuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (lmonto.getText().equals("       -") || liva.getText().equals("       -")
						|| ltotal.getText().equals("       -")) {
					JOptionPane.showMessageDialog(null, "Imposible pasar a imprimir ticket");
				} else {
					control.creaTicketVenta();
					dispose();
				}
			}
		});

		// Accion del boton Calcular
		calcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (control.esNumeroReal(tcodigo.getText()))
					control.calculaTotal();
				else
					JOptionPane.showMessageDialog(null, "No es posible calcular el total");
			}
		});

		// Accion del boton Regresar
		regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.muestraVistaVendedor();
				control.limpiarDatos("Venta");
				dispose();
			}
		});
	}

	// Estos Métodos Sirven para Modificar los Datos a los JTextField
	public void setIdProducto(String id) {
		this.tcodigo.setText(id);
	}

	public void setMontototal(String total) {
		this.ltotal.setText(total);
	}

	public void setIva(String iva) {
		this.liva.setText(iva);
	}

	public void setMontounitario(String monto) {
		this.lmonto.setText(monto);
	}

	// Estos Métodos Sirven para Obtener los Datos a los JTextField
	public String getIva() {
		return liva.getText();
	}

	public String getTotal() {
		return ltotal.getText();
	}

	// Metodo para Obtener el Modelo de la Tabla
	public DefaultTableModel getTablaModelo() {
		return modelo;
	}

	// Obtenemos la Instancia del Control Venta
	public void setControl(ControlVenta control) {
		this.control = control;

	}

	// Muestra una Linea para Simbolizar la Suma
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GRAY);
		g.drawLine(470, 265, 580, 265);
	}
}
