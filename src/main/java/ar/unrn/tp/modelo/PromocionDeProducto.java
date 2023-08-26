package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.FechaInvalidaExcepcion;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class PromocionDeProducto extends Promocion{
    private String marcaDeProducto;
    public PromocionDeProducto(LocalDate fechaInicio, LocalDate fechaFin, String marcaDeProducto, float porcentaje) throws FechaInvalidaExcepcion {
        super(fechaInicio,fechaFin,porcentaje);
        this.marcaDeProducto = marcaDeProducto;
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
