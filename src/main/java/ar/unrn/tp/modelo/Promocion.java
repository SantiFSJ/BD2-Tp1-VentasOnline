package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.FechaInvalidaExcepcion;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.List;

@MappedSuperclass
public abstract class Promocion extends ModeloGenerico {
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    protected float porcentaje;
    public Promocion(LocalDate fechaInicio, LocalDate fechaFin,float porcentaje) throws FechaInvalidaExcepcion {
        this.validarFechas(fechaInicio,fechaFin);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.porcentaje = porcentaje;
    }
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

    protected Boolean esValida(){
        return this.fechaInicio.isBefore(LocalDate.now()) && this.fechaFin.isAfter(LocalDate.now());
    }

    public double aplicarDescuento(List<ProductoDisponible> productos, TarjetaDeCredito tarjeta){
        return 0.0;
    }
}
