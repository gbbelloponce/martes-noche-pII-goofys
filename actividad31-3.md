### ACTIVIDAD MODELADO DE TDA###

Historial de navegación:
En cada caso se apilan las distintas páginas mencionadas, mostrando las mismas. Cuando el usuario apreta la "flechita de atrás", la última en haber aparecido, es la primera que desaparece.
Por ejemplo, el usuario ingresa a [campus.utn.edu.ar] y este se almacena en la posición 0 de la fila. Luego, presiona un link e ingresa a [fiuba.ar], este se almacena en la posición 0, dezplazando la anterior a la derecha y, por último, entra en [stackoverflow.com], el cual es guardado en la posición 0 nuevamente, empujando otra vez a las anteriores a la derecha. Como fue mencionado anteriormente, si el usuario apreta "la flechita de atrás", la primer página en ser desapilada (pop) es la última en haber ingresado. Método 2 de apilado.

Editor de código(Undo/Deshacer):
Por cada línea que hace el usuario se debería apilar, para recuperar el código una vez eliminado, se tiene que volver a la versión anterior, es decir, al último estado de la última línea hecha.
Por ejemplo, el usuario escribe código, por decir algo if 1 == 2, si es borrado, al apretar "undo" vuelve a escribir esa misma línea. Método 2.

Balanceo de paréntesis:
Al hacer la función, se apilan al escribirse y se desapilan al eliminar un elemento de la misma.

Reversión de Strings:
Una vez que el usuario ingresa la palabra "ALGORITMOS", para darla vuelta, inserta caracter por caracter, solo que de forma inversa, recorriendo la cadena, desde la primera posición hasta la última.

Pila de llamadas (Call Stack):
Al hacer llamado a esas funciones, la última llamada es la que está en la primer posición para ser ejecutada y aquella en el Tope() es CalcularPromedio().

Navegación de directorios:
Cada carpeta tiene una posición asignada, desde 0 hasta la última, siendo esta la que fue abierta al final, para subir el nivel, lo que se necesita hacer es ejecutar un (pop), para volver a la anterior carpeta, o sea, la anterior posición.
