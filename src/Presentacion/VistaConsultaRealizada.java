package Presentacion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Componentes;
import Modelo.Usuario;
import Negocio.ControlVendedores;

@SuppressWarnings("serial")
public class VistaConsultaRealizada extends JFrame {

	// Variables Globales
	private JButton finaliza, nueva;
	private JLabel lnombre, luser, lcurp, ldireccion, ltelefono, lid;
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
		// Creamos la Instancia del JPanel Así como de Algunos Componentes
		JPanel panel = new JPanel();
		JLabel titulo, nombre, curp, direccion, telefono, user, id;
		Componentes componente = new Componentes();

		// Modificamos Propiedades de JPanel y lo Agregamos a la Ventana
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setContentPane(panel);

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		finaliza = componente.creaBoton("Finalizar Consulta", 360, 380, 160, 30);
		nueva = componente.creaBoton("Nueva Consulta", 80, 380, 150, 30);
		finaliza.setToolTipText("Termina la Consulta y Regresa a la Ventana de Administrar Vendedores");
		nueva.setToolTipText("Realiza una Nueva Consulta");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo = componente.creaEtiqueta("Vendedor", 220, 40, 340, 35, 30);
		nombre = componente.creaEtiqueta("Nombre Completo:", 40, 120, 150, 25, 16);
		curp = componente.creaEtiqueta("Curp:", 40, 160, 150, 25, 16);
		direccion = componente.creaEtiqueta("Direccion:", 40, 200, 150, 25, 16);
		telefono = componente.creaEtiqueta("Telefono:", 40, 240, 150, 25, 16);
		user = componente.creaEtiqueta("Usuario:", 40, 280, 150, 25, 16);
		id = componente.creaEtiqueta("ID:", 40, 320, 150, 25, 16);
		lnombre = componente.creaEtiqueta("", 200, 120, 360, 25, 16);
		lcurp = componente.creaEtiqueta("", 200, 160, 360, 25, 16);
		ldireccion = componente.creaEtiqueta("", 200, 200, 360, 25, 16);
		ltelefono = componente.creaEtiqueta("", 200, 240, 360, 25, 16);
		luser = componente.creaEtiqueta("", 200, 280, 360, 25, 16);
		lid = componente.creaEtiqueta("", 200, 320, 360, 25, 16);
		lnombre.setToolTipText("Nombre Completo del Vendedor");
		lcurp.setToolTipText("Curp del Vendedor");
		ldireccion.setToolTipText("Direccion del Vendedor");
		ltelefono.setToolTipText("Telefono del Vendedor");
		luser.setToolTipText("Usuario del Vendedor para Ingresar al Sistema");
		lid.setToolTipText("ID del Vendedor para Identificar en el Sistema");

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
		panel.add(lnombre);
		panel.add(lcurp);
		panel.add(ldireccion);
		panel.add(ltelefono);
		panel.add(luser);
		panel.add(lid);
		panel.add(finaliza);
		panel.add(nueva);

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
		lnombre.setText(vendedor.getNombre());
		lcurp.setText(vendedor.getCurp());
		ldireccion.setText(vendedor.getCorreo());
		ltelefono.setText(vendedor.getTelefono());
		lid.setText(vendedor.getId());
		luser.setText(vendedor.getUsuario());
	}

	public void limpiarDatosConsultaVendedor() {
		lnombre.setText("");
		luser.setText("");
		lcurp.setText("");
		ldireccion.setText("");
		ltelefono.setText("");
		lid.setText("");
	}

	public void setControl(ControlVendedores controlvendedores) {
		this.control = controlvendedores;
	}

}
