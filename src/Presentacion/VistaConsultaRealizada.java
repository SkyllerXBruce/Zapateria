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

import Modelo.Componentes;
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
		Componentes componente =new Componentes();

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
		getnombre = componente.creaEtiqueta("", 200, 120, 360, 25, 16);
		getcurp = componente.creaEtiqueta("", 200, 160, 360, 25, 16);
		getdireccion = componente.creaEtiqueta("", 200, 200, 360, 25, 16);
		gettelefono = componente.creaEtiqueta("", 200, 240, 360, 25, 16);
		getuser = componente.creaEtiqueta("", 200, 280, 360, 25, 16);
		getid = componente.creaEtiqueta("", 200, 320, 360, 25, 16);
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
