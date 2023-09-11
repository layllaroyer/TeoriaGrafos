import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int qtd_vertice = sc.nextInt();

            if (qtd_vertice == 0) {
                break; // Sair do loop se a quantidade de vértices for zero
            }

            int maiorPesoOriginal = 0; // Inicialize o maior peso original

            Grafo grafo = new Grafo(qtd_vertice);
            int qtd_aresta = sc.nextInt();

            for (int i = 0; i < qtd_aresta; i++) {
                int origem = sc.nextInt();
                int destino = sc.nextInt();
                int peso = sc.nextInt();

                // Atualize o maior peso original
                if (peso > maiorPesoOriginal) {
                    maiorPesoOriginal = peso;
                }

                grafo.adicionarAresta(origem, destino, peso);
            }
            if(qtd_aresta!=0)
            {
                System.out.println(maiorPesoOriginal);
            }
            ArrayList<Aresta> mst = grafo.kruskalMST();
            grafo.imprimir(mst);
        }
    }
}