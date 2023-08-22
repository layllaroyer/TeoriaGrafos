import java.util.LinkedList;

public class Digrafo {
    LinkedList<Integer>[] adj;
    int vertices;
    private char[] valores;
    public Digrafo(int v){
        this.vertices = v;
        this.valores = new char[v];
        this.adj = new LinkedList[v];
        for(int i = 0; i<v; i++){
            this.adj[i]= new LinkedList<>();
            valores[i] = '!';
        }
    }

    public void novaAresta(int v, int w){
        this.adj[v].add(w);
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
}