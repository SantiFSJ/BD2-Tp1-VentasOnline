package ar.unrn.tp.modelo;

public class ServicioTarjetaDeCredito implements ServicioValidadorDeTarjetas{
    @Override
    public boolean validar(TarjetaDeCredito tarjeta) {
        return true;
    }
}
