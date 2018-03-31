package Persistencia;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Producto;
import java.sql.ResultSet;

public class DAOAlmacen {

	// Metodo para Agregar el Producto a la Base de Datos
	public boolean agregaProducto(Producto producto) {
		try {
			Statement statement = ManejadorBD.dameConnection().createStatement();
			statement.execute("INSERT INTO Almacen VALUES(" + producto.dameCodigo() + ",'" + producto.dameModelo()
					+ "', '" + producto.dameTipo() + "', '" + producto.dameColor() + "', " + producto.dameCosto() + ", "
					+ producto.dameTalla() + ", " + producto.dameCantidad() + ")");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Metodo para Quitar un Producto de la Base de Datos
	public boolean quitaProducto(Producto producto) {
		int resultado = 0;
		try {
			Statement statement = ManejadorBD.dameConnection().createStatement();
			resultado = statement.executeUpdate("DELETE FROM Almacen WHERE Modelo='" + producto.dameModelo()
					+ "' AND Tipo='" + producto.dameTipo() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultado == 0)
			return false;
		else
			return true;
	}

	// Metodos para la Busqueda de un Producto, si lo Encuentra Regresa el Producto
	// en Otro Caso null
	public Producto buscaProducto(String modelo, String tipo, String color, double talla) {
		Producto producto = null;
		try {
			Statement statement = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Almacen WHERE Modelo='" + modelo + "' AND Tipo='"
					+ tipo + "' AND Color='" + color + "' AND Talla=" + talla);
			if (rs.next())
				producto = new Producto(rs.getInt("Codigo"), rs.getString("Modelo"), rs.getString("Tipo"),
						rs.getString("Color"), rs.getDouble("Costo"), rs.getDouble("Talla"), rs.getInt("Cantidad"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;
	}

	public Producto buscaProducto(int codigo) {
		Producto producto = null;
		try {
			Statement statement = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Almacen WHERE Codigo=" + codigo);
			if (rs.next())
				producto = new Producto(rs.getInt("Codigo"), rs.getString("Modelo"), rs.getString("Tipo"),
						rs.getString("Color"), rs.getDouble("Costo"), rs.getDouble("Talla"), rs.getInt("Cantidad"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;
	}

	// Metodo para Obtener todos los Productos
	public Producto[] dameProductos() {
		ArrayList<Producto> productosTemp = new ArrayList<Producto>();
		try {
			Statement statement = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Almacen");
			while (rs.next()) {
				Producto producto = new Producto(rs.getInt("Codigo"), rs.getString("Modelo"), rs.getString("Tipo"),
						rs.getString("Color"), rs.getDouble("Costo"), rs.getDouble("Talla"), rs.getInt("Cantidad"));
				productosTemp.add(producto);
			}
			Producto productosTempArreglo[] = new Producto[productosTemp.size()];
			productosTemp.toArray(productosTempArreglo);
			return productosTempArreglo;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Metodo que Obtiene el Total de Productos en la Base de Datos
	public int cuantosProductos() {
		try {
			Statement statement = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Almacen");
			if (rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
