package ar.unrn.tp.api;

import java.util.List;

public interface ProductoService {
    //validar que sea una categoría existente y que codigo no se repita
    void crearProducto(String codigo, String descripcion, double precio, Long idCategoría, Long idMarca);


    //validar que sea un producto existente
    void modificarProducto(Long idProducto);
    //Devuelve todos los productos
    List listarProductos();

}
