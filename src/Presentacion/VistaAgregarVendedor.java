package Presentacion;

import java.awt.Font;
import java.awt.Image;
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

import Negocio.ControlVendedores;

@SuppressWarnings("serial")
public class VistaAgregarVendedor extends JFrame {

	private JButton ingresar, regresar;
	private JTextField tnombre, tapaterno, tamaterno, tcurp, tcorreo, ttelefono;
	private ControlVendedores control;

	public VistaAgregarVendedor() {
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
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente Desea Cancelar el Agregar un Vendedor?",
						"¿Cancelar Agregar Vendedor?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.muestraVistaAdministarVendedores();
					control.limpiarDatos("AgregarVendedor");
					dispose();
				}
			}
		});
	}

	private void iniciarComponentes() {
		// creamos el panel y lo agregamos a la ventana
		JPanel panel = new JPanel(null);
		setContentPane(panel);
		JLabel titulo, nombre, apaterno, amaterno, curp, correo, telefono;
		
		ImageIcon imgIcon = new ImageIcon(VistaAgregarVendedor.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		ingresar = creaBoton("Ingresar", 260, 380, 150, 30);
		regresar = creaBoton("Cancelar", 40, 380, 150, 30);
		ingresar.setToolTipText("Agrega los Datos del Vendedor");
		regresar.setToolTipText("Cancela la Operacion y Regresa a la Ventana de Administrar Vendedores");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo = creaEtiqueta("Agregar Vendedor", 120, 40, 340, 35, 30);
		nombre = creaEtiqueta("Nombre:", 40, 120, 140, 25, 16);
		apaterno = creaEtiqueta("Apellido Paterno:", 40, 160, 140, 25, 16);
		amaterno = creaEtiqueta("Apellido Materno:", 40, 200, 140, 25, 16);
		curp = creaEtiqueta("Curp:", 40, 240, 140, 25, 16);
		correo = creaEtiqueta("Correo:", 40, 280, 140, 25, 16);
		telefono = creaEtiqueta("Telefono:", 40, 320, 140, 25, 16);
		nombre.setToolTipText("Ingrese Nombre del Vendedor");
		apaterno.setToolTipText("Ingrese Apellido Paterno del Vendedor");
		apaterno.setToolTipText("Ingrese Apellido Materno del Vendedor");
		curp.setToolTipText("Ingrese Curp del Vendedor");
		correo.setToolTipText("Ingrese Correo del Vendedor");
		telefono.setToolTipText("Ingrese Telefono del Vendedor");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de los TextFields
		// Como de la Letra
		tnombre = creaCuadroTexto(190, 120, 270, 25, 14);
		tapaterno = creaCuadroTexto(190, 160, 270, 25, 14);
		tamaterno = creaCuadroTexto(190, 200, 270, 25, 14);
		tcurp = creaCuadroTexto(190, 240, 270, 25, 14);
		tcorreo = creaCuadroTexto(190, 280, 270, 25, 14);
		ttelefono = creaCuadroTexto(190, 320, 270, 25, 14);
		tnombre.setToolTipText("Ingrese Nombre del Vendedor");
		tapaterno.setToolTipText("Ingrese Apellido Paterno del Vendedor");
		tapaterno.setToolTipText("Ingrese Apellido Materno del Vendedor");
		tcurp.setToolTipText("Ingrese Curp del Vendedor");
		tcorreo.setToolTipText("Ingrese Correo del Vendedor");
		ttelefono.setToolTipText("Ingrese Telefono del Vendedor");

		// Se Realiza Acciones de los Componentes
		accionesComponentes();
		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(nombre);
		panel.add(apaterno);
		panel.add(amaterno);
		panel.add(curp);
		panel.add(correo);
		panel.add(telefono);
		panel.add(tnombre);
		panel.add(tapaterno);
		panel.add(tamaterno);
		panel.add(tcurp);
		panel.add(tcorreo);
		panel.add(ttelefono);
		panel.add(ingresar);
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
	private JLabel creaEtiqueta(String nombre, int posx, int posy, int ancho, int alto, int tama�o) {
		// Se Crea e Inicializa una Etiqueta de la Clase JLabel
		JLabel etiqueta = new JLabel(nombre);

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de la Etiqueta asi
		// Como la Letra del Texto
		etiqueta.setBounds(posx, posy, ancho, alto);
		etiqueta.setFont(new Font("Serif", Font.ITALIC, tama�o));
		return etiqueta;
	}

	// Método Para Crear las Propiedades de los Cuadros de Texto
	private JTextField creaCuadroTexto(int posx, int posy, int ancho, int alto, int tama�o) {
		// Se Crea e Inicializa el Cuadro de Texto de la Clase JTextField
		JTextField texto = new JTextField();

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto del Cuadro de Texto
		// asi como la Letra del Texto y se Asegura que Este Vacio el Cuadro de Texto
		texto.setBounds(posx, posy, ancho, alto);
		texto.setFont(new Font("Serif", Font.ITALIC, tama�o));
		texto.setText("");
		return texto;
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Accion del boton vendedores
		ingresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] datosvendedor = { tnombre.getText(), tapaterno.getText(), tamaterno.getText(), tcurp.getText(),
						tcorreo.getText(), ttelefono.getText() };
				if (!datosvendedor[0].isEmpty())
					if (!datosvendedor[1].isEmpty())
						if (!datosvendedor[2].isEmpty())
							if (!datosvendedor[3].isEmpty())
								if (!datosvendedor[4].isEmpty())
									if (!datosvendedor[5].isEmpty()) {
										if (JOptionPane.showConfirmDialog(rootPane,
												"¿Esta Seguro de Haber Ingresado los Datos Correctamente?",
												"Datos del Vendedor",
												JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
											control.obtenerDatosVendedor(datosvendedor);
											control.muestraVistaAgregarUsuario();
											dispose();
										}
									} else
										JOptionPane.showMessageDialog(null, "Es Necesario Ingresar el Telefono");
								else
									JOptionPane.showMessageDialog(null, "Es Necesario Ingresar el Dirección");
							else
								JOptionPane.showMessageDialog(null, "Es Necesario Ingresar el Curp");
						else
							JOptionPane.showMessageDialog(null, "Es Necesario Ingresar el Apellido Materno");
					else
						JOptionPane.showMessageDialog(null, "Es Necesario Ingresar el Apellido Paterno");
				else
					JOptionPane.showMessageDialog(null, "Es Necesario Ingresar el Nombre");
			}
		});

		// Accion del boton Comiciones
		regresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAdministarVendedores();
				control.limpiarDatos("AgregarVendedor");
				dispose();
			}
		});
	}

	public void limpiarDatosAgregarVendedor() {
		tnombre.setText("");
		tapaterno.setText("");
		tamaterno.setText("");
		tcurp.setText("");
		tcorreo.setText("");
		ttelefono.setText("");
	}

	public void setControl(ControlVendedores controlvendedores) {
		this.control = controlvendedores;
	}

}
