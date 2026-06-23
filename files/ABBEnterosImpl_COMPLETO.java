package ABB;

/**
 * SOLUCIÓN COMPLETA - PRÁCTICA DE ÁRBOLES BINARIOS DE BÚSQUEDA
 * Programación II - UADE 2026
 * 
 * Contiene la implementación de:
 * - Ejercicio 1: Inserción recursiva
 * - Ejercicio 2: Inserción iterativa + análisis
 * - Ejercicio 3: Búsqueda + análisis de complejidad
 * - Ejercicio 4: Estadísticas (altura, cantidad, mínimo, máximo)
 * - Ejercicio 5: Recorridos (inorden, preorden, postorden)
 * - Ejercicio 6: Eliminación
 * - Ejercicio 7: Validación de rango
 * - Ejercicio 8: Conjunto usando ABB (ConjuntoABB)
 * - Ejercicio 9: Índice de palabras (desafío)
 */
public class ABBEnterosImpl implements ABBEnteros {
    private Nodo raiz;

    // =========================================================================
    // EJERCICIO 1: INSERCIÓN RECURSIVA
    // =========================================================================
    /**
     * Inserta un valor en el árbol de forma recursiva.
     * No permite duplicados.
     * 
     * COMPLEJIDAD TEMPORAL:
     * - Mejor caso: O(log n) - árbol balanceado
     * - Peor caso: O(n) - árbol degenerado (lista enlazada)
     * - Caso promedio: O(log n)
     * 
     * COMPLEJIDAD ESPACIAL: O(h) donde h es la altura (pila recursiva)
     */
    @Override
    public void agregar(int valor) {
        raiz = agregarRecursivo(raiz, valor);
    }

    /**
     * Implementación recursiva de agregar.
     * Retorna el nodo actualizado para mantener la referencia en el árbol.
     */
    private Nodo agregarRecursivo(Nodo actual, int valor) {
        // Caso base: si el nodo es null, creamos uno nuevo
        if (actual == null) {
            return new Nodo(valor);
        }

        // Si el valor ya existe, no lo agregamos (no duplicados)
        if (valor == actual.valor) {
            return actual;
        }

        // Si el valor es menor, va a la izquierda
        if (valor < actual.valor) {
            actual.izquierdo = agregarRecursivo(actual.izquierdo, valor);
        } 
        // Si el valor es mayor, va a la derecha
        else {
            actual.derecho = agregarRecursivo(actual.derecho, valor);
        }

        return actual;
    }

    // =========================================================================
    // EJERCICIO 2: INSERCIÓN ITERATIVA
    // =========================================================================
    /**
     * Inserta un valor en el árbol de forma ITERATIVA (sin recursión).
     * No permite duplicados.
     * 
     * COMPLEJIDAD TEMPORAL:
     * - Mejor caso: O(log n)
     * - Peor caso: O(n)
     * - Caso promedio: O(log n)
     * 
     * COMPLEJIDAD ESPACIAL: O(1) - solo usa variables locales
     */
    public void agregarIterativo(int valor) {
        // Si el árbol está vacío, creamos la raíz
        if (raiz == null) {
            raiz = new Nodo(valor);
            return;
        }

        // Iniciamos con la raíz
        Nodo actual = raiz;

        // Recorremos el árbol hasta encontrar la posición correcta
        while (true) {
            // Si el valor ya existe, no lo agregamos
            if (valor == actual.valor) {
                return;
            }

            // Si el valor es menor, vamos a la izquierda
            if (valor < actual.valor) {
                if (actual.izquierdo == null) {
                    // Encontramos el lugar, agregamos el nodo
                    actual.izquierdo = new Nodo(valor);
                    return;
                }
                actual = actual.izquierdo;
            } 
            // Si el valor es mayor, vamos a la derecha
            else {
                if (actual.derecho == null) {
                    // Encontramos el lugar, agregamos el nodo
                    actual.derecho = new Nodo(valor);
                    return;
                }
                actual = actual.derecho;
            }
        }
    }

