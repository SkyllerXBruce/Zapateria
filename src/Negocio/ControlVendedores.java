package Negocio;

import Modelo.Usuario;
import Presentacion.VistaAdministarVendedores;
import Presentacion.VistaAdministrador;
import Presentacion.VistaAgregarUsuario;
import Presentacion.VistaAgregarVendedor;
import Presentacion.VistaBalanceGeneral;
import Presentacion.VistaConsultaRealizada;
import Presentacion.VistaConsultarVendedor;
import Presentacion.VistaEliminarVendedor;
import Presentacion.VistaMostrarVendedores;

public class ControlVendedores {

	// Variables Globales
	private VistaAdministrador vistaadmin;
	private VistaAdministarVendedores vistaadminvendedores;
	private VistaAgregarVendedor vistaagregarvendedor;
	private VistaAgregarUsuario vistaadduser;
	private VistaMostrarVendedores vistamostrarvvendedores;
	private VistaEliminarVendedor vistaeleminarvendedor;
	private VistaConsultarVendedor vistaconsultarvendedor;
	private VistaConsultaRealizada vistaconsultarealizada;
	private VistaBalanceGeneral vistabalancegeneral;
	private ServicioVendedores serviciovendedores;
	private ServicioTicket servicioticket;

	// Agrega las Instancias de las Vistas al Control Vendedores
	public void setServicioVendedores(ServicioVendedores serviciovendedores) {
		this.serviciovendedores = serviciovendedores;
	}

	public void setServicioTicket(ServicioTicket servicioticket) {
		this.servicioticket = servicioticket;
	}

	public void setVistaAdministrador(VistaAdministrador vistaadmin) {
		this.vistaadmin = vistaadmin;
	}

	public void setVistaAdministarVendedores(VistaAdministarVendedores vistaadminvendedores) {
		this.vistaadminvendedores = vistaadminvendedores;
	}

	public void setVistaAgregarVendedor(VistaAgregarVendedor vistaagregarvendedor) {
		this.vistaagregarvendedor = vistaagregarvendedor;
	}

	public void setVistaAgregarUsuario(VistaAgregarUsuario vistaadduser) {
		this.vistaadduser = vistaadduser;
	}

	public void setVistaMostrarVendedores(VistaMostrarVendedores vistamostrarvendedores) {
		this.vistamostrarvvendedores = vistamostrarvendedores;
	}

	public void setVistaEliminarVendedor(VistaEliminarVendedor vistaeliminarvendedor) {
		this.vistaeleminarvendedor = vistaeliminarvendedor;
	}

	public void setVistaConsultarVendedor(VistaConsultarVendedor vistaconsultarvendedor) {
		this.vistaconsultarvendedor = vistaconsultarvendedor;
	}

	public void setVistaConsultaRealizada(VistaConsultaRealizada vistaconsultarealizada) {
		this.vistaconsultarealizada = vistaconsultarealizada;
	}

	public void setVistaBalanceGeneral(VistaBalanceGeneral vistabalancegeneral) {
		this.vistabalancegeneral = vistabalancegeneral;
	}

	// Metodos para Mostrar las Vistas Correspondientes
	public void muestraVistaAdministarVendedores() {
		vistaadminvendedores.setVisible(true);
	}

	public void muestraVistaAdministrador() {
		vistaadmin.setVisible(true);
	}

	public void muestraVistaAgregarVendedor() {
		vistaagregarvendedor.setVisible(true);
	}

	public void muestraVistaAgregarUsuario() {
		vistaadduser.setVisible(true);
	}

	public void muestraVistaMuestraVendedores() {
		vistamostrarvvendedores.setVisible(true);
	}

	public void muestraVistaEliminarVendedor() {
		vistaeleminarvendedor.setVisible(true);
	}

	public void muestraVistaConsultarVendedor() {
		vistaconsultarvendedor.setVisible(true);
	}

	public void muestraVistaConsultaRealizada(Usuario vendedor) {
		vistaconsultarealizada.obtenerDatosVendedor(vendedor);
		vistaconsultarealizada.setVisible(true);
	}

