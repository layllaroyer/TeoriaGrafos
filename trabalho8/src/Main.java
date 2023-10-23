import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int totalPalavras = scanner.nextInt();
        while(totalPalavras != 0) {
            Grafo grafo = new Grafo(totalPalavras);
            String linguaInicial = scanner.next();
            String linguaFinal = scanner.next();
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
            grafo.dijkstra(linguaInicial);
            int i = grafo.existeVertice(linguaFinal);
            if(i>=0) {
                Vertice v = grafo.vertices.get(i);
                if (v.pai != -1) {
                    System.out.println(v.distancia);
                } else
                    System.out.println("impossivel");
            }else
                System.out.println("impossivel");
            totalPalavras = scanner.nextInt();
        }
    }
}