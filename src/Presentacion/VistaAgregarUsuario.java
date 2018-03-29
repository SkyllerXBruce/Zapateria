package Presentacion;

import java.awt.EventQueue;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Modelo.Usuario;
import Negocio.ControlVendedores;

@SuppressWarnings("serial")
public class VistaAgregarUsuario extends JFrame {

	private JButton agregar, regresar;
	private JTextField tusuario;
	private JPasswordField tcontrasena;
	private String[] datosVendedor;
	private ControlVendedores control;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAgregarUsuario frame = new VistaAgregarUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaAgregarUsuario() {
		// Tamaño de la Ventana
		setSize(480, 320);
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
					control.limpiarDatos("AgregarUsuario");
					dispose();
				}
			}
		});
	}

	private void iniciarComponentes() {
		// creamos el panel y lo agregamos a la ventana
		JPanel panel = new JPanel(null);
		JLabel titulo1, titulo2, usuario, contrasena;
		setContentPane(panel);
		
		ImageIcon imgIcon = new ImageIcon(VistaAgregarUsuario.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		agregar = creaBoton("Agregar", 260, 240, 150, 30);
		regresar = creaBoton("", 40, 230, 50, 50);
		regresar.setIcon(imgIcon);
		agregar.setToolTipText("Agrega Usuario y Contraseña del Vendedor");
		regresar.setToolTipText("Regresa a la Ventana de Agregar Vendedor");
		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo1 = creaEtiqueta("Asignar Usuario y Cotraseña", 30, 40, 480, 35, 30);
		titulo2 = creaEtiqueta("al Vendedor", 130, 70, 480, 35, 30);
		usuario = creaEtiqueta("Usuario:", 40, 140, 140, 25, 16);
		contrasena = creaEtiqueta("Contraseña:", 40, 180, 140, 25, 16);
		usuario.setToolTipText("Ingrese un Usuario");
		contrasena.setToolTipText("Ingrese una Contraseña");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de los TextFields
		// Como de la Letra
		tusuario = creaCuadroTexto(190, 140, 270, 25, 14);
		tusuario.setToolTipText("Ingrese un Usuario");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de los PasswordField
		// Como de la Letra
		tcontrasena = new JPasswordField();
		tcontrasena.setBounds(190, 180, 270, 25);
		tcontrasena.setFont(new Font("Serif", Font.ITALIC, 14));
		tcontrasena.setToolTipText("Ingrese una Contraseña");
		tcontrasena.setText("");

		// Se Realiza Acciones de los Componentes
		accionesComponentes();
		// Agregamos los Componentes al Panel
		panel.add(titulo1);
		panel.add(titulo2);
		panel.add(usuario);
		panel.add(tusuario);
		panel.add(contrasena);
		panel.add(tcontrasena);
		panel.add(agregar);
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
		// Accion del boton Aceptar
		agregar.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent e) {
				String user, pass, id, nombre, curp, correo, telefono;
				user = tusuario.getText();
				pass = tcontrasena.getText();
				id = control.generaId();
				nombre = datosVendedor[0] + " " + datosVendedor[1] + " " + datosVendedor[2];
				curp = datosVendedor[3];
				correo = datosVendedor[4];
				telefono = datosVendedor[5];
				if (!user.isEmpty())
					if (!pass.isEmpty()) {
						if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de Agregar Vendedor " + nombre,
								"¿Agregar?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
							Usuario vendedor = new Usuario(id, user, pass, "Vendedor", nombre, curp, correo, telefono);
							if (control.agregaVendedor(vendedor)) {
								JOptionPane.showMessageDialog(null,
										"El Vendedor " + nombre + " Fue Agregado Correctamente");
								control.muestraVistaAdministarVendedores();
								control.limpiarDatos("AgregarVendedor");
								control.limpiarDatos("AgregarUsuario");
								dispose();
							}
						}
					} else
						JOptionPane.showMessageDialog(null, "Es Necesario Ingresar el Usuario");
				else
					JOptionPane.showMessageDialog(null, "Es Necesario Ingresar la Contraseña");
			}
		});

		// Accion de Boton Cancelar
		regresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAgregarVendedor();
				control.limpiarDatos("AgregarUsuario");
				dispose();
			}
		});
	}

	public void obtenerDatosVendedor(String[] datosvendedor) {
		this.datosVendedor = datosvendedor;
	}

	public void limpiarDatosAgregarUsuario() {
		tusuario.setText("");
		tcontrasena.setText("");
	}

	public void setControl(ControlVendedores controlvendedores) {
		this.control = controlvendedores;
	}
}
