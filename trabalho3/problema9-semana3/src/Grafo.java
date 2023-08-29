import java.util.LinkedList;

public class Grafo {
    LinkedList<Integer>[] adj;
    int vertices;
    private int[] valores;
    public Grafo(int v){
        this.vertices = v;
        this.valores = new int[v];
        this.adj = new LinkedList[v];
        for(int i = 0; i<v; i++){
            this.adj[i]= new LinkedList<>();
        }
    }

    public void novaAresta(int v, int w){
        this.adj[v].add(w);
        //this.adj[w].add(v);
    }

    public void limparGrafo(){
        for(int i=0; i<vertices; i++){
            this.adj[i].clear();
            System.out.println(this.adj[i]);
        }

    }

    public void setValores(int u, int valor) {
        this.valores[u] = valor;
    }

    public int getValores(int u) {
        return this.valores[u];
    }
}
