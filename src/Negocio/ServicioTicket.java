package Negocio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Modelo.Ticket;
import Modelo.Usuario;
import Persistencia.DAOTicket;

public class ServicioTicket {

	// Instancia de la Base de Datos
	private DAOTicket daoticket = new DAOTicket();

	// Metodo para Agregar el Ticket a la Base de Datos
	public boolean agregaTicket(Ticket ticket) {
		return daoticket.agregaTicket(ticket);
	}

	// Metodo para Obtener un Ticket dependiendo del Folio, si lo Encuentra Regresa
	// el Ticket en Otro Caso null
	public Ticket dameTicket(int folio) {
		return daoticket.buscaTicket(folio);
	}

	// Metodo para Generar el Folio del Ticket
	public int generaFolio() {
		return daoticket.cantidadTickets() + 1;
	}

	// Metodo para Obtener la Cantidad de Tickets Existentes 
	public int dameCantidadTickets() {
		return daoticket.cantidadTickets();
	}

	// Metodo que Obtiene la Comicion Actual del Vendedor
	public double obtenerComicionVendedor(Usuario user) {
		double comicion = 0;
		for (Ticket t : daoticket.dameTickets())
			if (user.getId().equals(t.getIdvendedor()))
				comicion += t.getTotal() * 0.05;
		return comicion;
	}

	// Metodo que Obtiene la Cantidad de Ventas Realizadas por Vendedor
	public int obtenerCantidadVentasVendedor(Usuario user) {
		int vendidos = 0;
		for (Ticket t : daoticket.dameTickets())
			if (user.getId().equals(t.getIdvendedor()))
				vendidos += t.getVendidos();
		return vendidos;
	}

	// Metodo que Obtiene la Cantidad de Ganancias Realizadas por Vendedor
	public double obtenerGananciasVendedor(Usuario user) {
		double ganancias = 0;
		for (Ticket t : daoticket.dameTickets())
			if (user.getId().equals(t.getIdvendedor()))
				ganancias += t.getTotal();
		return ganancias;
	}

	// Metodo para Modificar las Cararcteristicas del ticket
	public boolean modificaTicket(int folio, String fecha, String idvendedor, int codigo, double total, double iva) {
		return daoticket.modificaTicket(folio, fecha, idvendedor, codigo, total, iva);
	}

	// Metodo para Obtener Todos los Tickets Existentes en la Base de Datos
	public Ticket[] obtenTodosTickets() {
		return daoticket.dameTickets();
	}

	// Método para Obtener la Fecha Actual con Día y Hora
	public String getFechaActual() {
		Date ahora = new Date();
		DateFormat formatoHora = new SimpleDateFormat("HH:mm");
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		return formateador.format(ahora) + ", " + formatoHora.format(ahora);
	}
}
