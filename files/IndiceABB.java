package ABB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

/**
 * EJERCICIO 9: ÍNDICE DE PALABRAS (DESAFÍO)
 * 
 * Implementa un índice de palabras que cuenta cuántas veces aparece cada palabra
 * en un texto, usando un Árbol Binario de Búsqueda.
 * 
 * Cada nodo del árbol almacena:
 * - Palabra (String)
 * - Cantidad de apariciones (int)
 * 
 * El resultado se muestra usando recorrido inorden (orden alfabético ascendente).
 * 
 * Ejemplo:
 *   Texto: "casa árbol casa río árbol árbol"
 *   Resultado (inorden):
 *     árbol -> 3
 *     casa -> 2
 *     río -> 1
 */
public class IndiceABB {
    private NodoPalabra raiz;

    /**
     * Nodo interno que almacena una palabra y su cantidad de apariciones.
     */
    private static class NodoPalabra {
        String palabra;
        int cantidad;
        NodoPalabra izquierdo;
        NodoPalabra derecho;

        public NodoPalabra(String palabra) {
            this.palabra = palabra;
            this.cantidad = 1;
        }

        public boolean esHoja() {
            return izquierdo == null && derecho == null;
        }
    }

    /**
     * Agrega una palabra al índice.
     * Si la palabra ya existe, incrementa su contador.
     * Si es nueva, la agrega con contador = 1.
     * 
     * COMPLEJIDAD: O(log n) en promedio, O(n) en peor caso
     */
    public void agregarPalabra(String palabra) {
        raiz = agregarPalabraRecursivo(raiz, palabra);
    }

    private NodoPalabra agregarPalabraRecursivo(NodoPalabra actual, String palabra) {
        // Caso base: crear nuevo nodo
        if (actual == null) {
            return new NodoPalabra(palabra);
        }

        // Comparamos las palabras alfabéticamente
        int comparacion = palabra.compareTo(actual.palabra);

        // La palabra ya existe, incrementamos el contador
        if (comparacion == 0) {
            actual.cantidad++;
        }
        // La palabra es menor alfabéticamente, va a la izquierda
        else if (comparacion < 0) {
            actual.izquierdo = agregarPalabraRecursivo(actual.izquierdo, palabra);
        }
        // La palabra es mayor alfabéticamente, va a la derecha
        else {
            actual.derecho = agregarPalabraRecursivo(actual.derecho, palabra);
        }

        return actual;
    }

    /**
     * Busca una palabra en el índice.
     * 
     * @param palabra palabra a buscar
     * @return cantidad de apariciones, 0 si no existe
     */
    public int obtenerCantidad(String palabra) {
        NodoPalabra nodo = buscarNodo(raiz, palabra);
        if (nodo == null) {
            return 0;
        }
        return nodo.cantidad;
    }

    private NodoPalabra buscarNodo(NodoPalabra actual, String palabra) {
        if (actual == null) {
            return null;
        }

        int comparacion = palabra.compareTo(actual.palabra);

        if (comparacion == 0) {
            return actual;
        } else if (comparacion < 0) {
            return buscarNodo(actual.izquierdo, palabra);
        } else {
            return buscarNodo(actual.derecho, palabra);
        }
    }

    /**
     * Retorna la cantidad total de palabras DISTINTAS en el índice.
     * 
     * COMPLEJIDAD: O(n) - visita cada nodo
     */
    public int cantidadPalabrasDistintas() {
        return cantidadRecursivo(raiz);
    }

    private int cantidadRecursivo(NodoPalabra actual) {
        if (actual == null) {
            return 0;
        }

        return 1 + cantidadRecursivo(actual.izquierdo) + cantidadRecursivo(actual.derecho);
    }

    /**
     * Retorna la cantidad TOTAL de palabras (sumando apariciones).
     * 
     * COMPLEJIDAD: O(n)
     */
    public int cantidadPalabrasTotal() {
        return cantidadTotalRecursivo(raiz);
    }

    private int cantidadTotalRecursivo(NodoPalabra actual) {
        if (actual == null) {
            return 0;
        }

        return actual.cantidad + cantidadTotalRecursivo(actual.izquierdo) 
                              + cantidadTotalRecursivo(actual.derecho);
    }

    /**
     * Muestra el índice completo en orden alfabético (inorden).
     * Formato: palabra -> cantidad
     * 
     * COMPLEJIDAD: O(n)
     */
    public void mostrarIndice() {
        System.out.println("\n=== ÍNDICE DE PALABRAS ===");
        mostrarInordenRecursivo(raiz);
        System.out.println("\nTotal de palabras distintas: " + cantidadPalabrasDistintas());
        System.out.println("Total de palabras (con repeticiones): " + cantidadPalabrasTotal());
    }

