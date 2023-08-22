import java.util.Scanner;
import java.util.Stack;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int nvertices = scanner.nextInt();
            int ligacoes = scanner.nextInt();
            if (nvertices == 0 && ligacoes == 0) {
                break;
            }

            Digrafo g = new Digrafo(nvertices);
            for (int i=0; i<ligacoes; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                g.novaAresta(a-1,b-1);
            }

            OrdenacaoTopologica ord = new OrdenacaoTopologica(g);

            Stack<Integer> sequencia;
            sequencia=ord.ordem();


            if(ord.ehciclico(g)){
                System.out.println("IMPOSSIBLE");
            }else {
                while (!sequencia.isEmpty()) {
                    int a=sequencia.pop();
                    System.out.println(a + 1);
                }
            }
        }
    }
}