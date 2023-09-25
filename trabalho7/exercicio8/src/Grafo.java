import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Grafo {
    LinkedList<Integer>[] adj;
    int vertices;
    private char[] valores;
    boolean[] marked;


    public Grafo(int v){
        this.vertices = v;
        this.valores = new char[v];
        this.adj = new LinkedList[v];
        this.marked = new boolean[v];
        for(int i = 0; i<v; i++){
            this.adj[i]= new LinkedList<>();
            valores[i] = '#';
            marked[i] = false;
        }
    }

    public void novaAresta(int v, int w){
        this.adj[v].add(w);
        this.adj[w].add(v);
    }

    public void setValores(int u, char valor) {
        this.valores[u] = valor;
    }

    public char getValores(int u) {
        return this.valores[u];
    }

    public int existeValor(char c) {
        for(int i = 0; i<valores.length; i++){
            if(valores[i] == c)
                return i;
        }
        return -1;
    }
    public void mostrarValores(){
        System.out.println(valores);
    }

    int bfs_menorCaminho(int s, int[] distTo, int[] edgeTo, int largura, int altura){
        Queue <Integer> q = new LinkedList<>();
        marked[s] = true;
        distTo[s] = 0;
        q.add(s);
        int des=0;
        while(!q.isEmpty()){
            int v=q.remove();
            for(int w:adj[v]){
                int aux1 = w%largura;
                if((w<largura)||(w>largura*(altura-1))||(aux1==0)||(aux1==1))
                    if(valores[w]=='.')
                        des = w;

                if(!marked[w]){
                    edgeTo[w]=v;
                    distTo[w] = distTo[v]+1;
                    marked[w] = true;
                    q.add(w);
                }
            }
        }
        return des;
    }

    void bfs(int s, int[] distTo){
        Queue <Integer> q = new LinkedList<>();
        boolean marked2[] = new  boolean[vertices];
        distTo[s] = 0;
        q.add(s);

        while(!q.isEmpty()){
            int v=q.remove();
            for(int w:adj[v]){
                if(!marked2[w]){
                    distTo[w] = distTo[v]+1;
                    marked2[w] = true;
                    q.add(w);
                }
            }
        }
    }


}