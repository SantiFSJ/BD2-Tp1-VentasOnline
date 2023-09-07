package org.example;

import ar.unrn.tp.api.*;
import ar.unrn.tp.excepciones.FechaInvalidaExcepcion;
import ar.unrn.tp.jpa.servicios.*;
import ar.unrn.tp.modelo.ServicioTarjetaDeCredito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Main {
    public static void main(String[] args) {

        /*try {
            MarcaService marcaService = new MarcaServiceImpl();
            marcaService.crearMarca("ACME");

            ClienteService clienteService = new ClienteServiceImpl();
            clienteService.crearCliente("Santiago2", "Fernández San Juan", "43217767", "santi@gmail.com");
            clienteService.crearCliente("Santiago2", "Fernández San Juan", "43217768", "santi@gmail.com");
           clienteService.agregarTarjeta((long) 2,"2920-5121-2524","MemeCard");
            CategoriaService categoriaService = new CategoriaServiceImpl();

            categoriaService.crearCategoria("DEPORTIVA");
            categoriaService.crearCategoria("EXPLOSIVOS");

            ProductoService productoService = new ProductoServiceImpl();

            productoService.crearProducto("1251", "Es dinamita", 250.0, (long)5, (long)1);
            productoService.crearProducto("1252", "Son minas terrestres antipersonales", 500.0, (long)5, (long)1);

            DescuentoService descuentoService = new DescuentoServiceImpl();

            descuentoService.crearDescuentoSobreTotal("MemeCard",LocalDate.now().minusDays(5),LocalDate.now().plusDays(5),5);

            descuentoService.crearDescuento("ACME",LocalDate.now().minusDays(5),LocalDate.now().plusDays(5),5);

            VentaService ventaService = new VentaServiceImpl(new ServicioTarjetaDeCredito());
            ventaService.realizarVenta((long) 2, List.of((long) 7,(long) 7), (long)4);

            /*Marca comarca = new Marca("Comarca");
            Marca acme = new Marca("Acme");

            ProductoDisponible dinamita = new ProductoDisponible("1251", "Es dinamita", EXPLOSIVOS, acme, 250.0);
            ProductoDisponible ropa = new ProductoDisponible("1350", "Es ropa deportiva", DEPORTIVA, comarca, 500.0);

            ArrayList<ProductoDisponible> productos = new ArrayList<ProductoDisponible>();
            productos.add(dinamita);
            productos.add(ropa);

            PromocionDeProducto promocionActivaAcme = new PromocionDeProducto(LocalDate.now().minusDays(5),LocalDate.now().plusDays(5),acme);

            ArrayList<Promocion> promociones = new ArrayList<Promocion>();
            promociones.add(promocionActivaAcme);

            ServicioTarjetaDeCredito tarjetaServicio = new ServicioTarjetaDeCredito();

            Carrito miCarrito = new Carrito(santi,productos,promociones,tarjetaServicio);

            System.out.println(miCarrito.calcularMontoConDescuentos(new TarjetaMemeCard()));

            Venta miCompra = miCarrito.realizarCompra(new TarjetaMemeCard());

        }catch (Exception e){
            e.printStackTrace();
        }
        */


    }



}