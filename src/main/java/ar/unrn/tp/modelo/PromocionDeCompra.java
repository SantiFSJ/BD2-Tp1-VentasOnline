package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.FechaInvalidaExcepcion;

import java.time.LocalDate;
import java.util.List;

public class PromocionDeCompra extends Promocion{
    TarjetaDeCredito tarjetaDeCredito;

    public PromocionDeCompra(LocalDate fechaInicio, LocalDate fechaFin, TarjetaDeCredito tarjeta) throws FechaInvalidaExcepcion {
        super(fechaInicio, fechaFin);
        this.tarjetaDeCredito = tarjeta;
    }

    @Override
    public double aplicarDescuento(List<ProductoDisponible> productos, TarjetaDeCredito tarjeta) {
        double montoADescontar = 0.0;
        if(this.fechaInicio.isBefore(LocalDate.now()) && this.fechaFin.isAfter(LocalDate.now())){
            if(this.tarjetaDeCredito.equals(tarjeta)){
                for(ProductoDisponible producto: productos){
                    montoADescontar += producto.precio();
                }
            }
        }
        return montoADescontar * 0.08;
    }
}
