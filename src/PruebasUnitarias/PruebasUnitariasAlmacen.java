package PruebasUnitarias;

import org.junit.jupiter.api.Test;

import Modelo.Producto;
import Negocio.ControlAlmacen;
import Negocio.ServicioAlmacen;

class PruebasUnitariasAlmacen {

	ControlAlmacen control = new ControlAlmacen();
	ServicioAlmacen servicio = new ServicioAlmacen();
	Producto producto = new Producto(16, "Modelo", "Tipo", "color", 32.0, 64.0, 128);

	@Test
	void testAgregarProducto() {
		control.setServicioAlmacen(servicio);
		assert (control.agregarProducto(producto) == true);
		control.eliminarProducto(producto);
	}

	@Test
	void testEliminarProducto() {
		control.setServicioAlmacen(servicio);
		control.agregarProducto(producto);
		assert (control.eliminarProducto(producto) == true);
	}

	@Test
	void testExisteProductoPorDatosProducto() {
		control.setServicioAlmacen(servicio);
		assert (control.existeProducto("Pumas Limited", "Calzado Deportivo", "Dorado", 28.5) == true);
	}

	@Test
	void testExisteProductoPorCodigo() {
		control.setServicioAlmacen(servicio);
		assert (control.existeProducto(4) == true);
	}

	@Test
	void testBuscaProductoPorDatosProducto() {
		control.setServicioAlmacen(servicio);
		assert (control.existeProducto("Pumas Limited", "Calzado Deportivo", "Dorado", 28.5) == true);
	}

	@Test
	void testBuscaProductoPorCodigo() {
		control.setServicioAlmacen(servicio);
		assert (control.existeProducto(4) == true);
	}

	@Test
	void testGeneraCodigo() {
		control.setServicioAlmacen(servicio);
		assert (control.generaCodigo() > 0);
	}

	@Test
	void testEsNumeroReal() {
		control.setServicioAlmacen(servicio);
		assert (control.esNumeroReal("256.2") == true);
	}
}
