package Presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Modelo.Componentes;
import Modelo.Usuario;
import Negocio.ControlVendedores;

@SuppressWarnings("serial")
public class VistaConsultarVendedor extends JFrame {

	// Variables Globales
	private JButton consultar, regresar;
	private JTextField tnombre, tapaterno, tamaterno, tid;
	private JLabel id, nombre, apaterno, amaterno;
	private boolean pornombre, porid;
	private ControlVendedores control;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaConsultarVendedor frame = new VistaConsultarVendedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaConsultarVendedor
	public VistaConsultarVendedor() {
		// Propiedades de la Ventana
		setSize(480, 480);
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
				if (JOptionPane.showConfirmDialog(rootPane, "¿Realmente Desea Cancelar la Consulta del Vendedor?",
						"¿Cancelar Consulta de un Vendedor?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.muestraVistaAdministarVendedores();
					dispose();
				}
			}
		});
	}

	private void iniciarComponentes() {
		// Creamos la Instancia del JPanel Así como de Algunos Componentes
		JPanel panel = new JPanel();
		JLabel titulo, name, numid;
		Componentes componente = new Componentes();

		// Modificamos Propiedades de JPanel y lo Agregamos a la Ventana
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);

		// Imagen del Boton regresar
		ImageIcon imgIcon = new ImageIcon(VistaAgregarVendedor.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del JButton
		consultar = componente.creaBoton("Consultar", 260, 400, 150, 30);
		regresar = componente.creaBoton("", 40, 390, 50, 50);
		regresar.setIcon(imgIcon);

		// Creamos y Agregamos las Propiedades del JLabel
		titulo = componente.creaEtiqueta("Consultar Vendedor", 120, 40, 340, 35, 30);
		name = componente.creaEtiqueta("Por Nombre", 40, 120, 140, 25, 16);
		nombre = componente.creaEtiqueta("Nombre:", 60, 160, 140, 25, 16);
		apaterno = componente.creaEtiqueta("Apellido Paterno:", 60, 200, 140, 25, 16);
		amaterno = componente.creaEtiqueta("Apellido Materno:", 60, 240, 140, 25, 16);
		numid = componente.creaEtiqueta("Por Número de ID", 40, 300, 140, 25, 16);
		id = componente.creaEtiqueta("ID:", 60, 340, 140, 25, 16);

		// Creamos y Agregamos las Propiedades del JTextField
		tnombre = componente.creaCuadroTexto(210, 160, 250, 25, 14);
		tapaterno = componente.creaCuadroTexto(210, 200, 250, 25, 14);
		tamaterno = componente.creaCuadroTexto(210, 240, 250, 25, 14);
		tid = componente.creaCuadroTexto(210, 340, 250, 25, 14);

		// Se Realiza Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(nombre);
		panel.add(apaterno);
		panel.add(amaterno);
		panel.add(name);
		panel.add(id);
		panel.add(numid);
		panel.add(tnombre);
		panel.add(tapaterno);
		panel.add(tamaterno);
		panel.add(tid);
		panel.add(consultar);
		panel.add(regresar);
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Accion del boton Concultar
		consultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pornombre) {
					String nombre = tnombre.getText() + " " + tapaterno.getText() + " " + tamaterno.getText();
					if (!tnombre.getText().isEmpty())
						if (!tapaterno.getText().isEmpty())
							if (!tamaterno.getText().isEmpty()) {
								if (control.existeVendedor(nombre)) {
									Usuario vendedor = control.buscaVendedorPorNombre(nombre);
									control.muestraVistaConsultaRealizada(vendedor);
									dispose();
								} else
									JOptionPane.showMessageDialog(null,
											"El Vendedor No se Encuentra, Verifique que sea el Nombre Correcto");
							} else
								JOptionPane.showMessageDialog(null, "Es Necesario Escribir el Apellido Materno");
						else
							JOptionPane.showMessageDialog(null, "Es Necesario Escribir el Apellido Paterno");
					else
						JOptionPane.showMessageDialog(null, "Es Necesario Escribir el Nombre");
				} else if (porid) {
					String id;
					id = tid.getText();
					if (!id.isEmpty()) {
						if (control.existeVendedor(id)) {
							Usuario vendedor = control.buscaVendedorPorId(id);
							control.muestraVistaConsultaRealizada(vendedor);
							dispose();
						} else
							JOptionPane.showMessageDialog(null,
									"El Vendedor No se Encuentra, Verifique que el ID del Vendedor sea Correcto");
					}
				} else
					JOptionPane.showMessageDialog(null, "Es Necesario llenar los Campos ya sea Por Nombre o Por ID");
			}
		});

		// Acciones del JTextField tnombre
		tnombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String nombre, apaterno, amaterno;
				nombre = tnombre.getText();
				apaterno = tapaterno.getText();
				amaterno = tamaterno.getText();
				if (nombre.isEmpty() && apaterno.isEmpty() && amaterno.isEmpty()) {
					id.setEnabled(true);
					tid.setEnabled(true);
					pornombre = false;
				} else {
					id.setEnabled(false);
					tid.setEnabled(false);
					pornombre = true;
				}
			}
		});

		// Acciones del JTextField tapaterno
		tapaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String nombre, apaterno, amaterno;
				nombre = tnombre.getText();
				apaterno = tapaterno.getText();
				amaterno = tamaterno.getText();
				if (nombre.isEmpty() && apaterno.isEmpty() && amaterno.isEmpty()) {
					id.setEnabled(true);
					tid.setEnabled(true);
					pornombre = false;
				} else {
					id.setEnabled(false);
					tid.setEnabled(false);
					pornombre = true;
				}
			}
		});

		// Acciones del JTextField tamaterno
		tamaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String nombre, apaterno, amaterno;
				nombre = tnombre.getText();
				apaterno = tapaterno.getText();
				amaterno = tamaterno.getText();
				if (nombre.isEmpty() && apaterno.isEmpty() && amaterno.isEmpty()) {
					id.setEnabled(true);
					tid.setEnabled(true);
					pornombre = false;
				} else {
					id.setEnabled(false);
					tid.setEnabled(false);
					pornombre = true;
				}
			}
		});

		// Acciones del JTextField tid
		tid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (tid.getText().isEmpty()) {
					nombre.setEnabled(true);
					apaterno.setEnabled(true);
					amaterno.setEnabled(true);
					tnombre.setEnabled(true);
					tapaterno.setEnabled(true);
					tamaterno.setEnabled(true);
					porid = false;
				} else {
					nombre.setEnabled(false);
					apaterno.setEnabled(false);
					amaterno.setEnabled(false);
					tnombre.setEnabled(false);
					tapaterno.setEnabled(false);
					tamaterno.setEnabled(false);
					porid = true;
				}
			}
		});

		// Accion del boton Regresa
		regresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAdministarVendedores();
				control.limpiarDatos("Consultar");
				dispose();
			}
		});
	}

	// Metodo que limpia los TextFields
	public void limpiarDatosConsultaVendedor() {
		tnombre.setText("");
		tapaterno.setText("");
		tamaterno.setText("");
		tid.setText("");
		nombre.setEnabled(true);
		apaterno.setEnabled(true);
		amaterno.setEnabled(true);
		tnombre.setEnabled(true);
		tapaterno.setEnabled(true);
		tamaterno.setEnabled(true);
		id.setEnabled(true);
		tid.setEnabled(true);
		pornombre = false;
		porid = false;
	}

	// Obtenemos la Instancia del Control Vendedores
	public void setControl(ControlVendedores controlvendedores) {
		this.control = controlvendedores;
	}

	// Método para Dibujar una Linea como Separador
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(20, 310, 460, 310);
	}
}
