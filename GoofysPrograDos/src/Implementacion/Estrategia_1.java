package Implementacion;

import Interfaces.PilaTDA;

public class Estrategia_1 implements PilaTDA {
    private static final int MAX = 100;
    private String[] datos;
    private int cantidad;

    public void InicializarPila() {
        this.datos = new String[100];
        this.cantidad = 0;
    }

    public void Apilar(String x) {
        if (this.cantidad < 100) {
            this.datos[this.cantidad] = x;
            ++this.cantidad;
        }

    }

    public void Desapilar() {
        if (!this.PilaVacia()) {
            --this.cantidad;
        }

    }

    public String Tope() {
        return this.datos[this.cantidad - 1];
    }

    public boolean PilaVacia() {
        return this.cantidad == 0;
    }
}
