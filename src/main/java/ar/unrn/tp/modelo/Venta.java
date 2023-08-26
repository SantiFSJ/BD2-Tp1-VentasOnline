package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.ProductoInvalidoExcepcion;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Venta extends ModeloGenerico{
    LocalDateTime fechaYHora;

    @Embedded
    Cliente cliente;
    List<ProductoVendido> productoVendidos;

    @Embedded
    TarjetaDeCredito tarjetaDeCredito;
    double montoTotal;

    public Venta(LocalDateTime fechaYHora, Cliente cliente, List<ProductoDisponible> productoVendidos, double montoTotal, TarjetaDeCredito tarjetaDeCredito) throws ProductoInvalidoExcepcion {
        this.fechaYHora = fechaYHora;
        this.cliente = cliente;
        this.productoVendidos = new ArrayList<ProductoVendido>();
        this.montoTotal = montoTotal;
        this.tarjetaDeCredito = tarjetaDeCredito;
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
