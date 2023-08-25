public class BuscaArticulacao {

    int dfs_n = 0;
    int[] parentes;
    int[] visitado;
    //visitado=1  não visitado =0
    int[] articulacao;
    int[] dfs_num;
    int[] dfs_low;

    BuscaArticulacao(Grafo lugares){
        parentes = new int [lugares.vertices];
        visitado = new int[lugares.vertices];
        articulacao = new int[lugares.vertices];
        dfs_num = new int[lugares.vertices];
        dfs_low = new int[lugares.vertices];

        for(int i=0; i<lugares.vertices; i++){
            visitado[i] = 0;
            articulacao[i] = 0;
            parentes[i] = 0;
        }
    }

    public void buscaArticulacoes(int u, Grafo lugares){
        dfs_num[u] = dfs_low[u] = dfs_n;
        dfs_n++;

        visitado[u] = 1;
        int filho = 0;
        for(int v : lugares.adj[u]){
            if(visitado[v] == 0){
                filho++;
                parentes[v] = u;
                buscaArticulacoes(v, lugares);

                dfs_low[u] = minimo(dfs_low[u], dfs_low[v]);

                if((parentes[u] == 0)&&(filho>1)){
                    articulacao[u] = 1;
                }
                if((parentes[u] != 0)&&(dfs_low[v]>=dfs_num[u])){
                    articulacao[u]=1;
                }
            }else if(v!=parentes[u]){
                dfs_low[u] = minimo(dfs_low[u], dfs_num[v]);
            }
        }

    }

    public int buscaArticulacaoTodos(Grafo lugares){
        int quantidadeArticulacoes = 0;
        for(int i = 1; i<lugares.vertices;i++){
            if(visitado[i]==0){
                buscaArticulacoes(i, lugares);
            }
        }
        for(int j = 1; j<articulacao.length; j++)
            quantidadeArticulacoes = quantidadeArticulacoes + articulacao[j];

        return quantidadeArticulacoes;
    }

    public int minimo(int num1, int num2){
        if(num1<num2)
            return num1;
        return num2;
    }
}
