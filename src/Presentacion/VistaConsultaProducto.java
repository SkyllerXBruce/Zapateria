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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Modelo.Producto;
import Negocio.ControlAlmacen;

/*import Modelo.Producto;
import Negocio.ControlConsultarProducto;
import Persistencia.DatabaseException;*/

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class VistaConsultaProducto extends JFrame {

	private JButton consultar, regresa;
	private JTextField tmodelo, ttipo, tcodigo;
	private JLabel codigo, modelo, tipo;
	private boolean pormodelo, porcodigo;
	private ControlAlmacen control;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaConsultaProducto frame = new VistaConsultaProducto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaConsultaProducto() {
		// Tamaño de la Ventana
		setSize(480, 460);
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
				if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de Cancelar la Consulta de un Producto?",
						"¿Cancelar Consulta?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
					control.muestraVistaAlmacen();
					control.limpiarDatos("Consultar");
					dispose();
				}
			}
		});
	}

	private void iniciarComponentes() {
		// creamos el panel y lo agregamos a la ventana
		JPanel panel = new JPanel(null);
		setContentPane(panel);
		JLabel titulo, modelotipo, numcodigo;

		ImageIcon imgIcon = new ImageIcon(VistaLogin.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		consultar = creaBoton("Consultar", 280, 360, 150, 30);
		regresa = creaBoton("", 40, 350, 50, 50);
		regresa.setIcon(imgIcon);
		consultar.setToolTipText("Consulta el Producto del Almacen");
		regresa.setToolTipText("Cancela la Operacion y Regresa a la Ventana de Administrar Almacen");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo = creaEtiqueta("Consultar Producto", 120, 40, 340, 35, 30);
		modelotipo = creaEtiqueta("Por Modelo y Tipo de Producto", 40, 120, 260, 25, 16);
		modelo = creaEtiqueta("Modelo:", 60, 160, 140, 25, 16);
		tipo = creaEtiqueta("Tipo:", 60, 200, 140, 25, 16);
		numcodigo = creaEtiqueta("Por Número de Código", 40, 260, 180, 25, 16);
		codigo = creaEtiqueta("Código:", 60, 300, 140, 25, 16);
		modelotipo.setToolTipText("Ingrese el Modelo y el Tipo del Producto");
		modelo.setToolTipText("Ingrese el Modelo del Producto");
		tipo.setToolTipText("Ingrese el Tipo del Producto");
		numcodigo.setToolTipText("Ingrese el Codigo del Producto");
		codigo.setToolTipText("Ingrese el Codigo del Producto");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de los TextFields
		// Como de la Letra
		tmodelo = creaCuadroTexto(210, 160, 250, 25, 14);
		ttipo = creaCuadroTexto(210, 200, 250, 25, 14);
		tcodigo = creaCuadroTexto(210, 300, 250, 25, 14);
		tmodelo.setToolTipText("Ingrese el Modelo del Producto");
		ttipo.setToolTipText("Ingrese el Tipo del Producto");
		tcodigo.setToolTipText("Ingrese el Codigo del Producto");

		// Se Realiza Acciones de los Componentes
		accionesComponentes();
		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(modelo);
		panel.add(tipo);
		panel.add(modelotipo);
		panel.add(codigo);
		panel.add(numcodigo);
		panel.add(tmodelo);
		panel.add(ttipo);
		panel.add(tcodigo);
		panel.add(consultar);
		panel.add(regresa);

	}

	// Método Para Crear las Propiedades del boton
	private JButton creaBoton(String nombre, int posx, int posy, int ancho, int alto) {
		JButton boton = new JButton(nombre);
		boton.setBounds(posx, posy, ancho, alto);
		boton.setFont(new Font("Serif", Font.ITALIC, 14));
		return boton;
	}

	// Método Para Crear las Propiedades de las Etiquetas
	private JLabel creaEtiqueta(String nombre, int posx, int posy, int ancho, int alto, int tama�o) {
		JLabel etiqueta = new JLabel(nombre);
		etiqueta.setBounds(posx, posy, ancho, alto);
		etiqueta.setFont(new Font("Serif", Font.ITALIC, tama�o));
		return etiqueta;
	}

	// Método Para Crear las Propiedades de los Cuadros de Texto
	private JTextField creaCuadroTexto(int posx, int posy, int ancho, int alto, int tama�o) {
		JTextField texto = new JTextField();
		texto.setBounds(posx, posy, ancho, alto);
		texto.setFont(new Font("Serif", Font.ITALIC, tama�o));
		texto.setText("");
		return texto;
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Accion del boton vendedores
		consultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pormodelo) {
					String productomodelo = tmodelo.getText();
					String productotipo = ttipo.getText();
					if (!tmodelo.getText().isEmpty())
						if (!ttipo.getText().isEmpty())
							if (control.existeProducto(productomodelo, productotipo)) {
								Producto producto = control.buscaProducto(productomodelo, productotipo);
								control.muestraVistaConsultaProductoRealizada(producto);
								dispose();
							} else
								JOptionPane.showMessageDialog(null,
										"El Producto No se Encuentra, Verifique que el Modelo o Tipo sean Correctos");
						else
							JOptionPane.showMessageDialog(null, "Es Necesario Escribir el Tipo");
					else
						JOptionPane.showMessageDialog(null, "Es Necesario Escribir el Modelo");
				} else if (porcodigo) {
					int productocodigo = 0;
					if (control.esNumero(tcodigo.getText()))
						productocodigo = Integer.valueOf(tcodigo.getText());
					if (productocodigo != 0) {
						if (control.existeVendedor(productocodigo)) {
							Producto producto = control.buscaProducto(productocodigo);
							control.muestraVistaConsultaProductoRealizada(producto);
							dispose();
						} else
							JOptionPane.showMessageDialog(null,
									"El Producto No se Encuentra, Verifique que el Codigo sea Correcto");
					}
				} else
					JOptionPane.showMessageDialog(null,
							"Es Necesario llenar los Campos ya sea Por Modelo y Tipo o Por Codigo del Producto");
			}
		});

		tmodelo.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				String nombre, apaterno;
				nombre = tmodelo.getText();
				apaterno = ttipo.getText();
				if (nombre.isEmpty() && apaterno.isEmpty()) {
					codigo.setEnabled(true);
					tcodigo.setEnabled(true);
					pormodelo = false;
				} else {
					codigo.setEnabled(false);
					tcodigo.setEnabled(false);
					pormodelo = true;
				}
			}
		});

		ttipo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String nombre, apaterno;
				nombre = tmodelo.getText();
				apaterno = ttipo.getText();
				if (nombre.isEmpty() && apaterno.isEmpty()) {
					codigo.setEnabled(true);
					tcodigo.setEnabled(true);
					pormodelo = false;
				} else {
					codigo.setEnabled(false);
					tcodigo.setEnabled(false);
					pormodelo = true;
				}
			}
		});

		tcodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (tcodigo.getText().isEmpty()) {
					modelo.setEnabled(true);
					tipo.setEnabled(true);
					tmodelo.setEnabled(true);
					ttipo.setEnabled(true);
					porcodigo = false;
				} else {
					modelo.setEnabled(false);
					tipo.setEnabled(false);
					tmodelo.setEnabled(false);
					ttipo.setEnabled(false);
					porcodigo = true;
				}
			}
		});

		// Accion del boton Comiciones
		regresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAlmacen();
				control.limpiarDatos("Consultar");
				dispose();
			}
		});
	}

	public void limpiarDatosConsultarProducto() {
		tmodelo.setText("");
		ttipo.setText("");
		tcodigo.setText("");
		modelo.setEnabled(true);
		tipo.setEnabled(true);
		tmodelo.setEnabled(true);
		ttipo.setEnabled(true);
		codigo.setEnabled(true);
		tcodigo.setEnabled(true);
		pormodelo = true;
		porcodigo = true;
	}

	public void setControl(ControlAlmacen controlalmacen) {
		this.control = controlalmacen;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(20, 280, 460, 280);
	}
}
