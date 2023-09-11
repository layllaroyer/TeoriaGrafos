import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int n;
        while (true) {
            n = entrada.nextInt();
            if (n == 0) {
                break;
            }
            ArrayList<String> primeirapalavra = new ArrayList<String>();
            ArrayList<String>[] palavras = new ArrayList[n];
            for (int i = 0; i < n; i++)
                palavras[i] = new ArrayList<>();
            clearBuffer(entrada);
            for (int i = 0; i < n; i++) {
                String linha = entrada.nextLine();
                int espaco = linha.indexOf(" ");
                String linha2 = linha.substring(0, espaco);
                palavras[i].add(linha2);
                primeirapalavra.add(linha2);
                while (espaco > 0) {
                    linha = linha.substring(espaco + 1);
                    espaco = linha.indexOf(" ");
                    if (espaco < 0)
                        linha2 = linha.substring(0);
                    else
                        linha2 = linha.substring(0, espaco);
                    palavras[i].add(linha2);
                }
            }

            Grafo g = new Grafo(n);
            for(int i=0; i<n; i++){
                String p = palavras[i].get(0);
                for(int j = 0; j<n;j++){
                    if (j!=i)
                        if(palavras[j].contains(p))
                            g.novaAresta(j,i);

                }
            }
            int visited[] = new int[g.vertices];
            int dfs_num[] = new int[g.vertices];
            int dfs_low[] = new int[g.vertices];
            int dfs_n = 0, contador=0;
            Stack pilha=new Stack<>();
            LinkedList<Integer> respostas= new LinkedList<>();
            for(int i=0; i<g.vertices; i++) {
                visited[i] = 0;
            }
            ArrayList<Integer>[] resposta = new ArrayList[n];
            for (int i = 0; i < n; i++)
                resposta[i] = new ArrayList<>();
            achafortementeconectados(dfs_num, dfs_low,  visited, dfs_n,g, pilha, resposta, contador);
            System.out.println(resposta[0].size());
            ArrayList<String> respostafinal = new ArrayList<String>();

            for(int i=0;i<resposta[0].size();i++){
                respostafinal.add(primeirapalavra.get(resposta[0].get(i)));
            }
            Collections.sort(respostafinal);
            int tamanho= respostafinal.size();
            for(int i=0;i<tamanho;i++) {
                System.out.printf("%s ", respostafinal.get(i));
            }
            System.out.println("");
        }
    }

    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
    public static void fortementeconectado(int u,int dfs_num[], int dfs_low[], int visited[], int dfs_n,Grafo g, Stack pilha,ArrayList<Integer>[] resposta, int contador) {
        dfs_num[u] = dfs_low[u] = dfs_n;
        dfs_n++;
        // Marca o nó como em processamento
        visited[u] = 1;
        // Insere o nó na pilha
        pilha.push(u);
        for (int v : g.adj[u]) {
            // Para cada aresta (u,v)
            if (visited[v] == 0) {
                // Se v não foi visitado, aplique o algoritmo recursivamente
                fortementeconectado(v,dfs_num, dfs_low,  visited, dfs_n,g, pilha, resposta, contador);
                // atualize o menor nó alcançável por u se preciso
                dfs_low[u] = min(dfs_low[u], dfs_low[v]);
            } else if (visited[v] == 1) {
                /** Se v foi visitado, porém não finalizado, então existe uma
                 * back-edge de u para v, correspondendo a um ciclo, logo
                 * atualiza-se dfs_low com a numeração de DFS de v, caso
                 * necessário
                 **/
                dfs_low[u] = min(dfs_low[u], dfs_num[v]);
            }
        }
        /**
         * Caso dfs_low[u] == dfs_num[u] então todos os nós pertencentes à
         * componente fortemente conexa de u estão na pilha.
         *
         **/
        if (dfs_low[u] == dfs_num[u]) {
            int v;
            /** Imprime os nós que compartilham a mesma componente
             * fortemente conexa de u.
             */
            do {
                v = (int)pilha.pop();
                visited[v] = 2;
                resposta[contador].add(v);
            } while (u != v);

        }
    }

    /**
     * @brief Aplica o algoritmo scc para cada nó não visitado
     */
    public static void achafortementeconectados(int dfs_num[], int dfs_low[], int visited[], int dfs_n,Grafo g, Stack pilha, ArrayList<Integer>[] resposta, int contador){
        for(int i=0;i<g.vertices;i++){
            if(visited[i]==0){
                fortementeconectado(i,dfs_num, dfs_low,  visited, dfs_n,g, pilha, resposta,contador);
                contador++;
            }
        }
    }
    public static int min(int num1, int num2){
        if(num1<num2)
            return num1;
        return num2;
    }
}

