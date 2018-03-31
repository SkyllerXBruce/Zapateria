package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Ticket;

public class DAOTicket {

	// Metodo para Agregar un Ticket a la Base de Datos
	public boolean agregaTicket(Ticket ticket) {
		try {
			Statement statement = ManejadorBD.dameConnection().createStatement();
			statement.execute("INSERT INTO Tickets VALUES(" + ticket.getFolio() + ", '" + ticket.getFecha() + "', '"
					+ ticket.getIdvendedor() + "', " + ticket.getCodigoproducto() + ", " + ticket.getIva() + ", "
					+ ticket.getTotal() + ", " + ticket.getVendidos() + ")");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Metodo para Modificar un Ticket Existente a la Base de Datos
	public boolean modificaTicket(int folio, String fecha, String idvendedor, int codigo, double total, double iva) {
		int resultado = 0;
		try {
			Statement statement = ManejadorBD.dameConnection().createStatement();
			resultado = statement.executeUpdate("DELETE FROM Tickets WHERE Folio=" + folio + "");
			if (resultado != 0) {
				statement.execute("INSERT INTO Tickets VALUES(" + folio + ", '" + fecha + "', '" + idvendedor + "', "
						+ codigo + ", " + iva + ", " + total + ", " + 1 + ")");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Permite realizar una búsqueda de ticket especifico en la base de datos.
	public Ticket buscaTicket(int folio) {
		Ticket ticket = null;
		try {
			Statement statement = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Tickets WHERE Folio=" + folio + "");
			if (rs.next())
				ticket = new Ticket(rs.getInt("Folio"), rs.getString("Fecha"), rs.getString("IDVendedor"),
						rs.getInt("CodigoProducto"), rs.getDouble("Iva"), rs.getDouble("Total"), rs.getInt("Vendidos"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticket;
	}

	// Metodo para Obtener todos los Tickets
	public Ticket[] dameTickets() {
		ArrayList<Ticket> ticketsTemp = new ArrayList<Ticket>();
		try {
			Statement statement = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Tickets");
			while (rs.next()) {
				Ticket ticket = new Ticket(rs.getInt("Folio"), rs.getString("Fecha"), rs.getString("IDVendedor"),
						rs.getInt("CodigoProducto"), rs.getDouble("Iva"), rs.getDouble("Total"), rs.getInt("Vendidos"));
				ticketsTemp.add(ticket);
			}
			Ticket ticketsTempArreglo[] = new Ticket[ticketsTemp.size()];
			ticketsTemp.toArray(ticketsTempArreglo);
			return ticketsTempArreglo;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Metodo que Obtiene el Total de Tickets en la Base de Datos
	public int cantidadTickets() {
		try {
			Statement statement = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Tickets");
			if (rs.next())
				return rs.getInt(1); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
