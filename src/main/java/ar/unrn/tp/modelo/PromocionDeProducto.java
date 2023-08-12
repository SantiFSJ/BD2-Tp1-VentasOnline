package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.FechaInvalidaExcepcion;

import java.time.LocalDate;
import java.util.List;

public class PromocionDeProducto extends Promocion{
    Marca marca;
    public PromocionDeProducto(LocalDate fechaInicio, LocalDate fechaFin, Marca marca) throws FechaInvalidaExcepcion {
        super(fechaInicio,fechaFin);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.marca = marca;
    }

    @Override
    public double aplicarDescuento(List<ProductoDisponible> productos, TarjetaDeCredito tarjeta){
        double montoADescontar = 0.0;
        if(this.fechaInicio.isBefore(LocalDate.now()) && this.fechaFin.isAfter(LocalDate.now())){
            for(ProductoDisponible producto: productos){
                if(producto.esDeMarca(this.marca))
                    montoADescontar += producto.precio() * 0.05;
            }
        }
        return montoADescontar;

    }

}
