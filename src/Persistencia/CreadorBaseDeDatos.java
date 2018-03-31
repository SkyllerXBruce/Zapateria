package Persistencia;

import java.sql.Connection;
import java.sql.Statement;

public class CreadorBaseDeDatos {

	// Se Ejecuta la Creacion de Tablas de la Base de Datos
	public static void main(String[] args) {
		try {
			Connection connection = ManejadorBD.dameConnection();
			Statement statement = connection.createStatement();

			// Tabla del Personal Incluyendo el Administrador
			statement.execute(
					"CREATE TABLE Loginpersonal(Id varchar(40),Usuario varchar(40), Contraseña varchar(40), Tipo varchar(40), Nombre varchar(40), Curp varchar(40), Correo varchar(40), Telefono varchar(40))");
			statement.execute(
					"INSERT INTO Loginpersonal VALUES ('ID-ZA-Master','Miguel','mig','Administrador','Mercado Velasco Miguel Angel','MEVM860916HDFRG02','skyllerx@gmail.com','5540215221')");
			statement.execute(
					"INSERT INTO Loginpersonal VALUES ('ID-ZA-V-1','Edgar','Feikel10','Vendedor','Edgar Lopez Perez','ECLP983200SDDRF','edgar.lp@gmail.com','5521365412')");

			// Tabla del Almacen de Productos
			statement.execute(
					"CREATE TABLE Almacen(Codigo integer, Modelo varchar(40), Tipo varchar(40), Color varchar(40), Costo double, Talla double, Cantidad integer)");
			statement.execute(
					"INSERT INTO Almacen VALUES(1,'Nike Airmax', 'Calzado Deportivo', 'Blanco', 989.60,27.5, 1)");
			statement.execute(
					"INSERT INTO Almacen VALUES(2,'Adidas Future', 'Calzado Deportivo', 'Rojo', 869.60,24.5, 4)");
			statement.execute("INSERT INTO Almacen VALUES(3,'Nike Conford', 'Calzado Casual', 'Gris', 1570.40,28, 2)");
			statement.execute(
					"INSERT INTO Almacen VALUES(4,'Pumas Limited', 'Calzado deportivo', 'Dorado', 1189.65,28.5, 5)");

			// Tabla de los Tickets de Venta
			statement.execute(
					"CREATE TABLE Tickets(Folio integer, Fecha varchar (40), IDVendedor varchar (40), CodigoProducto integer, Iva double, Total double, Vendidos integer)");
			System.out.println("datos agregados");
			ManejadorBD.termina();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
