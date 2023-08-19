package trabalho2.exercicio2;

import javax.swing.*;
import java.awt.image.AreaAveragingScaleFilter;
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

//    public void imprimeVertice(){
//        for(int i=0; i< vertices.size(); i++){
//            System.out.println(vertices.get(i).nome);
//        }
    //}

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
                armazena.add(arrOri.get(i));
            }
        }
        System.out.println(armazena);
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
