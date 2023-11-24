/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.Modelo;

import Controlador.TDA.Lista.Exepciones.EmptyList;
import Controlador.TDA.Lista.ListaDinamica;
import Modelo.Pasajero;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Victor
 */
public class ModeloTablaBoleto extends AbstractTableModel {

    private ListaDinamica<Pasajero> listaPasajero;

    public ListaDinamica<Pasajero> getListaPasajero() {
        return listaPasajero;
    }

    public void setListaPasajero(ListaDinamica<Pasajero> listaPasajero) {
        this.listaPasajero = listaPasajero;
    }
    
    @Override
    public int getRowCount() {
        return listaPasajero.getLongitud();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int Fila, int Columna) {

        try {
            Pasajero p = listaPasajero.getInfo(Fila);

            switch (Columna) {
                case 0:
                    return (p != null) ? p.getIdPasajero() : "";
                case 1:
                    return (p != null) ? p.getNumeroIdentificacion() : "";
                case 2:
                    return (p != null) ? p.getNombre() : "";
                case 3:
                    return (p != null) ? p.getApellido() : "";
                case 4:
                    return (p != null) ? p.getDatosBoleto().getCiudadOrigen() : "";
                case 5:
                    return (p != null) ? p.getDatosBoleto().getCiudadDestino() : "";
                case 6:
                    return (p != null) ? p.getDatosBoleto().getCantidadBoletos() : "";
                case 7:
                    return (p != null) ? p.getDatosBoleto().getFechaViaje() : "";
                case 8:
                    return (p != null) ? p.getDatosBoleto().getHoraViaje() : "";
                case 9:
                    return (p != null) ? p.getDatosBoleto().getTotalPagar() : "";

                default:
                    return null;

            }
        } catch (EmptyList ex) {

        } catch (IndexOutOfBoundsException ex) {

        }
        return listaPasajero;
    }


    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID VENTA";
            case 1:
                return "NUMERO IDENTIFICACION";
            case 2:
                return "NOMBRE";
            case 3:
                return "APELLIDO";
            case 4:
                return "CIUDAD ORIGEN";
            case 5:
                return "CIUDAD DESTINO";
            case 6:
                return "CANTIDAD BOLETOS";
            case 7:
                return "FECHA VIAJE";
            case 8:
                return "HORA VIAJE";
            case 9:
                return "TOTAL A PAGAR";

            default:
                return null;
        }
    }

    public double SumarTotalGanancias(int columna) {
        double Ganancias = 0.0;

        for (int fila = 0; fila < getRowCount(); fila++) {
            try {
                Object Calculo = getValueAt(fila, columna);

                if (Calculo instanceof Number) {
                    Ganancias += ((Number) Calculo).doubleValue();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Ganancias;
    }
}
