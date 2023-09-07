package ar.unrn.tp.jpa.servicios;

import ar.unrn.tp.api.MarcaService;
import ar.unrn.tp.excepciones.ClienteInvalidoExcepcion;
import ar.unrn.tp.excepciones.EmailInvalidoExcepcion;
import ar.unrn.tp.modelo.Marca;

import javax.persistence.EntityManagerFactory;

public class MarcaServiceImpl extends GenericServiceImpl implements MarcaService {

    public MarcaServiceImpl(EntityManagerFactory emf){
        super(emf);
    }

    @Override
    public void crearMarca(String nombre) {
        inTransactionExecute((em) -> {
            try {
                em.persist(new Marca(nombre));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
