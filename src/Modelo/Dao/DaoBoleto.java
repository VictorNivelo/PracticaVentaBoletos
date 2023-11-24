package Modelo.Dao;

import Controlador.Dao.DaoImplement;
import Controlador.TDA.Lista.ListaDinamica;
import Modelo.Boleto;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Victor
 */
public class DaoBoleto extends DaoImplement<Boleto>{
    private ListaDinamica<Boleto> BoletosLista = new ListaDinamica<>();
    private Boleto boletos;
    
    public DaoBoleto(){
        super (Boleto.class);
    }

    public ListaDinamica<Boleto> getBoletosLista() {
        BoletosLista = all();
        return BoletosLista;
    }

    public void setBoletosLista(ListaDinamica<Boleto> BoletosLista) {
        this.BoletosLista = BoletosLista;
    }

    public Boleto getBoletos() {
        if(boletos == null){
            boletos = new Boleto();
        }
        return boletos;
    }

    public void setBoletos(Boleto boletos) {
        this.boletos = boletos;
    }
    
    public Boolean Persist(){
        boletos.setIdBoleto(all().getLongitud()+1);
        return Persist(boletos);
    }
}
