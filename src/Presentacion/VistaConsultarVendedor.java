package Presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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

import Modelo.Usuario;
import Negocio.ControlVendedores;

@SuppressWarnings("serial")
public class VistaConsultarVendedor extends JFrame {

	private JButton consultar, regresar;
	private JTextField tnombre, tapaterno, tamaterno, tid;
	private JLabel id, nombre, apaterno, amaterno;
	private boolean pornombre, porid;
	private ControlVendedores control;
	
	/**
	 * Launch the application.
	 */
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

	public VistaConsultarVendedor() {
		// Tamaño de la Ventana
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
		// creamos el panel y lo agregamos a la ventana
		JPanel panel = new JPanel(null);
		setContentPane(panel);
		JLabel titulo, name, numid;
		
		ImageIcon imgIcon = new ImageIcon(VistaAgregarVendedor.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		consultar = creaBoton("Consultar", 260, 400, 150, 30);
		regresar = creaBoton("", 40, 390, 50, 50);
		regresar.setIcon(imgIcon);
		consultar.setToolTipText("Consulta el Vendedor Mostrando los Datos Correspondientes");
		regresar.setToolTipText("Cancela la Operacion y Regresa a la Ventana de Administrar Vendedores");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo = creaEtiqueta("Consultar Vendedor", 120, 40, 340, 35, 30);
		name = creaEtiqueta("Por Nombre", 40, 120, 140, 25, 16);
		nombre = creaEtiqueta("Nombre:", 60, 160, 140, 25, 16);
		apaterno = creaEtiqueta("Apellido Paterno:", 60, 200, 140, 25, 16);
		amaterno = creaEtiqueta("Apellido Materno:", 60, 240, 140, 25, 16);
		numid = creaEtiqueta("Por Número de ID", 40, 300, 140, 25, 16);
		id = creaEtiqueta("ID:", 60, 340, 140, 25, 16);

		nombre.setToolTipText("Ingrese Nombre del Vendedor");
		apaterno.setToolTipText("Ingrese Apellido Paterno del Vendedor");
		apaterno.setToolTipText("Ingrese Apellido Materno del Vendedor");
		name.setToolTipText("Ingrese Curp del Vendedor");
		id.setToolTipText("Ingrese Dirección del Vendedor");
		numid.setToolTipText("Ingrese Telefono del Vendedor");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de los TextFields
		// Como de la Letra
		tnombre = creaCuadroTexto(210, 160, 250, 25, 14);
		tapaterno = creaCuadroTexto(210, 200, 250, 25, 14);
		tamaterno = creaCuadroTexto(210, 240, 250, 25, 14);
		tid = creaCuadroTexto(210, 340, 250, 25, 14);
		tnombre.setToolTipText("Ingrese Nombre del Vendedor");
		tapaterno.setToolTipText("Ingrese Apellido Paterno del Vendedor");
		tapaterno.setToolTipText("Ingrese Apellido Materno del Vendedor");
		tid.setToolTipText("Ingrese ID del Vendedor");

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

	// Método Para Crear las Propiedades del boton
	private JButton creaBoton(String nombre, int posx, int posy, int ancho, int alto) {
		// Se Crea e Inicializa un boton de la Clase JButton
		JButton boton = new JButton(nombre);

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto del Boton Como la
		// Letra del Texto
		boton.setBounds(posx, posy, ancho, alto);
		boton.setFont(new Font("Serif", Font.ITALIC, 14));
		return boton;
	}

	// Método Para Crear las Propiedades de las Etiquetas
	private JLabel creaEtiqueta(String nombre, int posx, int posy, int ancho, int alto, int tamaño) {
		// Se Crea e Inicializa una Etiqueta de la Clase JLabel
		JLabel etiqueta = new JLabel(nombre);

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de la Etiqueta asi
		// Como la Letra del Texto
		etiqueta.setBounds(posx, posy, ancho, alto);
		etiqueta.setFont(new Font("Serif", Font.ITALIC, tamaño));
		return etiqueta;
	}

	// Método Para Crear las Propiedades de los Cuadros de Texto
	private JTextField creaCuadroTexto(int posx, int posy, int ancho, int alto, int tamaño) {
		// Se Crea e Inicializa el Cuadro de Texto de la Clase JTextField
		JTextField texto = new JTextField();

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto del Cuadro de Texto
		// asi como la Letra del Texto y se Asegura que Este Vacio el Cuadro de Texto
		texto.setBounds(posx, posy, ancho, alto);
		texto.setFont(new Font("Serif", Font.ITALIC, tamaño));
		texto.setText("");
		return texto;
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Accion del boton vendedores
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
									"El Vendedor No se Encuentra, Verifique que sea el ID Correcto");
					}
				} else
					JOptionPane.showMessageDialog(null, "Es Necesario llenar los Campos ya sea Por Nombre o Por ID");
			}
		});

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

		// Accion del boton Comiciones
		regresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAdministarVendedores();
				control.limpiarDatos("Consultar");
				dispose();
			}
		});
	}
	
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
		pornombre = true;
		porid = true;
	}

	public void setControl(ControlVendedores controlvendedores) {
		this.control = controlvendedores;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(20, 310, 460, 310);
	}
}
