package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.FechaInvalidaExcepcion;

import java.time.LocalDate;
import java.util.List;

public abstract class Promocion {
    LocalDate fechaInicio;
    LocalDate fechaFin;

    public Promocion(LocalDate fechaInicio, LocalDate fechaFin) throws FechaInvalidaExcepcion {
        this.validarFechas(fechaInicio,fechaFin);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    private void validarFechas(LocalDate fechaInicio, LocalDate fechaFin) throws FechaInvalidaExcepcion {
        if(fechaInicio.isAfter(fechaFin)){
            throw new FechaInvalidaExcepcion();
        }
    }

    public double aplicarDescuento(List<ProductoDisponible> productos, TarjetaDeCredito tarjeta){
        return 0.0;
    }
}
