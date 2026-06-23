package ABB;

/**
 * EJERCICIO 8: CONJUNTO USANDO ABB
 * 
 * Implementa un conjunto (set) usando un Árbol Binario de Búsqueda.
 * Un conjunto no permite duplicados (propiedad que hereda del ABB).
 * 
 * Operaciones:
 * - agregar(int): Añade un elemento al conjunto
 * - eliminar(int): Elimina un elemento del conjunto
 * - contiene(int): Verifica si un elemento está en el conjunto
 * - cantidad(): Retorna la cantidad de elementos
 * - elegir(): Devuelve un elemento arbitrario del conjunto
 * - interseccion(ConjuntoABB): Retorna la intersección con otro conjunto
 */
public class ConjuntoABB {
    private ABBEnteros arbol;

    /**
     * Constructor que inicializa el conjunto vacío.
     */
    public ConjuntoABB() {
        this.arbol = new ABBEnterosImpl();
    }

    /**
     * Agrega un elemento al conjunto.
     * Si el elemento ya existe, no hace nada (propiedad de conjunto).
     * 
     * COMPLEJIDAD: O(log n) en promedio, O(n) en peor caso
     * 
     * @param valor elemento a agregar
     */
    public void agregar(int valor) {
        arbol.agregar(valor);
    }

    /**
     * Elimina un elemento del conjunto.
     * 
     * COMPLEJIDAD: O(log n) en promedio, O(n) en peor caso
     * 
     * @param valor elemento a eliminar
     * @return true si fue eliminado, false si no estaba
     */
    public boolean eliminar(int valor) {
        return arbol.eliminar(valor);
    }

    /**
     * Verifica si un elemento está en el conjunto.
     * 
     * COMPLEJIDAD: O(log n) en promedio, O(n) en peor caso
     * 
     * @param valor elemento a buscar
     * @return true si está en el conjunto, false en caso contrario
     */
    public boolean contiene(int valor) {
        return arbol.contiene(valor);
    }

    /**
     * Retorna la cantidad de elementos en el conjunto.
     * 
     * COMPLEJIDAD: O(n) - debe contar todos los elementos
     * 
     * @return cantidad de elementos
     */
    public int cantidad() {
        return arbol.cantidad();
    }

    /**
     * Elige un elemento arbitrario del conjunto.
     * Para simplificar, retorna el elemento mínimo.
     * 
     * COMPLEJIDAD: O(log n) en promedio
     * 
     * @return un elemento del conjunto
     * @throws IllegalStateException si el conjunto está vacío
     */
    public int elegir() {
        if (cantidad() == 0) {
            throw new IllegalStateException("El conjunto está vacío");
        }
        return arbol.minimo();
    }

    /**
     * Calcula la intersección de este conjunto con otro.
     * Retorna un nuevo conjunto con los elementos que están en AMBOS conjuntos.
     * 
     * Ejemplo:
     *   Conjunto A: {1, 2, 3, 4, 5}
     *   Conjunto B: {3, 4, 5, 6, 7}
     *   A.interseccion(B) = {3, 4, 5}
     * 
     * COMPLEJIDAD: O(n * log m) donde n es la cantidad en este conjunto
     *              y m es la cantidad en el otro (en promedio)
     *              Caso peor: O(n * m)
     * 
     * @param conjunto el otro conjunto
     * @return nuevo conjunto con la intersección
     */
    public ConjuntoABB interseccion(ConjuntoABB conjunto) {
        ConjuntoABB resultado = new ConjuntoABB();

        // Recorremos todos los elementos de este conjunto
        recorridoInordenParaInterseccion(resultado, conjunto);

        return resultado;
    }

    /**
     * Método auxiliar para hacer inorden y construir la intersección.
     * Usamos reflexión interna del ABB para acceder a sus elementos.
     */
    private void recorridoInordenParaInterseccion(ConjuntoABB resultado, ConjuntoABB conjunto) {
        recorridoInordenParaInterseccionRecursivo(((ABBEnterosImpl)arbol), resultado, conjunto);
    }

