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

	private JTextField codigo;
	private JLabel total, iva, montounitario;
	private JButton buscar, continuar, regresar, calcular;
	private ControlVenta control;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
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

	// Crea Ventana Venta de Calzado
	public VistaVentaCalzado() {
		setTitle("Venta de calzado");
		setBounds(100, 100, 680, 360);
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
		JPanel contentPane = new JPanel();
		JLabel lblcodigo, lblmonto, lblIva, lblTotal;
		Componentes componente = new Componentes();
		JTable table = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		String columnaventa[] = { "Modelo", "Tipo", "Color", "Talla", "Costo", "Disponibles", "Pares a Vender" };
		String datosventa[][] = {};
		modelo = new DefaultTableModel(datosventa, columnaventa) {
			@Override
			   public boolean isCellEditable(int row, int column) {
			       return column == 6;
			   }
		};
		
		

		// Propiedades del Panel y se Agrega a la Ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//
		ImageIcon imgIcon = new ImageIcon(VistaVentaCalzado.class.getResource("return.png"));
		Image ticket = imgIcon.getImage();
		Image ticketScaled = ticket.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(ticketScaled);

		// Propiedades del Boton Buscar y se Agrega al Panel
		buscar = componente.creaBoton("Buscar", 340, 25, 90, 26);
		continuar = componente.creaBoton("Continuar", 460, 290, 120, 26);
		calcular = componente.creaBoton("Calcular Total", 180, 200, 160, 26);
		regresar = componente.creaBoton("", 40, 260, 50, 50);
		regresar.setIcon(imgIcon);

		// Propiedades de la Etiqueta "Id" y se Agrega al Panel
		lblcodigo = componente.creaEtiqueta("Codigo del Producto", 40, 25, 160, 26, 15);
		lblmonto = componente.creaEtiqueta("Monto por unidad  $", 320, 170, 160, 26, 15);
		lblIva = componente.creaEtiqueta("IVA (16%)   $", 380, 200, 100, 26, 15);
		lblTotal = componente.creaEtiqueta("Total  $", 420, 250, 60, 26, 15);
		montounitario = componente.creaEtiqueta("       -", 480, 170, 90, 24, 15);
		iva = componente.creaEtiqueta("       -", 480, 200, 90, 24, 15);
		total = componente.creaEtiqueta("       -", 480, 250, 90, 24, 15);

		// Propiedades del textfield "Id Producto" y se Agrega al Panel
		codigo = componente.creaCuadroTexto(220, 25, 90, 24, 15);

		// Propiedades de la table y se Agrega el modelo
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		
		// Propiedades del ScrollPane Agregando la Tabla al Scrollpane y se Agrega al
		// Panel
		scrollPane.setBounds(40, 80, 600, 74);
		scrollPane.setViewportView(table);

		// Acciones de los Componentes
		accionesComponentes();

		contentPane.add(buscar);
		contentPane.add(continuar);
		contentPane.add(calcular);
		contentPane.add(regresar);
		contentPane.add(lblcodigo);
		contentPane.add(lblmonto);
		contentPane.add(lblIva);
		contentPane.add(lblTotal);
		contentPane.add(codigo);
		contentPane.add(montounitario);
		contentPane.add(iva);
		contentPane.add(total);
		contentPane.add(scrollPane);
	}

	private void accionesComponentes() {
		// Validamos si los datos son correctos, y ejecutamos la búsqueda en este
		// método.
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String prod = codigo.getText();
				if (control.esNumeroReal(prod)) {
					if (modelo.getRowCount() != 0)
						control.limpiarDatos("Venta");
					control.buscaProducto(Integer.valueOf(prod));
					codigo.setText(prod);
				} else
					JOptionPane.showMessageDialog(null, "Ingrese id de producto a buscar");
			}
		});

		// Validamos si los datos son correctos, y ejecutamos la impresión del ticket.
		continuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (montounitario.getText().equals("       -") || iva.getText().equals("       -")
						|| total.getText().equals("       -")) {
					JOptionPane.showMessageDialog(null, "Imposible pasar a imprimir ticket");
				} else {
					control.creaTicketVenta();
					dispose();
				}
			}
		});

		// Validamos si ya hay producto seleccionado, para calcular su costo y total
		calcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (control.esNumeroReal(codigo.getText()))
					control.calculaTotal();
				else
					JOptionPane.showMessageDialog(null, "No es posible calcular el total");
			}
		});

		// Cerramos la ventana.
		regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.muestraVistaVendedor();
				control.limpiarDatos("Venta");
				dispose();
			}
		});
	}

	// Instanciamos control a nuestra vista.

	public void setControl(ControlVenta control) {
		this.control = control;

	}
	// Estos métodos nos sirven para agregar datos a los textfields que despliegan
	// los costos.

	public void setIdProducto(String id) {
		this.codigo.setText(id);
	}

	public void setMontototal(String total) {
		this.total.setText(total);
	}

	public void setIva(String iva) {
		this.iva.setText(iva);
	}

	public void setMontounitario(String montounitario) {
		this.montounitario.setText(montounitario);
	}

	// Al ya estar calculados, solo los retornamos, para la impresión del ticket.

	public String getIva() {
		return iva.getText();
	}

	public String getTotal() {
		return total.getText();
	}

	public DefaultTableModel getTablaModelo() {
		return modelo;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GRAY);
		g.drawLine(470, 265, 580, 265);
	}
}