    /**
     * ANÁLISIS EJERCICIO 2:
     * 
     * a) ¿QUÉ HACE TestRecursividadABB.main()?
     *    - Agrega 50,000 números consecutivos (1, 2, 3, ..., 50000) al árbol.
     *    - Intenta insertar valores en orden ascendente.
     *    - Si ocurre StackOverflowError, imprime la profundidad donde ocurrió.
     * 
     * b) PROBLEMÁTICA Y RELACIÓN CON RECURSIÓN/ITERACIÓN:
     *    - Al agregar números en orden ascendente (1,2,3...), el árbol se convierte
     *      en una lista degenerada: cada nodo solo tiene hijo derecho.
     *    - La implementación RECURSIVA crea una llamada recursiva por cada nivel.
     *    - Con 50,000 elementos, la pila de llamadas recursivas crece hasta 50,000.
     *    - La pila tiene un límite (típicamente 1000-10000 llamadas).
     *    - La implementación ITERATIVA NO usa la pila de llamadas, usa un bucle while.
     * 
     * c) ¿POR QUÉ OCURRE StackOverflowError?
     *    - Cada llamada recursiva usa espacio en la pila de ejecución (stack).
     *    - Cuando se agregan 50,000 valores recursivamente:
     *      - agregarRecursivo(raiz, 1)
     *        -> agregarRecursivo(raiz.derecho, 2)
     *          -> agregarRecursivo(raiz.derecho.derecho, 3)
     *            -> ... (hasta 50,000 llamadas anidadas)
     *    - La JVM solo permite hasta ~10,000-15,000 llamadas en la pila.
     *    - Cuando se excede este límite, lanza StackOverflowError.
     * 
     * VENTAJAS Y DESVENTAJAS:
     * 
     * RECURSIVA:
     *   Ventajas:
     *     - Código más limpio y fácil de entender
     *     - Refleja la naturaleza recursiva del árbol
     *     - Menos variables locales que gestionar
     *   Desventajas:
     *     - Usa la pila (StackOverflowError en árboles degenerados)
     *     - Más lenta por overhead de llamadas de función
     *     - Problemas con árboles muy grandes o desbalanceados
     * 
     * ITERATIVA:
     *   Ventajas:
     *     - No hay límite de profundidad (no usa pila de llamadas)
     *     - Más eficiente (menos overhead)
     *     - Funciona bien con árboles muy grandes
     *   Desventajas:
     *     - Código más complejo, más variables que manejar
     *     - Menos intuitiva para algunos
     *     - Necesita bucles y control de flujo manual
     */

    // =========================================================================
    // EJERCICIO 3: BÚSQUEDA
    // =========================================================================
    /**
     * Determina si un valor está presente en el árbol.
     * 
     * COMPLEJIDAD TEMPORAL:
     * - Mejor caso: O(1) - el valor está en la raíz
     * - Peor caso: O(n) - árbol degenerado
     * - Caso promedio: O(log n) - árbol balanceado
     * 
     * COMPLEJIDAD ESPACIAL: O(h) donde h es la altura (pila recursiva)
     */
    @Override
    public boolean contiene(int valor) {
        return contieneRecursivo(raiz, valor);
    }

    private boolean contieneRecursivo(Nodo actual, int valor) {
        // Si el nodo es null, el valor no está
        if (actual == null) {
            return false;
        }

        // Si encontramos el valor
        if (valor == actual.valor) {
            return true;
        }

        // Si el valor es menor, buscamos a la izquierda
        if (valor < actual.valor) {
            return contieneRecursivo(actual.izquierdo, valor);
        }

        // Si el valor es mayor, buscamos a la derecha
        return contieneRecursivo(actual.derecho, valor);
    }

