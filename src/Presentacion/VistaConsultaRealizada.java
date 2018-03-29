package Presentacion;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Modelo.Usuario;
import Negocio.ControlVendedores;

@SuppressWarnings("serial")
public class VistaConsultaRealizada extends JFrame {

	private JButton finaliza, nueva;
	private JLabel getnombre, getuser, getcurp, getdireccion, gettelefono, getid;
	private ControlVendedores control;

	public VistaConsultaRealizada() {
		// Tamaño de la Ventana
		setSize(580, 460);
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
				if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de Finalizar Consulta?", "¿Finaliza Consulta?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
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
		JLabel titulo, nombre, curp, direccion, telefono, user, id;

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		finaliza = creaBoton("Finalizar Consulta", 360, 380, 160, 30);
		nueva = creaBoton("Nueva Consulta", 80, 380, 150, 30);
		finaliza.setToolTipText("Termina la Consulta y Regresa a la Ventana de Administrar Vendedores");
		nueva.setToolTipText("Realiza una Nueva Consulta");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo = creaEtiqueta("Vendedor", 220, 40, 340, 35, 30);
		nombre = creaEtiqueta("Nombre Completo:", 40, 120, 150, 25, 16);
		curp = creaEtiqueta("Curp:", 40, 160, 150, 25, 16);
		direccion = creaEtiqueta("Direccion:", 40, 200, 150, 25, 16);
		telefono = creaEtiqueta("Telefono:", 40, 240, 150, 25, 16);
		user = creaEtiqueta("Usuario:", 40, 280, 150, 25, 16);
		id = creaEtiqueta("ID:", 40, 320, 150, 25, 16);
		getnombre = creaEtiqueta("", 200, 120, 360, 25, 16);
		getcurp = creaEtiqueta("", 200, 160, 360, 25, 16);
		getdireccion = creaEtiqueta("", 200, 200, 360, 25, 16);
		gettelefono = creaEtiqueta("", 200, 240, 360, 25, 16);
		getuser = creaEtiqueta("", 200, 280, 360, 25, 16);
		getid = creaEtiqueta("", 200, 320, 360, 25, 16);
		getnombre.setToolTipText("Nombre Completo del Vendedor");
		getcurp.setToolTipText("Curp del Vendedor");
		getdireccion.setToolTipText("Direccion del Vendedor");
		gettelefono.setToolTipText("Telefono del Vendedor");
		getuser.setToolTipText("Usuario del Vendedor para Ingresar al Sistema");
		getid.setToolTipText("ID del Vendedor para Identificar en el Sistema");

		// Se Realiza Acciones de los Componentes
		accionesComponentes();
		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(nombre);
		panel.add(curp);
		panel.add(direccion);
		panel.add(telefono);
		panel.add(user);
		panel.add(id);
		panel.add(getnombre);
		panel.add(getcurp);
		panel.add(getdireccion);
		panel.add(gettelefono);
		panel.add(getuser);
		panel.add(getid);
		panel.add(finaliza);
		panel.add(nueva);

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

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Accion del boton vendedores
		finaliza.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAdministarVendedores();
				control.limpiarDatos("Consultar");
				dispose();
			}
		});

		// Accion del boton Comiciones
		nueva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaConsultarVendedor();
				control.limpiarDatos("Consultar");
				dispose();
			}
		});
	}

	public void obtenerDatosVendedor(Usuario vendedor) {
		getnombre.setText(vendedor.getNombre());
		getcurp.setText(vendedor.getCurp());
		getdireccion.setText(vendedor.getCorreo());
		gettelefono.setText(vendedor.getTelefono());
		getid.setText(vendedor.getId());
		getuser.setText(vendedor.getUsuario());
	}
	
	public void limpiarDatosConsultaVendedor() {
		getnombre.setText("");
		getuser.setText("");
		getcurp.setText("");
		getdireccion.setText("");
		gettelefono.setText("");
		getid.setText("");
	}

	public void setControl(ControlVendedores controlvendedores) {
		this.control = controlvendedores;
	}

}
