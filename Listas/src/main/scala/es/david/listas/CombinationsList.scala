package es.david.listas

object CombinationsList{
    // combinations itera sobre combinaciones, estas son de longitud n y con orden, tomados de una lista
    // si hay mas de una manera de generar una solo devolvera una.
    def getCombinaciones(lista: String, n: Int) = {
        lista.combinations(n).toList
    }
}