    /**
     * ANÁLISIS EJERCICIO 3:
     * 
     * COMPLEJIDAD EN ABB vs BÚSQUEDA LINEAL vs BÚSQUEDA BINARIA:
     * 
     * 1) BÚSQUEDA EN ABB:
     *    - Mejor caso: O(1)
     *    - Peor caso: O(n) - árbol degenerado
     *    - Promedio: O(log n) - árbol balanceado
     *    - Ventaja: Búsqueda, inserción y eliminación en el mismo tiempo
     * 
     * 2) BÚSQUEDA LINEAL (en array o lista):
     *    - Mejor caso: O(1)
     *    - Peor caso: O(n)
     *    - Promedio: O(n)
     *    - Ventaja: Simple de implementar
     *    - Desventaja: Siempre necesita revisar muchos elementos
     * 
     * 3) BÚSQUEDA BINARIA (en array ordenado):
     *    - Mejor caso: O(1)
     *    - Peor caso: O(log n)
     *    - Promedio: O(log n)
     *    - Ventaja: Similar a ABB en búsqueda
     *    - Desventaja: Inserción y eliminación son O(n) (requiere desplazar elementos)
     * 
     * COMPARACIÓN:
     * - Para n = 1,000,000 elementos:
     *   * Búsqueda lineal: hasta 1,000,000 comparaciones
     *   * Búsqueda binaria: hasta 20 comparaciones
     *   * ABB: 20 comparaciones (si está balanceado)
     * 
     * - ABB es MEJOR que búsqueda lineal siempre
     * - ABB es SIMILAR a búsqueda binaria en búsqueda, pero MEJOR en inserción/eliminación
     */

    // =========================================================================
    // EJERCICIO 4: ESTADÍSTICAS DEL ÁRBOL
    // =========================================================================
    /**
     * Retorna la cantidad de nodos en el árbol.
     * 
     * COMPLEJIDAD TEMPORAL: O(n) - debe visitar cada nodo
     * COMPLEJIDAD ESPACIAL: O(h) - pila recursiva
     */
    @Override
    public int cantidad() {
        return cantidadRecursivo(raiz);
    }

    private int cantidadRecursivo(Nodo actual) {
        // Si el nodo es null, no hay nodos en este subárbol
        if (actual == null) {
            return 0;
        }

        // Contamos 1 (nodo actual) + cantidad izquierda + cantidad derecha
        return 1 + cantidadRecursivo(actual.izquierdo) + cantidadRecursivo(actual.derecho);
    }

    /**
     * Retorna la altura del árbol.
     * - Árbol vacío (null): altura 0
     * - Nodo solo (raíz): altura 1
     * 
     * COMPLEJIDAD TEMPORAL: O(n) - debe visitar cada nodo
     * COMPLEJIDAD ESPACIAL: O(h) - pila recursiva
     */
    @Override
    public int altura() {
        return alturaRecursivo(raiz);
    }

    private int alturaRecursivo(Nodo actual) {
        // Si el nodo es null, altura 0
        if (actual == null) {
            return 0;
        }

        // Si es una hoja (sin hijos), altura 1
        if (actual.esHoja()) {
            return 1;
        }

        // La altura es 1 + la altura máxima de los subárboles
        int alturaIzq = alturaRecursivo(actual.izquierdo);
        int alturaDer = alturaRecursivo(actual.derecho);

        return 1 + Math.max(alturaIzq, alturaDer);
    }

    /**
     * Retorna el valor mínimo del árbol.
     * El mínimo siempre está en el nodo más a la izquierda.
     * 
     * COMPLEJIDAD TEMPORAL: O(log n) en promedio, O(n) en peor caso
     * COMPLEJIDAD ESPACIAL: O(h) - pila recursiva
     * 
     * @throws IllegalStateException si el árbol está vacío
     */
    @Override
    public int minimo() {
        if (raiz == null) {
            throw new IllegalStateException("El árbol está vacío");
        }

        return minimoRecursivo(raiz).valor;
    }

    private Nodo minimoRecursivo(Nodo actual) {
        // Si no hay hijo izquierdo, este es el mínimo
        if (actual.izquierdo == null) {
            return actual;
        }

        // Seguimos buscando a la izquierda
        return minimoRecursivo(actual.izquierdo);
    }

