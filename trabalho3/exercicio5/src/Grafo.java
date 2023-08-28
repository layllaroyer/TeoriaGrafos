import java.util.ArrayList;

public class Grafo<T> {
    private ArrayList<Vertice<T>> vertices;
    private ArrayList<Aresta<T>> arestas;
    private int time;
    private int[] dfs_num;
    private int[] dfs_low;
    private int[] visited;
    private int[] parent;
    private int[] dfs_parent;  
    private int dfs_n = 0;
    private ArrayList<Aresta<T>> bridges;  
    ArrayList<String> guarda = new ArrayList<>();


    public Grafo() {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
        this.bridges = new ArrayList<>();
        this.time = 0;
    }

    public void addVertice(T dado) {
        Vertice<T> novoVertice = new Vertice<>(dado);
        this.vertices.add(novoVertice);
    }

    public int getNumVertices() {
        return vertices.size();
    }
    public String findBridges() {
        int numVertices = vertices.size();
        dfs_num = new int[numVertices];
        dfs_low = new int[numVertices];
        visited = new int[numVertices];
        parent = new int[numVertices];
        dfs_parent = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            if (visited[i] == 0) {
                dfsForBridges(i);
            }
        }

        StringBuilder result = new StringBuilder();

        if (bridges.isEmpty()) {
            result.append("No bridges found");
        } else {
            for (Aresta<T> bridge : bridges) {
                guarda.add(bridge.toString());
                result.append(bridge.toString()).append("\n");
            }
        }

        return result.toString();
    }
    private void dfsForBridges(int u) {
        dfs_num[u] = dfs_low[u] = dfs_n;
        dfs_n++;
        visited[u] = 1;

        for (Aresta<T> aresta : vertices.get(u).getArestasSaida()) {
            Vertice<T> vizinho = aresta.getFim();
            int v = vertices.indexOf(vizinho);

            if (visited[v] == 0) {
                parent[v] = u;
                dfsForBridges(v);
                dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);

                if (dfs_low[v] > dfs_num[u]) {
                    bridges.add(aresta);
                }
            } else if (v != parent[u] && dfs_num[v] < dfs_num[u]) {
                dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
            }
        }
    }

    public void addAresta(int indexInicio, int indexFim) {
        Vertice<T> inicio = getVertice(indexInicio);
        Vertice<T> fim = getVertice(indexFim);

        if (inicio != null && fim != null) {
            Aresta<T> aresta = new Aresta<>(inicio, fim);
            inicio.addArestaSaida(aresta);
            fim.addArestaEntrada(aresta);
            this.arestas.add(aresta);
        }
    }

    public Vertice<T> getVertice(int index) {
        if (index >= 0 && index < vertices.size()) {
            return vertices.get(index);
        }
        return null;
    }

    public ArrayList<Aresta<T>> getArestas() {
        return arestas;
    }
    public void removeAresta(Aresta<T> aresta) {
        Vertice<T> inicio = aresta.getInicio();
        Vertice<T> fim = aresta.getFim();

        inicio.removeArestaSaida(aresta);
        fim.removeArestaEntrada(aresta);

        arestas.remove(aresta);
    }

}



