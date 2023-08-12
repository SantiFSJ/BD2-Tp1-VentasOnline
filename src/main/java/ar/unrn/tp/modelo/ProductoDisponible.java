package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.ProductoInvalidoExcepcion;

public class ProductoDisponible{
    private String codigo;
    private String descripcion;
    private Categoria categoria;
    private Marca marca;
    private Double precio;

    public ProductoDisponible(String codigo, String descripcion, Categoria categoria, Marca marca, Double precio) throws RuntimeException, ProductoInvalidoExcepcion {
        this.validarProducto(codigo,descripcion,categoria,marca,precio);
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.marca = marca;
        this.precio = precio;
    }
    private void validarProducto(String codigo, String descripcion, Categoria categoria, Marca marca, Double precio) throws RuntimeException, ProductoInvalidoExcepcion {
        if(codigo == null
                || codigo.equals("")
                || descripcion == null
                || descripcion.equals("")
                || categoria == null
                || marca == null
                || precio == null){
            throw new ProductoInvalidoExcepcion();
        }
    }
    public double precio(){
        return this.precio;
    }
    public String codigo() {
        return codigo;
    }
    public String descripcion() {
        return descripcion;
    }
    public Categoria categoria() {
        return categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public boolean esDeMarca(Marca marca){
        return this.marca.equals(marca);
    }
}
