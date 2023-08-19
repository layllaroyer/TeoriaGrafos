package trabalho2.exercicio2;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

public class Grafo {
    List<Vertice> vertices;
    List<Aresta> arestas;

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
                //System.out.println("Destino " + e.destino.nome);
                arrOri.add(e.origem.nome);
                arrDest.add(e.destino.nome);
            }
        }

        System.out.println("Origem ");
        System.out.println(arrOri);

        System.out.println("Destino");
        System.out.println(arrDest);



        int i = 0;
        for( i = 0 ; i < arrDest.size();i++)
        {
            if(!(arrOri.contains(arrDest.get(i))))
            {
                System.out.println(arrDest.get(i));
            }
        }


    }
}
