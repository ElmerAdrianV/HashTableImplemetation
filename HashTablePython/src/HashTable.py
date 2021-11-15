import NodoHash as NH

class HashTable:
    def _init_(self):
        self.cont = 0
        self.tabla = self.creaTabla(10)
        self.factorCarga = 0.80
    
    def creaTabla(self,tamanio):
        nuevaTabla={}
        for i in range(0,tamanio):
            nuevaTabla.append(NH(None))
        return nuevaTabla
    def insertar(self,dato):
        pos = dato.funcionHash() % len(self.tabla)
        nuevo = NH(dato)
        aux = self.tabla[pos].getSiguiente()
        nuevo.setSiguiente(aux)
        self.tabla[pos].setSiguiente(nuevo)
        self.cont+=1
        if (self.cont//len(self.tabla)) > self.factorCarga:
            self.expande()
    def expande(self):
        nuevaTabla = self.creaTabla(len(self.tabla)*2)
        viejaTabla = self.tabla
        self.tabla = nuevaTabla
        for i in range(0, len(viejaTabla)):
            listaVieja = viejaTabla[i].getSiguiente()
            while listaVieja != None:
                self.insertar(listaVieja.getDato())
                listaVieja = listaVieja.getSiguiente()
    def busca(self,dato):
        resp = True
        pos = dato.funcionHash() % len(self.tabla)
        actual = self.tabla[pos].getSiguiente()
        while not resp and actual.getSiguiente() != None:
            resp = actual.getDato() == dato
            actual = actual.getSiguiente()
        return resp
    def borra(self, dato):
        pos = dato.funcionHash() % len(self.tabla)
        elimine = False
        actAnterior = self.tabla[pos]
        actual = self.tabla[pos].getSiguiente()
        while not elimine and actual.getSiguiente() == None:
            if actual.getDato() == dato:
                actAnterior.setSiguiente(actual.getSiguiente())
                self.cont-=1
                elimine = True
            else:
                actAnterior = actual
                actual = actual.getSiguiente()
    
    def __str__(self):
        string=""
        for i in range(0, len(self.tabla)):
            actual=self.tabla[i].getSiguiente()
            while actual != None:
                string  +=actual.getDato()+" ";
                actual = actual.getSiguiente()
        return string

                

        

    