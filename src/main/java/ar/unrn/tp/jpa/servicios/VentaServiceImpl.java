package ar.unrn.tp.jpa.servicios;

import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.excepciones.FechaInvalidaExcepcion;
import ar.unrn.tp.excepciones.ProductoInvalidoExcepcion;
import ar.unrn.tp.excepciones.TarjetaInvalidaExcepcion;
import ar.unrn.tp.modelo.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class VentaServiceImpl extends GenericServiceImpl implements VentaService {

    private ServicioValidadorDeTarjetas servicioValidadorTarjetas;

    private DescuentoService descuentoService;

    public VentaServiceImpl(ServicioValidadorDeTarjetas servicioValidadorTarjetas, DescuentoService descuentoService, EntityManagerFactory emf){
        super(emf);
        this.descuentoService = descuentoService;
        this.servicioValidadorTarjetas = servicioValidadorTarjetas;
    }
    @Override
    public void realizarVenta(Long idCliente, List<Long> productos, Long idTarjeta) {
        inTransactionExecute((em) -> {
            List<Promocion> promociones = this.descuentoService.recuperarDescuentos();
            List<ProductoDisponible> listaProductos = em.createQuery("SELECT o FROM ProductoDisponible o WHERE o.id IN :ids", ProductoDisponible.class).setParameter("ids", productos).getResultList();
            try {
                Venta venta = new Carrito(em.getReference(Cliente.class,idCliente),listaProductos,promociones,servicioValidadorTarjetas).realizarCompra(em.getReference(TarjetaDeCredito.class,idTarjeta));
                em.persist(venta);
            } catch (TarjetaInvalidaExcepcion | ProductoInvalidoExcepcion e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Override
    public float calcularMonto(List<Long> productos, Long idTarjeta) {
        AtomicReference<Float> monto = new AtomicReference<>(0F);
        inTransactionExecute((em) -> {
            TarjetaDeCredito tarjetaCredito = em.find(TarjetaDeCredito.class, idTarjeta);
            List<ProductoDisponible> listaProductos = em.createQuery("SELECT o FROM ProductoDisponible o WHERE o.id IN :ids", ProductoDisponible.class).setParameter("ids", productos).getResultList();
            List<Promocion> promociones = this.descuentoService.recuperarDescuentos();
            monto.set((float) new Carrito(listaProductos,promociones,servicioValidadorTarjetas).calcularMontoConDescuentos(em.getReference(TarjetaDeCredito.class,idTarjeta)));
        });
        return monto.get();

    }

    @Override
    public List<Venta> ventas() {
        List<Venta> ventas = new ArrayList<>();
        inTransactionExecute((em) -> {
            ventas.addAll(em.createQuery("SELECT v FROM Venta v", Venta.class).getResultList());
        });
        return ventas;
    }
}
