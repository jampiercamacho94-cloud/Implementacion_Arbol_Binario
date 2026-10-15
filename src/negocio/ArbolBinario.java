package negocio;

import modelo.Nodo;

public class ArbolBinario<T> {

    private Nodo<T> raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public boolean crearRaiz(T dato) {
        if (raiz != null) {
            System.out.println("No se puede crear la raiz: el arbol ya tiene raiz ("
                    + raiz.getDato() + ").");
            return false;
        }
        raiz = new Nodo<>(dato);
        return true;
    }

    public boolean agregarIzquierdo(Nodo<T> padre, T dato) {
        if (padre == null) {
            System.out.println("No se puede insertar \"" + dato + "\": el nodo padre no existe.");
            return false;
        }
        if (padre.getIzquierdo() != null) {
            System.out.println("No se puede insertar \"" + dato + "\": el nodo \""
                    + padre.getDato() + "\" ya tiene hijo izquierdo (\""
                    + padre.getIzquierdo().getDato() + "\").");
            return false;
        }
        padre.setIzquierdo(new Nodo<>(dato));
        return true;
    }

    public boolean agregarDerecho(Nodo<T> padre, T dato) {
        if (padre == null) {
            System.out.println("No se puede insertar \"" + dato + "\": el nodo padre no existe.");
            return false;
        }
        if (padre.getDerecho() != null) {
            System.out.println("No se puede insertar \"" + dato + "\": el nodo \""
                    + padre.getDato() + "\" ya tiene hijo derecho (\""
                    + padre.getDerecho().getDato() + "\").");
            return false;
        }
        padre.setDerecho(new Nodo<>(dato));
        return true;
    }

    public Nodo<T> buscar(T dato) {
        return buscar(raiz, dato);
    }

    private Nodo<T> buscar(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getDato().equals(dato)) {
            return nodo;
        }
        Nodo<T> enIzquierdo = buscar(nodo.getIzquierdo(), dato);
        if (enIzquierdo != null) {
            return enIzquierdo;
        }
        return buscar(nodo.getDerecho(), dato);
    }

    public int contarNodos() {
        return contarNodos(raiz);
    }

    private int contarNodos(Nodo<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.getIzquierdo()) + contarNodos(nodo.getDerecho());
    }

    public int contarHojas() {
        return contarHojas(raiz);
    }

    private int contarHojas(Nodo<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.esHoja()) {
            return 1;
        }
        return contarHojas(nodo.getIzquierdo()) + contarHojas(nodo.getDerecho());
    }

    public int altura() {
        return altura(raiz);
    }

    private int altura(Nodo<T> nodo) {
        if (nodo == null) {
            return -1;
        }
        return 1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho()));
    }

    public void mostrarArbol() {
        if (esVacio()) {
            System.out.println("El arbol esta vacio.");
            return;
        }
        System.out.println(raiz.getDato());
        mostrarHijos(raiz, "");
    }

    private void mostrarHijos(Nodo<T> nodo, String prefijo) {
        Nodo<T> izq = nodo.getIzquierdo();
        Nodo<T> der = nodo.getDerecho();

        if (izq != null) {
            if (der != null) {
                System.out.println(prefijo + "|-- I: " + izq.getDato());
                mostrarHijos(izq, prefijo + "|   ");
            } else {
                System.out.println(prefijo + "`-- I: " + izq.getDato());
                mostrarHijos(izq, prefijo + "    ");
            }
        }
        if (der != null) {
            System.out.println(prefijo + "`-- D: " + der.getDato());
            mostrarHijos(der, prefijo + "    ");
        }
    }
}