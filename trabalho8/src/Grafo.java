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
        while(!fila.isEmpty()){
            Vertice v = fila.remove();
            int pos = buscaAresta(v.vertice);
            while(pos != -1){
                marked[pos] = true;
                Aresta  aresta = arestas.get(pos);
                String u = aresta.vertice1;
                if(v.vertice.equals(u))
                    u = aresta.vertice2;
                int posVet = buscaVertice(u);
                Vertice vet = vertices.get(posVet);
                if(relaxa(vet, v, aresta)) {
                    alteraDistanciaPai(vet.vertice, v.distancia + aresta.peso, pos);
                }
            }
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
        PriorityQueue<Vertice> fila = new PriorityQueue<>(filaOrigem.size(), Comparator.comparingInt(o->o.distancia));
        while(!filaOrigem.isEmpty()){
            Vertice vet = filaOrigem.remove();
            filaOrigem.add(vet);
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

    public int buscaVertice(String v){
        for(int i = 0;i<vertices.size(); i++){
            if(vertices.get(i).vertice.equals(v)) {
                return i;
            }
        }
        return -1;
    }

    public boolean relaxa(Vertice v, Vertice u, Aresta a){
         if(v.distancia>(u.distancia+a.peso))
             if(v.pai!=-1){
                 String palavra = buscaPalavra(u.vertice, vertices.get(u.pai).vertice);
                 if(palavra.indexOf(0)!=a.palavra.indexOf(0))
                    return true;
             }
        return false;
    }
}
