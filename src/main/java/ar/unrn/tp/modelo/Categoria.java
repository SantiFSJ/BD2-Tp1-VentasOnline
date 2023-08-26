package ar.unrn.tp.modelo;

import javax.persistence.Entity;

@Entity
public class Categoria extends ModeloGenerico{
    private String nombre;

    public Categoria(String nombre){
        this.nombre = nombre;
    }

}
