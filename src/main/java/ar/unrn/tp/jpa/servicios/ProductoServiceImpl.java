package ar.unrn.tp.jpa.servicios;

import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.excepciones.ClienteInvalidoExcepcion;
import ar.unrn.tp.excepciones.EmailInvalidoExcepcion;
import ar.unrn.tp.excepciones.ProductoInvalidoExcepcion;
import ar.unrn.tp.modelo.Categoria;
import ar.unrn.tp.modelo.Marca;
import ar.unrn.tp.modelo.ProductoDisponible;

import java.util.List;

public class ProductoServiceImpl extends GenericServiceImpl implements ProductoService {
    @Override
    public void crearProducto(String codigo, String descripcion, double precio, Long idCategoría, Long idMarca) {
        inTransactionExecute((em) -> {
            try {
                em.persist(new ProductoDisponible(codigo,descripcion
                        ,em.getReference(Categoria.class,idCategoría)
                        ,em.getReference(Marca.class,idMarca),precio));
            } catch (ProductoInvalidoExcepcion e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void modificarProducto(Long idProducto) {

    }

    @Override
    public List listarProductos() {
        return null;
    }
}
