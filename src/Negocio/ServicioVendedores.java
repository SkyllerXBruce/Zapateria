package Negocio;

import Modelo.Usuario;
import Persistencia.DAOLogin;

public class ServicioVendedores {

	// Instancia de la Base de Datos
	private DAOLogin dao = new DAOLogin();

	// Metodos para Verficar la existencia del Vendedor si Existe manda true otro
	// caso false
	public boolean existeVendedor(String dato) {
		if (dao.buscarUsuario(dato) != null)
			return true;
		else if (dao.buscarVendedor(dato) != null)
			return true;
		return false;
	}

	// Metodos para la Busqueda de un Vendedor, si lo Encuentra Regresa el Producto
	// en Otro Caso null
	public Usuario buscaVendedorPorNombre(String nombre) {
		return dao.buscarUsuario(nombre);
	}

	public Usuario buscaVendedorPorId(String id) {
		return dao.buscarVendedor(id);
	}

	// Metodo para Agregar el Vendedor a la Base de Datos
	public boolean agregaVendedor(Usuario vendedor) {
		return dao.agregaVendedor(vendedor);
	}

	// Metodo para Quitar el Vendedor de la Base de Datos
	public boolean eliminarVendedor(Usuario vendedor) {
		return dao.quitaVendedor(vendedor);
	}

	// Metodo para Obtener Todos los Vendedores Existentes en la Base de Datos
	public Usuario[] dameVendedores() {
		Usuario vendedores[] = dao.dameVendedores();
		if (vendedores != null)
			return vendedores;
		return null;
	}

	// Metodo para Obtener la Cantidad de Vendedores que Existen en la Base de Datos
	public int cuantosVendedores() {
		int cantidadvendedores = dao.cuantosVendedores();
		System.out.println("cantidad: " + cantidadvendedores);
		if (cantidadvendedores != 0)
			return cantidadvendedores;
		return 0;
	}
}
