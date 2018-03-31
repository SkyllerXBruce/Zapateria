package Negocio;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JOptionPane;
import Modelo.Producto;
import Modelo.Ticket;
import Modelo.Usuario;
import Presentacion.VistaAdministarVendedores;
import Presentacion.VistaCambioCalzado;
import Presentacion.VistaComiciones;
import Presentacion.VistaLogin;
import Presentacion.VistaTicket;
import Presentacion.VistaVendedor;
import Presentacion.VistaVentaCalzado;

public class ControlVenta implements Printable {

	// Variables Globales
	private VistaCambioCalzado vistacambio;
	private VistaVentaCalzado vistaventacalzado;
	private VistaTicket vistaticket;
	private VistaLogin vistalogin;
	private VistaVendedor vistavendedor;
	private VistaAdministarVendedores vistaadminvendedores;
	private VistaComiciones vistacomiciones;
	private ServicioTicket servicioticket;
	private ServicioAlmacen servicioalmacen;
	private ServicioLogin serviciologin;
	private Usuario user;

	// En estos Métodos se Agrega las Instancias del Servicio y las Vistas al
	// Control Venta
	public void setVistaLogin(VistaLogin vistalogin) {
		this.vistalogin = vistalogin;
	}

	public void setVistaVendedor(VistaVendedor vistavendedor) {
		this.vistavendedor = vistavendedor;
	}

	public void setVistaAdministarVendedores(VistaAdministarVendedores vistaadminvendedores) {
		this.vistaadminvendedores = vistaadminvendedores;
	}

	public void setVistaComiciones(VistaComiciones vistacomiciones) {
		this.vistacomiciones = vistacomiciones;
	}

	public void setVistaTicket(VistaTicket vistaticket) {
		this.vistaticket = vistaticket;
	}

	public void setVistaCambioCalzado(VistaCambioCalzado vistacambio) {
		this.vistacambio = vistacambio;
	}

	public void setVistaVentaCalzado(VistaVentaCalzado vistaventacalzado) {
		this.vistaventacalzado = vistaventacalzado;
	}

	public void setServicioTicket(ServicioTicket servicioticket) {
		this.servicioticket = servicioticket;
	}

	public void setServicioAlmacen(ServicioAlmacen servicioalmacen) {
		this.servicioalmacen = servicioalmacen;
	}

	public void setServicioLogin(ServicioLogin serviciologin) {
		this.serviciologin = serviciologin;
	}

	// Metodos para Mostrar las Vistas Correspondientes
	public void muestraVistaAdministarVendedores() {
		vistaadminvendedores.setVisible(true);
	}

	public void muestraVentadeCalzado() {
		vistaventacalzado.setVisible(true);
	}

	public void muestraCambioCalzado() {
		vistacambio.setVisible(true);
	}

	public void muestraVistaLogin() {
		vistalogin.setVisible(true);
	}

	public void muestraVistaVendedor() {
		vistavendedor.setVisible(true);
	}

	public void muestraVistaComiciones() {
		vistacomiciones.setVisible(true);
	}

	// Se Guarda el Vendedor que Ingreso en el Login
	public void setVendedor(Usuario user) {
		this.user = user;
	}

	// Obtiene los Datos del Vendedor
	public Usuario getVendedor() {
		return user;
	}

	// Busca el Vendedor por el nombre Ingresado
	public Usuario buscaVendedor(String nombre, String tipo) {
		return serviciologin.dameVendedor(nombre, tipo);
	}

	// Este Método nos Permite Agregar los Datos del Producto a la Tabla
	public void buscaProducto(int id) {
		Object[] fila = new Object[vistaventacalzado.getTablaModelo().getColumnCount()];
		Producto producto = servicioalmacen.buscaProducto(id);
		if (producto != null) {
			fila[0] = producto.dameModelo();
			fila[1] = producto.dameTipo();
			fila[2] = producto.dameColor();
			fila[3] = producto.dameTalla();
			fila[4] = producto.dameCosto();
			fila[5] = producto.dameCantidad();
			vistaventacalzado.getTablaModelo().addRow(fila);
		} else
			JOptionPane.showMessageDialog(null, "No se encontraron productos");
	}

