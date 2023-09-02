import java.util.*;
import java.io.*;

public class p11838 {


    public static void main(String args[]) throws Exception {



        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while ( true ) {

            String l  = br.readLine();
            if (l.equals("0 0")) System.exit(0);
            String numeros[] = l.split(" ");
            int N = new Integer(numeros[0]);
            int M = new Integer(numeros[1]);

            Grafo g = new Grafo(N); // n Ã© o numero de vertices (interseÃ§Ãµes da cidade)

            for (int i = 0; i < M; i++) {
                l  = br.readLine();
                numeros = l.split(" ");
                int u = new Integer(numeros[0]);
                int v = new Integer(numeros[1]);

                g.addEdge(u,v);
                if (numeros[2].equals("2"))
                    g.addEdge(v,u);


                Stack<Integer> minha_pilha = new Stack<>();

                for (int i = 0; i < N; i++) {
                    if (g.marked[i] == true) continue;
                    g.dfs(i, minha_pilha);
                }

                Grafo gt = g.transpose();

                int SCC = 0;
                for (Integer u: minha_pilha) {

                    if (gt.marked[u] == true) continue;
                    gt.dfs(u, new Stack<>());
                    SCC++;
                    if (SCC > 1) {
                        System.out.println("0");
                        break;
                    }
                }
                if (SCC == 1) System.out.println("1");

            }


        }


    }
}


class Grafo {

    ArrayList<Integer>[] G;
    boolean marked[];
    public Grafo(int N) {
        marked = new boolean[N];
        G = new ArrayList[N];
        for (int i = 0; i < N; i++)
            G[i] = new ArrayList<>();

        for (int i =0 ; i < N; i++) marked[i] = false;
    }

    Grafo transpose() {
        Grafo gt = new Grafo(G.length);
        for (int i = 0; i < G.length; i++) {
            for (int adj : G[i])
                gt.addEdge(adj,i);
        }

        return gt;
    }

    void dfs(int u , Stack<Integer> pilha) {

        marked[u]  = true;
        for (Integer v : G[u]) {
            if (marked[v] == false)
                dfs(v, pilha);
        }
        pilha.push(u);
    }


}