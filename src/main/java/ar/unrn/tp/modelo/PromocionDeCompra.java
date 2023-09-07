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

    private String marcaDeTarjeta(){
        return this.marcaDeTarjeta;
    }

    private LocalDate fechaDeInicio(){
        return this.fechaInicio;
    }

    private LocalDate fechaDeFin(){
        return this.fechaFin;
    }

    private float porcentaje(){
        return this.porcentaje;
    }

    public boolean sosIgual(PromocionDeCompra promocion){
        return this.marcaDeTarjeta.equals(promocion.marcaDeTarjeta()) &&
                this.fechaInicio.equals(promocion.fechaDeInicio()) &&
                this.fechaFin.equals((promocion.fechaDeFin())) &&
                this.porcentaje == promocion.porcentaje();
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
