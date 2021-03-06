package Modelo;

public class Ticket {

	// Variables Globles
	private int folio = 0, codigoproducto = 0, vendidos = 0;
	private String idvendedor, fecha;
	private double iva = 0, total = 0;

	// Constructor para Asignar Valores al Ticket
	public Ticket(int folio, String fecha, String idvendedor, int codigoproducto, double iva, double total,
			int vendidos) {
		this.folio = folio;
		this.fecha = fecha;
		this.idvendedor = idvendedor;
		this.codigoproducto = codigoproducto;
		this.iva = iva;
		this.total = total;
		this.vendidos = vendidos;
	}

	// Declaramos estos Métodos, para que Modifiquemos los Valores Cuando sea
	// Necesario
	public void setFolio(int folio) {
		this.folio = folio;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setIdvendedor(String idvendedor) {
		this.idvendedor = idvendedor;
	}

	public void setCodigoproducto(int codigoproducto) {
		this.codigoproducto = codigoproducto;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setVendidos(int vendidos) {
		this.vendidos = vendidos;
	}

	// Estos Metodos nos Serviran para Mostrar los Atributos
	public int getFolio() {
		return folio;
	}

	public String getFecha() {
		return fecha;
	}

	public String getIdvendedor() {
		return idvendedor;
	}

	public int getCodigoproducto() {
		return codigoproducto;
	}

	public double getIva() {
		return iva;
	}

	public double getTotal() {
		return total;
	}

	public int getVendidos() {
		return vendidos;
	}

	@Override // Método para Obtener la Cadena que Representa al Objeto
	public String toString() {
		return "Ticket [Folio=" + folio + ", Fecha=" + fecha + ", IDVendedor=" + idvendedor + ", CodigoProducto="
				+ codigoproducto + ", Iva=" + iva + ", Total=" + total + ", Vendidos=" + vendidos + "]";
	}
}
