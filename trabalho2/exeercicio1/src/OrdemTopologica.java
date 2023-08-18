package trabalho2.exeercicio1.src;

import trabalho2.exeercicio1.src.Digrafo;

import java.util.Stack;
public class OrdemTopologica {
    boolean []marcado;
    Stack<Integer> posOrdemRev;

    public OrdemTopologica(Digrafo g){
        posOrdemRev = new Stack<Integer>();
        marcado = new boolean[g.vertices];
        for(int v = 0; v<g.vertices;v++){
            if(g.getValores(v) != '!'){
                if(!marcado[v])
                    dfs(g, v);
            }
        }
    }

    public void dfs(Digrafo g, int v){
        marcado[v] = true;
        for(int w:g.adj[v])
            if(!marcado[w])
                dfs(g, w);
        posOrdemRev.push(v);
    }

    public void imprimeOrdem(){
        while(!posOrdemRev.empty()){
            System.out.print((char) (posOrdemRev.pop() + 'A'));
        }
    }
}
