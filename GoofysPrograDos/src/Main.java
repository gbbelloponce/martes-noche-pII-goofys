import Implementacion.Estrategia_2;
import Interfaces.PilaTDA;

public class Main {
    public static void main(String[] args) {
        PilaTDA p1 = new Estrategia_2();
        probarPila("Estrategia 2", p1);
    }

    public static void probarPila(String nombre, PilaTDA pila) {

        System.out.println("=== " + nombre + " ===");

        pila.InicializarPila();

        pila.Apilar("fi.uba.ar");
        pila.Apilar("campus.utn.edu.ar");

        pila.Apilar("stackoverflow.com");
        System.out.println("Tope luego de apilar fi.uba.ar, campus.utn.edu.ar, stackoverflow.com: " + pila.Tope());

        pila.Desapilar();

        System.out.println("Tope luego de desapilar una vez: " + pila.Tope());

        pila.Desapilar();

        System.out.println("Tope luego de desapilar otra vez: " + pila.Tope());

        pila.Desapilar();

        System.out.println("¿Pila vacía?: " + pila.PilaVacia());
        System.out.println();
    }
}
