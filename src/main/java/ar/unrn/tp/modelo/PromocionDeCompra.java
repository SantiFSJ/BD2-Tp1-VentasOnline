package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.FechaInvalidaExcepcion;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class PromocionDeCompra extends Promocion{
    private String marcaDeTarjeta;

    public PromocionDeCompra(LocalDate fechaInicio, LocalDate fechaFin, String marcaDeTarjeta) throws FechaInvalidaExcepcion {
        super(fechaInicio, fechaFin);
        this.marcaDeTarjeta = marcaDeTarjeta;
    }

    public PromocionDeCompra(LocalDate fechaInicio, LocalDate fechaFin, String marcaDeTarjeta, Float porcentaje) throws FechaInvalidaExcepcion {
        super(fechaInicio, fechaFin, porcentaje);
        this.marcaDeTarjeta = marcaDeTarjeta;
        this.porcentaje = porcentaje;
    }

    @Override
    public double aplicarDescuento(List<ProductoDisponible> productos, TarjetaDeCredito tarjeta) {
        double montoADescontar = 0.0;
        if(this.esValida() && tarjeta.esDeMarca(this.marcaDeTarjeta)){
            for(ProductoDisponible producto: productos){
                montoADescontar += producto.precio();
            }
        }
        return montoADescontar * 0.08;
    }
}
