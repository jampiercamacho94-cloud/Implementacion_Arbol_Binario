# Implementación de un árbol binario en Java

Práctica individual de Estructura de Datos, tercer nivel de Ingeniería de Software.

## Descripción del problema

La Universidad necesita representar la organización de sus dependencias académicas mediante un árbol binario. Cada nodo almacena el nombre de una dependencia y puede tener como máximo dos hijos.

El programa permite construir la estructura inicial, visualizarla y consultar su número de nodos, hojas, altura y grado de cada nodo.

## Estructura del árbol

| Nodo        | Hijo izquierdo | Hijo derecho |
| ----------- | -------------- | ------------ |
| UNIVERSIDAD | FISEI          | DIDE         |
| FISEI       | SOFTWARE       | TI           |
| DIDE        | INVESTIGACION  | VINCULACION  |
| SOFTWARE    | NIVEL1         | NIVEL3       |

Las hojas son NIVEL1, NIVEL3, TI, INVESTIGACION y VINCULACION.

Los hijos se asignan según la jerarquía del ejercicio. No se utiliza un orden alfabético ni se implementa un árbol binario de búsqueda.

## Organización del código

| Archivo                         | Función                                                                                                          |
| ------------------------------- | ---------------------------------------------------------------------------------------------------------------- |
| `src/modelo/Nodo.java`          | Clase genérica con el dato, las referencias a los hijos, getters, setters y métodos para consultar hoja y grado. |
| `src/negocio/ArbolBinario.java` | Creación de raíz, inserciones, búsqueda, conteos, altura y visualización.                                        |
| `src/app/Main.java`             | Menú interactivo con Scanner y ejecución de los casos de prueba.                                                 |

## Requisitos

- JDK instalado y disponible en la terminal.
- Visual Studio Code con soporte para Java.

## Compilación y ejecución

Abrir la terminal en la carpeta principal del proyecto y compilar:

```powershell
javac -encoding UTF-8 -d bin src/modelo/Nodo.java src/negocio/ArbolBinario.java src/app/Main.java
```

Ejecutar el menú:

```powershell
java -cp bin app.Main
```

Ejecutar los casos de prueba:

```powershell
java -cp bin app.Main pruebas
```

## Opciones del menú

1. Crear el árbol inicial.
2. Visualizar el árbol.
3. Consultar el número de nodos.
4. Consultar el número de hojas.
5. Consultar la altura.
6. Consultar el grado de un nodo.
7. Verificar si un nodo es hoja.
8. Salir.

El programa inicia con el árbol vacío. Para construir la estructura se utiliza la opción 1.

Las consultas aceptan nombres en minúsculas o mayúsculas. Se deben escribir sin tildes, como están almacenados.

## Implementación recursiva

El conteo de nodos suma el nodo actual y los nodos de sus dos subárboles.

El conteo de hojas devuelve 1 cuando el nodo no tiene hijos. En los demás casos suma las hojas de ambos subárboles.

La altura devuelve -1 cuando el nodo es nulo. Para los demás nodos suma 1 a la mayor altura de sus dos subárboles. Así, un árbol con solo la raíz tiene altura 0.

La búsqueda revisa el nodo actual y después sus subárboles izquierdo y derecho.

## Resultados de las pruebas

| Caso                          | Resultado obtenido                                                      |
| ----------------------------- | ----------------------------------------------------------------------- |
| Árbol vacío                   | 0 nodos y altura -1.                                                    |
| Árbol con un solo nodo        | 1 hoja y altura 0.                                                      |
| Árbol inicial                 | 9 nodos, 5 hojas y altura 3.                                            |
| Inserción en posición ocupada | Se rechaza la inserción, se conserva SOFTWARE y permanecen los 9 nodos. |
| Grado de FISEI                | 2.                                                                      |
| Grado de SOFTWARE             | 2.                                                                      |

Todos los casos obligatorios mostraron `[OK]`.

También se comprobó que TI es hoja, FISEI no es hoja, las consultas de nodos inexistentes muestran un aviso y las entradas inválidas no interrumpen el menú.

## Evidencias

Las capturas de ejecución y del historial de commits se encuentran en la carpeta [Ejecución del código y commits](<Ejecución del código y commits/>).

## Restricciones cumplidas

- Nodos implementados mediante referencias.
- Conteos y altura mediante recursividad.
- Separación en los paquetes modelo, negocio y app.
- Interacción mediante Scanner.
- Sin TreeSet, TreeMap, PriorityQueue, NavigableSet ni NavigableMap.
