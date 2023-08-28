public class Aresta<T> {
    private Vertice<T> inicio;
    private Vertice<T> fim;

    public Aresta(Vertice<T> inicio, Vertice<T> fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public Vertice<T> getInicio() {
        return inicio;
    }

    public Vertice<T> getFim() {
        return fim;
    }
    public String toString() {
        return "Aresta{" + inicio.getDado() + " -> " + fim.getDado() + "}";
    }

}


