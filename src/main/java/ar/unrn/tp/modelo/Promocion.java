package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.FechaInvalidaExcepcion;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.List;


public abstract class Promocion extends ModeloGenerico {
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    protected float porcentaje;



    protected Boolean esValida(){
        return null;
    }

    public double aplicarDescuento(List<ProductoDisponible> productos, TarjetaDeCredito tarjeta){
        return 0.0;
    }
}