    private void mostrarInordenRecursivo(NodoPalabra actual) {
        if (actual == null) {
            return;
        }

        // Procesar izquierda (palabras anteriores alfabéticamente)
        mostrarInordenRecursivo(actual.izquierdo);

        // Procesar nodo actual
        System.out.printf("%s -> %d%n", actual.palabra, actual.cantidad);

        // Procesar derecha (palabras posteriores alfabéticamente)
        mostrarInordenRecursivo(actual.derecho);
    }

    /**
     * Procesa un archivo de texto y construye el índice.
     * 
     * @param rutaArchivo ruta del archivo a procesar
     */
    public void procesarArchivo(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            
            while ((linea = br.readLine()) != null) {
                procesarLinea(linea);
            }
            
            System.out.println("Archivo '" + rutaArchivo + "' procesado correctamente.");
            
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Procesa una línea de texto, extrayendo palabras.
     * 
     * @param linea línea de texto a procesar
     */
    private void procesarLinea(String linea) {
        // Convertir a minúsculas para uniformidad
        linea = linea.toLowerCase(new Locale("es_ES"));

        // Reemplazar caracteres especiales por espacios
        linea = linea.replaceAll("[^a-záéíóúñ ]", " ");

        // Dividir por espacios
        String[] palabras = linea.split("\\s+");

        // Agregar cada palabra al índice
        for (String palabra : palabras) {
            // Ignorar palabras vacías
            if (!palabra.isEmpty()) {
                agregarPalabra(palabra);
            }
        }
    }

    /**
     * Procesa una cadena de texto simple (sin archivo).
     * 
     * @param texto texto a procesar
     */
    public void procesarTexto(String texto) {
        // Convertir a minúsculas
        texto = texto.toLowerCase(new Locale("es_ES"));

        // Reemplazar caracteres especiales por espacios
        texto = texto.replaceAll("[^a-záéíóúñ ]", " ");

        // Dividir por espacios
        String[] palabras = texto.split("\\s+");

        // Agregar cada palabra
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                agregarPalabra(palabra);
            }
        }
    }

    /**
     * Encuentra la palabra más frecuente.
     * 
     * @return palabra con más apariciones, null si el índice está vacío
     */
    public String palabraMasFrecuente() {
        return palabraMasFrecuenteRecursivo(raiz, new Object[] {null, 0});
    }

    private String palabraMasFrecuenteRecursivo(NodoPalabra actual, Object[] maximo) {
        if (actual == null) {
            return (String) maximo[0];
        }

        // Procesar izquierda
        palabraMasFrecuenteRecursivo(actual.izquierdo, maximo);

        // Procesar nodo actual
        if ((int) maximo[1] < actual.cantidad) {
            maximo[0] = actual.palabra;
            maximo[1] = actual.cantidad;
        }

        // Procesar derecha
        palabraMasFrecuenteRecursivo(actual.derecho, maximo);

        return (String) maximo[0];
    }

    /**
     * Método main para demostración del Ejercicio 9.
     */
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 9: ÍNDICE DE PALABRAS ===\n");

        // Ejemplo 1: Texto simple
        System.out.println("--- Ejemplo 1: Texto Simple ---");
        String textoSimple = "casa árbol casa río árbol árbol";
        System.out.println("Texto: \"" + textoSimple + "\"");

        IndiceABB indice1 = new IndiceABB();
        indice1.procesarTexto(textoSimple);
        indice1.mostrarIndice();

        // Ejemplo 2: Texto más largo
        System.out.println("\n--- Ejemplo 2: Texto Extenso ---");
        String textoLargo = "el gato persigue al ratón. " +
                           "el ratón corre rápido. " +
                           "el gato es rápido. " +
                           "el gato y el ratón juegan.";
        System.out.println("Texto: \"" + textoLargo + "\"");

        IndiceABB indice2 = new IndiceABB();
        indice2.procesarTexto(textoLargo);
        indice2.mostrarIndice();

        // Información adicional
        System.out.println("\nPalabra más frecuente: " + indice2.palabraMasFrecuente());

        // Ejemplo 3: Procesar archivo
        System.out.println("\n--- Ejemplo 3: Procesar Archivo (Texto.txt) ---");
        IndiceABB indice3 = new IndiceABB();
        indice3.procesarArchivo("Texto.txt");
        indice3.mostrarIndice();
        System.out.println("\nPalabra más frecuente: " + indice3.palabraMasFrecuente());

        // Búsquedas específicas
        System.out.println("\n--- Búsquedas Específicas ---");
        System.out.println("Apariciones de 'el': " + indice3.obtenerCantidad("el"));
        System.out.println("Apariciones de 'muerte': " + indice3.obtenerCantidad("muerte"));
        System.out.println("Apariciones de 'xyz': " + indice3.obtenerCantidad("xyz"));
    }
}
