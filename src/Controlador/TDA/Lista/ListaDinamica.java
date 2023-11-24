/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.TDA.Lista;

import Controlador.TDA.Lista.Exepciones.EmptyList;

/**
 *
 * @author Victor
 * @param <E>
 */
public class ListaDinamica<E> {

    private Nodo<E> cabezera;
    private Nodo<E> ultimo;
    private Integer Longitud;

    public ListaDinamica() {
        cabezera = null;
        ultimo = null;
        Longitud = 0;
    }

    public Boolean EstaVacio() {
        return (cabezera == null || Longitud == 0);
    }

    public void Agregar(E info) {
        AgregarFinal(info);
    }

    public void AgregarCabeza(E info) {
        Nodo<E> Ayuda;
        if (EstaVacio()) {
            Ayuda = new Nodo<>(info);
            cabezera = Ayuda;
            ultimo = Ayuda;
            Longitud++;
        } else {
            Nodo<E> CabezaAyuda = cabezera;
            Ayuda = new Nodo<>(info, CabezaAyuda);
            cabezera = Ayuda;
            Longitud++;
        }
    }

    public void AgregarFinal(E info) {
        Nodo<E> Ayuda;
        if (EstaVacio()) {
            AgregarCabeza(info);
        } else {
            Ayuda = new Nodo<>(info, null);
            ultimo.setSiguiente(Ayuda);
            ultimo = Ayuda;
            Longitud++;
        }
    }

    private E getPrimero() throws EmptyList {
        if (EstaVacio()) {
            throw new EmptyList("La lista esta vacia");
        }
        return cabezera.getInfo();
    }

    private E getFinal() throws EmptyList {
        if (EstaVacio()) {
            throw new EmptyList("La lista esta vacia");
        }
        return ultimo.getInfo();
    }

    public E getInfo(Integer indice) throws EmptyList, IndexOutOfBoundsException {
        return ObtenerNodo(indice).getInfo();
    }

    private Nodo<E> ObtenerNodo(Integer indice) throws EmptyList, IndexOutOfBoundsException {
        if (EstaVacio()) {
            throw new EmptyList("La lista esta vacia");
        } else if (indice < 0 || indice.intValue() == Longitud) {
            throw new IndexOutOfBoundsException("Fuera de nodo");
        } else if (indice == 0) {
            return cabezera;
        } else if (indice == (Longitud - 1)) {
            return ultimo;
        } else {
            Nodo<E> Buscar = cabezera;
            int contador = 0;
            while (contador < indice) {
                contador++;
                Buscar = Buscar.getSiguiente();
            }
            return Buscar;
        }
    }

    public E eliminar(Integer pos) throws EmptyList, IndexOutOfBoundsException {
        if (!EstaVacio()) {
            E dato = null;
            if (pos >= 0 && pos < Longitud) {
                if (pos == 0) {
                    dato = cabezera.getInfo();
                    cabezera = cabezera.getSiguiente();
                    Longitud--;
                } else {
                    Nodo<E> aux = cabezera;

                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }

                    dato = aux.getInfo();
                    Nodo<E> proximo = aux.getSiguiente();
                    aux.setSiguiente(proximo.getSiguiente());
                    Longitud--;
                }
            } else {
                throw new IndexOutOfBoundsException();
            }
            return dato;
        } else {
            throw new EmptyList();
        }
    }

    public E obtener(Integer pos) throws EmptyList, IndexOutOfBoundsException {

        if (!EstaVacio()) {
            E dato = null;
            if (pos >= 0 && pos < Longitud) {
                if (pos == 0) {
                    dato = cabezera.getInfo();
                } else {
                    Nodo<E> aux = cabezera;

                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }

                    dato = aux.getInfo();
                }
            } else {
                throw new IndexOutOfBoundsException();
            }

            return dato;
        } else {
            throw new EmptyList();
        }
    }

    public void ModificarInfo(E dato, Integer pos) throws IndexOutOfBoundsException {
        if (EstaVacio()) {
            Agregar(dato);
        } else if (pos >= 0 && pos < Longitud) {
            if (pos == 0) {
                cabezera.setInfo(dato);
            } else {
                Nodo<E> aux = cabezera;

                for (int i = 0; i < pos; i++) {
                    aux = aux.getSiguiente();
                }

                aux.setInfo(dato);
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public ListaDinamica<E> ObtenerLista() {
        ListaDinamica<E> lista = new ListaDinamica<>();
        Nodo<E> actual = cabezera;

        while (actual != null) {
            lista.AgregarFinal(actual.getInfo());
            actual = actual.getSiguiente();
        }

        return lista;
    }

    @Override
    public String toString() {
        StringBuilder StringB = new StringBuilder("Datos de lista \n");
        try {
            EstaVacio();

            Nodo<E> ayuda = cabezera;

            while (ayuda != null) {
                StringB.append(ayuda.getInfo().toString());
                ayuda = ayuda.getSiguiente();
            }
        } catch (Exception e) {
            StringB.append(e.getMessage());
        }
        return StringB.toString();
    }

    public Nodo<E> getCabezera() {
        return cabezera;
    }

    public void setCabezera(Nodo<E> cabezera) {
        this.cabezera = cabezera;
    }

    public Nodo<E> getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo<E> ultimo) {
        this.ultimo = ultimo;
    }

    public Integer getLongitud() {
        return Longitud;
    }

    public void setLongitud(Integer Longitud) {
        this.Longitud = Longitud;
    }
}
