package app;

import java.util.Scanner;
import modelo.Nodo;
import negocio.ArbolBinario;

public class Main {

    public static void main(String[] args) {
        // Si se ejecuta con el argumento "pruebas", corre los casos de prueba
        if (args.length > 0 && args[0].equalsIgnoreCase("pruebas")) {
            ejecutarPruebas();
        } else {
            ejecutarMenu();
        }
    }

    private static void ejecutarMenu() {
        Scanner scanner = new Scanner(System.in);
        ArbolBinario<String> arbol = new ArbolBinario<>();
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion(scanner);

            switch (opcion) {
                case 1:
                    crearArbolInicial(arbol);
                    break;
                case 2:
                    arbol.mostrarArbol();
                    break;
                case 3:
                    System.out.println("Numero de nodos: " + arbol.contarNodos());
                    break;
                case 4:
                    System.out.println("Numero de hojas: " + arbol.contarHojas());
                    break;
                case 5:
                    System.out.println("Altura (en aristas): " + arbol.altura());
                    break;
                case 6:
                    consultarGrado(scanner, arbol);
                    break;
                case 7:
                    consultarHoja(scanner, arbol);
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion invalida. Elija un numero del 1 al 8.");
            }
        } while (opcion != 8);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("=== DEPENDENCIAS ACADEMICAS ===");
        System.out.println("1. Crear el arbol inicial");
        System.out.println("2. Visualizar el arbol");
        System.out.println("3. Consultar el numero de nodos");
        System.out.println("4. Consultar el numero de hojas");
        System.out.println("5. Consultar la altura");
        System.out.println("6. Consultar el grado de un nodo");
        System.out.println("7. Verificar si un nodo es hoja");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static int leerOpcion(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static String leerNombre(Scanner scanner) {
        System.out.print("Nombre de la dependencia: ");
        return scanner.nextLine().trim().toUpperCase();
    }

    private static void crearArbolInicial(ArbolBinario<String> arbol) {
        if (!arbol.esVacio()) {
            System.out.println("El arbol ya fue creado. Use la opcion 2 para verlo.");
            return;
        }
        construirInicial(arbol);
        System.out.println("Arbol inicial creado correctamente.");
    }

    private static void consultarGrado(Scanner scanner, ArbolBinario<String> arbol) {
        if (arbol.esVacio()) {
            System.out.println("El arbol esta vacio. Use la opcion 1 para crearlo.");
            return;
        }
        String nombre = leerNombre(scanner);
        Nodo<String> nodo = arbol.buscar(nombre);
        if (nodo == null) {
            System.out.println("El nodo \"" + nombre + "\" no existe en el arbol.");
        } else {
            System.out.println("Grado de " + nombre + ": " + nodo.grado());
        }
    }

    private static void consultarHoja(Scanner scanner, ArbolBinario<String> arbol) {
        if (arbol.esVacio()) {
            System.out.println("El arbol esta vacio. Use la opcion 1 para crearlo.");
            return;
        }
        String nombre = leerNombre(scanner);
        Nodo<String> nodo = arbol.buscar(nombre);
        if (nodo == null) {
            System.out.println("El nodo \"" + nombre + "\" no existe en el arbol.");
        } else if (nodo.esHoja()) {
            System.out.println(nombre + " es una hoja.");
        } else {
            System.out.println(nombre + " NO es una hoja.");
        }
    }

    private static void construirInicial(ArbolBinario<String> arbol) {
        arbol.crearRaiz("UNIVERSIDAD");
        arbol.agregarIzquierdo(arbol.buscar("UNIVERSIDAD"), "FISEI");
        arbol.agregarDerecho(arbol.buscar("UNIVERSIDAD"), "DIDE");
        arbol.agregarIzquierdo(arbol.buscar("FISEI"), "SOFTWARE");
        arbol.agregarDerecho(arbol.buscar("FISEI"), "TI");
        arbol.agregarIzquierdo(arbol.buscar("DIDE"), "INVESTIGACION");
        arbol.agregarDerecho(arbol.buscar("DIDE"), "VINCULACION");
        arbol.agregarIzquierdo(arbol.buscar("SOFTWARE"), "NIVEL1");
        arbol.agregarDerecho(arbol.buscar("SOFTWARE"), "NIVEL3");
    }

    private static void ejecutarPruebas() {
        System.out.println("=== CASOS DE PRUEBA ===");

        System.out.println("\nPrueba 1: arbol vacio");
        ArbolBinario<String> vacio = new ArbolBinario<>();
        verificar("Cero nodos", vacio.contarNodos() == 0);
        verificar("Altura -1", vacio.altura() == -1);

        System.out.println("\nPrueba 2: arbol con un solo nodo");
        ArbolBinario<String> unico = new ArbolBinario<>();
        unico.crearRaiz("SOLO");
        verificar("Una hoja", unico.contarHojas() == 1);
        verificar("Altura 0", unico.altura() == 0);

        System.out.println("\nPrueba 3: arbol inicial");
        ArbolBinario<String> inicial = new ArbolBinario<>();
        construirInicial(inicial);
        verificar("9 nodos", inicial.contarNodos() == 9);
        verificar("5 hojas", inicial.contarHojas() == 5);
        verificar("Altura 3", inicial.altura() == 3);

        System.out.println("\nPrueba 4: insertar en posicion ocupada");
        boolean insertado = inicial.agregarIzquierdo(inicial.buscar("FISEI"), "OTRO");
        verificar("Insercion rechazada", !insertado);
        verificar("FISEI conserva a SOFTWARE como hijo izquierdo",
                inicial.buscar("FISEI").getIzquierdo().getDato().equals("SOFTWARE"));
        verificar("El arbol sigue con 9 nodos", inicial.contarNodos() == 9);

        System.out.println("\nPrueba 5: grado de FISEI y SOFTWARE");
        verificar("Grado de FISEI = 2", inicial.buscar("FISEI").grado() == 2);
        verificar("Grado de SOFTWARE = 2", inicial.buscar("SOFTWARE").grado() == 2);
    }

    private static void verificar(String descripcion, boolean condicion) {
        if (condicion) {
            System.out.println("  [OK] " + descripcion);
        } else {
            System.out.println("  [FALLO] " + descripcion);
        }
    }
}
