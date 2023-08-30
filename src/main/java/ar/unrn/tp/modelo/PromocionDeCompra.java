package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.FechaInvalidaExcepcion;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
public class PromocionDeCompra extends Promocion{
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    protected float porcentaje;

    private String marcaDeTarjeta;

    public PromocionDeCompra(LocalDate fechaInicio, LocalDate fechaFin, String marcaDeTarjeta) throws FechaInvalidaExcepcion {
        this.validarFechas(fechaInicio,fechaFin);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.marcaDeTarjeta = marcaDeTarjeta;
    }

    public PromocionDeCompra(LocalDate fechaInicio, LocalDate fechaFin, String marcaDeTarjeta, Float porcentaje) throws FechaInvalidaExcepcion {
        this.validarFechas(fechaInicio,fechaFin);
        this.marcaDeTarjeta = marcaDeTarjeta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.porcentaje = porcentaje;
    }

    private void validarFechas(LocalDate fechaInicio, LocalDate fechaFin) throws FechaInvalidaExcepcion {
        if(fechaInicio.isAfter(fechaFin)){
            throw new FechaInvalidaExcepcion();
        }
    }

    @Override
    protected Boolean esValida(){
        return this.fechaInicio.isBefore(LocalDate.now()) && this.fechaFin.isAfter(LocalDate.now());
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
