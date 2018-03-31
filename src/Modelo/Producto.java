package Modelo;

public class Producto {

	// Variables Globales
	private String modelo, tipo, color;
	private double costo = 0, talla = 0;
	private int cantidad = 0, codigo = 0;

	// Constructor que nos Permite Asignar Valores a los Atributos.
	public Producto(int codigo, String modelo, String tipo, String color, double costo, double talla, int cantidad) {
		this.codigo = codigo;
		this.modelo = modelo;
		this.tipo = tipo;
		this.color = color;
		this.costo = costo;
		this.talla = talla;
		this.cantidad = cantidad;
	}

	// Declaramos estos Métodos, para que Modifiquemos los Valores Cuando sea
	// Necesario
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void setTalla(double talla) {
		this.talla = talla;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	// Estos Metodos nos Serviran para Mostrar los Atributos
	public int dameCodigo() {
		return codigo;
	}

	public String dameModelo() {
		return modelo;
	}

	public String dameTipo() {
		return tipo;
	}

	public String dameColor() {
		return color;
	}

	public double dameCosto() {
		return costo;
	}

	public double dameTalla() {
		return talla;
	}

	public int dameCantidad() {
		return cantidad;
	}

	@Override // Método para Obtener la Cadena que Representa al Objeto
	public String toString() {
		return "Producto [Codigo=" + codigo + ", Modelo=" + modelo + ", Tipo=" + tipo + ", Color=" + color + ", Costo="
				+ costo + ", Talla=" + talla + ", Cantidad=" + cantidad + "]";
	}

}
