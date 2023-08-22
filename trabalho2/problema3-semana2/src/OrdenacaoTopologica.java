import java.util.*;
public class OrdenacaoTopologica  {

        boolean[] marcado ;
        Stack<Integer>posOrdemRev;
        public OrdenacaoTopologica (Digrafo g) {
            posOrdemRev = new Stack<Integer>();
            marcado = new boolean[g.vertices] ;
            for(int v=0;v<g.vertices;v++)
            if(!marcado[v]) dfs(g, v); ;
            }
        void dfs(Digrafo g, int v ) {
            marcado[v] = true ;
            for (int w:g.adj[v]){
                if (!marcado[w]) dfs(g,w) ;
            }
            posOrdemRev.push(v);
        }

         public Stack<Integer>ordem() {
            return posOrdemRev ;
        }

    private boolean isCyclicUtil(int v, boolean[] visitado, boolean[] recStack, Digrafo g) {
        if (recStack[v]) {
            return true;
        }

        if (visitado[v]) {
            return false;
        }

        visitado[v] = true;
        recStack[v] = true;

        for (Integer neighbor : g.adj[v]) {
            if (isCyclicUtil(neighbor, visitado, recStack,g)) {
                return true;
            }
        }

        recStack[v] = false;
        return false;
    }

    public boolean ehciclico(Digrafo g) {
        boolean[] visitado = new boolean[g.vertices];
        boolean[] recStack = new boolean[g.vertices];

        for (int i = 0; i < g.vertices; i++) {
            if (isCyclicUtil(i, visitado, recStack,g)) {
                return true;
            }
        }
        return false;
    }
}



