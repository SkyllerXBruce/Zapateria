package Negocio;

import Persistencia.DAOAlmacen;
import Modelo.Producto;

public class ServicioAlmacen {

	// Instancia de la Base de Datos
	private DAOAlmacen dao = new DAOAlmacen();

	// Metodos para Verficar la existencia de Productos si Existe manda true otro
	// caso false

	// Metodos para la Busqueda de un Producto, si lo Encuentra Regresa el Producto
	// en Otro Caso null
	public Producto buscaProducto(int codigo) {
		return dao.buscaProducto(codigo);
	}

	public Producto buscaProducto(String modelo, String tipo, String color, double talla) {
		return dao.buscaProducto(modelo, tipo, color, talla);
	}

	// Metodo para Agregar el Producto a la Base de Datos
	public boolean agregarProducto(Producto producto) {
		return dao.agregaProducto(producto);
	}

	// Metodo para Quitar el Producto de la Base de Datos
	public boolean eliminarProducto(Producto producto) {
		return dao.quitaProducto(producto);
	}

	// Metodo para Consultar los Datos del Producto en la Base de Datos


	// Metodo para Obtener todos los Productos
	public Producto[] dameProductos() {
		return dao.dameProductos();
	}

	// Metodo que Obtiene el Total de Productos en la Base de Datos
	public int cantidadProductos() {
		if (dao.cuantosProductos() != 0)
			return dao.cuantosProductos();
		return 0;
	}
}
