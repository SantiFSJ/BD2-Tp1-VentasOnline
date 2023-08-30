package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.FechaInvalidaExcepcion;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class PromocionDeProducto extends Promocion{

    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    protected float porcentaje;
    private String marcaDeProducto;
    public PromocionDeProducto(LocalDate fechaInicio, LocalDate fechaFin, String marcaDeProducto, float porcentaje) throws FechaInvalidaExcepcion {
        this.validarFechas(fechaInicio,fechaFin);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.porcentaje = porcentaje;
        this.marcaDeProducto = marcaDeProducto;
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
    public double aplicarDescuento(List<ProductoDisponible> productos, TarjetaDeCredito tarjeta){
        double montoADescontar = 0.0;
        if(this.esValida()){
            for(ProductoDisponible producto: productos){
                if(producto.esDeMarca(this.marcaDeProducto))
                    montoADescontar += producto.precio() * 0.05;
            }
        }
        return montoADescontar;

    }

}
