package org.example;

import ar.unrn.tp.excepciones.FechaInvalidaExcepcion;
import ar.unrn.tp.modelo.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static ar.unrn.tp.modelo.Categoria.DEPORTIVA;
import static ar.unrn.tp.modelo.Categoria.EXPLOSIVOS;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<TarjetaDeCredito> tarjetas = new ArrayList<TarjetaDeCredito>();
            tarjetas.add(new TarjetaMemeCard());
            Cliente santi = new Cliente("Santiago", "Fernández San Juan", "43217767", "santi@gmail.com", tarjetas);

            Marca comarca = new Marca("Comarca");
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
            System.out.println(e.getMessage());
        }



    }



}