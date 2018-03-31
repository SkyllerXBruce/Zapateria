package Modelo;

public class Usuario {

	// Variables Globales
	private String id, user, pass, tipo, nombre, curp, correo, telefono;

	// Constructor que Hace Referencia al Constructor Debajo
	public Usuario() {
		this("", "", "", "", "", "", "", "");
	}

	// Constructor para Asignar Valores al Usuario
	public Usuario(String id, String user, String pass, String tipo, String nombre, String curp, String correo,
			String telefono) {
		this.id = id;
		this.user = user;
		this.pass = pass;
		this.tipo = tipo;
		this.nombre = nombre;
		this.curp = curp;
		this.correo = correo;
		this.telefono = telefono;
	}

	// Declaramos estos Métodos, para que Modifiquemos los Valores Cuando sea
	// Necesario
	public void setId(String id) {
		this.id = id;
	}

	public void setUsuario(String user) {
		this.user = user;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	// Estos Metodos nos Serviran para Mostrar los Atributos
	public String getId() {
		return id;
	}

	public String getUsuario() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	public String getTipo() {
		return tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCurp() {
		return curp;
	}

	public String getCorreo() {
		return correo;
	}

	public String getTelefono() {
		return telefono;
	}

	@Override // Método para Obtener la Cadena que Representa al Objeto
	public String toString() {
		return "Usuario [IDVendedor=" + id + ", Usuario=" + user + ", Pass=" + pass + ", Tipo=" + tipo + ", Nombre="
				+ nombre + ", Curp=" + curp + ", Correo=" + correo + ", Telefono=" + telefono + "]";
	}

}
