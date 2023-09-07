package ar.unrn.tp.servicios;

import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.excepciones.FechaInvalidaExcepcion;
import ar.unrn.tp.jpa.servicios.DescuentoServiceImpl;
import ar.unrn.tp.modelo.PromocionDeCompra;
import ar.unrn.tp.modelo.PromocionDeProducto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class DescuentoServiceTest extends GenericServiceTest{

    @Test
    public void crearDescuentoSobreTotal(){
        DescuentoService descuentoService = new DescuentoServiceImpl(this.emf);
        descuentoService.crearDescuentoSobreTotal("Patagonia", LocalDate.now().minusDays(5),LocalDate.now().plusDays(5),5);
        try {
            PromocionDeCompra promocionResultado = new PromocionDeCompra(LocalDate.now().minusDays(5),LocalDate.now().plusDays(5),"Patagonia",5F);
            inTransactionExecute(
                    (em) -> {
                        PromocionDeCompra descuento = em.find(PromocionDeCompra.class, 1L);
                        assertTrue(descuento.sosIgual(promocionResultado));

                    }
            );
        } catch (FechaInvalidaExcepcion e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void crearDescuento(){
        DescuentoService descuentoService = new DescuentoServiceImpl(this.emf);
        descuentoService.crearDescuento("ACME", LocalDate.now().minusDays(5),LocalDate.now().plusDays(5),5);
        try {
            PromocionDeProducto promocionResultado = new PromocionDeProducto(LocalDate.now().minusDays(5),LocalDate.now().plusDays(5),"ACME",5F);
            inTransactionExecute(
                    (em) -> {
                        PromocionDeProducto descuento = em.find(PromocionDeProducto.class, 1L);
                        assertTrue(descuento.sosIgual(promocionResultado));

                    }
            );
        } catch (FechaInvalidaExcepcion e) {
            throw new RuntimeException(e);
        }
    }

}
