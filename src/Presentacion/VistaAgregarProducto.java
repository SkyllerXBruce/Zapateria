package Presentacion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import Negocio.ControlAlmacen;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class VistaAgregarProducto extends JFrame {
	
	private JButton agregar, regresa;
	private JTextField tmodelo, ttipo, tcolor, tcosto, ttalla, tcantidad;
	private ControlAlmacen control;
	
	/**
	 * Launch the application.
	 */
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

	public VistaAgregarProducto() {
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
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente Desea Cancelar el Agregar un Producto?",
						"¿Cancelar Agregar Producto?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.muestraVistaAlmacen();
					control.limpiarDatos("Agregar");
					dispose();
				}
			}
		});
	}

	private void iniciarComponentes() {
		// creamos el panel y lo agregamos a la ventana
		JPanel panel = new JPanel(null);
		setContentPane(panel);
		JLabel titulo, modelo, tipo, color, costo, talla, cantidad;
		
		ImageIcon imgIcon = new ImageIcon(VistaAgregarProducto.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		agregar = creaBoton("Agregar", 260, 380, 150, 30);
		regresa = creaBoton("", 40, 370, 50, 50);
		regresa.setIcon(imgIcon);
		agregar.setToolTipText("Agrega los Datos del Producto");
		regresa.setToolTipText("Cancela la Operacion y Regresa a la Ventana de Administrar Almacen");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo = creaEtiqueta("Agregar Producto", 120, 40, 340, 35, 30);
		modelo = creaEtiqueta("Modelo:", 40, 120, 140, 25, 16);
		tipo = creaEtiqueta("Tipo:", 40, 160, 140, 25, 16);
		color = creaEtiqueta("Color:", 40, 200, 140, 25, 16);
		costo = creaEtiqueta("Costo:", 40, 240, 140, 25, 16);
		talla = creaEtiqueta("Talla:", 40, 280, 140, 25, 16);
		cantidad = creaEtiqueta("Cantidad:", 40, 320, 140, 25, 16);
		modelo.setToolTipText("Ingrese el Modelo del Producto");
		tipo.setToolTipText("Ingrese de que Tipo es el Producto");
		color.setToolTipText("Ingrese el Color del Producto");
		costo.setToolTipText("Ingrese el Costo Unitario del Producto");
		talla.setToolTipText("Ingrese la Talla del Producto");
		cantidad.setToolTipText("Ingrese la Cantidad de Productos con las Mismas Caracteristicas");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de los TextFields
		// Como de la Letra
		tmodelo = creaCuadroTexto(190, 120, 270, 25, 14);
		ttipo = creaCuadroTexto(190, 160, 270, 25, 14);
		tcolor = creaCuadroTexto(190, 200, 270, 25, 14);
		tcosto = creaCuadroTexto(190, 240, 270, 25, 14);
		ttalla = creaCuadroTexto(190, 280, 270, 25, 14);
		tcantidad = creaCuadroTexto(190, 320, 270, 25, 14);
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
		agregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] datosvendedor = { tmodelo.getText(), ttipo.getText(), tcolor.getText(), tcosto.getText(),
						ttalla.getText(), tcantidad.getText() };
				if (!datosvendedor[0].isEmpty())
					if (!datosvendedor[1].isEmpty())
						if (!datosvendedor[2].isEmpty())
							if (!datosvendedor[3].isEmpty())
								if (!datosvendedor[4].isEmpty())
									if (!datosvendedor[5].isEmpty()) {
										if (JOptionPane.showConfirmDialog(rootPane,
												"¿Esta Seguro de Haber Ingresado los Datos Correctamente?",
												"Datos del Producto",
												JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
											System.out.println("click en Agregar Producto");
											control.muestraVistaAlmacen();
											dispose();
										}
									} else
										JOptionPane.showMessageDialog(null, "Es Necesario Ingresar la Cantidad");
								else
									JOptionPane.showMessageDialog(null, "Es Necesario Ingresar la Talla");
							else
								JOptionPane.showMessageDialog(null, "Es Necesario Ingresar el Costo Unitario");
						else
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

	public void limpiarDatosAgregarProducto() {
		tmodelo.setText("");
		ttipo.setText("");
		tcolor.setText("");
		tcosto.setText("");
		ttalla.setText("");
		tcantidad.setText("");
	}

	public void setControl(ControlAlmacen controlalmacen) {
		this.control = controlalmacen;
	}
}
