import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<String> nomes = new ArrayList<String>();
        ArrayList<String>[] linguagens = new ArrayList[n];
        for(int i = 0; i<n; i++)
            linguagens[i] = new ArrayList<>();
        clearBuffer(scanner);
        for(int i = 0; i<n;i++){
            String linha = scanner.nextLine();
            int espaco = linha.indexOf(" ");
            String linha2 = linha.substring(0, espaco);
            nomes.add(linha2);
            while(espaco > 0) {
                linha = linha.substring(espaco+1);
                espaco = linha.indexOf(" ");
                if(espaco<0)
                    linha2 = linha.substring(0);
                else
                    linha2 = linha.substring(0, espaco);
                linguagens[i].add(linha2);
            }
        }

        Grafo grafo = new Grafo(n);
        for(int i=0; i<n; i++){
            String lingua = linguagens[i].get(0);
            for(int j = 0; j<n;j++){
                if (j!=i)
                    if(linguagens[j].contains(lingua))
                        grafo.novaAresta(i,j);
            }
        }
        grafo.dfs();
        Grafo grafoT = grafo.transpose();
        grafoT.dfsTransposto(grafo);
        int quantidade = 0;
        int quantidadeMaior = 0;
        for(int i=0;i<grafoT.componenteQuant.size();i++){
            int aux = grafoT.componenteQuant.get(i) ;
            if(aux<grafoT.quantMaior)
                quantidade=quantidade+aux;
            else if(aux == grafoT.quantMaior)
                quantidadeMaior++;
        }
        if(quantidadeMaior>1)
            quantidade=quantidade+(quantidadeMaior-1)*grafoT.quantMaior;
        System.out.println(quantidade);
    }

    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }


}

class Grafo{
    LinkedList<Integer>[] adj;
    int vertices;
    private int[] valores;
    char []corVertice;
    int []descoberta;
    int tempo = 0;
    int componentes = 0;
    int quantMaior = 0;

    int contaComponentes = 0;

    ArrayList<Integer> componenteQuant = new ArrayList<>();
    public Grafo(int v){
        this.vertices = v;
        this.descoberta = new int[v];
        this.corVertice = new char[v];
        this.valores = new int[v];
        this.adj = new LinkedList[v];
        for(int i = 0; i<v; i++){
            this.adj[i]= new LinkedList<>();
            this.corVertice[i] = 'b';
        }
    }

    public void novaAresta(int v, int w){
        if(!adj[v].contains(w))
            this.adj[v].add(w);
    }

    public void setValores(int u, int valor) {
        this.valores[u] = valor;
    }

    public int getValores(int u) {
        return this.valores[u];
    }

    public Grafo transpose() {
        Grafo gt = new Grafo(vertices);
        for (int i = 0; i < vertices; i++) {
            for (int adj : adj[i])
                gt.novaAresta(adj,i);
        }

        return gt;
    }

    public void dfsTransposto(Grafo gOri){
        ArrayList<Integer> descobertaF = new ArrayList<>();
        for(int i = 0; i<vertices;i++){
            int aux = gOri.descoberta[i];
            descobertaF.add(aux);
        }
        for(int i=vertices*2; i>0;i--){
            int pos = descobertaF.indexOf(i);
            if(pos>=0){
                if(corVertice[pos] == 'b'){
                    dfs_visita(pos);
                    componentes=componentes+1;
                }
                componenteQuant.add(contaComponentes);
                if(quantMaior<contaComponentes)
                    quantMaior=contaComponentes;
            }
            contaComponentes = 0;
        }
    }

    public void dfs(){
        for(int i = 0; i<vertices;i++){
            if(corVertice[i]=='b')
                dfs_visita(i);
        }
    }

    public void dfs_visita(int u){
        tempo++;
        corVertice[u] = 'c';
        for(int v:adj[u]){
            if(corVertice[v] == 'b')
                dfs_visita(v);
        }
        corVertice[u] = 'p';
        contaComponentes++;
        tempo++;
        descoberta[u] = tempo;
    }
}