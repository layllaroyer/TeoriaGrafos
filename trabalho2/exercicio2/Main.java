package trabalho2.exercicio2;

public class Main {
    public static void main(String[] args) {
        Grafo g = new Grafo();
        Vertice a = g.addVertice("Arlington");
        Vertice s = g.addVertice("San_Antonio");
        Vertice b = g.addVertice("Baltimore");
        Vertice n = g.addVertice("New_York");
        Vertice d = g.addVertice("Dallas");


        Aresta as = g.addAresta(a,s);
        Aresta sb = g.addAresta(s, b);
        Aresta bn = g.addAresta(b, n);
        Aresta nd = g.addAresta(n, d);
        Aresta ba = g.addAresta(b, a);


        //b.imprimir();

        //System.out.println(g);
        g.imprimirArestas();
    }
}

