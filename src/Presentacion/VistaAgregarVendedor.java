package Presentacion;

import java.awt.EventQueue;
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
import javax.swing.border.EmptyBorder;

import Modelo.Componentes;
import Negocio.ControlVendedores;

@SuppressWarnings("serial")
public class VistaAgregarVendedor extends JFrame {

	// Variables Globales
	private JButton ingresar, regresar;
	private JTextField tnombre, tapaterno, tamaterno, tcurp, tcorreo, ttelefono;
	private ControlVendedores control;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAgregarVendedor frame = new VistaAgregarVendedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaAgregarVendedor
	public VistaAgregarVendedor() {
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
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente Desea Cancelar el Agregar un Vendedor?",
						"¿Cancelar Agregar Vendedor?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.muestraVistaAdministarVendedores();
					control.limpiarDatos("AgregarVendedor");
					dispose();
				}
			}
		});
	}

	// Creamos y Agregamos los Componetes de la Ventana
	private void iniciarComponentes() {
		// Creamos la Instancia del JPanel Así como de Algunos Componentes
		JPanel panel = new JPanel();
		Componentes componente = new Componentes();
		JLabel titulo, nombre, apaterno, amaterno, curp, correo, telefono;

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
		ingresar = componente.creaBoton("Ingresar", 260, 380, 150, 30);
		regresar = componente.creaBoton("", 40, 360, 50, 50);
		regresar.setIcon(imgIcon);

		// Creamos y Agregamos las Propiedades del JLabel
		titulo = componente.creaEtiqueta("Agregar Vendedor", 120, 40, 340, 35, 30);
		nombre = componente.creaEtiqueta("Nombre:", 40, 120, 140, 25, 16);
		apaterno = componente.creaEtiqueta("Apellido Paterno:", 40, 160, 140, 25, 16);
		amaterno = componente.creaEtiqueta("Apellido Materno:", 40, 200, 140, 25, 16);
		curp = componente.creaEtiqueta("Curp:", 40, 240, 140, 25, 16);
		correo = componente.creaEtiqueta("Correo:", 40, 280, 140, 25, 16);
		telefono = componente.creaEtiqueta("Telefono:", 40, 320, 140, 25, 16);

		// Creamos y Agregamos las Propiedades del JTextField
		tnombre = componente.creaCuadroTexto(190, 120, 270, 25, 14);
		tapaterno = componente.creaCuadroTexto(190, 160, 270, 25, 14);
		tamaterno = componente.creaCuadroTexto(190, 200, 270, 25, 14);
		tcurp = componente.creaCuadroTexto(190, 240, 270, 25, 14);
		tcorreo = componente.creaCuadroTexto(190, 280, 270, 25, 14);
		ttelefono = componente.creaCuadroTexto(190, 320, 270, 25, 14);

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
									JOptionPane.showMessageDialog(null, "Es Necesario Ingresar el Correo");
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

		// Accion del boton Regresa
		regresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAdministarVendedores();
				control.limpiarDatos("AgregarVendedor");
				dispose();
			}
		});
	}

	// Metodo que limpia los TextFields
	public void limpiarDatosAgregarVendedor() {
		tnombre.setText("");
		tapaterno.setText("");
		tamaterno.setText("");
		tcurp.setText("");
		tcorreo.setText("");
		ttelefono.setText("");
	}

	// Obtenemos la Instancia del Control Vendedor
	public void setControl(ControlVendedores controlvendedores) {
		this.control = controlvendedores;
	}

}
