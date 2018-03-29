package Negocio;

import javax.swing.JOptionPane;

import Modelo.Usuario;
import Presentacion.VistaAdministrador;
import Presentacion.VistaLogin;
import Presentacion.VistaVendedor;

public class ControlLogin {

	// declaramos los atributos de nuestro control.
	private VistaVendedor vistavendedor;
	private VistaAdministrador vistaadmin;
	private VistaLogin vistalogin;
	private ServicioLogin serviciologin;
	private Usuario usuario = new Usuario();

	// Agregamos una instancia de las vistas a nuesto control
	public void setVistaVendedor(VistaVendedor vistavendedor) {
		this.vistavendedor = vistavendedor;
	}
	
	public void setVistaAdministrador(VistaAdministrador vistaadmin) {
		this.vistaadmin=vistaadmin;	
	}
	
	public void setVistaLogin(VistaLogin vistalogin) {
		this.vistalogin=vistalogin;	
	}

	// Hacemos visible la ventana "VistaVendedor"
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

	// Este metodo nos permite saber si el usuario ingresado existe en nuestra base
	// de datos, pasandolo al servicio Login.
	public boolean validaIngreso() {
		String nl = System.getProperty("line.separator");
		Usuario user = serviciologin.dameUsuario(usuario.getUsuario(), usuario.getTipo());
		if (usuario.getTipo().equals("Vendedor")) {
			if (serviciologin.validaingreso(usuario)) {
				JOptionPane.showMessageDialog(null,"Ingresaste como: Vendedor" + nl + " Bienvenido " + user.getNombre());
				muestraVistaVendedor(user);
				return true;
			} else
				JOptionPane.showMessageDialog(null, "Datos incorrectos");
		} else if (usuario.getTipo().equals("Administrador")) {
			if (serviciologin.validaingreso(usuario)) {
				JOptionPane.showMessageDialog(null, "Ingresaste como: Administrador" + nl + "Bienvenido "+ user.getNombre());
				muestraVistaAdministrador(user);
				return true;
			} else
				JOptionPane.showMessageDialog(null, "Datos incorrectos");
		} else
			JOptionPane.showMessageDialog(null,
					"Usuario y/o contraseña incorrecta" + nl + "¿Seleccionó correctamente el campo cargo?");
		return false;
	}

	// Agregamos instancia de servicio login
	public void setServicioLogin(ServicioLogin serviciologin) {
		this.serviciologin = serviciologin;
	}

	// Validamos que hay algo escrito en el textfield de usuario para consultarlo al
	// DAO
	public void recibeUsuario(String user) {
		if (user.length() > 1)
			usuario.setUsuario(user);
	}

	// Validamos que tipo de usuario trata de ingresar al sistema
	public void recibeTipo(String tipo) {
		usuario.setTipo(tipo);
	}

	// Validamos que hay algo escrito en el textfield de contraseña para la
	// consulta.
	public void recibeContrase�a(String pass) {
		if (pass.length() > 1)
			usuario.setPass(pass);
	}
}
