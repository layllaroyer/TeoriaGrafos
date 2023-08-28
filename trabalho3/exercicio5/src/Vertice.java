import java.util.ArrayList;

public class Vertice<T> {
    private T dado;
    private ArrayList<Aresta<T>> arestasEntrada;
    private ArrayList<Aresta<T>> arestasSaida;
    private boolean visited;
    private int discoveryTime;
    private int lowTime;

    public Vertice(T valor) {
        this.dado = valor;
        this.arestasEntrada = new ArrayList<>();
        this.arestasSaida = new ArrayList<>();
        this.visited = false;
        this.discoveryTime = -1;
        this.lowTime = -1;
    }
        public T getDado() {
            return dado;
        }

        public void setDado(T dado) {
            this.dado = dado;
        }

        public ArrayList<Aresta<T>> getArestasEntrada() {
            return arestasEntrada;
        }

        public ArrayList<Aresta<T>> getArestasSaida() {
            return arestasSaida;
        }


        public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

        public void addArestaEntrada(Aresta<T> aresta) {
            this.arestasEntrada.add(aresta);
        }

        public void addArestaSaida(Aresta<T> aresta) {
            this.arestasSaida.add(aresta);
        }

    public void removeArestaSaida(Aresta aresta) {
        this.arestasSaida.remove(aresta);
    }

    public void removeArestaEntrada(Aresta aresta) {
        this.arestasEntrada.remove(aresta);
    }
}