	// Este Método nos Permite Agregar los Datos del Ticket a la Tabla
	public void buscaTicket(int folio) {
		double total = 0;
		Object[] filaventa = new Object[vistacambio.getTablaModeloVenta().getColumnCount()];
		Object[] filacambio = new Object[vistacambio.getTablaModeloCambio().getColumnCount()];
		if (servicioticket.dameCantidadTickets() != 0) {
			Ticket ticket = servicioticket.dameTicket(folio);
			if (ticket != null && ticket.getCodigoproducto() != 0) {
				Producto producto = servicioalmacen.buscaProducto(ticket.getCodigoproducto());
				filaventa[0] = producto.dameModelo();
				filaventa[1] = producto.dameTipo();
				filaventa[2] = producto.dameColor();
				filaventa[3] = producto.dameTalla();
				filaventa[4] = producto.dameCosto();
				filaventa[5] = producto.dameCosto() * 1.16;
				vistacambio.getTablaModeloVenta().addRow(filaventa);
				for (Producto p : servicioalmacen.dameProductos()) {
					total = p.dameCosto() * 1.16;
					if (total >= (double) filaventa[5] && p.dameCantidad() > 0) {
						filacambio[0] = p.dameModelo();
						filacambio[1] = p.dameTipo();
						filacambio[2] = p.dameColor();
						filacambio[3] = p.dameTalla();
						filacambio[4] = p.dameCosto();
						filacambio[5] = total;
						filacambio[6] = p.dameCantidad();
						filacambio[7] = false;
						vistacambio.getTablaModeloCambio().addRow(filacambio);
					}
				}
			} else
				JOptionPane.showMessageDialog(null, "No se Encontraron los Datos del Folio");
		} else
			JOptionPane.showMessageDialog(null, "No Existen Tickets Actualmente");
	}

	// Este Método Calcula el Total Sobre las Unidades que se Van a Vender y su
	// Precio Unitario
	public void calculaTotal() {
		if (vistaventacalzado.getTablaModelo().getValueAt(0, 6) == null)
			JOptionPane.showMessageDialog(null, "Agregue pares a vender y presione enter");
		else {
			double costo = (double) vistaventacalzado.getTablaModelo().getValueAt(0, 4);
			int cantidadproductos = (int) (vistaventacalzado.getTablaModelo().getValueAt(0, 5));
			int cantidadventa = Integer.parseInt((String) vistaventacalzado.getTablaModelo().getValueAt(0, 6));
			if (cantidadventa > cantidadproductos)
				JOptionPane.showMessageDialog(null, "Cantidad insuficiente de productos en almacén");
			else {
				double iva = (costo * 0.16);
				double montototal = (costo * cantidadventa) + iva;
				vistaventacalzado.setMontounitario(String.valueOf(costo));
				vistaventacalzado.setIva(String.format("%.2f", iva));
				vistaventacalzado.setMontototal(String.format("%.2f", montototal));
			}
		}
	}

