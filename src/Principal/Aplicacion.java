package Principal;

import Negocio.ControlAlmacen;
import Negocio.ControlLogin;
import Negocio.ControlVendedores;
import Negocio.ControlVenta;
import Negocio.ServicioAlmacen;
import Negocio.ServicioLogin;
import Negocio.ServicioTicket;
import Negocio.ServicioVendedores;
import Presentacion.VistaAdministarVendedores;
import Presentacion.VistaAdministrador;
import Presentacion.VistaAgregarProducto;
import Presentacion.VistaAgregarUsuario;
import Presentacion.VistaAgregarVendedor;
import Presentacion.VistaAlmacen;
import Presentacion.VistaBalanceGeneral;
import Presentacion.VistaCambioCalzado;
import Presentacion.VistaComiciones;
import Presentacion.VistaConsultaProducto;
import Presentacion.VistaConsultaProductoRealizada;
import Presentacion.VistaConsultaRealizada;
import Presentacion.VistaConsultarVendedor;
import Presentacion.VistaEliminarProducto;
import Presentacion.VistaEliminarVendedor;
import Presentacion.VistaLogin;
import Presentacion.VistaMostrarProductos;
import Presentacion.VistaMostrarVendedores;
import Presentacion.VistaTicket;
import Presentacion.VistaVendedor;
import Presentacion.VistaVentaCalzado;

public class Aplicacion {

	// Este metodo crea el objeto incial
	public static void main(String[] args) {
		Aplicacion app = new Aplicacion();
		app.inicia(); // La inicia
	}

