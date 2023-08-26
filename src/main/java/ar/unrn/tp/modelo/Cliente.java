package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.ClienteInvalidoExcepcion;
import ar.unrn.tp.excepciones.EmailInvalidoExcepcion;
import lombok.NoArgsConstructor;

import javax.jdo.annotations.Unique;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class Cliente extends ModeloGenerico {
    private String nombre;
    private String apellido;
    @Unique
    private String dni;
    private String email;

    @OneToMany(cascade={CascadeType.ALL})
    private List<TarjetaDeCredito> tarjetasDeCredito;


    public Cliente(String nombre, String apellido, String dni, String email) throws ClienteInvalidoExcepcion, EmailInvalidoExcepcion {
        this.validarCliente(nombre,apellido,dni,email);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.tarjetasDeCredito = new ArrayList<TarjetaDeCredito>();

    }
    public Cliente(String nombre, String apellido, String dni, String email, List<TarjetaDeCredito> tarjetasDeCredito) throws ClienteInvalidoExcepcion, EmailInvalidoExcepcion {
        this.validarCliente(nombre,apellido,dni,email);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.tarjetasDeCredito = tarjetasDeCredito;
    }

    public void añadirTarjeta(TarjetaDeCredito tarjetaDeCredito){
        this.tarjetasDeCredito.add(tarjetaDeCredito);
    }

    private void validarCliente(String nombre, String apellido, String dni, String email) throws ClienteInvalidoExcepcion, EmailInvalidoExcepcion {
        if (nombre == null
                || nombre.equals("")
                || apellido == null
                || apellido.equals("")
                || dni == null
                || dni.equals("")
                || email == null
                || email.equals("")
        ) {
            throw new ClienteInvalidoExcepcion();
        }else{
            if(!this.validarEmail(email)){
                throw new EmailInvalidoExcepcion();
            }
        }

    }

    private boolean validarEmail(String email){
        Pattern patronCorreo = Pattern.compile("^(.+)@(\\S+)$");
        Matcher comparador = patronCorreo.matcher(email);
        return comparador.matches();
    }
}
