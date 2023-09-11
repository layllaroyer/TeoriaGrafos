import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.*;
import java.util.Comparator;

public class Grafo {
    public Grafo(int vertices) {
        this.vertices = vertices;
    }

    private int vertices;
    private ArrayList<Aresta> todasArestas = new ArrayList<>();

    public void adicionarAresta(int origem, int destino, int peso) {
        Aresta aresta = new Aresta(origem, destino, peso);
        todasArestas.add(aresta);
    }

    public ArrayList<Aresta> kruskalMST() {
        PriorityQueue<Aresta> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.peso));
        for (int i = 0; i < todasArestas.size(); i++) {
            pq.add(todasArestas.get(i));
        }

        int[] pai = new int[vertices];
        criarConjunto(pai);

        ArrayList<Aresta> mst = new ArrayList<>();
        int indice = 0;

        while (indice < vertices - 1 && !pq.isEmpty()) {
            Aresta aresta = pq.remove();
            int conjuntoOrigem = encontrar(pai, aresta.origem);
            int conjuntoDestino = encontrar(pai, aresta.destino);

            if (conjuntoOrigem == conjuntoDestino) {
                continue; // Ignorar arestas que formam ciclos
            } else {
                mst.add(aresta);
                indice++;
                unir(pai, conjuntoOrigem, conjuntoDestino);
            }
        }
        return mst;
    }

    private void criarConjunto(int[] pai) {
        for (int i = 0; i < vertices; i++) {
            pai[i] = i;
        }
    }

    private int encontrar(int[] pai, int vertice) {
        if (pai[vertice] != vertice) {
            return encontrar(pai, pai[vertice]);
        }
        return vertice;
    }

    private void unir(int[] pai, int x, int y) {
        int conjuntoPaiY = encontrar(pai, y);
        pai[conjuntoPaiY] = x;
    }

    public void imprimir(ArrayList<Aresta> listaArestas) {
        if (listaArestas.isEmpty()) {
            System.out.println("Impossível");
        } else {
            Aresta maiorPeso = listaArestas.get(0);
            List<Aresta> lista=new ArrayList<>();
            for (int i = listaArestas.size()-1; i>=0; i--) {
                lista.add(listaArestas.get(i));
            }
            Collections.sort(lista,new Comparador());
            for (Aresta aresta : lista) {
                System.out.println(aresta.origem+" "+aresta.destino);
            }
        }
    }
}


