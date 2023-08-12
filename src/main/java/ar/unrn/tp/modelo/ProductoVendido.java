package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.ProductoInvalidoExcepcion;

public class ProductoVendido extends ProductoDisponible{

    public ProductoVendido(ProductoDisponible producto) throws ProductoInvalidoExcepcion {
        super(producto.codigo(),
                producto.descripcion(),
                producto.categoria(),
                producto.getMarca(),
                producto.precio());
    }
}
