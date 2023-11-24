/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Victor
 */
public class Pasajero {
    private Integer idPasajero;
    private String NumeroIdentificacion;
    private String Nombre;
    private String Apellido;
    private Boleto datosBoleto;
    
    public Pasajero() {
        
    }

    public Pasajero(Integer idPasajero, String NumeroIdentificacion, String Nombre, String Apellido, Boleto datosBoleto) {
        this.idPasajero = idPasajero;
        this.NumeroIdentificacion = NumeroIdentificacion;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.datosBoleto = datosBoleto;
    }

    public Integer getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(Integer idPasajero) {
        this.idPasajero = idPasajero;
    }

    public String getNumeroIdentificacion() {
        return NumeroIdentificacion;
    }

    public void setNumeroIdentificacion(String NumeroIdentificacion) {
        this.NumeroIdentificacion = NumeroIdentificacion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public Boleto getDatosBoleto() {
        return datosBoleto;
    }

    public void setDatosBoleto(Boleto datosBoleto) {
        this.datosBoleto = datosBoleto;
    }

    @Override
    public String toString() {
        return "Pasajero{" + "idPasajero=" + idPasajero + ", NumeroIdentificacion=" + NumeroIdentificacion + ", Nombre=" + Nombre + ", Apellido=" + Apellido + ", datosBoleto=" + datosBoleto + '}';
    }
    
}
