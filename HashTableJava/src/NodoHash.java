package src;
public class NodoHash<T> {
    private T dato;
    private NodoHash<T> siguiente;

    public NodoHash(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoHash<T> getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoHash<T> siguiente) {
        this.siguiente = siguiente;
    }
}
