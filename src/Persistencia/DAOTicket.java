package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Ticket;

public class DAOTicket {

	public boolean agregaTicket(Ticket ticket) {
		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();
			// Envia instruccion SQL
			statement.execute("INSERT INTO Tickets VALUES(" + ticket.getFolio() + ", '" + ticket.getFecha() + "', '"
					+ ticket.getIdvendedor() + "', " + ticket.getCodigoproducto() + ", " + ticket.getIva() + ", "
					+ ticket.getTotal() + ", " + ticket.getVendidos() + ")");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean modificaTicket(int folio, String fecha, String idvendedor, int codigo, double total, double iva) {
		int resultado = 0;
		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();
			// Recibe los resutados
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
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();
			// Recibe los resutados
			ResultSet rs = statement.executeQuery("SELECT * FROM Tickets WHERE Folio=" + folio + "");
			if (rs.next())
				// Crea una nueva instancia del objeto
				ticket = new Ticket(rs.getInt("Folio"), rs.getString("Fecha"), rs.getString("IDVendedor"),
						rs.getInt("CodigoProducto"), rs.getDouble("Iva"), rs.getDouble("Total"), rs.getInt("Vendidos"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticket;
	}

	public Ticket[] dameTickets() {
		ArrayList<Ticket> ticketsTemp = new ArrayList<Ticket>();
		try {
			// Crea el Statement
			Statement statement = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Tickets");
			while (rs.next()) {
				// Crea una nueva instancia del objeto
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

	public int cantidadTickets() {
		try {
			// Crea el Statement
			Statement statement = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Tickets");
			if (rs.next())
				return rs.getInt(1); // Obtiene el Valor a la Columna 1
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
