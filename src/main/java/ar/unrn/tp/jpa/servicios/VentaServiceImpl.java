package ar.unrn.tp.jpa.servicios;

import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.excepciones.FechaInvalidaExcepcion;
import ar.unrn.tp.excepciones.ProductoInvalidoExcepcion;
import ar.unrn.tp.excepciones.TarjetaInvalidaExcepcion;
import ar.unrn.tp.modelo.*;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

public class VentaServiceImpl extends GenericServiceImpl implements VentaService {

    private ServicioValidadorDeTarjetas servicioValidadorTarjetas;
    public VentaServiceImpl(ServicioValidadorDeTarjetas servicioValidadorTarjetas){
        this.servicioValidadorTarjetas = servicioValidadorTarjetas;
    }
    @Override
    public void realizarVenta(Long idCliente, List<Long> productos, Long idTarjeta) {
        inTransactionExecute((em) -> {
            List<Promocion> promociones = em.createQuery("SELECT o FROM PromocionDeCompra o ").getResultList();
            promociones.addAll(em.createQuery("SELECT o FROM PromocionDeProducto o ").getResultList());
            //List<ProductoDisponible> listaProductos = em.createQuery("SELECT o FROM ProductoDisponible o WHERE o.id IN :ids", ProductoDisponible.class).setParameter("ids", productos).getResultList();

            //em.persist( new Carrito(em.getReference(Cliente.class,idCliente),listaProductos,promociones,servicioValidadorTarjetas).realizarCompra(em.getReference(TarjetaDeCredito.class,idTarjeta)));
        });
    }
    @Override
    public float calcularMonto(List<Long> productos, Long idTarjeta) {
        return 0;
    }

    @Override
    public List ventas() {
        return null;
    }
}
