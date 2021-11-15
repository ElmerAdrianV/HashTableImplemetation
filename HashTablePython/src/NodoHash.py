"""
Autores:
    -   Ruben 
    -   Adaya
    -   Elmer
"""

class NodoHash:
    def __init__(self, dato): 
        self.dato = dato
        self.siguiente = None
    def getDato(self):
        return self.dato
    def setDato(self,nuevoDato):
        self.dato = nuevoDato
    def getSiguiente(self):
        return self.siguiente
    def setSiguiente(self,nuevoSig):
        self.siguiente = nuevoSig