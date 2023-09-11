import java.util.LinkedList;
import java.util.*;

public class Grafo {
    LinkedList<Integer>[] adj;
    int vertices;
    private String[] valores;
   public Grafo(int v){
        this.vertices = v;
        this.valores = new String[v];
        this.adj = new LinkedList[v];
        for(int i = 0; i<v; i++){
            this.adj[i]= new LinkedList<>();
        }
    }

    public void novaAresta(int v, int w){
        if(!adj[v].contains(w))
            this.adj[v].add(w);
    }

    public void setValores(int u, String valor) {
        this.valores[u] = valor;
    }

    public String getValores(int u) {
        return this.valores[u];
    }


}

