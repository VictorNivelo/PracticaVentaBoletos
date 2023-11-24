/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Dao;

import Controlador.Dao.DaoImplement;
import Controlador.TDA.Lista.ListaDinamica;
import Modelo.Pasajero;

/**
 *
 * @author Victor
 */
public class DaoPasajero extends DaoImplement<Pasajero>{
    private ListaDinamica<Pasajero> PasajerosLista = new ListaDinamica<>();
    private Pasajero pasajero;
    
    public DaoPasajero(){
        super (Pasajero.class);
    }

    public ListaDinamica<Pasajero> getPasajerosLista() {
        PasajerosLista = all();
        return PasajerosLista;
    }

    public void setPasajerosLista(ListaDinamica<Pasajero> PasajerosLista) {
        this.PasajerosLista = PasajerosLista;
    }

    public Pasajero getPasajero() {
        if(pasajero == null){
            pasajero = new Pasajero();
        }
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }
    
    public Boolean Persist(){
        pasajero.setIdPasajero(all().getLongitud()+1);
        return Persist(pasajero);
    }
}
