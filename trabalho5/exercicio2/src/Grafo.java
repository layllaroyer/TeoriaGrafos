import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Grafo {
    int vertices;
    ArrayList<Aresta> allArestas = new ArrayList<>();
    int somaOriginal = 0;
    int somaEconomica = 0;
    int total = 0;

    Grafo(int vertices) {
        this.vertices = vertices;
    }

    public int somaCusto(){
        for(Aresta caminho : allArestas){
            somaOriginal += caminho.peso;
        }
        return somaOriginal;
    }

    public void addAresta(int origem, int destino, int peso) {
        Aresta aresta = new Aresta(origem, destino, peso);
        allArestas.add(aresta);
    }

    public void kruskalMST() {
        PriorityQueue<Aresta> filaPrioridade = new PriorityQueue<>(allArestas.size(), Comparator.comparingInt(o -> o.peso));
        for (int i = 0; i < allArestas.size(); i++) {
            filaPrioridade.add(allArestas.get(i));
        }

        int[] pai = new int[vertices];

        makeSet(pai);

        ArrayList<Aresta> mst = new ArrayList<>();

        int index = 0;
        while (index < vertices - 1) {
            Aresta edge = filaPrioridade.remove();
            int x_set = find(pai, edge.origem);
            int y_set = find(pai, edge.destino);

            if (x_set == y_set) {
            } else {
                mst.add(edge);
                index++;
                union(pai, x_set, y_set);
            }
        }
        calculaCustos(mst);
    }

    public void makeSet(int[] pai) {
        for (int i = 0; i < vertices; i++) {
            pai[i] = i;
        }
    }

    public int find(int[] pai, int vertice) {
        if (pai[vertice] != vertice)
            return find(pai, pai[vertice]);
        ;
        return vertice;
    }

    public void union(int[] parent, int x, int y) {
        int x_set_parent = find(parent, x);
        int y_set_parent = find(parent, y);

        parent[y_set_parent] = x_set_parent;
    }

    public void calculaCustos(ArrayList<Aresta> arestaMinimaLista) {
        for (int i = 0; i < arestaMinimaLista.size(); i++) {
            Aresta aresta = arestaMinimaLista.get(i);
            somaEconomica += aresta.peso;
        }
        somaOriginal = somaCusto();
        total = somaOriginal - somaEconomica;
        System.out.println(total);
        System.out.println();
    }
}