	// Inicia la Aplicacion
	public void inicia() {
		// Declaramos e instanciamos las clases de las Vistas
		VistaAdministarVendedores vistaadminvendedores = new VistaAdministarVendedores();
		VistaAdministrador vistaadmin = new VistaAdministrador();
		VistaAgregarProducto vistaaddproducto = new VistaAgregarProducto();
		VistaAgregarUsuario vistaadduser = new VistaAgregarUsuario();
		VistaAgregarVendedor vistaaddvendedor = new VistaAgregarVendedor();
		VistaAlmacen vistaalmacen = new VistaAlmacen();
		VistaBalanceGeneral vistabalancegeneral = new VistaBalanceGeneral();
		VistaCambioCalzado vistacambio = new VistaCambioCalzado();
		VistaComiciones vistacomiciones = new VistaComiciones();
		VistaConsultaProducto vistaconsultaproducto = new VistaConsultaProducto();
		VistaConsultaProductoRealizada vistaconsultaproductorealizada = new VistaConsultaProductoRealizada();
		VistaConsultaRealizada vistaconsultarealizada = new VistaConsultaRealizada();
		VistaConsultarVendedor vistaconsultavendedor = new VistaConsultarVendedor();
		VistaEliminarProducto vistadelproducto = new VistaEliminarProducto();
		VistaEliminarVendedor vistadelvendedor = new VistaEliminarVendedor();
		VistaLogin vistalogin = new VistaLogin();
		VistaMostrarProductos vistamostrarproductos = new VistaMostrarProductos();
		VistaMostrarVendedores vistamostrarvendedores = new VistaMostrarVendedores();
		VistaTicket vistaticket = new VistaTicket();
		VistaVendedor vistavendedor = new VistaVendedor();
		VistaVentaCalzado vistavendedorcalzado = new VistaVentaCalzado();

		// Declaramos e instanciamos las clases de Control
		ControlAlmacen controlalmacen = new ControlAlmacen();
		ControlLogin controllogin = new ControlLogin();
		ControlVendedores controlvendedores = new ControlVendedores();
		ControlVenta controlventa = new ControlVenta();

		// Declaramos e instanciamos las clases de Servicio
		ServicioAlmacen servicioalmacen = new ServicioAlmacen();
		ServicioLogin serviciologin = new ServicioLogin();
		ServicioTicket servicioticket = new ServicioTicket();
		ServicioVendedores serviciovendedores = new ServicioVendedores();

		// Mandamos el Control Correspondiente a las vistas
		vistaadmin.setControl(controlvendedores);
		vistaadmin.setControl(controllogin);
		vistaadmin.setControl(controlalmacen);
		vistaadminvendedores.setControl(controlvendedores);
		vistaaddproducto.setControl(controlalmacen);
		vistaadduser.setControl(controlvendedores);
		vistaaddvendedor.setControl(controlvendedores);
		vistaalmacen.setControl(controlalmacen);
		vistabalancegeneral.setControl(controlvendedores);
		vistacambio.setControl(controlventa);
		vistacomiciones.setControl(controlventa);
		vistaconsultaproducto.setControl(controlalmacen);
		vistaconsultaproductorealizada.setControl(controlalmacen);
		vistaconsultarealizada.setControl(controlvendedores);
		vistaconsultavendedor.setControl(controlvendedores);
		vistadelproducto.setControl(controlalmacen);
		vistadelvendedor.setControl(controlvendedores);
		vistalogin.setControl(controllogin);
		vistamostrarproductos.setControl(controlalmacen);
		vistamostrarproductos.setControl(controlventa);
		vistamostrarvendedores.setControl(controlvendedores);
		vistaticket.setControl(controlventa);
		vistaticket.setControl(controlalmacen);
		vistavendedor.setControl(controlventa);
		vistavendedor.setControl(controlalmacen);
		vistavendedorcalzado.setControl(controlventa);

		// Mandamos al Control Login las Vistas y el Servicion Correspondiente
		controllogin.setServicioLogin(serviciologin);
		controllogin.setVistaAdministrador(vistaadmin);
		controllogin.setVistaLogin(vistalogin);
		controllogin.setVistaVendedor(vistavendedor);

		// Mandamos al Control Venta las Vistas y los Servicion Correspondiente
		controlventa.setServicioAlmacen(servicioalmacen);
		controlventa.setServicioLogin(serviciologin);
		controlventa.setServicioTicket(servicioticket);
		controlventa.setVistaCambioCalzado(vistacambio);
		controlventa.setVistaComiciones(vistacomiciones);
		controlventa.setVistaLogin(vistalogin);
		controlventa.setVistaTicket(vistaticket);
		controlventa.setVistaVendedor(vistavendedor);
		controlventa.setVistaVentaCalzado(vistavendedorcalzado);

		// Mandamos al Control Vendedor las Vistas y el Servicion Correspondiente
		controlvendedores.setServicioTicket(servicioticket);
		controlvendedores.setServicioVendedores(serviciovendedores);
		controlvendedores.setVistaAdministarVendedores(vistaadminvendedores);
		controlvendedores.setVistaAdministrador(vistaadmin);
		controlvendedores.setVistaAgregarUsuario(vistaadduser);
		controlvendedores.setVistaAgregarVendedor(vistaaddvendedor);
		controlvendedores.setVistaBalanceGeneral(vistabalancegeneral);
		controlvendedores.setVistaConsultaRealizada(vistaconsultarealizada);
		controlvendedores.setVistaConsultarVendedor(vistaconsultavendedor);
		controlvendedores.setVistaEliminarVendedor(vistadelvendedor);
		controlvendedores.setVistaMostrarVendedores(vistamostrarvendedores);

		// Mandamos al Control Almacen las Vistas y el Servicion Correspondiente
		controlalmacen.setServicioAlmacen(servicioalmacen);
		controlalmacen.setVistaAdministrador(vistaadmin);
		controlalmacen.setVistaAgregarProducto(vistaaddproducto);
		controlalmacen.setVistaAlmacen(vistaalmacen);
		controlalmacen.setVistaCosultaProducto(vistaconsultaproducto);
		controlalmacen.setVistaConsultaProductoRealizada(vistaconsultaproductorealizada);
		controlalmacen.setVistaEliminarProducto(vistadelproducto);
		controlalmacen.setVistaMostrarProductos(vistamostrarproductos);

		// Hacemos Visible la Clase Vistalogin
		vistalogin.setVisible(true);
	}

}
