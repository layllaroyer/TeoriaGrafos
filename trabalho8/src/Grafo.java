import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Grafo {
    ArrayList<Aresta> arestas;
    int qtdArestas;
    ArrayList<Vertice> vertices;
    LinkedList<Vertice>[] adj;
    boolean[] marked;
    public Grafo(int a){
        arestas = new ArrayList<>();
        qtdArestas = a;
        vertices = new ArrayList<>();
        marked = new boolean[a];
    }

    public void addVertice( String v){
        if(existeVertice(v)==-1){
            Vertice vet = new Vertice(v);
            vertices.add(vet);
        }
    }

    public void addAresta(String v1, String v2, String palavra){
        Aresta aresta = new Aresta(v1, v2, palavra.length(), palavra);
        arestas.add(aresta);
    }

    public int buscaAresta(String v1){
        for(int i =0; i<arestas.size(); i++){
            Aresta aux = arestas.get(i);
            if((aux.vertice1.equals(v1)) || (aux.vertice2.equals(v1))){
                if(!marked[i])
                    return i;
            }
        }
        return -1;
    }

    public String buscaPalavra(String v1, String v2){
        for(int i =0; i<arestas.size(); i++){
            Aresta aux = arestas.get(i);
            if(((aux.vertice1.equals(v1)) && (aux.vertice2.equals(v2)))||((aux.vertice1.equals(v2)) && (aux.vertice2.equals(v1))))
                return aux.palavra;
        }
        return null;
    }

    public void dijkstra(String inicio){
        alteraDistanciaPai(inicio, 0, -1);
        PriorityQueue<Vertice> fila = filaPrioridadeDist();
        Vertice v = fila.remove();
        while(!fila.isEmpty()){
            int posv = existeVertice(v.vertice);
            int pos = buscaAresta(v.vertice);
            while(pos != -1){
                marked[pos] = true;
                Aresta aresta = arestas.get(pos);
                String u = aresta.vertice1;
                if(v.vertice.equals(u))
                    u = aresta.vertice2;
                int posVet = existeVertice(u);
                Vertice vet = vertices.get(posVet);
                if(relaxa(posVet, posv, aresta)) {
                    alteraDistanciaPai(vet.vertice, v.distancia + aresta.peso, posv);
                }
                pos = buscaAresta(v.vertice);
            }
            fila = atualizaFila(fila);
            v = fila.remove();
        }
    }

    public int existeVertice(String v){
        for(int i = 0;i<vertices.size(); i++){
            if(vertices.get(i).vertice.equals(v))
                return i;
        }
        return -1;
    }

    public PriorityQueue<Vertice> filaPrioridadeDist(){
        PriorityQueue<Vertice> fila = new PriorityQueue<>(vertices.size(), Comparator.comparingInt(o->o.distancia));
        for(int j = 0;j<vertices.size();j++){
            Vertice vet = vertices.get(j);
            fila.add(vet);
        }
        return fila;
    }

    public PriorityQueue<Vertice> atualizaFila(PriorityQueue<Vertice> filaOrigem){
        PriorityQueue<Vertice> fila = new PriorityQueue<>(1, Comparator.comparingInt(o -> o.distancia));
        if(filaOrigem.size()>0) {
            fila = new PriorityQueue<>(filaOrigem.size(), Comparator.comparingInt(o -> o.distancia));
            while (!filaOrigem.isEmpty()) {
                Vertice vet = filaOrigem.remove();
                fila.add(vet);
            }
        }
        return fila;
    }

    public void alteraDistanciaPai(String v, int d, int p){
        for(int i = 0;i<vertices.size(); i++){
            if(vertices.get(i).vertice.equals(v)) {
                vertices.get(i).distancia = d;
                vertices.get(i).pai = p;
            }
        }
    }

    public boolean relaxa(int v, int u, Aresta a){
         if(vertices.get(v).distancia>(vertices.get(u).distancia+a.peso)) {
             if (vertices.get(v).pai != -1) {
                 String palavra = buscaPalavra(vertices.get(u).vertice, vertices.get(vertices.get(u).pai).vertice);
                 if (palavra.indexOf(0) != a.palavra.indexOf(0))
                     return true;
                 else
                     return false;
             }
             else
                 return true;
         }
        return false;
    }
}
