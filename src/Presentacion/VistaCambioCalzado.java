package Presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.Componentes;
import Negocio.ControlVenta;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VistaCambioCalzado extends JFrame {

	// Variables Globales
	private JTextField tfolio;
	private DefaultTableModel modeloventa, modelocambio;
	private JButton buscar, regresar, cambio;
	private JTable tablacambio;
	private boolean existecambio = false;
	private ControlVenta control;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaCambioCalzado frame = new VistaCambioCalzado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaCambioCalzado
	public VistaCambioCalzado() {
		// Propiedades de la Ventana
		setTitle("Cambio de calzado");
		setBounds(100, 100, 660, 440);
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
					control.limpiarDatos("Cambio");
					control.muestraVistaVendedor();
					dispose();
				}
			}
		});
	}

	// Creamos y Agregamos los Componetes de la Ventana
	private void iniciarComponentes() {
		// Creamos la Instancia del JPanel Así como de Algunos Componentes
		JPanel panel = new JPanel();
		JLabel folio, cambios;
		JTable tablaventa = new JTable();
		tablacambio = new JTable();
		JScrollPane scrollventa = new JScrollPane();
		JScrollPane scrollcambio = new JScrollPane();
		Componentes componente = new Componentes();
		String[] columnaventa = { "Modelo", "Tipo", "Color", "Talla", "Costo Unitario", "Total" };
		String[] columnacambio = { "Modelo", "Tipo", "Color", "Talla", "Costo Unitario", "Total", "Disponibles",
				"Selección" };
		Object[][] datosventa = {};
		Object[][] datoscambio = {};

		// Imagen del Boton regresar
		ImageIcon imgIcon = new ImageIcon(VistaAgregarVendedor.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Modificamos Propiedades de JPanel y lo Agregamos a la Ventana
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);

		// Creamos y Agregamos las Propiedades del JButton
		buscar = componente.creaBoton("Buscar", 260, 20, 100, 26);
		cambio = componente.creaBoton("Cambio", 440, 360, 100, 26);
		regresar = componente.creaBoton("", 120, 350, 50, 50);
		regresar.setIcon(imgIcon);

		// Creamos y Agregamos las Propiedades del JLabel
		folio = componente.creaEtiqueta("Folio de Venta", 20, 20, 140, 30, 14);
		cambios = componente.creaEtiqueta("Opciones de Cambio", 20, 110, 160, 30, 14);

		// Creamos y Agregamos las Propiedades del JTextField
		tfolio = componente.creaCuadroTexto(140, 20, 90, 24, 14);

		// Se Crean el Modelo y Se Agregan los Datos de Venta y Cambio
		modeloventa = new DefaultTableModel(datosventa, columnaventa);
		modelocambio = new DefaultTableModel(datoscambio, columnacambio) {
			@SuppressWarnings("rawtypes") // Identifica El Tipo del Objeto Ingresado
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, Double.class, Double.class,
					Double.class, Integer.class, Boolean.class };

			// Obtiene la Cantidad de Columnas
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};

		// Se Agregan los Modelos a las Tablas
		tablaventa.setModel(modeloventa);
		tablacambio.setModel(modelocambio);

		// Propiedades de los scrollPane
		scrollventa.setBounds(20, 60, 620, 40);
		scrollventa.setViewportView(tablaventa);
		scrollcambio.setBounds(20, 140, 620, 200);
		scrollcambio.setViewportView(tablacambio);

		// Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panel.add(buscar);
		panel.add(cambio);
		panel.add(regresar);
		panel.add(folio);
		panel.add(cambios);
		panel.add(tfolio);
		panel.add(scrollventa);
		panel.add(scrollcambio);
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Verifica que solo este Seleccionada una Casilla
		tablacambio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = tablacambio.getSelectedRow();
				for (int j = 0; j < modelocambio.getRowCount(); j++)
					if (j != i)
						modelocambio.setValueAt(false, j, 7);
			}
		});

		// Accion del boton Buscar
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String folio = tfolio.getText();
				if (control.esNumeroReal(folio)) {
					if (modeloventa.getRowCount() != 0)
						control.limpiarDatos("Cambio");
					control.buscaTicket(Integer.valueOf(folio));
					tfolio.setText(folio);
				} else
					JOptionPane.showMessageDialog(null, "Ingrese folio de venta");
			}
		});

		// Accion del boton Cambio
		cambio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String folio = tfolio.getText();
				int f = 0;
				if (control.esNumeroReal(folio)) {
					f = Integer.valueOf(folio);
					if (control.cambioProducto(f)) {
						control.limpiarDatos("Cambio");
						existecambio = true;
						dispose();
					} 
				}else
					JOptionPane.showMessageDialog(null, "Error, Ingrese un Folio de Ticket Valido");
			}
		});

		// Accion del boton regresar
		regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.limpiarDatos("Cambio");
				control.muestraVistaVendedor();
				dispose();
			}
		});
	}

	// Método que Muestra si se Realizo un Cambio o no
	public boolean realizaCambio() {
		return existecambio;
	}

	// Metodos que Obtienen los Modelos de las Tablas
	public DefaultTableModel getTablaModeloVenta() {
		return this.modeloventa;
	}

	public DefaultTableModel getTablaModeloCambio() {
		return this.modelocambio;
	}

	// Método para Modificar el Folio de Venta del JTextField
	public void setFolioventa(String folioventa) {
		this.tfolio.setText(folioventa);
	}

	// Obtenemos la Instancia del Control Venta
	public void setControl(ControlVenta control) {
		this.control = control;
	}
}
