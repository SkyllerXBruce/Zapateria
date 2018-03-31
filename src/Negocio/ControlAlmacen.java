package Negocio;

import Modelo.Producto;
import Presentacion.VistaAdministrador;
import Presentacion.VistaAgregarProducto;
import Presentacion.VistaAlmacen;
import Presentacion.VistaConsultaProducto;
import Presentacion.VistaConsultaProductoRealizada;
import Presentacion.VistaEliminarProducto;
import Presentacion.VistaMostrarProductos;

public class ControlAlmacen {

	// Variables Globales
	private VistaAlmacen vistaalmacen;
	private VistaAdministrador vistaadmin;
	private VistaAgregarProducto vistaaddproducto;
	private VistaEliminarProducto vistadelproducto;
	private VistaConsultaProducto vistaconsultaproducto;
	private VistaConsultaProductoRealizada vistaconsultaproductorealizada;
	private VistaMostrarProductos vistamostrarproductos;
	private ServicioAlmacen servicioalmacen;

	// En estos Métodos se Agrega las Instancias del Servicio y las Vistas al
	// Control Almacen
	public void setVistaAlmacen(VistaAlmacen vistaalmacen) {
		this.vistaalmacen = vistaalmacen;
	}

	public void setVistaAdministrador(VistaAdministrador vistaadmin) {
		this.vistaadmin = vistaadmin;
	}

	public void setVistaAgregarProducto(VistaAgregarProducto vistaaddproducto) {
		this.vistaaddproducto = vistaaddproducto;
	}

	public void setVistaEliminarProducto(VistaEliminarProducto vistadelproducto) {
		this.vistadelproducto = vistadelproducto;
	}

	public void setVistaCosultaProducto(VistaConsultaProducto vistaconsultaproducto) {
		this.vistaconsultaproducto = vistaconsultaproducto;
	}

	public void setVistaConsultaProductoRealizada(VistaConsultaProductoRealizada vistaconsultaproductorealizada) {
		this.vistaconsultaproductorealizada = vistaconsultaproductorealizada;
	}

	public void setVistaMostrarProductos(VistaMostrarProductos vistamostrarproductos) {
		this.vistamostrarproductos = vistamostrarproductos;
	}
	
	public void setServicioAlmacen(ServicioAlmacen servicioalmacen) {
		this.servicioalmacen = servicioalmacen;
	}

	// Metodos para Mostrar las Vistas Correspondientes
	public void muestraVistaAlmacen() {
		this.vistaalmacen.setVisible(true);
	}

	public void muestraVistaAdministrador() {
		this.vistaadmin.setVisible(true);
	}

	public void muestraVistaAgregarProducto() {
		this.vistaaddproducto.setVisible(true);
	}

	public void muestraVistaEliminarProducto() {
		this.vistadelproducto.setVisible(true);
	}

	public void muestraVistaConsultaProducto() {
		this.vistaconsultaproducto.setVisible(true);
	}

	public void muestraVistaConsultaProductoRealizada(Producto producto) {
		this.vistaconsultaproductorealizada.obtenerDatosProducto(producto);
		this.vistaconsultaproductorealizada.setVisible(true);
	}

	public void muestraVistaMostrarProductos() {
		this.vistamostrarproductos.setVisible(true);
	}

	// Metodos para Verficar la Existencia de Productos si Existe Regresa True en
	// Otro
	// Caso False
	public boolean existeProducto(String modelo, String tipo, String color, double talla) {
		if (servicioalmacen.buscaProducto(modelo, tipo, color, talla) != null)
			return true;
		return false;
	}

	public boolean existeProducto(int codigo) {
		if (servicioalmacen.buscaProducto(codigo) != null)
			return true;
		return false;
	}

	// Metodos para la Busqueda de un Producto, si lo Encuentra Regresa el Producto
	// en Otro Caso null
	public Producto buscaProducto(String modelo, String tipo) {
		return servicioalmacen.buscaProducto(modelo, tipo);
	}

	public Producto buscaProducto(String modelo, String tipo, String color, double talla) {
		return servicioalmacen.buscaProducto(modelo, tipo, color, talla);
	}

	public Producto buscaProducto(int codigo) {
		return servicioalmacen.buscaProducto(codigo);
	}

	// Metodo para Agregar el Producto a la Base de Datos
	public boolean agregarProducto(Producto producto) {
		return servicioalmacen.agregarProducto(producto);
	}

	// Metodo para Quitar el Producto de la Base de Datos
	public boolean eliminarProducto(Producto producto) {
		return servicioalmacen.eliminarProducto(producto);
	}

	// Metodo para Obtener los Datos de Todos los Productos y los Cargar a la Tabla
	public void cargarDatosProductos() {
		String[] nuevo = new String[vistamostrarproductos.getTablaModelo().getColumnCount()];
		for (Producto producto : servicioalmacen.dameProductos()) {
			nuevo[0] = String.valueOf(producto.dameCodigo());
			nuevo[1] = producto.dameModelo();
			nuevo[2] = producto.dameTipo();
			nuevo[3] = producto.dameColor();
			nuevo[4] = String.format("%.2f", producto.dameCosto());
			nuevo[5] = String.valueOf(producto.dameTalla());
			nuevo[6] = String.valueOf(producto.dameCantidad());
			vistamostrarproductos.getTablaModelo().addRow(nuevo);
		}
	}

	// Metodo que crea un codigo no repetido para el Producto
	public int generaCodigo() {
		int codigo = 1, j = 0, cantidad = servicioalmacen.cantidadProductos();
		for (int i = j; i < cantidad; i++) {
			for (Producto p : servicioalmacen.dameProductos())
				if (p.dameCodigo() == codigo)
					codigo++;
			j++;
		}
		return codigo;
	}

	// Metodo que Verifica el Texto Ingresado es un Numero Real, en Caso de ser
	// un Numero manda True en Otro Caso false
	public boolean esNumeroReal(String num) {
		int puntos = 0, tam = num.length();
		if (num.isEmpty())
			return false;
		for (Character c : num.toCharArray())
			if (!Character.isDigit(c))
				if (c.equals('.') && tam > 1) {
					if (puntos > 0)
						return false;
					puntos++;
				} else
					return false;
		return true;
	}

	// Metodo que Manda a Limpiar los Datos de la Vista del Caso Ingresado
	public void limpiarDatos(String limpiar) {
		switch (limpiar) {
		case "Agregar":
			vistaaddproducto.limpiarDatosAgregarProducto();
			break;
		case "Eliminar":
			vistadelproducto.limpiarDatosEliminarProducto();
			break;
		case "Consultar":
			vistaconsultaproducto.limpiarDatosConsultarProducto();
			vistaconsultaproductorealizada.limpiarDatosConsultaProducto();
			break;
		case "Mostrar":
			vistamostrarproductos.limpiarDatosMostrarProductos();
			break;
		default:
			throw new IllegalArgumentException("Error, No se Puede Limpiar");
		}
	}
}
