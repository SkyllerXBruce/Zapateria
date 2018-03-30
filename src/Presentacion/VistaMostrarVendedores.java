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
import javax.swing.table.DefaultTableModel;

import Modelo.Componentes;
import Negocio.ControlVendedores;

@SuppressWarnings("serial")
public class VistaMostrarVendedores extends JFrame {

	private JButton finaliza;
	private ControlVendedores control;
	private DefaultTableModel modelo;
	
	//
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaMostrarVendedores frame = new VistaMostrarVendedores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//
	public VistaMostrarVendedores() {
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
					control.muestraVistaAdministarVendedores();
					dispose();
				}
			}
		});
	}

	private void iniciarComponentes() {
		// creamos el panel y lo agregamos a la ventana
		JPanel panel = new JPanel(null);
		Componentes componente = new Componentes();
		String[] nombrescolumnas = { "ID", "Nombre Completo", "Curp", "Correo", "Telefono", "Usuario" };
		String[][] datos = {};
		modelo = new DefaultTableModel(datos, nombrescolumnas);
		JTable tablavendedores = new JTable(modelo);
		JLabel titulo;

		setContentPane(panel);

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		finaliza = componente.creaBoton("Finalizar", 380, 420, 140, 30);
		finaliza.setToolTipText("Regresa a la Ventana de Administrar Vendedores");

		tablavendedores.setModel(modelo);
		tablavendedores.setEnabled(false);
		tablavendedores.getColumnModel().getColumn(0).setPreferredWidth(60);
		tablavendedores.getColumnModel().getColumn(1).setPreferredWidth(160);
		tablavendedores.getColumnModel().getColumn(2).setPreferredWidth(160);
		tablavendedores.getColumnModel().getColumn(3).setPreferredWidth(160);
		tablavendedores.getColumnModel().getColumn(4).setPreferredWidth(60);
		tablavendedores.getColumnModel().getColumn(5).setPreferredWidth(80);

		//
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 100, 820, 300);
		scrollPane.setViewportView(tablavendedores);

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo = componente.creaEtiqueta("Mostrar Vendedores", 300, 40, 340, 35, 30);

		// Se Realiza Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(finaliza);
		panel.add(scrollPane);
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Accion del boton vendedores
		finaliza.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAdministarVendedores();
				control.limpiarDatos("Mostrar");
				dispose();
			}
		});
	}

	public void limpiarDatosMostrarVendedores() {
		if (modelo.getColumnCount() != 0)
			for (int i = modelo.getRowCount() - 1; i >= 0; i--)
				modelo.removeRow(i);
	}

	public DefaultTableModel getTablaModelo() {
		return modelo;
	}

	public void setControl(ControlVendedores controlvendedores) {
		this.control = controlvendedores;
	}
}