    /**
     * Recorrido recursivo en inorden para extraer elementos y hacer intersección.
     * Nota: Como ABBEnterosImpl no es pública su estructura, usamos un workaround.
     * En una solución real, ABBEnterosImpl debería proporcionar un método
     * que permita iterar sobre los elementos.
     */
    private void recorridoInordenParaInterseccionRecursivo(ABBEnterosImpl abImpl, 
                                                            ConjuntoABB resultado, 
                                                            ConjuntoABB conjunto) {
        // Solución alternativa: recorrer de 0 a cantidad buscando con contiene
        // Este es un workaround para mantener la encapsulación
        
        // Obtenemos el mínimo y máximo del árbol actual
        try {
            int minimo = abImpl.minimo();
            int maximo = abImpl.maximo();
            
            // Recorremos todos los valores posibles en el rango
            // (esto es ineficiente, pero mantiene la encapsulación)
            for (int i = minimo; i <= maximo; i++) {
                if (abImpl.contiene(i) && conjunto.contiene(i)) {
                    resultado.agregar(i);
                }
            }
        } catch (IllegalStateException e) {
            // El árbol está vacío, la intersección es vacía
        }
    }

    /**
     * Muestra los elementos del conjunto en orden ascendente.
     */
    public void mostrar() {
        System.out.print("Conjunto: {");
        arbol.mostrarInOrden();
        System.out.println("}");
    }

    /**
     * Muestra los elementos del conjunto en una línea, separados por comas.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        
        // Usamos un contador auxiliar (workaround para acceder a elementos)
        int minimo = 0;
        int maximo = 0;
        
        try {
            minimo = arbol.minimo();
            maximo = arbol.maximo();
            
            boolean first = true;
            for (int i = minimo; i <= maximo; i++) {
                if (arbol.contiene(i)) {
                    if (!first) {
                        sb.append(", ");
                    }
                    sb.append(i);
                    first = false;
                }
            }
        } catch (IllegalStateException e) {
            // Conjunto vacío
        }
        
        sb.append("}");
        return sb.toString();
    }

    /**
     * Método de prueba para el Ejercicio 8.
     */
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 8: CONJUNTO USANDO ABB ===\n");

        // Crear dos conjuntos
        ConjuntoABB conjunto1 = new ConjuntoABB();
        ConjuntoABB conjunto2 = new ConjuntoABB();

        // Agregar elementos al conjunto 1
        int[] elementos1 = {5, 3, 7, 1, 4, 6, 8};
        System.out.println("Agregando elementos al Conjunto 1:");
        for (int e : elementos1) {
            conjunto1.agregar(e);
            System.out.print(e + " ");
        }
        System.out.println("\nConjunto 1: " + conjunto1.toString());

        // Agregar elementos al conjunto 2
        int[] elementos2 = {4, 6, 8, 2, 5, 9};
        System.out.println("\nAgregando elementos al Conjunto 2:");
        for (int e : elementos2) {
            conjunto2.agregar(e);
            System.out.print(e + " ");
        }
        System.out.println("\nConjunto 2: " + conjunto2.toString());

        // Operaciones básicas
        System.out.println("\n--- Operaciones Básicas ---");
        System.out.println("Cantidad en Conjunto 1: " + conjunto1.cantidad());
        System.out.println("Contiene 4 en Conjunto 1: " + conjunto1.contiene(4));
        System.out.println("Contiene 10 en Conjunto 1: " + conjunto1.contiene(10));
        System.out.println("Elegir elemento de Conjunto 1: " + conjunto1.elegir());

        // Eliminar elemento
        System.out.println("\nEliminando 4 de Conjunto 1...");
        conjunto1.eliminar(4);
        System.out.println("Conjunto 1 después de eliminar 4: " + conjunto1.toString());

        // Intersección
        System.out.println("\n--- Intersección ---");
        ConjuntoABB interseccion = conjunto1.interseccion(conjunto2);
        System.out.println("Intersección (Conjunto 1 ∩ Conjunto 2): " + interseccion.toString());
        System.out.println("Cantidad en intersección: " + interseccion.cantidad());
    }
}
