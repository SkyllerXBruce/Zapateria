package Negocio;

import Modelo.Usuario;
import Persistencia.DAOLogin;

public class ServicioLogin {

	// Instancia de la Base de Datos
	DAOLogin dao = new DAOLogin();

	// Este método realiza la consulta al DAO, y lo retorna como falso o verdadero.
	// si es falso el login falla
	public boolean validaingreso(Usuario usuario) {
		if (dao.valida(usuario)) {
			return true;
		} else {
			return false;
		}
	}

	// Metodos para la Obtener un Usuario, si lo Encuentra Regresa el Usuario
	// en Otro Caso null
	public Usuario dameUsuario(String usuario, String tipo) {
		return dao.buscarUsuario(usuario, tipo);
	}

	public Usuario dameVendedor(String nombre, String tipo) {
		Usuario user = dao.buscarUsuario(nombre);
		if (user.getTipo().equals(tipo))
			return user;
		return null;
	}
}