    /**
     * Retorna el valor máximo del árbol.
     * El máximo siempre está en el nodo más a la derecha.
     * 
     * COMPLEJIDAD TEMPORAL: O(log n) en promedio, O(n) en peor caso
     * COMPLEJIDAD ESPACIAL: O(h) - pila recursiva
     * 
     * @throws IllegalStateException si el árbol está vacío
     */
    @Override
    public int maximo() {
        if (raiz == null) {
            throw new IllegalStateException("El árbol está vacío");
        }

        return maximoRecursivo(raiz).valor;
    }

    private Nodo maximoRecursivo(Nodo actual) {
        // Si no hay hijo derecho, este es el máximo
        if (actual.derecho == null) {
            return actual;
        }

        // Seguimos buscando a la derecha
        return maximoRecursivo(actual.derecho);
    }

    // =========================================================================
    // EJERCICIO 5: RECORRIDOS
    // =========================================================================
    /**
     * Recorre el árbol en INORDEN (izquierda, raíz, derecha).
     * 
     * PARTICULARIDAD: En un ABB, el recorrido INORDEN imprime los valores
     * en ORDEN ASCENDENTE. Esta es una propiedad fundamental del ABB.
     * 
     * Ejemplo:
     *        5
     *       / \
     *      3   7
     *     / \
     *    1   4
     * 
     * Inorden: 1, 3, 4, 5, 7 (ascendente)
     * 
     * COMPLEJIDAD TEMPORAL: O(n) - visita cada nodo una vez
     * COMPLEJIDAD ESPACIAL: O(h) - pila recursiva
     */
    @Override
    public void mostrarInOrden() {
        mostrarInOrdenRecursivo(raiz);
        System.out.println(); // Salto de línea al final
    }

    private void mostrarInOrdenRecursivo(Nodo actual) {
        if (actual == null) {
            return;
        }

        // Procesar izquierda
        mostrarInOrdenRecursivo(actual.izquierdo);

        // Procesar nodo actual
        System.out.print(actual.valor + " ");

        // Procesar derecha
        mostrarInOrdenRecursivo(actual.derecho);
    }

    /**
     * Recorre el árbol en PREORDEN (raíz, izquierda, derecha).
     * Útil para copiar el árbol o serializar.
     * 
     * COMPLEJIDAD TEMPORAL: O(n)
     * COMPLEJIDAD ESPACIAL: O(h)
     */
    @Override
    public void mostrarPreOrden() {
        mostrarPreOrdenRecursivo(raiz);
        System.out.println();
    }

    private void mostrarPreOrdenRecursivo(Nodo actual) {
        if (actual == null) {
            return;
        }

        // Procesar nodo actual
        System.out.print(actual.valor + " ");

        // Procesar izquierda
        mostrarPreOrdenRecursivo(actual.izquierdo);

        // Procesar derecha
        mostrarPreOrdenRecursivo(actual.derecho);
    }

    /**
     * Recorre el árbol en POSTORDEN (izquierda, derecha, raíz).
     * Útil para eliminar el árbol o procesar desde las hojas.
     * 
     * COMPLEJIDAD TEMPORAL: O(n)
     * COMPLEJIDAD ESPACIAL: O(h)
     */
    @Override
    public void mostrarPostOrden() {
        mostrarPostOrdenRecursivo(raiz);
        System.out.println();
    }

    private void mostrarPostOrdenRecursivo(Nodo actual) {
        if (actual == null) {
            return;
        }

        // Procesar izquierda
        mostrarPostOrdenRecursivo(actual.izquierdo);

        // Procesar derecha
        mostrarPostOrdenRecursivo(actual.derecho);

        // Procesar nodo actual
        System.out.print(actual.valor + " ");
    }

