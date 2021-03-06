package Presentacion;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import Negocio.ControlAlmacen;
import Modelo.Componentes;
import Modelo.Producto;

@SuppressWarnings("serial")
public class VistaEliminarProducto extends JFrame {

	// Variables Globales
	private JButton eliminar, regresa;
	private JTextField tmodelo, ttipo, tcolor, ttalla, tcodigo;
	private JLabel codigo, modelo, tipo, color, talla;
	private boolean pormodelo, porcodigo;
	private ControlAlmacen control;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaEliminarProducto frame = new VistaEliminarProducto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaEliminarProducto
	public VistaEliminarProducto() {
		// Propiedades de la Ventana
		setSize(480, 540);
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
				if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de Cancelar la Eliminación?",
						"¿Cancelar Eliminar un Producto?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
					control.muestraVistaAlmacen();
					control.limpiarDatos("Eliminar");
					dispose();
				}
			}
		});
	}

	private void iniciarComponentes() {
		// Creamos la Instancia del JPanel Así como de Algunos Componentes
		JPanel panel = new JPanel();
		JLabel titulo, datosproducto, codigoproducto;
		Componentes componente = new Componentes();

		// Modificamos Propiedades de JPanel y lo Agregamos a la Ventana
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);

		// Imagen del Boton regresar
		ImageIcon imgIcon = new ImageIcon(VistaLogin.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del JButton
		eliminar = componente.creaBoton("Eliminar", 280, 440, 150, 30);
		regresa = componente.creaBoton("", 40, 430, 50, 50);
		regresa.setIcon(imgIcon);

		// Creamos y Agregamos las Propiedades del JLabel
		titulo = componente.creaEtiqueta("Eliminar Producto", 120, 40, 340, 35, 30);
		datosproducto = componente.creaEtiqueta("Por Datos del Producto", 40, 120, 220, 25, 16);
		modelo = componente.creaEtiqueta("Modelo:", 60, 160, 140, 25, 16);
		tipo = componente.creaEtiqueta("Tipo:", 60, 200, 140, 25, 16);
		color = componente.creaEtiqueta("Color:", 60, 240, 140, 25, 16);
		talla = componente.creaEtiqueta("Talla:", 60, 280, 140, 25, 16);
		codigoproducto = componente.creaEtiqueta("Por Código del Producto", 40, 340, 220, 25, 16);
		codigo = componente.creaEtiqueta("Código:", 60, 380, 140, 25, 16);

		// Creamos y Agregamos las Propiedades del JTextField
		tmodelo = componente.creaCuadroTexto(210, 160, 250, 25, 14);
		ttipo = componente.creaCuadroTexto(210, 200, 250, 25, 14);
		tcolor = componente.creaCuadroTexto(210, 240, 250, 25, 14);
		ttalla = componente.creaCuadroTexto(210, 280, 250, 25, 14);
		tcodigo = componente.creaCuadroTexto(210, 380, 250, 25, 14);

		// Se Realiza Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(modelo);
		panel.add(tipo);
		panel.add(color);
		panel.add(talla);
		panel.add(datosproducto);
		panel.add(codigo);
		panel.add(codigoproducto);
		panel.add(tmodelo);
		panel.add(ttipo);
		panel.add(tcolor);
		panel.add(ttalla);
		panel.add(tcodigo);
		panel.add(eliminar);
		panel.add(regresa);
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Accion del boton Eliminar
		eliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pormodelo) {
					double productotalla = 0;
					String productomodelo = tmodelo.getText();
					String productotipo = ttipo.getText();
					String productocolor = tcolor.getText();
					if (!productomodelo.isEmpty())
						if (!productotipo.isEmpty())
							if (!productocolor.isEmpty()) {
								if (control.esNumeroReal(ttalla.getText()))
									productotalla = Double.valueOf(ttalla.getText());
								if (productotalla > 0)
									if (control.existeProducto(productomodelo, productotipo, productocolor,
											productotalla)) {
										Producto producto = control.buscaProducto(productomodelo, productotipo,
												productocolor, productotalla);
										if (JOptionPane.showConfirmDialog(null,
												"¿Esta seguro de Eliminar el Producto " + producto.dameModelo() + "?",
												"¿Eliminar Producto?",
												JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
											if (control.eliminarProducto(producto))
												JOptionPane.showMessageDialog(null,
														"El Producto Se Elimino Correctamente");
											else
												JOptionPane.showMessageDialog(null,
														"Error, El Producto No Se Pudo Eliminar");
											control.muestraVistaAlmacen();
											control.limpiarDatos("Eliminar");
											dispose();
										}
									} else
										JOptionPane.showMessageDialog(null,
												"El Producto No se Encuentra, Verifique que los Datos sean Correctos");
								else
									JOptionPane.showMessageDialog(null, "Es Necesario Escribir una Talla Valida");
							} else
								JOptionPane.showMessageDialog(null, "Es Necesario Escribir el Color");
						else
							JOptionPane.showMessageDialog(null, "Es Necesario Escribir el Tipo");
					else
						JOptionPane.showMessageDialog(null, "Es Necesario Escribir el Modelo");
				} else if (porcodigo) {
					int codigop = 0;
					if (control.esNumeroReal(tcodigo.getText()))
						codigop = Integer.valueOf(tcodigo.getText());
					if (codigop != 0) {
						if (control.existeProducto(codigop)) {
							Producto producto = control.buscaProducto(codigop);
							if (JOptionPane.showConfirmDialog(null,
									"¿Esta seguro de Eliminar el Producto " + producto.dameModelo() + "?",
									"¿Eliminar Producto?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
								if (control.eliminarProducto(producto))
									JOptionPane.showMessageDialog(null, "El Producto Se Elimino Correctamente");
								else
									JOptionPane.showMessageDialog(null, "Error, El Producto No Se Pudo Eliminar");
								control.muestraVistaAlmacen();
								control.limpiarDatos("Eliminar");
								dispose();
							}
						} else
							JOptionPane.showMessageDialog(null,
									"El Producto No se Encuentra, Verifique que el Codigo sea Correcto");
					} else
						JOptionPane.showMessageDialog(null,
								"El Producto No se Encuentra, Verifique que el Codigo sea Correcto");
				} else
					JOptionPane.showMessageDialog(null,
							"Es Necesario llenar los Campos ya sea Por Datos o Por Codigo de Producto");
			}
		});

		// Acciones del JTextField tmodelo
		tmodelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String modelo, tipo, color, talla;
				modelo = tmodelo.getText();
				tipo = ttipo.getText();
				color = tcolor.getText();
				talla = ttalla.getText();
				if (modelo.isEmpty() && tipo.isEmpty() && color.isEmpty() && talla.isEmpty()) {
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

		// Acciones del JTextField ttipo
		ttipo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String modelo, tipo, color, talla;
				modelo = tmodelo.getText();
				tipo = ttipo.getText();
				color = tcolor.getText();
				talla = ttalla.getText();
				if (modelo.isEmpty() && tipo.isEmpty() && color.isEmpty() && talla.isEmpty()) {
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

		// Acciones del JTextField tcolor
		tcolor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String modelo, tipo, color, talla;
				modelo = tmodelo.getText();
				tipo = ttipo.getText();
				color = tcolor.getText();
				talla = ttalla.getText();
				if (modelo.isEmpty() && tipo.isEmpty() && color.isEmpty() && talla.isEmpty()) {
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

		// Acciones del JTextField ttalla
		ttalla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String modelo, tipo, color, talla;
				modelo = tmodelo.getText();
				tipo = ttipo.getText();
				color = tcolor.getText();
				talla = ttalla.getText();
				if (modelo.isEmpty() && tipo.isEmpty() && color.isEmpty() && talla.isEmpty()) {
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

		// Acciones del JTextField tcodigo
		tcodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (tcodigo.getText().isEmpty()) {
					modelo.setEnabled(true);
					tipo.setEnabled(true);
					color.setEnabled(true);
					talla.setEnabled(true);
					tmodelo.setEnabled(true);
					ttipo.setEnabled(true);
					tcolor.setEnabled(true);
					ttalla.setEnabled(true);
					porcodigo = false;
				} else {
					modelo.setEnabled(false);
					tipo.setEnabled(false);
					color.setEnabled(false);
					talla.setEnabled(false);
					tmodelo.setEnabled(false);
					ttipo.setEnabled(false);
					tcolor.setEnabled(false);
					ttalla.setEnabled(false);
					porcodigo = true;
				}
			}
		});

		// Accion del boton Regresa
		regresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAlmacen();
				control.limpiarDatos("Eliminar");
				dispose();
			}
		});
	}

	// Metodo que limpia los TextFields
	public void limpiarDatosEliminarProducto() {
		tmodelo.setText("");
		ttipo.setText("");
		tcolor.setText("");
		ttalla.setText("");
		tcodigo.setText("");
		modelo.setEnabled(true);
		tipo.setEnabled(true);
		color.setEnabled(true);
		talla.setEnabled(true);
		tmodelo.setEnabled(true);
		ttipo.setEnabled(true);
		tcolor.setEnabled(true);
		ttalla.setEnabled(true);
		codigo.setEnabled(true);
		tcodigo.setEnabled(true);
		pormodelo = false;
		porcodigo = false;
	}

	// Obtenemos la Instancia del Control Almacen
	public void setControl(ControlAlmacen controlalmacen) {
		this.control = controlalmacen;
	}

	// Método para Dibujar una Linea como Separador
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(20, 360, 460, 360);
	}
}