	// Metodo para Obtener el Nuevo Producto de Cambio Seleccionado, Si Existe un
	// Producto Seleccionado Regresa True en Otro Caso False
	public boolean cambioProducto(int folio) {
		int fila = -1;
		String modelocambio, tipocambio, colorcambio;
		double total = 0, iva = 0, tallacambio = 0, diferencia = 0, tventa = 0;
		boolean seleccion;
		for (int i = 0; i < vistacambio.getTablaModeloCambio().getRowCount(); i++) {
			seleccion = (boolean) vistacambio.getTablaModeloCambio().getValueAt(i, 7);
			if (seleccion)
				fila = i;
		}
		if (fila >= 0) {
			modelocambio = (String) vistacambio.getTablaModeloCambio().getValueAt(fila, 0);
			tipocambio = (String) vistacambio.getTablaModeloCambio().getValueAt(fila, 1);
			colorcambio = (String) vistacambio.getTablaModeloCambio().getValueAt(fila, 2);
			tallacambio = (double) vistacambio.getTablaModeloCambio().getValueAt(fila, 3);
			if (JOptionPane.showConfirmDialog(null, "¿Realmente Desea Realizar el Cambio?", "¿Cambio?",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				Producto productocambio = servicioalmacen.buscaProducto(modelocambio, tipocambio, colorcambio,
						tallacambio);
				total = (double) vistacambio.getTablaModeloCambio().getValueAt(fila, 5);
				iva = productocambio.dameCosto() * 0.16;
				tventa = (double) vistacambio.getTablaModeloVenta().getValueAt(0, 5);
				diferencia = (productocambio.dameCosto() * 1.16) - tventa;
				creaTicketCambio(folio, productocambio, iva, total, diferencia, tventa);
				return true;
			}
		} else
			JOptionPane.showMessageDialog(null, "Seleccione un Producto para Realizar Cambio");
		return false;
	}

	// Creamos el Ticket de Venta
	public void creaTicketVenta() {
		String datosventa[] = { String.valueOf(servicioticket.generaFolio()), servicioticket.getFechaActual(),
				(String) vistaventacalzado.getTablaModelo().getValueAt(0, 0),
				(String) vistaventacalzado.getTablaModelo().getValueAt(0, 1),
				(String) vistaventacalzado.getTablaModelo().getValueAt(0, 2),
				String.valueOf(vistaventacalzado.getTablaModelo().getValueAt(0, 3)),
				(String) vistaventacalzado.getTablaModelo().getValueAt(0, 6), vistaventacalzado.getIva(),
				String.valueOf(vistaventacalzado.getTablaModelo().getValueAt(0, 4)), vistaventacalzado.getTotal() };
		vistaticket.setDatosTicket(datosventa);
		vistaticket.setVisible(true);
	}

	// Creamos el Ticket de Cambio
	public void creaTicketCambio(int folio, Producto producto, double iva, double total, double diferencia,
			double tventa) {
		String datoscambio[] = { String.valueOf(folio), servicioticket.getFechaActual(), producto.dameModelo(),
				producto.dameTipo(), producto.dameColor(), String.valueOf(producto.dameTalla()), String.valueOf(1),
				String.format("%.2f", iva), String.format("%.2f", producto.dameCosto()),
				String.format("%.2f", diferencia) };
		vistaticket.setDatosTicket(datoscambio);
		vistaticket.setTotalAnterior(String.format("%.2f", tventa));
		vistaticket.setVisible(true);
	}

	// Realiza el Cambio del Producto, Si Realizo Cambio Regresa True en Otro Caso
	// False
	public boolean realizaCambioProducto(int folio, Producto productocambio, double iva, double total) {
		Ticket ticket = servicioticket.dameTicket(folio);
		Producto productoventa = servicioalmacen.buscaProducto(ticket.getCodigoproducto());
		Usuario user = getVendedor();
		if (servicioalmacen.eliminarProducto(productocambio)) {
			productocambio.setCantidad(productocambio.dameCantidad() - 1);
			servicioalmacen.agregarProducto(productocambio);
			if (servicioticket.modificaTicket(folio, servicioticket.getFechaActual(), user.getId(),
					productocambio.dameCodigo(), total, iva)) {
				if (servicioalmacen.eliminarProducto(productoventa)) {
					productoventa.setCantidad(productoventa.dameCantidad() + 1);
					servicioalmacen.agregarProducto(productoventa);
					return true;
				} else {
					servicioalmacen.eliminarProducto(productocambio);
					productocambio.setCantidad(productocambio.dameCantidad() + 1);
					servicioalmacen.agregarProducto(productocambio);
				}
			} else {
				servicioalmacen.eliminarProducto(productocambio);
				productocambio.setCantidad(productocambio.dameCantidad() + 1);
				servicioalmacen.agregarProducto(productocambio);
			}
		}
		return false;
	}

	// Metodo que Verifica si el Ticket a Imprimir es de Cambio o no, Si es Cambio
	// regresa true en Otro Caso false
	public boolean esCambio() {
		return vistacambio.realizaCambio();
	}

	// Estos Métodos Muestran o Modifican si el Vendedor esta Activo o No, En Caso
	// de Estar Activo Regresa True en Otro Caso False
	public boolean esVendedor() {
		return vistavendedor.esVendedor();
	}

	public void setVendedor(boolean vendedor) {
		vistavendedor.setVendedor(vendedor);
	}

	// Metodo para Guardar los Datos del Ticket en la Base de Datos
	public void guardarDatosTicket(String[] datosTicket) {
		Producto producto = servicioalmacen.buscaProducto(datosTicket[2], datosTicket[3], datosTicket[4],
				Double.valueOf(datosTicket[5]));
		Usuario user = getVendedor();
		String fecha;
		double iva, total;
		int folio, vendidos, cantidad;
		folio = Integer.valueOf(datosTicket[0]);
		fecha = datosTicket[1];
		iva = Double.valueOf(datosTicket[6]);
		total = Double.valueOf(datosTicket[7]);
		vendidos = Integer.valueOf(datosTicket[8]);
		cantidad = producto.dameCantidad();
		if (cantidad != 0) {
			servicioalmacen.eliminarProducto(producto);
			producto.setCantidad(cantidad - vendidos);
			if (producto.dameCantidad() > 0)
				servicioalmacen.agregarProducto(producto);
			Ticket ticket = new Ticket(folio, fecha, user.getId(), producto.dameCodigo(), iva, total, vendidos);
			servicioticket.agregaTicket(ticket);
		} else
			JOptionPane.showMessageDialog(null, "No hay Productos Disponibles");
	}

	// Metodo Para Obtener los Datos de las Comiciones del Vendedor
	public void obtenDatosComiciones() {
		Usuario user = getVendedor();
		double comicion = servicioticket.obtenerComicionVendedor(user);
		int vendidos = servicioticket.obtenerCantidadVentasVendedor(user);
		vistacomiciones.obtenDatosComicion(user.getNombre(), comicion, vendidos);
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
	public void limpiarDatos(String tipo) {
		if (vistaventacalzado.getTablaModelo().getRowCount() != 0 && tipo.equals("Venta")) {
			vistaventacalzado.setIdProducto("");
			vistaventacalzado.setMontounitario("       -");
			vistaventacalzado.setIva("       -");
			vistaventacalzado.setMontototal("       -");
			vistaventacalzado.getTablaModelo().removeRow(0);
		} else if (vistacambio.getTablaModeloVenta().getRowCount() != 0
				|| vistacambio.getTablaModeloCambio().getRowCount() != 0 && tipo.equals("Cambio")) {
			vistacambio.getTablaModeloVenta().removeRow(0);
			vistacambio.setFolioventa("");
			for (int i = vistacambio.getTablaModeloCambio().getRowCount() - 1; i >= 0; i--)
				vistacambio.getTablaModeloCambio().removeRow(i);
		} else if (tipo.equals("Comicion")) {
			vistacomiciones.limpiarDatosComiciones();
		}
	}

	// Método para Imprimir el Ticket
	public void imprimeTicket() {
		try {
			PrinterJob gap = PrinterJob.getPrinterJob();
			gap.setPrintable(this);
			boolean top = gap.printDialog();
			if (top)
				gap.print();
		} catch (PrinterException e) {
			JOptionPane.showMessageDialog(null, "Error al imprimir");
		}
	}

	// Método para Validar y dar Formato de Impresión al Ticket
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex > 0)
			return NO_SUCH_PAGE;
		Graphics2D hub = (Graphics2D) graphics;
		hub.translate(pageFormat.getImageableX() + 20, pageFormat.getImageableY() + 20);
		hub.scale(1, 1);
		vistaticket.getTicket().printAll(graphics);
		return PAGE_EXISTS;
	}
}
