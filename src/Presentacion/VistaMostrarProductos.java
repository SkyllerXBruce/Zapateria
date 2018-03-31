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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.Componentes;
import Negocio.ControlAlmacen;
import Negocio.ControlVenta;

@SuppressWarnings("serial")
public class VistaMostrarProductos extends JFrame {

	// Variables Globales
	private JButton finaliza;
	private ControlAlmacen control;
	private ControlVenta controlventa;
	private DefaultTableModel modelo;
	private boolean vendedor = false;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaMostrarProductos frame = new VistaMostrarProductos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaMostrarProductos
	public VistaMostrarProductos() {
		// Propiedades de la Ventana
		setSize(900, 500);
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
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente Desea Salir de la Lista de Vendedores?",
						"¿Cancelar Mostrar Lista de Vendedores?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.muestraVistaAlmacen();
					dispose();
				}
			}
		});
	}

	private void iniciarComponentes() {
		// Creamos la Instancia del JPanel Así como de Algunos Componentes
		JPanel panel = new JPanel();
		Componentes componente = new Componentes();
		JTable tablaproductos = new JTable();
		JScrollPane scroll = new JScrollPane();
		JLabel titulo;
		String[] nombrescolumnas = { "Codigo", "Modelo", "Tipo", "Color", "Costo Unitario", "Talla", "Cantidad" };
		String[][] datos = {};

		// Modificamos Propiedades de JPanel y lo Agregamos a la Ventana
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);

		// Creamos y Agregamos las Propiedades del JButton
		finaliza = componente.creaBoton("Finalizar", 380, 420, 140, 30);
		
		// Creamos y Agregamos las Propiedades del JLabel
		titulo = componente.creaEtiqueta("Mostrar Productos", 300, 40, 340, 35, 30);
		
		// Se Crean el Modelo y Se Agregan los Datos de los Productos
		modelo = new DefaultTableModel(datos, nombrescolumnas);

		// Modificamos las Propiedades de la Tablas y Agregamos el Modelo de Tabla
		tablaproductos.setModel(modelo);
		tablaproductos.setEnabled(false);
		tablaproductos.getColumnModel().getColumn(0).setPreferredWidth(60);
		tablaproductos.getColumnModel().getColumn(1).setPreferredWidth(160);
		tablaproductos.getColumnModel().getColumn(2).setPreferredWidth(160);
		tablaproductos.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaproductos.getColumnModel().getColumn(4).setPreferredWidth(100);
		tablaproductos.getColumnModel().getColumn(5).setPreferredWidth(80);
		tablaproductos.getColumnModel().getColumn(6).setPreferredWidth(60);

		// Modificamos Propiedades del ScrollPane Y Agregamos la Tabla
		scroll.setBounds(40, 100, 820, 300);
		scroll.setViewportView(tablaproductos);

		// Se Realiza Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(finaliza);
		panel.add(scroll);
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Accion del boton Finaliza
		finaliza.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vendedor = controlventa.esVendedor();
				if (vendedor)
					controlventa.muestraVistaVendedor();
				else
					control.muestraVistaAlmacen();
				controlventa.setVendedor(false);
				control.limpiarDatos("Mostrar");
				dispose();
			}
		});
	}

	// Limpia los Datos del Producto de la Tabla
	public void limpiarDatosMostrarProductos() {
		if (modelo.getColumnCount() != 0)
			for (int i = modelo.getRowCount() - 1; i >= 0; i--)
				modelo.removeRow(i);
	}

	// Metodo para Obtener el Modelo de la Tabla
	public DefaultTableModel getTablaModelo() {
		return modelo;
	}

	// Métodos para Obtener la Instancia de los Controles Correspondientes
	public void setControl(ControlAlmacen controlproductos) {
		this.control = controlproductos;
	}

	public void setControl(ControlVenta controlventa) {
		this.controlventa = controlventa;
	}
}
