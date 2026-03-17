# Pila (Stack)

## Actividad 1

### Desde el lado del usuario:

- InicializarPila
  -> no le doy nigun parametro, me devuelve una pila vacia

- Apilar
  -> le doy un elemento, me lo apila

- Desapilar
  -> me devuelve el elemento que está en el tope

- Tope
  -> me devuelve el proximo elemento a desapilar

- PilaVacia
  -> me indica si la pila está vacía


### Desde el lado del implementador:

- InicializarPila
  -> inicializo una lista vacia

- Apilar
  -> inserto el elemento dado en el índice 0 de la lista, corro todos los elementos existentes al siguiente índice

- Desapilar
  -> devuelvo el elemento en el índice 0 de la lista, corro todos los elementos existentes al índice anterior

- Tope
  -> devuelvo el elemento en el índice 0

- PilaVacia
  -> devuelvo true si la lista no tiene elementos

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