package trabalho2.exercicio2;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    String nome;
    List<Aresta> adj;

    Vertice(String nome) {
        this.nome = nome;
        this.adj = new ArrayList<Aresta>();
    }

    void addAdj(Aresta e) {
        adj.add(e);
    }
}
