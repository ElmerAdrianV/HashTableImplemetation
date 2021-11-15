package src;

public class HashTable<T> {
    NodoHash<T> tabla[];
    int cont;
    double factorCarga = 0.7;
    // regla de dedo es 0.7 es decir que cuando el numero de elementos almacenados
    // en la tabla sea el 70% del tama;o de la tabla entonces se expande

    // Constructor
    public HashTable() {
        cont = 0;
        tabla = creaTabla(10);
    }

    private NodoHash<T>[] creaTabla(int tam) {
        NodoHash<T> [] tablaAux=(NodoHash<T>[]) new Object[tam];
        //impoNodoHash<T>[] tablaAux = new NodoHash[tam];
        for (int i = 0; i < tablaAux.length; i++)
            tablaAux[i] = new NodoHash<>(null);
        return tablaAux;
    }

    public void insertar(T dato) {
        // Suposición que el dato tiene implementado el metodo hashCode().
        // Suposicion que en cada posición de la tabla tengo un nodo cabeza con un elemento null con el 
        // que se accede a la lista de datos encadenados.
        //Obtengo la posicion en la tabla donde va a estar el dato
        int pos = dato.hashCode() % tabla.length;
        //Creo el nodo que almacena el dato
        NodoHash<T> nuevo = new NodoHash<>(dato);
        // Obtengo la lista de elementos que hay en la posicion de la tabla
        NodoHash<T> aux = tabla[pos].getSiguiente();
        // enlazo el nuevo nodo con la lista de elementos que hay en la posicion de la tabla
        nuevo.setSiguiente(aux);
        // enlazo el nuevo nodo, ya enlazdo con los demas elementos, en la posicion de la tabla que le corresponde.
        tabla[pos].setSiguiente(nuevo);
        //incremento el número de elementos en la tabla
        cont++;
        // expando la lista si la densidad de la tabla es mayor que el factor de carga
        if ((cont / tabla.length) > factorCarga)
            expande();
    }
    


    private void expande() {
        // Creo una nueva tabla con el doble de tamaño que la tabla actual
        NodoHash<T>[] nuevaTabla = creaTabla(tabla.length * 2);
        // obtengo la tabla actual con los datos.
        NodoHash<T>[] viejaTabla = this.tabla;
        // Cambio mi apuntador de tabla a la nueva tabla
        this.tabla = nuevaTabla;
        // Recorro la tabla vieja
        for(int i = 0; i < viejaTabla.length; i++){
            //Inserto cada elemento de la lista de elementos de la tabla vieja en la nueva tabla
            NodoHash<T> listaViejaI = viejaTabla[i].getSiguiente();
            while(listaViejaI != null){
                insertar(listaViejaI.getDato());
                listaViejaI = listaViejaI.getSiguiente();
            }
        }
    }

    //NodoHash<T>[] nuevaTabla = creaTabla(tabla.length);
    public String toString() {
        String s = "";
        NodoHash<T> actual;
        for (int i = 0; i < tabla.length; i++) {
            actual = tabla[i].getSiguiente();
            while (actual != null) {
                s += actual.getDato() + " ";
                actual = actual.getSiguiente();
            }
        }
        return s;

    }

    // Método de busqueda
    public boolean busca(T elem) {
        boolean resp = false;
        int pos = elem.hashCode() % tabla.length;
        NodoHash<T> actual = tabla[pos].getSiguiente();
        while (!resp && actual.getSiguiente() != null) {
            resp = actual.getDato().equals(elem);
            actual = actual.getSiguiente();
        }
        return resp;
    }

    // Método para borrar
    public void borra(T elem) {
        int pos = elem.hashCode() % tabla.length;
        boolean elimine = false;
        NodoHash<T> actAnterior = tabla[pos], actual = tabla[pos].getSiguiente();
        while (!elimine && actual.getSiguiente() != null) {
            if (actual.getDato().equals(elem)) {
                actAnterior.setSiguiente(actual.getSiguiente());
                cont--;
                elimine=true;
            }
            else{
                actAnterior=actual;
                actual=actual.getSiguiente();
            }
        }
    }
}
