package Implementacion;

import Interfaces.PilaTDA;

public class Estrategia_2 implements PilaTDA {
    private Nodo tope;

    public void InicializarPila() {
        this.tope = null;
    }

    public void Apilar(String x) {
        Nodo nuevo = new Nodo(x);
        nuevo.siguiente = this.tope;
        this.tope = nuevo;
    }

    public void Desapilar() {
        if (!this.PilaVacia()) {
            this.tope = this.tope.siguiente;
        }

    }

    public String Tope() {
        return this.tope.dato;
    }

    public boolean PilaVacia() {
        return this.tope == null;
    }
}
