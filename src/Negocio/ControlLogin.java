package Negocio;

import javax.swing.JOptionPane;

import Modelo.Usuario;
import Presentacion.VistaAdministrador;
import Presentacion.VistaLogin;
import Presentacion.VistaVendedor;

public class ControlLogin {

	// Variables Globales
	private VistaVendedor vistavendedor;
	private VistaAdministrador vistaadmin;
	private VistaLogin vistalogin;
	private ServicioLogin serviciologin;
	private Usuario usuario = new Usuario();

	// En estos Métodos se Agrega las Instancias del Servicio y las Vistas al
	// Control Login
	public void setVistaVendedor(VistaVendedor vistavendedor) {
		this.vistavendedor = vistavendedor;
	}

	public void setVistaAdministrador(VistaAdministrador vistaadmin) {
		this.vistaadmin = vistaadmin;
	}

	public void setVistaLogin(VistaLogin vistalogin) {
		this.vistalogin = vistalogin;
	}

	public void setServicioLogin(ServicioLogin serviciologin) {
		this.serviciologin = serviciologin;
	}

	// Metodos para Mostrar las Vistas Correspondientes
	public void muestraVistaLogin() {
		this.vistalogin.setVisible(true);
	}

	public void muestraVistaVendedor(Usuario user) {
		vistavendedor.actualizarDatos(user);
		vistavendedor.setVisible(true);
	}

	public void muestraVistaAdministrador(Usuario user) {
		vistaadmin.actualizarDatos(user);
		vistaadmin.setVisible(true);
	}

	// Este Metodo nos Permite Validar si el Usuario Ingresado Existe en la Base de
	// Datos
	public boolean validaIngreso() {
		String nl = System.getProperty("line.separator");
		Usuario user = serviciologin.dameUsuario(usuario.getUsuario(), usuario.getTipo());
		if (usuario.getTipo().equals("Vendedor")) {
			if (serviciologin.validaingreso(usuario)) {
				JOptionPane.showMessageDialog(null,
						"Ingresaste como: Vendedor" + nl + " Bienvenido " + user.getNombre());
				muestraVistaVendedor(user);
				return true;
			} else
				JOptionPane.showMessageDialog(null, "Datos incorrectos");
		} else if (usuario.getTipo().equals("Administrador")) {
			if (serviciologin.validaingreso(usuario)) {
				JOptionPane.showMessageDialog(null,
						"Ingresaste como: Administrador" + nl + "Bienvenido " + user.getNombre());
				muestraVistaAdministrador(user);
				return true;
			} else
				JOptionPane.showMessageDialog(null, "Datos incorrectos");
		} else
			JOptionPane.showMessageDialog(null,
					"Usuario y/o contraseña incorrecta" + nl + "¿Seleccionó correctamente el campo cargo?");
		return false;
	}

	// Estos Métodos Obtienen los Datos Ingresados del la Vista Login
	public void recibeUsuario(String user) {
		if (user.length() > 1)
			usuario.setUsuario(user);
	}

	public void recibeTipo(String tipo) {
		usuario.setTipo(tipo);
	}

	public void recibeContraseña(String pass) {
		if (pass.length() > 1)
			usuario.setPass(pass);
	}
}
