import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int totalPalavras = scanner.nextInt();
        while(totalPalavras != 0) {
            Grafo grafo = new Grafo(totalPalavras);
            String linguaInicial = scanner.next();
            String linguaFinal = scanner.next();
            System.out.println(linguaInicial + " " + linguaFinal);
            int contador = 0;
            while (contador < totalPalavras) {
                String lingua1 = scanner.next();
                grafo.addVertice(lingua1);
                String lingua2 = scanner.next();
                grafo.addVertice(lingua2);
                String palavra = scanner.next();
                grafo.addAresta(lingua1, lingua2,palavra);
                contador++;
            }
            for(int i=0;i<grafo.arestas.size();i++){
                System.out.println(grafo.arestas.get(i).vertice1+" "+grafo.arestas.get(i).vertice2+" "+grafo.arestas.get(i).palavra+" "+grafo.arestas.get(i).peso);
            }
            for(int i = 0; i<grafo.vertices.size();i++){
                System.out.println(grafo.vertices.get(i).vertice);
            }

            totalPalavras = scanner.nextInt();
        }
    }
}