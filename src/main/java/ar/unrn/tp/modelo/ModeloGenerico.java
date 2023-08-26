package ar.unrn.tp.modelo;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
public abstract class ModeloGenerico {
    @Id
    @GeneratedValue
    private Long id;
}
