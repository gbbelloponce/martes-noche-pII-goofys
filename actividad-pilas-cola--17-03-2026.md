# Pila (Stack) de pestañas del navegador web

## Actividad 1

### Desde el lado del usuario (uno como usuario del navegador):

- InicializarPila
  -> Abro una pestaña nueva; el historial nace vacío.

- Apilar
  -> Entri a una página (ej. Google) y luego a otra (ej. Wikipedia); el sistema las va "apilando" una sobre otra.

- Desapilar
  -> Toco la flechita de "Atrás"; el navegador te saca de la página actual (el último que entró) para devolverte a la anterior.

- Tope
  -> Es la página que estás viendo ahora mismo; representa el próximo elemento a salir si decidís volver atrás.

- PilaVacia
  -> Si el botón de "Atrás" está deshabilitado, es porque la pila no tiene más páginas guardadas.


### Desde el lado del implementador:

- InicializarPila
  -> Creo un arreglo de memoria (implementación estática) para guardar las direcciones URL.

- Apilar
  -> Cuando el usuario entra a una web nueva, la inserto obligatoriamente en el índice 0. Para hacer lugar, tenés que "correr" todos los elementos existentes un lugar hacia la derecha.

<small>**Nota técnica**: Según la fuente, esta estrategia es "lentísima" porque desplazar elementos es muy caro en tiempo de CPU.</small>

- Desapilar
  -> Devuelvo la URL que está en el índice 0. Inmediatamente después, desplazo todos los elementos restantes hacia la izquierda para que el nuevo tope vuelva a ocupar la posición inicial.

- Tope
  -> Simplemente muestro el contenido guardado en la posición 0 del arreglo.

- PilaVacia
  -> Simplemente muestro el contenido guardado en la posición 0 del arreglo.

<hr/>

## Actividad 2

### Estrategia 1

- InicializarPila
  -> inicializo una lista vacia, y una variable externa en 0

- Apilar
  -> inserto un elemento en la lista, e incremento mi variable externa en 1

- Desapilar
  -> extraigo el elemento de la lista con el indice en la variable externa

- Tope
  -> extraigo el elemento de la lista con el indice en la variable externa

- PilaVacia
  -> devuelvo true si mi variable externa esta en 0


### Estrategia 2

- InicializarPila
  -> inicializo una lista vacia

- Apilar
  -> inserto el elemento dado en el índice 0 de la lista, corro todos los elementos existentes al siguiente índice

- Desapilar
  -> extraigo el elemento en el índice 0 de la lista, corro todos los elementos existentes al índice anterior

- Tope
  -> devuelvo el elemento en el índice 0

- PilaVacia
  -> devuelvo true si la lista no tiene elementos

### Estrategia 3

- InicializarPila
  -> inicializo una lista vacia con el primer indice con el numero 0 

- Apilar
  -> agrego un elemento a la lista, e incremento el contador en el primer indice + 1

- Desapilar
  -> extraigo el ultimo elemento de la lista y decremento el contador en el primer indice - 1

- Tope
  -> devuelvo el ultimo elemento de la lista

- PilaVacia
  -> devuelvo true si el primer elemento de mi lista (contador) es 0