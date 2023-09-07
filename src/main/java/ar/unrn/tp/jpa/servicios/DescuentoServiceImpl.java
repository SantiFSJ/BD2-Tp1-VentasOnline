package ar.unrn.tp.jpa.servicios;

import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.excepciones.FechaInvalidaExcepcion;
import ar.unrn.tp.modelo.Promocion;
import ar.unrn.tp.modelo.PromocionDeCompra;
import ar.unrn.tp.modelo.PromocionDeProducto;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DescuentoServiceImpl extends GenericServiceImpl implements DescuentoService {

    public DescuentoServiceImpl(EntityManagerFactory emf){
        super(emf);
    }

    @Override
    public void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde, LocalDate fechaHasta, float porcentaje) {
        inTransactionExecute((em) -> {
            try {
                em.persist(new PromocionDeCompra(fechaDesde,fechaHasta,marcaTarjeta,porcentaje));
            } catch (FechaInvalidaExcepcion e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void crearDescuento(String marcaProducto, LocalDate fechaDesde, LocalDate fechaHasta, float porcentaje) {
        inTransactionExecute((em) -> {
            try {
                em.persist(new PromocionDeProducto(fechaDesde,fechaHasta,marcaProducto,porcentaje));
            } catch (FechaInvalidaExcepcion e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public List<Promocion> recuperarDescuentos() {
        List<Promocion> descuentos = new ArrayList<>();
        inTransactionExecute((em) -> {
            List<Promocion> promocionesDeCompra = em.createQuery("SELECT p FROM PromocionDeCompra p", Promocion.class).getResultList();
            List<Promocion> promocionesDeProducto = em.createQuery("SELECT p FROM PromocionDeProducto p", Promocion.class).getResultList();

            if (!promocionesDeCompra.isEmpty()) {
                descuentos.addAll(promocionesDeCompra);
            }

            if (!promocionesDeProducto.isEmpty()) {
                descuentos.addAll(promocionesDeProducto);
            }
        });
        return descuentos;

    }


}
