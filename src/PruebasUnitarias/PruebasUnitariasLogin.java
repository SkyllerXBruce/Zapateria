package PruebasUnitarias;

import org.junit.jupiter.api.Test;

import Modelo.Usuario;
import Negocio.ControlLogin;
import Negocio.ServicioLogin;
import Presentacion.VistaAdministrador;
import Presentacion.VistaVendedor;

class PruebasUnitariasLogin {

	VistaAdministrador vistaadmin = new VistaAdministrador();
	VistaVendedor vistavendedor = new VistaVendedor();
	ControlLogin control = new ControlLogin();
	ServicioLogin serviciologin = new ServicioLogin();

	@Test
	public void testLoginVendedor() {
		Usuario user = new Usuario("", "Abraham", "abraham", "Vendedor", "", "", "", "");
		control.setServicioLogin(serviciologin);
		control.setVistaVendedor(vistavendedor);
		control.recibeUsuario(user.getUsuario());
		control.recibeContraseña(user.getPass());
		control.recibeTipo(user.getTipo());
		assert (control.validaIngreso() == true);
	}

	@Test
	public void testLoginAdministrador() {
		Usuario user = new Usuario("", "Miguel", "mig", "Administrador", "", "", "", "");
		control.setServicioLogin(serviciologin);
		control.setVistaAdministrador(vistaadmin);
		control.recibeUsuario(user.getUsuario());
		control.recibeContraseña(user.getPass());
		control.recibeTipo(user.getTipo());
		assert (control.validaIngreso() == true);
	}

}
