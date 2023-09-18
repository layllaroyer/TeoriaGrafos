
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Grafo {
    int quantVertice;
    ArrayList<Vertice> vertices = new ArrayList<>();
    ArrayList<Aresta> arestas = new ArrayList<>();
    public Grafo(int quantVertice){
        this.quantVertice = quantVertice;
    }

    public void inserirVertice(Vertice v){
        vertices.add( v);
    }

    public void inserirAresta(int v1, int v2, double dist){
        Aresta ares = new Aresta(vertices.get(v1), vertices.get(v2), dist);
        arestas.add(ares);
    }

    public double calculaDist(int ver1, int ver2){
        Vertice v1 = vertices.get(ver1);
        Vertice v2 = vertices.get(ver2);
        if(v1.x == v2.x) {
            return Math.abs(v2.y - v1.y);
        }
        else if(v1.y == v2.y) {
            return Math.abs(v2.x - v1.x);
        }
        else{
            double aux1 = Math.pow(Math.abs(v2.x-v1.x),2);
            double aux2 = Math.pow(Math.abs(v2.y-v1.y),2);
            return Math.sqrt(aux1+aux2);
        }
    }

    public void mostraVertices(){
        for(int i=0;i<vertices.size();i++)
            System.out.println("pos: "+i+" "+vertices.get(i).x+" "+vertices.get(i).y);
    }

    public PriorityQueue<Aresta> filaPrioridadeDist(){
        PriorityQueue<Aresta> fila = new PriorityQueue<>(quantVertice-1, Comparator.comparingDouble(o->o.distancia));
        for(int i =0; i<vertices.size();i++){
            for(int j = 0;j<vertices.size();j++){
                if(i!=j){
                    double aux = calculaDist(i, j);
                    Aresta aresta = new Aresta(vertices.get(i), vertices.get(j), aux);
                    fila.add(aresta);
                }
            }
        }
        return fila;
    }

    public void kruskalMST(){
        PriorityQueue<Aresta> filaPrioridade = filaPrioridadeDist();
        //while(!filaPrioridade.isEmpty()){
          //  System.out.println(filaPrioridade.remove().distancia);
       // }
        Vertice[] pai = new Vertice[quantVertice];
        makeSet(pai);
        int index = 0;
        double custoIni = 0;

        for(int i = 0;i<arestas.size();i++){
            Vertice v1 = arestas.get(i).origem;
            Vertice v2 = arestas.get(i).destino;
            custoIni = custoIni +arestas.get(i).distancia;
            index++;
            union(pai, v1, v2);
        }

        while(index<quantVertice-1){
            Aresta aresta = filaPrioridade.remove();
            Vertice x_set = find(pai, aresta.origem);
            Vertice y_set = find(pai, aresta.destino);

            if(x_set == y_set){
            }else{
                arestas.add(aresta);
                index++;
                union(pai, x_set, y_set);
            }
        }
        System.out.printf("%.2f \n", calcularCusto()-custoIni);
    }

    public void makeSet(Vertice[] pai) {
        for (int i = 0; i < quantVertice; i++) {
            pai[i] = vertices.get(i);
        }
    }

    public Vertice find(Vertice[] pai, Vertice vertice) {
        if (pai[vertices.indexOf(vertice)] != vertice)
            return find(pai, pai[vertices.indexOf(vertice)]);
        ;
        return vertice;
    }

    public void union(Vertice[] parent, Vertice x, Vertice y) {
        Vertice x_set_parent = find(parent,x);
        Vertice y_set_parent = find(parent,y);

        parent[vertices.indexOf(y_set_parent)] = x_set_parent;
    }

    public double calcularCusto(){
        double custo = 0;
        for (int i = 0; i< arestas.size(); i++)
            custo = custo+arestas.get(i).distancia;
        return custo;
    }
}
