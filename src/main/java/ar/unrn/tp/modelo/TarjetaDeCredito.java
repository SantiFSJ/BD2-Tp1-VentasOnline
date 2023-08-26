package ar.unrn.tp.modelo;

import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class TarjetaDeCredito extends ModeloGenerico{
    private String nombre;
    private String numero;

    public TarjetaDeCredito(String nombre, String numero) {
        this.nombre = nombre;
        this.numero = numero;
    }

    public Boolean esDeMarca(String marcaDeTarjeta){
        return this.nombre.equals(marcaDeTarjeta);
    }

}