    // =========================================================================
    // EJERCICIO 6: ELIMINACIÓN
    // =========================================================================
    /**
     * Elimina un valor del árbol si está presente.
     * Mantiene la propiedad de ABB después de la eliminación.
     * 
     * COMPLEJIDAD TEMPORAL:
     * - Mejor caso: O(1) - valor en raíz, es hoja
     * - Peor caso: O(n) - árbol degenerado
     * - Promedio: O(log n)
     * 
     * COMPLEJIDAD ESPACIAL: O(h) - pila recursiva
     * 
     * @return true si se eliminó, false si no estaba
     */
    @Override
    public boolean eliminar(int valor) {
        if (!contiene(valor)) {
            return false;
        }

        raiz = eliminarRecursivo(raiz, valor);
        return true;
    }

    /**
     * Implementación recursiva de eliminación.
     * 
     * Casos:
     * 1. Nodo es hoja: lo eliminamos (retornamos null)
     * 2. Nodo tiene un hijo: lo subimos (retornamos ese hijo)
     * 3. Nodo tiene dos hijos: lo reemplazamos por su sucesor (mínimo derecho)
     */
    private Nodo eliminarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }

        // Si el valor es menor, está a la izquierda
        if (valor < actual.valor) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, valor);
        }
        // Si el valor es mayor, está a la derecha
        else if (valor > actual.valor) {
            actual.derecho = eliminarRecursivo(actual.derecho, valor);
        }
        // Encontramos el nodo a eliminar
        else {
            // CASO 1: Es una hoja (sin hijos)
            if (actual.esHoja()) {
                return null;
            }

            // CASO 2: Tiene solo hijo derecho
            if (actual.izquierdo == null) {
                return actual.derecho;
            }

            // CASO 2: Tiene solo hijo izquierdo
            if (actual.derecho == null) {
                return actual.izquierdo;
            }

            // CASO 3: Tiene dos hijos
            // Buscamos el mínimo del subárbol derecho (el sucesor)
            Nodo sucesor = minimoRecursivo(actual.derecho);

            // Reemplazamos el valor del nodo por el sucesor
            actual.valor = sucesor.valor;

            // Eliminamos el sucesor del subárbol derecho
            actual.derecho = eliminarRecursivo(actual.derecho, sucesor.valor);
        }

        return actual;
    }

    // =========================================================================
    // EJERCICIO 7: VALIDACIÓN DE RANGO
    // =========================================================================
    /**
     * Valida si TODOS los valores del ABB están contenidos en el rango [desde, hasta].
     * 
     * COMPLEJIDAD TEMPORAL: O(n) - en el peor caso visita todos los nodos
     * COMPLEJIDAD ESPACIAL: O(h) - pila recursiva
     * 
     * @param desde valor mínimo del rango (inclusivo)
     * @param hasta valor máximo del rango (inclusivo)
     * @return true si todos los valores están en el rango, false en caso contrario
     */
    public boolean enRango(int desde, int hasta) {
        return enRangoRecursivo(raiz, desde, hasta);
    }

    private boolean enRangoRecursivo(Nodo actual, int desde, int hasta) {
        // Si el nodo es null, está en rango
        if (actual == null) {
            return true;
        }

        // Verificar que el valor actual está en rango
        if (actual.valor < desde || actual.valor > hasta) {
            return false;
        }

        // Verificar que el subárbol izquierdo está en rango
        // (todos los valores a la izquierda deben ser < actual.valor, ya en rango si <= hasta)
        if (!enRangoRecursivo(actual.izquierdo, desde, actual.valor - 1)) {
            return false;
        }

        // Verificar que el subárbol derecho está en rango
        // (todos los valores a la derecha deben ser > actual.valor, ya en rango si >= desde)
        if (!enRangoRecursivo(actual.derecho, actual.valor + 1, hasta)) {
            return false;
        }

        return true;
    }

    // =========================================================================
    // EJERCICIO 8: CONJUNTO USANDO ABB
    // =========================================================================
    // Implementado en clase ConjuntoABB (ver más adelante)

    // =========================================================================
    // EJERCICIO 9: ÍNDICE DE PALABRAS
    // =========================================================================
    // Implementado en clase IndiceABB (ver más adelante)
}
