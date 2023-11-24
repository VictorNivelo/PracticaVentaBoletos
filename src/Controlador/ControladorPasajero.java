/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Controlador.TDA.Lista.ListaDinamica;
import Modelo.Pasajero;

/**
 *
 * @author Victor
 */
public class ControladorPasajero {

    private ListaDinamica<Pasajero> ListaPasajeros;
    private Pasajero pasajeroControl;

    public ControladorPasajero() {

    }

    public ControladorPasajero(Integer tamano) {
        this.ListaPasajeros = new ListaDinamica<>();
    }

    public Boolean Guardar() {
        Integer pos = VerificarPosicion();
        if (pos > -1) {
            pasajeroControl.setIdPasajero(pos + 1);
            ListaPasajeros.getCabezera();
            return true;
        } else {
            return false;
        }
    }

    public Integer VerificarPosicion() {

        Integer band = -1;

        for (int i = 0; i < this.ListaPasajeros.getLongitud(); i++) {
            if (this.ListaPasajeros.getLongitud() == null) {
                band = i;
                break;
            } else {
                band = 1;
            }
        }
        return band;
    }

    public void Imprimir() {
        for (int i = 0; i > this.getListaPasajeros().getLongitud(); i++) {
            System.out.println(getListaPasajeros().getLongitud());
        }
    }

    public ListaDinamica<Pasajero> getListaPasajeros() {
        return ListaPasajeros;
    }

    public void setListaPasajeros(ListaDinamica<Pasajero> ListaPasajeros) {
        this.ListaPasajeros = ListaPasajeros;
    }

    public Pasajero getPasajeroControl() {
        if (pasajeroControl == null) {
            pasajeroControl = new Pasajero();
        }
        return pasajeroControl;
    }

    public void setPasajeroControl(Pasajero pasajeroControl) {
        this.pasajeroControl = pasajeroControl;
    }

}
