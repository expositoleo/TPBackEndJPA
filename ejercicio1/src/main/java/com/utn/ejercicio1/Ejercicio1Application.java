package com.utn.ejercicio1;

import com.utn.ejercicio1.entidades.*;
import com.utn.ejercicio1.enumeracion.Estado;
import com.utn.ejercicio1.enumeracion.FormaDePago;
import com.utn.ejercicio1.enumeracion.TipoDeEnvio;
import com.utn.ejercicio1.enumeracion.TipoProdcuto;
import com.utn.ejercicio1.repositorios.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;


@SpringBootApplication
public class Ejercicio1Application {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	DomicilioRepository domicilioRepository;
	@Autowired
	RubroRepository rubroRepository;
	@Autowired
	ProductoRepository productoRepository;
	@Autowired
	DetallePedidoRepository detallePedidoRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	FacturaRepository facturaRepository;


	public static void main(String[] args) {
		SpringApplication.run(Ejercicio1Application.class, args);
		System.out.println("Persistencia Finalizada Exitosamente");
	}

	/*En los siguientes 4 @Bean se muestra la persistencia y recuperación de una clase y los datos de sus asociadas.
	Como las clases fueron hardcodeadas en todas las oportunidades, al ver la tabla en H2, encontraremos campos nulos
	en dónde de deberíamos ver una llave foranea. Esto no quiere decir que el programa funcione erroneamente,
	sino que esa relación puntual no está contemplada intencionalmente, ya que este código se fue haciendo a medida que
	se armaban las clases para ir testeando si se persistían, recuperaban y se relacionaban correctamente con las demás clases*/

	@Bean //EL CLIENTE CON SUS DOMICILIOS Y PEDIDOS ASOCIADOS
	CommandLineRunner initCliente(ClienteRepository clienteRepo,DomicilioRepository domicilioRepo, PedidoRepository pedidoRepo) {
		return args -> {
			System.out.println("--RECUPERO CLIENTE--");

			Domicilio domicilioA1 = Domicilio.builder()
					.calle("Calle A1")
					.numero(1)
					.localidad("A1")
					.build();

			Domicilio domicilioA2 = Domicilio.builder()
					.calle("Calle A2")
					.numero(2)
					.localidad("A2")
					.build();

			Cliente clienteA1 = Cliente.builder()
					.nombre("Jason")
					.apellido("A1")
					.telefono("1")
					.email("A1")
					.build();

			Date today = new Date();
			Pedido pedidoA1 = Pedido.builder()
					.estado(Estado.Iniciado)
					.fecha(today)
					.tipoEnvio(TipoDeEnvio.Delivery)
					.total(11231.29)
					.build();

			Pedido pedidoA2 = Pedido.builder()
					.estado(Estado.Entregado)
					.fecha(today)
					.tipoEnvio(TipoDeEnvio.Retiro)
					.total(5000.0)
					.build();

			clienteA1.agregarDomicilio(domicilioA1);
			clienteA1.agregarDomicilio(domicilioA2);

			clienteA1.agregarPedido(pedidoA1);
			clienteA1.agregarPedido(pedidoA2);

			clienteRepository.save(clienteA1);

			Cliente clienteRecuperado = clienteRepository.findById(clienteA1.getId()).orElse(null);

			if (clienteRecuperado != null) {
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				System.out.println("Email: " + clienteRecuperado.getEmail());
				clienteRecuperado.mostrarDomicilios();
				clienteRecuperado.mostrarPedidos(); //Como no seteamos detalles, facturas y demás objetos, estos figuran NULL
			}

		};

	}

