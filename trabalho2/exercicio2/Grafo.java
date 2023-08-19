package trabalho2.exercicio2;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    List<Vertice> vertices;
    List<Aresta> arestas;
    List<String> armazena = new ArrayList<>();

    public Grafo() {
        vertices = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();
    }

    Vertice addVertice(String nome) {
        Vertice v = new Vertice(nome);
        vertices.add(v);
        return v;
    }

    Aresta addAresta(Vertice origem, Vertice destino) {
        Aresta e = new Aresta(origem, destino);
        origem.addAdj(e);
        arestas.add(e);
        return e;
    }

    public String toString() {
        String r = "";
        for (Vertice u : vertices) {
            r += u.nome + " -> ";
            for (Aresta e : u.adj) {
                Vertice v = e.destino;
                r += v.nome + ", ";
            }
            r += "\n";
        }
        return r;
    }

    public void imprimirArestas(){
        ArrayList<String> arrDest = new ArrayList<>();
        ArrayList<String> arrOri = new ArrayList<>();

        for (Vertice u: vertices) {
            for(Aresta e: u.adj){
                arrOri.add(e.origem.nome);
                arrDest.add(e.destino.nome);
            }
        }
        int i = 0;
        for( i = 0 ; i < arrDest.size();i++)
        {
            if(!(arrOri.contains(arrDest.get(i))))
            {
                armazena.add(arrOri.get(i));
            }
        }
    }
    public void checagem(ArrayList<String> testes) {
        for (int i = 0; i < testes.size(); i++) {
            if (!(armazena.contains(testes.get(i)))) {
                System.out.println(testes.get(i) + " safe");
            } else {
                System.out.println(testes.get(i) + " trapped");
            }
        }
    }

}
