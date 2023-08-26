package ar.unrn.tp.jpa.servicios;

import ar.unrn.tp.api.CategoriaService;
import ar.unrn.tp.excepciones.ClienteInvalidoExcepcion;
import ar.unrn.tp.excepciones.EmailInvalidoExcepcion;
import ar.unrn.tp.modelo.Categoria;

public class CategoriaServiceImpl extends GenericServiceImpl implements CategoriaService{

    @Override
    public void crearCategoria(String nombre) {
        inTransactionExecute((em) -> {
            em.persist(new Categoria(nombre));
        });
    }
}