	@Bean //UN RUBRO CON SUS PRODUCTOS ASOCIADOS
	CommandLineRunner initRubro(RubroRepository rubroRepo,ProductoRepository productoRepo) {
		return args -> {
			System.out.println("--RECUPERO RUBRO--");

			Producto productoA1 = Producto.builder()
					.tipo(TipoProdcuto.Manufacturado)
					.tiempoEstimadoCocina(2)
					.denominacion("Papas Fritas")
					.precioVenta(500)
					.precioCompra(350)
					.stockActual(100)
					.stockMinimo(25)
					.unidadVendida("Papas Fritas")
					.receta("receta Papas Fritas")
					.build();

			Producto productoA2 = Producto.builder()
					.tipo(TipoProdcuto.Insumo)
					.tiempoEstimadoCocina(0)
					.denominacion("Queso")
					.precioVenta(100)
					.precioCompra(75)
					.stockActual(300)
					.stockMinimo(150)
					.unidadVendida("")
					.receta("")
					.build();

			Rubro rubroA1 = Rubro.builder()
					.denominacion("Comida rapida")
					.build();

			rubroA1.agregarProducto(productoA1);
			rubroA1.agregarProducto(productoA2);

			rubroRepository.save(rubroA1);

			Rubro rubroRecuperado = rubroRepository.findById(rubroA1.getId()).orElse(null);


			if (rubroRecuperado != null) {
				System.out.println("Nombre: " + rubroRecuperado.getDenominacion());
				rubroRecuperado.mostrarProductos();

			}





		};

	}

	@Bean //UN DETALLE CON SU PRODUCTO ASOCIADO
	CommandLineRunner initDetallePedido(ProductoRepository productoRepo,DetallePedidoRepository detallePedidoRepo) {
		return args -> {
			System.out.println("--RECUPERO DETALLE PEDIDO--");

			DetallePedido detallePedidoA1 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(300)
					.build();

			Producto productoA1 = Producto.builder()
					.tipo(TipoProdcuto.Manufacturado)
					.tiempoEstimadoCocina(7)
					.denominacion("lomo")
					.precioVenta(1500)
					.precioCompra(800)
					.stockActual(10)
					.stockMinimo(5)
					.unidadVendida("Lomo")
					.receta("receta Lomo: ....")
					.build();

			detallePedidoA1.setProducto(productoA1); //Cada detalle pedido puede tener un producto (setProducto)

			detallePedidoRepository.save(detallePedidoA1);

			DetallePedido detallePedidoRecuperado = detallePedidoRepository.findById(detallePedidoA1.getId()).orElse(null);


			if (detallePedidoRecuperado != null) {
				System.out.println("Cantidad: " + detallePedidoRecuperado.getCantidad());
				System.out.println("Subtotal: " + detallePedidoRecuperado.getSubtotal());
				detallePedidoRecuperado.mostrarProducto();
			}



		};
	}

	@Bean //UN PEDIDO CON SU FACTURA Y SUS DETALLES ASOCIADOS
	CommandLineRunner initPedido(PedidoRepository pedidoRepo,DetallePedidoRepository detallePedidoRepo, FacturaRepository facturaRepo) {
		return args -> {
			System.out.println("--RECUPERO PEDIDO--");

			DetallePedido detallePedidoA1 = DetallePedido.builder()
					.cantidad(10)
					.subtotal(110)
					.build();

			DetallePedido detallePedidoA2 = DetallePedido.builder()
					.cantidad(5)
					.subtotal(555)
					.build();

			Date today = new Date();

			Factura facturaA1 = Factura.builder()
					.numero(1)
					.fecha(today)
					.formaPago(FormaDePago.Efectivo)
					.descuento(10)
					.total(900)
					.build();

			Pedido pedidoA1 = Pedido.builder()
					.fecha(today)
					.estado(Estado.Entregado)
					.tipoEnvio(TipoDeEnvio.Delivery)
					.total(120)
					.build();

			pedidoA1.agregarDetalle(detallePedidoA1);
			pedidoA1.agregarDetalle(detallePedidoA2);
			pedidoA1.setFactura(facturaA1);

			pedidoRepository.save(pedidoA1);

			Pedido pedidoRecuperado = pedidoRepository.findById(pedidoA1.getId()).orElse(null);

			if (pedidoRecuperado != null) {
				System.out.println("Fecha: " + pedidoRecuperado.getFecha());
				System.out.println("Estado: " + pedidoRecuperado.getEstado());
				System.out.println("Envio: " + pedidoRecuperado.getTipoEnvio());
				System.out.println("Total: " + pedidoRecuperado.getTotal());
				pedidoRecuperado.mostrarDetalle();
				pedidoRecuperado.mostrarFactura();
			}

		};

	}

}
