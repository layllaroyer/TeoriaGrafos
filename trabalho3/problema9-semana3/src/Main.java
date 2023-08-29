import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        int n;
        try {
            Grafo g;

            FileReader arq = new FileReader("teste.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine(); // lê a primeira linha
            while(linha!=null){
                n = Integer.parseInt(String.valueOf(linha.charAt(0)));
                g = criarGrafo(n);
                linha = lerArq.readLine(); // lê a segunda linha
                while (linha != null) {
                    if(linha.length()!=0){
                        int a = Integer.parseInt(String.valueOf(linha.charAt(0)));
                        int nligacao = Integer.parseInt(String.valueOf(linha.charAt(3)));
                        for (int i = 0; i < nligacao; i++) {
                            int b = Integer.parseInt(String.valueOf(linha.charAt(6 + 2 * i)));
                            g.novaAresta(a, b);
                        }
                        linha = lerArq.readLine(); // lê da terceira até a última linha
                    }else
                        linha=null;
                }
                preencheGrafo(g);
                System.out.println(" ");
                linha = lerArq.readLine();
            }
            arq.close();

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        System.out.println();

    }

    public static void preencheGrafo(Grafo g){
        int parent[] = new int [g.vertices];
        int visited[] = new int[g.vertices];
        int dfs_num[] = new int[g.vertices];
        int dfs_low[] = new int[g.vertices];

        int dfs_n = 0;
        LinkedList<Integer>respostas= new LinkedList<>();

        for(int i=0; i<g.vertices; i++) {
            visited[i] = 0;
            parent[i] = 0;
        }
        achaponte(g,dfs_num,dfs_low,visited,parent, dfs_n,respostas);
        while(!respostas.isEmpty()) {
            int a= respostas.poll();
            int b= respostas.poll();
            System.out.printf("%d - %d\n", a,b);
        }
    }
    public static Grafo criarGrafo(int v){
        Grafo g= new Grafo(v);
        return g;
    }
    public static void dfspontes(int v, Grafo g, int dfs_num[], int dfs_low[], int visited[], int parent[], int dfs_n, LinkedList resp){

        dfs_num[v]= dfs_low[v] = dfs_n;
        dfs_n=dfs_n+1;
        // Marca v como visitado
        visited[v] = 2; //preto=2
        // Número de vizinhos imediatos de v não visitados
        int children = 0;
        for (int s : g.adj[v]) {
            // Para cada aresta (u,v)  branco ==0
            if (visited[s] == 0) {
                // Se v não foi visitado, aplique o algoritmo recursivamente
                children++;
                parent[s] = v;
                dfspontes(s,g, dfs_num,dfs_low,visited,parent, dfs_n,resp);
                // atualize o menor nó alcançável por u se preciso
                dfs_low[v]=min(dfs_low[v], dfs_low[s]);
                if((parent[v]!= -1 ) && (dfs_low[s]>dfs_num[v])){
                    resp.add(v);
                    resp.add(s);

                }

            }
            else if (s!=parent[v]) {
                dfs_low[v] = min(dfs_low[v], dfs_num[s]);

            }
        }
    }

    public static void achaponte(Grafo g,int dfs_num[], int dfs_low[], int visited[], int parent[], int dfs_n,LinkedList resp){
        int total=0;
        for(int i=0;i<g.vertices;i++){
            if(visited[i]==0){
                dfspontes(i,g, dfs_num,dfs_low,visited,parent, dfs_n,resp);
                total++;
            }
        }
        System.out.printf("%d critical links\n", total);
    }
    public static int min(int num1, int num2){
        if(num1<num2)
            return num1;
        return num2;
    }
}

