package Presentacion;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import Negocio.ControlAlmacen;
import javax.swing.JTextField;

import Modelo.Componentes;
import Modelo.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class VistaAgregarProducto extends JFrame {

	// Variables Globales
	private JButton agregar, regresa;
	private JTextField tmodelo, ttipo, tcolor, tcosto, ttalla, tcantidad;
	private ControlAlmacen control;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAgregarProducto frame = new VistaAgregarProducto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaAgregarProducto
	public VistaAgregarProducto() {
		// Propiedades de la Ventana
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
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente Desea Cancelar el Agregar un Producto?",
						"¿Cancelar Agregar Producto?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.muestraVistaAlmacen();
					control.limpiarDatos("Agregar");
					dispose();
				}
			}
		});
	}

	// Creamos y Agregamos los Componetes de la Ventana
	private void iniciarComponentes() {
		// creamos el panel y lo agregamos a la ventana
		JPanel panel = new JPanel(null);
		JLabel titulo, modelo, tipo, color, costo, talla, cantidad;
		Componentes componente = new Componentes();

		setContentPane(panel);

		// Imagen del Boton regresar
		ImageIcon imgIcon = new ImageIcon(VistaAgregarProducto.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		agregar = componente.creaBoton("Agregar", 260, 380, 150, 30);
		regresa = componente.creaBoton("", 40, 370, 50, 50);
		regresa.setIcon(imgIcon);
		agregar.setToolTipText("Agrega los Datos del Producto");
		regresa.setToolTipText("Cancela la Operacion y Regresa a la Ventana de Administrar Almacen");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo = componente.creaEtiqueta("Agregar Producto", 120, 40, 340, 35, 30);
		modelo = componente.creaEtiqueta("Modelo:", 40, 120, 140, 25, 16);
		tipo = componente.creaEtiqueta("Tipo:", 40, 160, 140, 25, 16);
		color = componente.creaEtiqueta("Color:", 40, 200, 140, 25, 16);
		costo = componente.creaEtiqueta("Costo:", 40, 240, 140, 25, 16);
		talla = componente.creaEtiqueta("Talla:", 40, 280, 140, 25, 16);
		cantidad = componente.creaEtiqueta("Cantidad:", 40, 320, 140, 25, 16);
		modelo.setToolTipText("Ingrese el Modelo del Producto");
		tipo.setToolTipText("Ingrese de que Tipo es el Producto");
		color.setToolTipText("Ingrese el Color del Producto");
		costo.setToolTipText("Ingrese el Costo Unitario del Producto");
		talla.setToolTipText("Ingrese la Talla del Producto");
		cantidad.setToolTipText("Ingrese la Cantidad de Productos con las Mismas Caracteristicas");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de los TextFields
		// Como de la Letra
		tmodelo = componente.creaCuadroTexto(190, 120, 270, 25, 14);
		ttipo = componente.creaCuadroTexto(190, 160, 270, 25, 14);
		tcolor = componente.creaCuadroTexto(190, 200, 270, 25, 14);
		tcosto = componente.creaCuadroTexto(190, 240, 270, 25, 14);
		ttalla = componente.creaCuadroTexto(190, 280, 270, 25, 14);
		tcantidad = componente.creaCuadroTexto(190, 320, 270, 25, 14);
		tmodelo.setToolTipText("Ingrese el Modelo del Producto");
		ttipo.setToolTipText("Ingrese de que Tipo es el Producto");
		tcolor.setToolTipText("Ingrese el Color del Producto");
		tcosto.setToolTipText("Ingrese el Costo Unitario del Producto");
		ttalla.setToolTipText("Ingrese la Talla del Producto");
		tcantidad.setToolTipText("Ingrese la Cantidad de Productos con las Mismas Caracteristicas");

		// Se Realiza Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(modelo);
		panel.add(tipo);
		panel.add(color);
		panel.add(costo);
		panel.add(talla);
		panel.add(cantidad);
		panel.add(tmodelo);
		panel.add(ttipo);
		panel.add(tcolor);
		panel.add(tcosto);
		panel.add(ttalla);
		panel.add(tcantidad);
		panel.add(agregar);
		panel.add(regresa);
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Accion del boton vendedores
		agregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String datomodelo, datotipo, datocolor;
				double datocosto = 0, datotalla = 0;
				int productocodigo = control.generaCodigo(), datocantidad = 0;
				datomodelo = tmodelo.getText();
				datotipo = ttipo.getText();
				datocolor = tcolor.getText();
				if (!datomodelo.isEmpty())
					if (!datotipo.isEmpty())
						if (!datocolor.isEmpty()) {
							if (control.esNumeroReal(tcosto.getText()))
								datocosto = Double.valueOf(tcosto.getText());
							if (datocosto != 0) {
								if (control.esNumeroReal(ttalla.getText()))
									datotalla = Double.valueOf(ttalla.getText());
								if (datotalla != 0) {
									if (control.esNumeroReal(tcantidad.getText()))
										datocantidad = Integer.valueOf(tcantidad.getText());
									if (datocantidad != 0) {
										if (JOptionPane.showConfirmDialog(rootPane,
												"¿Esta Seguro de Haber Ingresado los Datos Correctamente?",
												"Datos del Producto",
												JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
											if (control.existeProducto(datomodelo, datotipo, datocolor, datotalla)) {
												Producto nuevo = control.buscaProducto(datomodelo, datotipo, datocolor,
														datotalla);
												if (control.eliminarProducto(nuevo)) {
													nuevo.setCosto(datocosto);
													nuevo.setCantidad(datocantidad);
													if (control.agregarProducto(nuevo))
														JOptionPane.showMessageDialog(null,
																"El Producto fue Agregado Correctamente");
													else
														JOptionPane.showMessageDialog(null,
																"Error No se Pudo Agregar El Producto");
												}
											} else {
												Producto producto = new Producto(productocodigo, datomodelo, datotipo,
														datocolor, datocosto, datotalla, datocantidad);
												if (control.agregarProducto(producto))
													JOptionPane.showMessageDialog(null,
															"El Producto fue Agregado Correctamente");
												else
													JOptionPane.showMessageDialog(null,
															"Error No se Pudo Agregar El Producto");
											}
											control.muestraVistaAlmacen();
											control.limpiarDatos("Agregar");
											dispose();
										}
									} else
										JOptionPane.showMessageDialog(null,
												"Es Necesario Ingresar una Cantidad Valida");
								} else
									JOptionPane.showMessageDialog(null, "Es Necesario Ingresar una Talla Valida");
							} else
								JOptionPane.showMessageDialog(null, "Es Necesario Ingresar un Costo Unitario Valido");
						} else
							JOptionPane.showMessageDialog(null, "Es Necesario Ingresar el Color");
					else
						JOptionPane.showMessageDialog(null, "Es Necesario Ingresar el Tipo");
				else
					JOptionPane.showMessageDialog(null, "Es Necesario Ingresar el Modelo");
			}
		});

		// Accion del boton Comiciones
		regresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAlmacen();
				control.limpiarDatos("Agregar");
				dispose();
			}
		});
	}

	// Metodo que limpia los TextFields
	public void limpiarDatosAgregarProducto() {
		tmodelo.setText("");
		ttipo.setText("");
		tcolor.setText("");
		tcosto.setText("");
		ttalla.setText("");
		tcantidad.setText("");
	}

	// Obtenemos la Instancia del Control Almacen
	public void setControl(ControlAlmacen controlalmacen) {
		this.control = controlalmacen;
	}
}
