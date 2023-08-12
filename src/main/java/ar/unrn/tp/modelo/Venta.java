package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.ProductoInvalidoExcepcion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    LocalDateTime fechaYHora;
    Cliente cliente;
    List<ProductoVendido> productoVendidos;
    double montoTotal;

    public Venta(LocalDateTime fechaYHora, Cliente cliente, List<ProductoDisponible> productoVendidos, double montoTotal) throws ProductoInvalidoExcepcion {
        this.fechaYHora = fechaYHora;
        this.cliente = cliente;
        this.productoVendidos = new ArrayList<ProductoVendido>();
        this.montoTotal = montoTotal;
        this.parseProductos(productoVendidos);
    }

    public double montoTotal(){
        return this.montoTotal;
    }

    public int cantidadDeProductos(){
        return this.productoVendidos.size();
    }

    private void parseProductos(List<ProductoDisponible> productoVendidos) throws ProductoInvalidoExcepcion {
        for(ProductoDisponible producto: productoVendidos){
            this.productoVendidos.add(new ProductoVendido(producto));
        }
    }
}