	public void muestraVistaBalanceGeneral() {
		vistabalancegeneral.setVisible(true);
	}

	// Metodos para Verficar la existencia de Vendedores si Existe Manda true otro
	// caso false
	public boolean existeVendedor(String dato) {
		return serviciovendedores.existeVendedor(dato);
	}

	// Metodos para la Busqueda de un Vendedor, si lo Encuentra Regresa el Producto
	// en Otro Caso null
	public Usuario buscaVendedorPorNombre(String nombre) {
		return serviciovendedores.buscaVendedorPorNombre(nombre);
	}

	public Usuario buscaVendedorPorId(String id) {
		return serviciovendedores.buscaVendedorPorId(id);
	}

	// Metodo para Agregar un Vendedor a la Base de Datos
	public boolean agregaVendedor(Usuario vendedor) {
		return serviciovendedores.agregaVendedor(vendedor);
	}

	// Metodo para Quitar un Vendedor de la Base de Datos
	public boolean eliminarVendedor(Usuario vendedor) {
		return serviciovendedores.eliminarVendedor(vendedor);
	}

	// Metodo para Obtener los Datos de Todos los Vendedores y los Carga a la Tabla
	public void cargarDatosVendedores() {
		String[] nuevo = new String[vistamostrarvvendedores.getTablaModelo().getColumnCount()];
		for (Usuario vendedor : serviciovendedores.dameVendedores()) {
			if (!vendedor.getId().equals("ID-ZA-Master")) {
				nuevo[0] = vendedor.getId();
				nuevo[1] = vendedor.getNombre();
				nuevo[2] = vendedor.getCurp();
				nuevo[3] = vendedor.getCorreo();
				nuevo[4] = vendedor.getTelefono();
				nuevo[5] = vendedor.getUsuario();
				vistamostrarvvendedores.getTablaModelo().addRow(nuevo);
			}
		}
	}

	// Metodo para Obtener los Datos del Vendedor
	public void obtenerDatosVendedor(String[] datosvendedor) {
		vistaadduser.obtenerDatosVendedor(datosvendedor);
	}

	// Metodo Para Obtener los Datos del Balance General
	public void obtenDatosBalanceGeneral() {
		double ganancias = 0, comicion = 0;
		int vendidos = 0;
		for (Usuario user : serviciovendedores.dameVendedores()) {
			vendidos += servicioticket.obtenerCantidadVentasVendedor(user);
			ganancias += servicioticket.obtenerGananciasVendedor(user);
			comicion += servicioticket.obtenerComicionVendedor(user);
		}
		vistabalancegeneral.obtenDatosBalanceGeneral(vendidos, comicion, ganancias);
	}

	// Metodo que crea un ID no repetido para el Vendedor
	public String generaId() {
		int contador = 0;
		String id = "ID-ZA-V-" + contador;
		for (Usuario vendedor : serviciovendedores.dameVendedores()) {
			if (vendedor.getId().equals(id) || vendedor.getId().equals("ID-ZA-Master")) {
				contador++;
				id = "ID-ZA-V-" + contador;
			}
		}
		return id;
	}

	// Metodo que Manda a Limpiar los Datos de la Vista del Caso Ingresado
	public void limpiarDatos(String limpiar) {
		switch (limpiar) {
		case "AgregarVendedor":
			vistaagregarvendedor.limpiarDatosAgregarVendedor();
			break;
		case "AgregarUsuario":
			vistaadduser.limpiarDatosAgregarUsuario();
			break;
		case "Eliminar":
			vistaeleminarvendedor.limpiarDatosEliminarVendedor();
			break;
		case "Consultar":
			vistaconsultarvendedor.limpiarDatosConsultaVendedor();
			vistaconsultarealizada.limpiarDatosConsultaVendedor();
			break;
		case "Mostrar":
			vistamostrarvvendedores.limpiarDatosMostrarVendedores();
			break;
		default:
			throw new IllegalArgumentException("Error, No se Puede Limpiar");
		}
	}

}
