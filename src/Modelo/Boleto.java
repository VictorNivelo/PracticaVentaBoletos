/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Victor
 */
public class Boleto {
    private Integer idBoleto;
    private String CiudadOrigen;
    private String CiudadDestino;
    private Integer CantidadBoletos;
    private String FechaViaje;
    private String HoraViaje;
    private Float TotalPagar;

    public Boleto() {
        
    }

    public Boleto(Integer idBoleto, String CiudadOrigen, String CiudadDestino, Integer CantidadBoletos, String FechaViaje, String HoraViaje, Float TotalPagar) {
        this.idBoleto = idBoleto;
        this.CiudadOrigen = CiudadOrigen;
        this.CiudadDestino = CiudadDestino;
        this.CantidadBoletos = CantidadBoletos;
        this.FechaViaje = FechaViaje;
        this.HoraViaje = HoraViaje;
        this.TotalPagar = TotalPagar;
    }

    public Integer getIdBoleto() {
        return idBoleto;
    }

    public void setIdBoleto(Integer idBoleto) {
        this.idBoleto = idBoleto;
    }

    public String getCiudadOrigen() {
        return CiudadOrigen;
    }

    public void setCiudadOrigen(String CiudadOrigen) {
        this.CiudadOrigen = CiudadOrigen;
    }

    public String getCiudadDestino() {
        return CiudadDestino;
    }

    public void setCiudadDestino(String CiudadDestino) {
        this.CiudadDestino = CiudadDestino;
    }

    public Integer getCantidadBoletos() {
        return CantidadBoletos;
    }

    public void setCantidadBoletos(Integer CantidadBoletos) {
        this.CantidadBoletos = CantidadBoletos;
    }

    public String getFechaViaje() {
        return FechaViaje;
    }

    public void setFechaViaje(String FechaViaje) {
        this.FechaViaje = FechaViaje;
    }

    public String getHoraViaje() {
        return HoraViaje;
    }

    public void setHoraViaje(String HoraViaje) {
        this.HoraViaje = HoraViaje;
    }

    public Float getTotalPagar() {
        return TotalPagar;
    }

    public void setTotalPagar(Float TotalPagar) {
        this.TotalPagar = TotalPagar;
    }

    @Override
    public String toString() {
        return "Boleto{" + "idBoleto=" + idBoleto + ", CiudadOrigen=" + CiudadOrigen + ", CiudadDestino=" + CiudadDestino + ", CantidadBoletos=" + CantidadBoletos + ", FechaViaje=" + FechaViaje + ", HoraViaje=" + HoraViaje + ", TotalPagar=" + TotalPagar + '}';
    }
    
}
