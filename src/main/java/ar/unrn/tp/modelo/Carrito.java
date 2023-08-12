package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.ProductoInvalidoExcepcion;
import ar.unrn.tp.excepciones.TarjetaInvalidaExcepcion;

import java.time.LocalDateTime;
import java.util.List;

public class Carrito {
    private Cliente cliente;
    private List<ProductoDisponible> productos;
    private List<Promocion> promociones;
    private ServicioValidadorDeTarjetas servicioTarjetas;

    public Carrito(Cliente cliente, List<ProductoDisponible> productos, List<Promocion> promociones, ServicioValidadorDeTarjetas servicioTarjetas) {
        this.cliente = cliente;
        this.productos = productos;
        this.promociones = promociones;
        this.servicioTarjetas = servicioTarjetas;
    }

    private double calcularMontoTotal(){
        double sumaPrecios = 0.0;
        for(ProductoDisponible producto: productos){
            sumaPrecios += producto.precio();
        }
        return sumaPrecios;
    }

    public double calcularMontoConDescuentos(TarjetaDeCredito tarjeta){
        double montoTotal = this.calcularMontoTotal();
        for(Promocion promocion: this.promociones){
            montoTotal -= promocion.aplicarDescuento(this.productos,tarjeta);
        }
        return montoTotal;
    }
    public Venta realizarCompra(TarjetaDeCredito tarjeta) throws TarjetaInvalidaExcepcion, ProductoInvalidoExcepcion {
        if(this.servicioTarjetas.validar(tarjeta))
            return new Venta(LocalDateTime.now(),this.cliente,this.productos,calcularMontoConDescuentos(tarjeta));
        else
            throw new TarjetaInvalidaExcepcion();

    }
}
