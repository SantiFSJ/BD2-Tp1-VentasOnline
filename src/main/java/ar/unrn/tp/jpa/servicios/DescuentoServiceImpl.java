package ar.unrn.tp.jpa.servicios;

import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.excepciones.FechaInvalidaExcepcion;
import ar.unrn.tp.modelo.PromocionDeCompra;
import ar.unrn.tp.modelo.PromocionDeProducto;

import java.time.LocalDate;

public class DescuentoServiceImpl extends GenericServiceImpl implements DescuentoService {
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
}
