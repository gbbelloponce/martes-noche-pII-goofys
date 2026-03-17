# Pila (Stack)


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