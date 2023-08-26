package ar.unrn.tp.modelo;

import javax.persistence.Entity;

@Entity
public class Marca extends ModeloGenerico {
    private String nombre;

    public Marca(String nombre) {
        this.nombre = nombre;
    }

}
