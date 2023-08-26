package ar.unrn.tp.jpa.servicios;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.excepciones.ClienteInvalidoExcepcion;
import ar.unrn.tp.excepciones.EmailInvalidoExcepcion;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.TarjetaDeCredito;

import java.util.List;


public class ClienteServiceImpl extends GenericServiceImpl implements ClienteService {

    @Override
    public void crearCliente(String nombre, String apellido, String dni, String email) {
        inTransactionExecute((em) -> {
            try {
                em.persist(new Cliente(nombre,apellido,dni,email));
            } catch (ClienteInvalidoExcepcion | EmailInvalidoExcepcion e) {
                throw new RuntimeException(e);
            }
        });

    }

    @Override
    public void modificarCliente(Long idCliente, String nombre) {

    }

    @Override
    public void agregarTarjeta(Long idCliente, String nro, String marca) {
        inTransactionExecute((em) -> {
            try {
                Cliente cliente = em.getReference(Cliente.class, idCliente);
                cliente.añadirTarjeta(new TarjetaDeCredito(marca, nro));
                em.persist(cliente);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public List listarTarjetas(Long idCliente) {
        return null;
    }
}
