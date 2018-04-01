package PruebasUnitarias;

import org.junit.jupiter.api.Test;

import Modelo.Usuario;
import Negocio.ControlVendedores;
import Negocio.ServicioVendedores;

class PruebasUnitariasVendedor {

	ControlVendedores control = new ControlVendedores();
	ServicioVendedores servicio = new ServicioVendedores();
	Usuario vendedor = new Usuario("ID", "User", "Pass", "Vendedor", "Nom", "Curp", "Correo", "Tel");
	
	@Test
	void testAgregarVendedor() {
		control.setServicioVendedores(servicio);
		assert (control.agregaVendedor(vendedor) == true);
	}

	@Test
	void testEliminarVendedor() {
		control.setServicioVendedores(servicio);
		assert (control.eliminarVendedor(vendedor) == true);
	}

	@Test
	void testExisteVendedor() {
		control.setServicioVendedores(servicio);
		assert (control.existeVendedor("ID-ZA-V-1") == true);
	}

	@Test
	void testBuscaVendedorPorNombre() {
		control.setServicioVendedores(servicio);
		assert (control.buscaVendedorPorNombre("Abraham Aviles Martinez") != null);
	}

	@Test
	void testBuscaVendedorPorId() {
		control.setServicioVendedores(servicio);
		assert (control.buscaVendedorPorId("ID-ZA-V-1") != null);
	}

	@Test
	void testgeneraIdVendedor() {
		control.setServicioVendedores(servicio);
		assert (control.generaId().contains("ID-ZA-V-") == true);
	}
}
